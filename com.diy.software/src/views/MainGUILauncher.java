package views;

import com.diy.hardware.DoItYourselfStationAR;

import util.CustomerUI;
import util.AttendantStation;
import util.AttendantUI;

public class MainGUILauncher {
	public static ScanScreenGUI scanScreenGUI;
	public static AttendantGUI attendantGUI;
	public static PayWithCashGUI payWithCashGUI;
	public static PayWithCreditGUI payWithCreditGUI;
	public static PayWithDebitGUI payWithDebitGUI;

	
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
	}

	public static void changeView(int windowNum) {
		switch (windowNum) {
		case 1:
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(false);
			break;
			
		case 2:
			scanScreenGUI.setVisible(false);
			attendantGUI.setVisible(true);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(false);
			
		case 3:
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(true);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(false);
			
		case 4:
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(true);
			payWithDebitGUI.setVisible(false);
			
		case 5:
			scanScreenGUI.setVisible(true);
			attendantGUI.setVisible(false);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(true);
		}
	}

}
