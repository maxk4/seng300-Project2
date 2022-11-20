package util;

/**
 * Class controlling the UI of the attendant station
 * @author Taylor Wong
 *
 */
public class AttendantUI {
	
	private AttendantStation station;
	
	/**
	 * Make a new AttendantUI attached to the touch screen of the provided AttendantStation
	 * @param station AttendantStation who's touch screen the new AttendantUI will be attached to
	 */
	public AttendantUI(AttendantStation station, int maxStations) {
		this.station = station;
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
	}
	
	/**
	 * Remove a customer from the AttendantUI
	 * @param customer CustomerUI to remove
	 */
	public void removeCustomerUI(CustomerUI customer) {
	}
}
