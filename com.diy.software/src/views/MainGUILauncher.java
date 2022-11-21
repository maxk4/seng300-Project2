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
	
	private boolean screenLocked = false;

	
	public static void main(String[] args) {
		DoItYourselfStationAR diyStation = new DoItYourselfStationAR();
		diyStation.plugIn();
		diyStation.turnOn();
		
		AttendantStation attendantStation = new AttendantStation();
		
		
		CustomerUI customer = new CustomerUI(diyStation);
		
		scanScreenGUI = new ScanScreenGUI(customer);
		attendantGUI = new AttendantGUI(new AttendantUI(attendantStation, 1));
		
		payWithCashGUI = new PayWithCashGUI(customer);
		payWithCreditGUI = new PayWithCreditGUI(customer);
		payWithDebitGUI = new PayWithDebitGUI(customer);
		
		//changeView(2);
		startScreenGUI = new StartScreenGUI(customer);

		weightDiscrepancyGUI = new WeightDiscrepancyGUI();
		orderFinishedGUI = new OrderFinishedGUI();
		
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

	public static void changeView(int windowNum) {
		switch (windowNum) {
		
		case -1:
			startScreenGUI.setVisible(true);
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
