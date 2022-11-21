package views;
import com.jimmyselectronics.disenchantment.TouchScreen;

public class MainGUILauncher {
	public static StartScreenGUI startScreenGUI;
	public static ScanScreenGUI scanScreenGUI;
	public static AttendantGUI attendantGUI;
	public static PayWithCashGUI payWithCashGUI;
	public static PayWithCreditGUI payWithCreditGUI;
	public static PayWithDebitGUI payWithDebitGUI;
	public static WeightDiscrepancyGUI weightDiscrepancyGUI;
	public static OrderFinishedGUI orderFinishedGUI;

	
	public static void main(String[] args) {
		startScreenGUI = new StartScreenGUI();
		scanScreenGUI = new ScanScreenGUI();
		attendantGUI = new AttendantGUI();
		payWithCashGUI = new PayWithCashGUI();
		payWithCreditGUI = new PayWithCreditGUI();
		payWithDebitGUI = new PayWithDebitGUI();
		weightDiscrepancyGUI = new WeightDiscrepancyGUI();
		orderFinishedGUI = new OrderFinishedGUI();
		
		changeView(-1);
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
