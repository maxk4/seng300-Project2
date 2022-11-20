import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import com.diy.hardware.DoItYourselfStationAR;
import com.unitedbankingservices.DisabledException;
import com.unitedbankingservices.OutOfCashException;
import com.unitedbankingservices.TooMuchCashException;
import com.unitedbankingservices.banknote.Banknote;
import com.unitedbankingservices.banknote.BanknoteDispenserObserver;
import com.unitedbankingservices.banknote.BanknoteValidator;
import com.unitedbankingservices.banknote.BanknoteValidatorObserver;
import com.unitedbankingservices.coin.CoinValidator;
import com.unitedbankingservices.coin.CoinValidatorObserver;

public class CashPayment implements BanknoteValidatorObserver, CoinValidatorObserver,
	BanknoteDispenserObserver{
	
	private CustomerUI customer;
	private AttendantUI attendant;
	private DoItYourselfStationAR station;
	private boolean needMaintenance;
	private int validBanknoteCount, validCoinCount;
	
	public CashPayment(CustomerUI customer, AttendantUI attendant, DoItYourselfStationAR station) {
		this.customer = customer;
		this.attendant = attendant;
		this.station = station;
		this.station.banknoteValidator.attach(this);
		this.station.coinValidator.attach(this);
		needMaintenance = false;
		validBanknoteCount = 0;
		validCoinCount = 0;
	}
	
	@Override
	public void validBanknoteDetected(BanknoteValidator validator, Currency currency, long value) {
		validBanknoteCount++;
		customer.setBalance(customer.getBalance() - (double)value);
	}
	
	@Override
	public void validCoinDetected(CoinValidator validator, long value) {
		validCoinCount++;
		customer.setBalance(customer.getBalance() - (double)value/100);		
	}
	
	public int getValidBanknoteCount() {
		return validBanknoteCount;
	}
	
	public int getValidCoinCount() {
		return validCoinCount;
	}
	
	/**
	 * balance must be a negative in order to issue change to customers
	 * change to dispense (balance) is broken into two values
	 * - value below 5 is issued by coins
	 * - the rest is by banknotes
	 * for example, to return 6.5 dollars, we would issue a 5 dollar in banknote
	 * and 1.5 in coins. If 5 dollar dispenser is empty, we would not issue coins 
	 * instead
	 * banknotes and coins are dispensed from the largest to the smallest denomination  
	 * @throws TooMuchCashException
	 * @throws OutOfCashException
	 * @throws DisabledException
	 */
	public void returnChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		
		// balance should be a negative number to return change
		if (customer.getBalance() >= 0)
			return;
		
		double changeToDispense = - customer.getBalance();  
		double changeIssued = 0;
		
		// break change to dispense by above 5 and below 5
		// above 5 value would be a multiple of 5 and passed to emitBannknotes
		// the rest is passed to emitCoins
		long banknoteToDispense = (int)(changeToDispense / 5) * 5;
		double coinToDispense = changeToDispense - banknoteToDispense;
		changeIssued = emitBanknotes(banknoteToDispense, changeIssued);
		changeIssued = emitCoins(coinToDispense, changeIssued);
		
		customer.setBalance(customer.getBalance() + changeIssued);
		
		if (needMaintenance) {
//		notify attendant (customer, customer.getBalance()), suspend station
		}
	} 
	
	/*
	 * issue banknote as the change
	 * return changeIssued
	 */
	private double emitBanknotes(long banknoteToDispense, double changeIssued) throws OutOfCashException, DisabledException, TooMuchCashException {

		if (banknoteToDispense == 0)
			return changeIssued;

		// get banknote denomination available 
		int[] banknoteDenominations = station.banknoteDenominations;
		banknoteDenominations = sort(banknoteDenominations);
		
		int index = 0; // index of denomination
		while (banknoteToDispense != 0) {
			
			// not enough banknote to issue
			if (index == banknoteDenominations.length) {
				needMaintenance = true;
				break;
			}
			
			if (banknoteDenominations[index] <= banknoteToDispense) {
				if (station.banknoteDispensers.get(banknoteDenominations[index]).size() > 0) {
					station.banknoteDispensers.get(banknoteDenominations[index]).emit();
					banknoteToDispense -= banknoteDenominations[index];
					changeIssued += banknoteDenominations[index];
				} else {
					index++; // not enough banknotes for this denomination.
				}
			} else {
				index++; // the current denomination is too large
			}
		}
		return changeIssued;		
	}
	
	/*
	 * issue banknote as the change
	 * return changeIssued 
	 */
	private double emitCoins(double coinToDispense, double changeIssued) throws TooMuchCashException, OutOfCashException, DisabledException {

		if (coinToDispense == 0)
			return changeIssued;
		
		// get coin denomination available 
		List<Long> coinDenominations = station.coinDenominations;
		Collections.reverse(coinDenominations);
		
		// turn coin value into cents
		long changeInCents = (long)(coinToDispense * 100);
		long centsIssued = (long)(changeIssued * 100);
		
		int index = 0; // index of denomination
		while (changeInCents != 0) {
			
			// note enough coins to issue
			if (index == coinDenominations.size()) {
				needMaintenance = true;
				break;
			}
			
			double coinValue = (double)coinDenominations.get(index);
			if (coinValue <= changeInCents) {
				if (station.coinDispensers.get(coinDenominations.get(index)).size() > 0) {
					station.coinDispensers.get(coinDenominations.get(index)).emit();
					changeInCents -= coinValue;
					centsIssued += coinValue;
				} else {
					index++; // not enough coins for this denomination.
				}
			} else {
				index++; // the current denomination is too large
			}
		}
		return (double)centsIssued / 100;
	}
	
	/*
	 * change to primitive type array to non-primitive type array 
	 * sort
	 * change array back to primitive type
	 */
	private int[] sort(int[] denominations) {
		List<Integer> temp = new ArrayList<Integer>();
		for (int d : denominations) 
			temp.add(d);
		Collections.reverse(temp);
		for (int i = 0; i < denominations.length; i++) 
			denominations[i] = temp.get(i);		
		return denominations;
	}
}
