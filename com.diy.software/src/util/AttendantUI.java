package util;

import java.util.ArrayList;
import java.util.List;

import views.AttendantGUI;

/**
 * Class controlling the UI of the attendant station
 * @author Taylor Wong
 *
 */
public class AttendantUI {
	
	private AttendantStation station;
	private AttendantGUI gui;
	private List<CustomerUI> stations;
	
	/**
	 * Make a new AttendantUI attached to the touch screen of the provided AttendantStation
	 * @param station AttendantStation who's touch screen the new AttendantUI will be attached to
	 */
	public AttendantUI(AttendantStation station, int maxStations) {
		this.station = station;
		stations = new ArrayList<CustomerUI>(maxStations);
		gui = new AttendantGUI(this);
		gui.setVisible(true);
	}
	
	public void show() {
		gui.setVisible(true);
	}
	
	/**
	 * Notify the attendant that the customer is making a no bagging request
	 * @param customer CustomerUI that is making the request
	 */
	public void notifyNoBaggingRequest(CustomerUI customer) {
		
	}
	
	/**
	 * Notify the attendant that there is a weight discrepancy
	 * @param customer CustomerUI who has a weight discrepancy
	 */
	public void notifyWeightDiscrepancyDetected(CustomerUI customer) {
	}
	
	/**
	 * Notify the attendant that the discrepancy was resolved;
	 * @param customer CustomerUI that had the discrepancy
	 */
	public void notifyWeightDiscrepancyResolved(CustomerUI customer) {
	}
	
	/**
	 * Add a customer to the AttendantUI
	 * @param customer CustomerUI to add
	 */
	public void addCustomerUI(CustomerUI customer) {
		stations.add(customer);
	}
	
	/**
	 * Remove a customer from the AttendantUI
	 * @param customer CustomerUI to remove
	 */
	public void removeCustomerUI(CustomerUI customer) {
		stations.remove(customer);
	}
}
