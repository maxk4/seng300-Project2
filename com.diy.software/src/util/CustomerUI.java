package util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.Product;
import com.jimmyselectronics.EmptyException;
import com.jimmyselectronics.OverloadException;
import com.unitedbankingservices.DisabledException;

import views.OrderFinishedGUI;
import views.PayWithCashGUI;
import views.PayWithCreditGUI;
import views.PayWithDebitGUI;
import views.PurchaseBagsGUI;
import views.ScanScreenGUI;
import views.StartScreenGUI;
import views.WeightDiscrepancyGUI;


public class CustomerUI {
	
	private long balance = 0;
	private boolean inProgress = false;
	private long total = 0;
	
	private ExpectedWeightListener weightListener;
	private ProductList productList;
	
	private List<DiscrepancyListener> discrepancyListeners = new ArrayList<DiscrepancyListener>();
	private List<NoBaggingRequestListener> nbrListeners = new ArrayList<NoBaggingRequestListener>();
	private DoItYourselfStationAR station;
	
	private CashPayment cashPayment;
	
	private PayWithCashGUI payWithCashGUI;
	private PayWithCreditGUI payWithCreditGUI;
	private PayWithDebitGUI payWithDebitGUI;

	private ScanScreenGUI scanScreenGUI;
	private StartScreenGUI startScreenGUI;
	private PurchaseBagsGUI purchaseBagsGUI;
	
	private WeightDiscrepancyGUI weightDiscrepancyGUI;
	private OrderFinishedGUI orderFinishedGUI;
	
	public CustomerUI(DoItYourselfStationAR station) {
		this.station = station;
		
		//PayWithCardListener cardListener = new PayWithCardListener(this);
		//station.cardReader.register(cardListener);

		cashPayment = new CashPayment(this, null, station);
		beginSession();
	}
	
	/**
	 * Gets the cashPayment field
	 * @return CashPayment cashPayment field of this customer ui
	 */
	public CashPayment getCashPaymentController() {
		return cashPayment;
	}
	
	/**
	 * Get the main frame of the customerGUI
	 * @return the frame
	 */
	public JFrame getFrame() {
		return scanScreenGUI;
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
		productList.add(product, product.getDescription(), product.getPrice());
		balance += product.getPrice();
		scanScreenGUI.update(balance, productList);
	}
	

	/**
	 * Check if product exists in list
	 * 
	 * @param Ensure product added exists in list
	 */
	public boolean checkProductList(Product product) {
		boolean statement;
		statement = productList.containsProduct(product);
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
		if (inProgress) {
			// Show weight discrepancy gui
			station.scanner.disable();
			for (DiscrepancyListener listener : discrepancyListeners)
				listener.notifyWeightDiscrepancyDetected(this);
			
			weightDiscrepancyGUI.setVisible(true);
		}
	}
	
	/**
	 * Resolve a weight discrepancy *only to be called by the AttendantStation or
	 * the attached expected weight listener
	 */
	public void resolveWeightDiscrepancy() {
		if (inProgress) {
			station.scanner.enable();
			for (DiscrepancyListener listener : discrepancyListeners)
				listener.notifyWeightDiscrepancyResolved(this);
			weightDiscrepancyGUI.setVisible(false);
		} else {
			startScreenGUI.dispose();
			scanScreenGUI.dispose();
			payWithCashGUI.dispose();
			payWithCreditGUI.dispose();
			payWithDebitGUI.dispose();
			weightDiscrepancyGUI.dispose();
			orderFinishedGUI.dispose();
			
			beginSession();
		}
	}

	/**
	 * Notify the customer that their no bagging request was approved Note: only to
	 * be called by attendant & related classes IMPORTANT!
	 */
	public void notifyNoBaggingRequestApproved() {
		approveWeightDiscrepancy();
	}

	/**
	 * Notify the customer that their no bagging request was denied Note: only to
	 * be called by attendant & related classes IMPORTANT!
	 */
	public void notifyNoBaggingRequestDenied() {
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
		scanScreenGUI.update(balance, productList);

		startScreenGUI.setVisible(false);
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
	}
	
