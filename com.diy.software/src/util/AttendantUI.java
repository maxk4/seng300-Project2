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
	
	/**
	 * Notify the attendant that the customer is making a no bagging request
	 * @param customer CustomerUI that is making the request
	 */
	public void notifyNoBaggingRequest(CustomerUI customer) {}
	
	/**
	 * Notify the attendant that there is a weight discrepancy
	 * @param customer CustomerUI who has a weight discrepancy
	 */
	public void notifyWeightDiscrepancyDetected(CustomerUI customer) {
		int index = idOf(customer);
		System.out.println(String.format("Station %d: Weight Discrepancy Detected", index + 1));
	}
	
	/**
	 * Notify the attendant that the discrepancy was resolved;
	 * @param customer CustomerUI that had the discrepancy
	 */
	public void notifyWeightDiscrepancyResolved(CustomerUI customer) {
		int index = idOf(customer);
		System.out.println(String.format("Station %d: Weight Discrepancy Resolved", index + 1));
	}

	public void notifyLowInkDetected(CustomerUI customer) {
		int index = idOf(customer);
		System.out.println(String.format("Station %d: Low Ink", index + 1));
	}
	
	public void notifyLowInkResolved(CustomerUI customer) {
		int index = idOf(customer);
		System.out.println(String.format("Station %d: Ink Refilled", index + 1));
	}
	
	public void notifyLowPaperDetected(CustomerUI customer) {
		int index = idOf(customer);
		System.out.println(String.format("Station %d: Low Paper", index + 1));
	}
	
	public void notifyLowPaperResolved(CustomerUI customer) {
		int index = idOf(customer);
		System.out.println(String.format("Station %d: Paper Refilled", index + 1));
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
	
	private int idOf(CustomerUI customer) {
		return stations.indexOf(customer);
	}

	public void notifyOutOfChange(CustomerUI customer) {
		int index = idOf(customer);
		System.out.println(String.format("Station %d: Out of Change", index + 1));
	}
	
	public void notifyAddBag(CustomerUI customer) {
		// Prompt attendant
	}
 }
