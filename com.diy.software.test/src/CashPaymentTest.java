import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.diy.hardware.DoItYourselfStationAR;
import com.unitedbankingservices.DisabledException;
import com.unitedbankingservices.OutOfCashException;
import com.unitedbankingservices.Sink;
import com.unitedbankingservices.TooMuchCashException;
import com.unitedbankingservices.banknote.Banknote;
import com.unitedbankingservices.banknote.BanknoteDispenserObserver;
import com.unitedbankingservices.banknote.BanknoteValidatorObserver;
import com.unitedbankingservices.coin.Coin;

import ca.powerutility.PowerGrid;

public class CashPaymentTest {
	
	private CustomerUI customer;
	private AttendantUI attendant;
	private DoItYourselfStationAR checkoutStation;
	private AttendantStation attendantStation;
	private CashPayment cashPayment;
	
	@Before
	public void setup() throws TooMuchCashException {
		
		// configure denominations
		int[] banknoteDenominations = {50,20,10,5};
		long[] coinDenominations = {200, 100, 25, 10, 5};
		DoItYourselfStationAR.configureBanknoteDenominations(banknoteDenominations);
		DoItYourselfStationAR.configureCoinDenominations(coinDenominations);
		
		// setup checkout station
		checkoutStation = new DoItYourselfStationAR();
		PowerGrid.engageUninterruptiblePowerSource();
		PowerGrid.instance().forcePowerRestore();
		checkoutStation.plugIn();
		checkoutStation.turnOn();
		
		// setup banknote and coin dispensers
		for (int denomination: banknoteDenominations) {
			checkoutStation.banknoteDispensers.get(denomination).load(new Banknote(Currency.getInstance(Locale.CANADA), denomination));
			checkoutStation.banknoteDispensers.get(denomination).sink = new Sink<Banknote>() {
				@Override
				public void receive(Banknote cash) throws TooMuchCashException, DisabledException {
				}
				@Override
				public boolean hasSpace() {
					return true;
				}
			};
		}
		
		for (long denomination: coinDenominations) {
			checkoutStation.coinDispensers.get(denomination).load(new Coin(Currency.getInstance(Locale.CANADA), denomination));
		}
		
		// initialize software
		customer = new CustomerUI(checkoutStation);
		attendant = new AttendantUI(attendantStation, 1);
		cashPayment = new CashPayment(customer,attendant, checkoutStation);
	}
	
	@After
	public void teardown() {
		PowerGrid.reconnectToMains();
	}
	
	private void banknoteSlotToValidator() {
		checkoutStation.banknoteInput.sink = new Sink<Banknote>() {
			@Override
			public void receive(Banknote cash) throws TooMuchCashException, DisabledException {
				checkoutStation.banknoteValidator.receive(cash);
			}
			@Override
			public boolean hasSpace() {
				return true;
			}
			
		};		
	}
	
	private void coinSlotToValidator() {
		checkoutStation.coinSlot.sink = new Sink<Coin>() {
			
			@Override
			public void receive(Coin cash) throws TooMuchCashException, DisabledException {
				checkoutStation.coinValidator.receive(cash);			
			}

			@Override
			public boolean hasSpace() {
				return true;				
			}
			
		};		
	}
	
	@Test
	public void testBanknotePayment() throws DisabledException, TooMuchCashException {
		
		banknoteSlotToValidator();
		
		Banknote fiveDollar = new Banknote(Currency.getInstance(Locale.CANADA), 5);
		
		customer.setBalance(1);
		
		while(true) {
			checkoutStation.banknoteInput.receive(fiveDollar);
			if (cashPayment.getValidBanknoteCount() == 1) {
				break;
			}
			checkoutStation.banknoteInput.removeDanglingBanknote();
		}
		assertEquals((double)-4, customer.getBalance(), (double)0.01);
		
		while(true) {
			checkoutStation.banknoteInput.receive(fiveDollar);
			if (cashPayment.getValidBanknoteCount() == 2)
				break;
			checkoutStation.banknoteInput.removeDanglingBanknote();
		}
		assertEquals((double)-9, customer.getBalance(), (double)0.01);
		
	}
	
	@Test
	public void testCoinPayment() throws DisabledException, TooMuchCashException {
		
		coinSlotToValidator();
		
		Coin quarter = new Coin(Currency.getInstance(Locale.CANADA), 25);
		
		customer.setBalance(1);
		
		while(true) {
			checkoutStation.coinSlot.receive(quarter);
			if (cashPayment.getValidCoinCount() == 1)
				break;
		}
		assertEquals(0.75, customer.getBalance(), 0.01);
		
		while(true) {
			checkoutStation.coinSlot.receive(quarter);
			if (cashPayment.getValidCoinCount() == 2)
				break;
		}
		assertEquals(0.5, customer.getBalance(), 0.01);
		
	}
	
	@Test
	public void testBanknoteAndCoinPayment() throws DisabledException, TooMuchCashException {
		
		banknoteSlotToValidator();
		coinSlotToValidator();
		
		Banknote fiveDollar = new Banknote(Currency.getInstance(Locale.CANADA), 5);		
		Coin quarter = new Coin(Currency.getInstance(Locale.CANADA), 25);
		
		customer.setBalance(1);
		
		while(true) {
			checkoutStation.banknoteInput.receive(fiveDollar);
			if (cashPayment.getValidBanknoteCount() == 1)
				break;
			checkoutStation.banknoteInput.removeDanglingBanknote();
		}
		assertEquals(-4, customer.getBalance(), 0.01);
		
		while(true) {
			checkoutStation.coinSlot.receive(quarter);
			if (cashPayment.getValidCoinCount() == 1)
				break;
		}
		assertEquals(-4.25, customer.getBalance(), 0.01);
	}
	
	@Test
	public void testReturnBanknoteChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-5);
		cashPayment.returnChange();
		assertEquals(0, customer.getBalance(), 0.01);
	}
	
	@Test
	public void testReturnCoinChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-0.25);
		cashPayment.returnChange();
		assertEquals(0, customer.getBalance(), 0.01);
	}

	@Test
	public void testBanknoteAndCoinChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-6);
		cashPayment.returnChange();
		assertEquals(0, customer.getBalance(), 0.01);
	}
	
	@Test
	public void testInsufficientBanknoteChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-40);
		cashPayment.returnChange();
		assertEquals(-5, customer.getBalance(), 0.01);
	}
	
	@Test
	public void testInsufficientCoinChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-0.2);
		cashPayment.returnChange();
		assertEquals(-0.05, customer.getBalance(), 0.01);
	}
	
	@Test
	public void testInsufficientBanknoteAndCoinChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-40.2);
		cashPayment.returnChange();
		assertEquals(-5.05, customer.getBalance(), 0.01);
	}
}
