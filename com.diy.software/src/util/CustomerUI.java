package util;

import java.util.ArrayList;
import java.util.List;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.Product;
import com.diy.hardware.external.ProductDatabases;
import com.jimmyselectronics.disenchantment.TouchScreen;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;

import views.PayWithCashGUI;
import views.PayWithCreditGUI;
import views.PayWithDebitGUI;
import views.ScanScreenGUI;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerUI {

//	JFrame customerFrame;
//	JPanel customerPanel;
//	JTextField message;
//	JLabel messageToShow;
//	JLabel ShowLabel;
//	JButton showMessage;
	
	private long balance;
	
	private ExpectedWeightListener weightListener;
	private List<Product> productList = new ArrayList<Product>();
	private List<DiscrepancyListener> discrepancyListeners = new ArrayList<DiscrepancyListener>();
	private List<NoBaggingRequestListener> nbrListeners = new ArrayList<NoBaggingRequestListener>();
	private DoItYourselfStationAR station;

	private JDialog activeDialog;
	
	private PayWithCashGUI payWithCashGUI;
	private PayWithCreditGUI payWithCreditGUI;
	private PayWithDebitGUI payWithDebitGUI;

	private ScanScreenGUI scanScreenGUI;
	
	
	public CustomerUI(DoItYourselfStationAR station) {
		this.station = station;
		payWithCashGUI = new PayWithCashGUI(this);
		payWithCreditGUI = new PayWithCreditGUI(this);
		payWithDebitGUI = new PayWithDebitGUI(this);
		
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
	}

	/**
	 * Setter method for weightListener
	 * 
	 * @param weightListener ExpectedWeightListener to set
	 */
	public void setWeightListener(ExpectedWeightListener weightListener) {
		this.weightListener = weightListener;
	}

	/**
	 * Notify the weight listener that the expected weight should be updated
	 * 
	 * @param weight double weight to change the expected weight by
	 */
	public void notifyExpectedWeight(double weight) {
		weightListener.updateExpectedWeight(weight);
	}

	/**
	 * Add a product to the UI
	 * 
	 * @param product Product to add to the UI
	 */
	public void addBarcodedProductToList(BarcodedProduct product) {
		// Add product to UI
		productList.add(product);
		
		// update scanScreenGUI
	}
	

	/**
	 * Check if product exists in list
	 * 
	 * @param Ensure product added exists in list
	 */
	public boolean checkProductList(Product product) {
		boolean statement;
		statement = productList.contains(product);
		return statement;
	}
	
	/**
	 * Get the number of products in the product list
	 * @return int the number of products in the product list
	 */
	public int productCount() {
		return productList.size();
	}

	/**
	 * Register a DiscrepancyListener
	 * 
	 * @param listener DiscrepancyListener to register
	 * @throws IllegalStateException    when the listener is already registered
	 * @throws IllegalArgumentException when the listener is null
	 */
	public void registerDiscrepancyListener(DiscrepancyListener listener)
			throws IllegalStateException, IllegalArgumentException {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");
		if (discrepancyListeners.contains(listener))
			throw new IllegalStateException("listener is already registerd");
		discrepancyListeners.add(listener);
	}

	/**
	 * Deregister a DiscrepancyListener
	 * 
	 * @param listener DiscrepancyListener to register
	 * @throws IllegalStateException    when the listener is not registered
	 * @throws IllegalArgumentException when the listener is null
	 */
	public void deregisterDiscrepancyListener(DiscrepancyListener listener)
			throws IllegalStateException, IllegalArgumentException {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");
		if (!discrepancyListeners.remove(listener))
			throw new IllegalStateException("listener is not registerd");
		discrepancyListeners.remove(listener);
	}

	/**
	 * Register a NoBaggingRequestListener
	 * 
	 * @param listener NoBaggingRequestListener to register
	 * @throws IllegalStateException when the listener is already registered
	 * @throws IllegalArgumentException when the listener is null
	 */
	public void registerNoBaggingRequestListener(NoBaggingRequestListener listener)
			throws IllegalStateException, IllegalArgumentException {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");
		if (nbrListeners.contains(listener))
			throw new IllegalStateException("listener is already registerd");
		nbrListeners.add(listener);
	}

	/**
	 * Deregister a DiscrepancyListener
	 * 
	 * @param listener DiscrepancyListener to register
	 * @throws IllegalStateException    when the listener is not registered
	 * @throws IllegalArgumentException when the listener is null
	 */
	public void deregisterNoBaggingRequestListener(NoBaggingRequestListener listener)
			throws IllegalStateException, IllegalArgumentException {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");
		if (!nbrListeners.remove(listener))
			throw new IllegalStateException("listener is not registerd");
		nbrListeners.remove(listener);
	}
	
	/**
	 * Check if No Bagging Request Listener is added to array
	 * 
	 * @param Check array contents to ensure discrepancy listener is present
	 */
	public boolean checkNoBaggingRequestListener(NoBaggingRequestListener listener) {
		boolean contains;
		contains = nbrListeners.contains(listener);
		return contains;
	}

	/**
	 * Check if Discrepancy Listener is added to array
	 * 
	 * @param Check array contents to ensure discrepancy listener is present
	 */
	public boolean checkDiscrepancyListener(DiscrepancyListener listener) {
		boolean array_contains;
		array_contains = discrepancyListeners.contains(listener);
		return array_contains;
	}

	/**
	 * Alert the UI that there is a weight discrepancy
	 */
	public void alertWeightDiscrepancy() {
		
		// Show weight discrepancy gui
		station.scanner.disable();
		for (DiscrepancyListener listener : discrepancyListeners)
			listener.notifyWeightDiscrepancyDetected(this);
	}
	
	/**
	 * Resolve a weight discrepancy *only to be called by the AttendantStation or
	 * the attached expected weight listener
	 */
	public void resolveWeightDiscrepancy() {
		if (activeDialog != null) activeDialog.dispose();
		station.scanner.enable();
		for (DiscrepancyListener listener : discrepancyListeners)
			listener.notifyWeightDiscrepancyResolved(this);
	}

	/**
	 * Notify the customer that their no bagging request was approved Note: only to
	 * be called by attendant & related classes IMPORTANT!
	 */
	public void notifyNoBaggingRequestApproved() {
		approveWeightDiscrepancy();
		if (activeDialog != null) activeDialog.dispose();
	}

	/**
	 * Notify the customer that their no bagging request was denied Note: only to
	 * be called by attendant & related classes IMPORTANT!
	 */
	public void notifyNoBaggingRequestDenied() {
		activeDialog.dispose();
		if (activeDialog != null) activeDialog.dispose();
		alertWeightDiscrepancy();
	}

	/**
	 * Force resolve a weight discrepancy Note: only to be called by attendant &
	 * related classes IMPORTANT!
	 */
	public void approveWeightDiscrepancy() {
		weightListener.approveWeightDiscrepancy();
	}
	
	/**
	 * Force set the balance for notify payment in case of system error
	 * @param Set the balance used for notify payment method
	 */
	public void setBalance(long tbalance) {
		this.balance = tbalance;
	}
	
	/**
	 * Notify the UI of a successful payment
	 * @param amount long the amount paid successfully
	 */
	public void notifyPayment(long amount) {
		balance -= amount;
		
		// Update scanScreenGUI
		// close payUI
	}
	
	
	public void completePayment() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
	}
	
	public void payWithCash() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(true);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
	}
	
	public void payWithCredit() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(true);
		payWithDebitGUI.setVisible(false);
	}
	
	public void payWithDedit() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(true);
	}
	
	/**
	 * Get the current balance
	 * @param Get the current balance
	 */
	public long getBalance() {
		return balance;
	}
	
	/**
	 * End the current session
	 */
	public void endSession() {
		// Show startScreenGUI
	}
}
