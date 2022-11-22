package views;


import com.diy.hardware.DoItYourselfStationAR;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.disenchantment.TouchScreenListener;

import util.CustomerUI;
import util.AttendantStation;
import util.AttendantUI;

public class MainGUILauncher extends AbstractDevice <TouchScreenListener>  {
	public static StartScreenGUI startScreenGUI;
	public static ScanScreenGUI scanScreenGUI;
	public static AttendantGUI attendantGUI;
	public static PayWithCashGUI payWithCashGUI;
	public static PayWithCreditGUI payWithCreditGUI;
	public static PayWithDebitGUI payWithDebitGUI;
	public static WeightDiscrepancyGUI weightDiscrepancyGUI;
	public static OrderFinishedGUI orderFinishedGUI;
	public static KeypadGUI keypadGUI;
	public static PurchaseBagsGUI purchaseBagsGUI;
	
	public static int START_SCREEN = 			1;
	public static int SCAN_SCREEN = 			2;
	public static int ATTENDANT_SCREEN = 		3;
	public static int PAY_CREDIT_SCREEN = 		4;
	public static int PAY_DEBIT_SCREEN = 		5;
	public static int PAY_CASH_SCREEN = 		6;
	public static int WEIGHT_DISCREP_SCREEN = 	7;
	public static int KEYPAD_SCREEN = 			8;
	public static int ORDER_FINISHED_SCREEN = 	9;
	public static int PURCHASE_BAGS_SCREEN = 	10;
	
	private boolean screenLocked = false;

	
	public static void main(String[] args) {
		DoItYourselfStationAR diyStation = new DoItYourselfStationAR();
		diyStation.plugIn();
		diyStation.turnOn();
		
		AttendantStation attendantStation = new AttendantStation();
		
		
		CustomerUI customer = new CustomerUI(diyStation);
		
		scanScreenGUI = new ScanScreenGUI(customer);
		keypadGUI = new KeypadGUI();
		attendantGUI = new AttendantGUI(new AttendantUI(attendantStation, 1));
		
		payWithCashGUI = new PayWithCashGUI(customer);
		payWithCreditGUI = new PayWithCreditGUI(customer);
		payWithDebitGUI = new PayWithDebitGUI(customer);
		
		//changeView(2);
		startScreenGUI = new StartScreenGUI(customer);

		weightDiscrepancyGUI = new WeightDiscrepancyGUI();
		orderFinishedGUI = new OrderFinishedGUI();
		purchaseBagsGUI = new PurchaseBagsGUI();
		
		//changeView(-1);
		// Launch Startup Screen
		startScreenGUI.setVisible(true);
	}
	

	public void lockScreen() {
		screenLocked = true;
	}
	public void unlockScreen() {
		screenLocked = false;
	}

	/**
	 * Set the visibility of the various screens
	 * Use the static global fields for this method to avoid errors when values change
	 * 
	 * example:
	 * 		setScreenVisiblility(MainGUILauncher.KEYPAD_SCREEN, true);
	 * 
	 * @param viewIntegerValue
	 * @param isVisible
	 */
	public void setScreenVisiblility(int viewIntegerValue, boolean isVisible) {
		switch (viewIntegerValue) {
		
		case 1:
			startScreenGUI.setVisible(isVisible);
			break;
		case 2:
			scanScreenGUI.setVisible(isVisible);
			break;
		case 3:
			attendantGUI.setVisible(isVisible);
			break;
		case 4:
			payWithCashGUI.setVisible(isVisible);
			break;
		case 5:
			payWithCreditGUI.setVisible(isVisible);
			break;
		case 6:
			payWithDebitGUI.setVisible(isVisible);
			break;
		case 7:
			weightDiscrepancyGUI.setVisible(isVisible);
			break;
		case 8:
			keypadGUI.setVisible(isVisible);
			break;
		case 9:
			orderFinishedGUI.setVisible(isVisible);
			break;
		case 10:
			purchaseBagsGUI.setVisible(isVisible);
			break;
		}
	}
	/**
	 *
	 *	!!!!!!!!!!!!!!!! DEPRECATING THIS METHOD! DO NOT  USE !!!!!!!!!!!!!!
	 */
	public static void changeView(int windowNum) {
		switch (windowNum) {
		
		case -1:
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(true);
			payWithCashGUI.setVisible(true);
			payWithCreditGUI.setVisible(true);
			payWithDebitGUI.setVisible(true);
			weightDiscrepancyGUI.setVisible(true);
			orderFinishedGUI.setVisible(true);
			break;
		case 1:
			startScreenGUI.setVisible(true);
			scanScreenGUI.setVisible(false);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(false);
			weightDiscrepancyGUI.setVisible(false);
			orderFinishedGUI.setVisible(false);
			break;
			
		case 2:
			startScreenGUI.setVisible(false);
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(false);
			weightDiscrepancyGUI.setVisible(false);
			orderFinishedGUI.setVisible(false);
			
		case 3:
			startScreenGUI.setVisible(false);
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(true);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(false);
			weightDiscrepancyGUI.setVisible(false);
			orderFinishedGUI.setVisible(false);
			
		case 4:
			startScreenGUI.setVisible(false);
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(true);
			payWithDebitGUI.setVisible(false);
			weightDiscrepancyGUI.setVisible(false);
			orderFinishedGUI.setVisible(false);
			
		case 5:
			startScreenGUI.setVisible(false);
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(true);
			weightDiscrepancyGUI.setVisible(false);
			orderFinishedGUI.setVisible(false);
		}
	}

}