	/**
	 * Enable/Disable the station associated with this customerUI's scanner
	 * @param enabled boolean true if the scanner should be enabled false otherwise
	 */
	public void setScanningEnabled(boolean enabled) {
		if (enabled) station.scanner.enable();

		startScreenGUI.setVisible(false);
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		purchaseBagsGUI.setVisible(false);
		inProgress = true;
	}
	
	/**
	 * Notify customerui that payment has completed
	 */
	public void completePayment() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		station.cardReader.disable();
		station.coinSlot.disable();
		station.banknoteInput.disable();
		station.banknoteOutput.disable();
		
		scanScreenGUI.update(balance, productList);
	}
	
	/**
	 * Notify the customerui that the customer has requested to pay with cash
	 * 
	 */
	public void payWithCash() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(true);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		
		station.coinSlot.enable();
		station.banknoteInput.enable();
		station.banknoteOutput.enable();
		station.banknoteValidator.activate();
	}

	/**
	 * Notify the customerui that the customer has requested to pay with credit
	 * 
	 */
	public void payWithCredit() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(true);
		payWithDebitGUI.setVisible(false);
		
		station.cardReader.enable();
	}

	/**
	 * Notify the customerui that the customer has requested to pay with debit
	 * 
	 */
	public void payWithDebit() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(true);
		
		station.cardReader.enable();
	}
	
	/**
	 * Get the current balance
	 * @return Get the current balance
	 */
	public long getBalance() {
		return balance;
	}
	
	/**
	 * End the current session
	 * @throws IllegalStateException when balance > 0
	 */
	public void endSession() throws IllegalStateException {
		if (balance > 0) throw new IllegalStateException("Customer balance is greater than 0");

		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		// Show end screen
		orderFinishedGUI.setVisible(true);
		
		printReceipt();
		
		try {
			cashPayment.returnChange();
		} catch (DisabledException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// Alert attendant
		}
		
		inProgress = false;
		
		weightListener.setExpectedWeight(0);
		
	}
	
	/**
	 * Print Receipt
	 */
	public void printReceipt() {
		total = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("Transaction Complete\n");
		sb.append("--------------------\n");
		productList.forEach((prod, desc, price) -> {
			sb.append(String.format("%s\t\t$%.2f\n", desc, price / 100d));
			total += price;
		});
		sb.append("--------------------\n");
		sb.append(String.format("\t\tTotal:\t$%.2f", total / 100d));
		String receipt = sb.toString();
		for (char c : receipt.toCharArray()) {
			try {
				station.printer.print(c);
			} catch (EmptyException e) {
				// Notify attendant
			} catch (OverloadException e) {
				e.printStackTrace();
			}
		}
		station.printer.cutPaper();
	}
	
	/**
	 * Begin a session
	 */
	public void beginSession() {

		startScreenGUI = new StartScreenGUI(this);
		scanScreenGUI = new ScanScreenGUI(this);
		payWithCashGUI = new PayWithCashGUI(this);
		payWithCreditGUI = new PayWithCreditGUI(this);
		payWithDebitGUI = new PayWithDebitGUI(this);
		purchaseBagsGUI = new PurchaseBagsGUI(this);
		weightDiscrepancyGUI = new WeightDiscrepancyGUI();
		orderFinishedGUI = new OrderFinishedGUI();
		
		
		startScreenGUI.setVisible(true);
		scanScreenGUI.setVisible(false);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		weightDiscrepancyGUI.setVisible(false);
		orderFinishedGUI.setVisible(false);
		purchaseBagsGUI.setVisible(false);
		
		balance = 0;
		productList = new ProductList();
		scanScreenGUI.update(0, productList);
		
		station.coinSlot.disable();
		station.banknoteInput.disable();
		station.banknoteOutput.disable();
		station.banknoteValidator.disactivate();
		station.cardReader.disable();
	}

	public void purchageBags() {
		startScreenGUI.setVisible(false);
		scanScreenGUI.setVisible(false);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		weightDiscrepancyGUI.setVisible(false);
		orderFinishedGUI.setVisible(false);
		purchaseBagsGUI.setVisible(true);
	}
}
