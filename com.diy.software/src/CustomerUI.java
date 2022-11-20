import java.util.ArrayList;
import java.util.List;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.Product;
import com.diy.hardware.external.ProductDatabases;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.disenchantment.TouchScreen;
import com.jimmyselectronics.disenchantment.TouchScreenListener;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;

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
	
	private JFrame frame;
	private JPanel container, productView, functionPanel, addPanel, payPanel;
	private JButton scanItemButton, payWithCashButton, payWithCardButton, payWithCryptoButton;
	private JLabel totalLabel;
	
	private long balance;
	
	private ExpectedWeightListener weightListener;
	private List<Product> productList = new ArrayList<Product>();
	private List<DiscrepancyListener> discrepancyListeners = new ArrayList<DiscrepancyListener>();
	private List<NoBaggingRequestListener> nbrListeners = new ArrayList<NoBaggingRequestListener>();
	private DoItYourselfStationAR station;
	
	private JDialog activeDialog;

	private TouchScreen touchScreen;
	private TouchScreenListener screenlistener;
	
	public CustomerUI(DoItYourselfStationAR station) {
		this.station = station;
		station.plugIn();
		station.turnOn();
		
		touchScreen = new TouchScreen();
		screenlistener = new TouchScreenListener(touchScreen);
		touchScreen.plugIn();
		touchScreen.turnOn();
		touchScreen.register(screenlistener);
//		customerFrame = station.touchScreen.getFrame();
//		customerPanel = new JPanel();
//		customerPanel.setLayout(new GridLayout(1, 2));
		
		frame = touchScreen.getFrame();
		
		container = new JPanel();
		container.setLayout(new GridLayout(1, 2));
		container.setBackground(Color.DARK_GRAY);
		
		station.scanner.enable();
		
		addWidgets();

		frame.getContentPane().add(container);
		frame.pack();
		
//
//		customerFrame.getContentPane().add(customerPanel, BorderLayout.CENTER);
//		customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		customerFrame.pack();
//		customerFrame.setVisible(true);
	}

	private void addWidgets() {
//		message = new JTextField();
//		messageToShow = new JLabel("Enter Message", SwingConstants.CENTER);
//		ShowLabel = new JLabel("Message", SwingConstants.CENTER);
//		showMessage = new JButton("!SHOW");
//		showMessage.addActionListener(e -> {
//			String Msg = String.valueOf(message.getText());
//			ShowLabel.setText(Msg);
//		});
//		customerPanel.add(message);
//		customerPanel.add(messageToShow);
//		customerPanel.add(showMessage);
//		customerPanel.add(ShowLabel);
		productView = new JPanel();
		productView.setLayout(new BoxLayout(this.productView, BoxLayout.PAGE_AXIS));
		productView.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 16));
		//productView.setBackground(Color.WHITE);
		
		functionPanel = new JPanel();
		functionPanel.setLayout(new GridLayout(2, 1));
		functionPanel.setBackground(Color.DARK_GRAY);
		
		addPanel = new JPanel();
		addPanel.setLayout(new GridLayout(1, 1));
		addPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		addPanel.setBackground(Color.DARK_GRAY);
		
		payPanel = new JPanel();
		payPanel.setLayout(new GridLayout(4, 1, 0, 8));
		payPanel.setBackground(Color.DARK_GRAY);
		
		scanItemButton = new JButton("Start Scanning");
		scanItemButton.addActionListener(e -> { 
			station.scanner.enable();
			scanItemButton.setEnabled(false);
		});
		scanItemButton.setSize(200, 200);
		
		addPanel.add(scanItemButton);
		
		totalLabel = new JLabel("Total: $" + balance);
		totalLabel.setForeground(Color.WHITE);
		totalLabel.setFont(new Font("arial", Font.PLAIN, 24));
		
		payWithCashButton = new JButton("Pay with Cash");
		payWithCashButton.setSize(200, 50);
		
		payWithCardButton = new JButton("Pay with Card");
		payWithCardButton.setSize(200, 50);
		payWithCardButton.addActionListener(e -> {
			station.cardReader.enable();
			JDialog insertCardDialog = genInsertCardDialog();
			insertCardDialog.setVisible(true);
			activeDialog = insertCardDialog;
		});
		
		payWithCryptoButton = new JButton("Pay with Crypto-Currency");
		payWithCryptoButton.setSize(200, 50);
		
		payPanel.add(totalLabel);
		payPanel.add(payWithCashButton);
		payPanel.add(payWithCardButton);
		payPanel.add(payWithCryptoButton);
		payPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		functionPanel.add(addPanel);
		functionPanel.add(payPanel);
		
		container.add(productView);
		container.add(functionPanel);
	}
	
	private JDialog genInsertCardDialog() {
		JDialog dialog = new JDialog(frame);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		
		JLabel label = new JLabel("Please insert your card into the card reader");
		label.setFont(new Font("arial", Font.PLAIN, 24));
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(e -> {
			dialog.dispose();
			activeDialog = null;
		});
		cancel.setFont(new Font("arial", Font.PLAIN, 24));
		
		panel.add(label);
		panel.add(cancel);
		panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		dialog.getContentPane().add(panel);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setUndecorated(true);
		dialog.pack();
		
		return dialog;
	}

	/**
	 * Get the JFrame the UI is stored in
	 * @return JFrame the jframe
	 */
	public JFrame getFrame() {
		return frame;
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
		
		JLabel prodLabel = genProductPanel(product.getDescription(), product.getPrice(), product.isPerUnit());
		productView.add(prodLabel);
		productView.revalidate();
		
		balance += product.getPrice();
		totalLabel.setText(String.format("Total: $%d", balance));
	}
	
	private JLabel genProductPanel(String desc, long price, boolean perUnit) {
		JLabel label = new JLabel(String.format("%s               $%d%s", desc, price, perUnit ? "" : "/kg"));
		
		label.setSize(200, 12);
		label.setVerticalAlignment(Label.CENTER);
		label.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		return label;
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
		
		JDialog dialog = genWeightAlertDialog();
		activeDialog = dialog;
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
		
		station.scanner.disable();
		for (DiscrepancyListener listener : discrepancyListeners)
			listener.notifyWeightDiscrepancyDetected(this);
	}
	
	private JDialog genWeightAlertDialog() {
		JDialog dialog = new JDialog(frame, Dialog.ModalityType.MODELESS);
		
		JPanel box = new JPanel();
		box.setLayout(new GridLayout(3,1));
		
		JLabel msg = new JLabel("Please place the item in the bagging area");
		msg.setFont(new Font("arial", Font.PLAIN, 36));
		msg.setSize(200, 50);
		msg.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel or = new JLabel("or");
		or.setSize(200, 50);
		or.setHorizontalAlignment(JLabel.CENTER);
		
		JButton makeNoBaggingRequestButton = new JButton("Make \"No Bagging Request\"");
		makeNoBaggingRequestButton.setFont(new Font("arial", Font.PLAIN, 36));
		makeNoBaggingRequestButton.setSize(200, 50);
		makeNoBaggingRequestButton.addActionListener(e ->  {
			dialog.dispose();
			JDialog waitDialog = genWaitDialog();
			activeDialog = waitDialog;
			waitDialog.setVisible(true);
			waitDialog.setLocationRelativeTo(null);
			for (NoBaggingRequestListener listener : nbrListeners) 
				listener.notifyNoBaggingRequest(this);
		});
		
		box.add(msg);
		box.add(or);
		box.add(makeNoBaggingRequestButton);
		box.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		dialog.getContentPane().add(box);

		dialog.setUndecorated(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.pack();
		return dialog;
	}
	
	private JDialog genWaitDialog() {
		JDialog dialog = new JDialog(frame, Dialog.ModalityType.MODELESS);

		JLabel msg = new JLabel("<html><center>No Bagging Request has been made<br>Please Wait ...</center></html>");
		msg.setFont(new Font("arial", Font.PLAIN, 36));
		msg.setSize(200, 50);
		msg.setHorizontalAlignment(JLabel.CENTER);
		msg.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		dialog.getContentPane().add(msg);

		dialog.setUndecorated(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.pack();
		
		return dialog;
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
		

		totalLabel.setText(String.format("Total: $%d", balance));
		
		if (balance <= 0) {
			// Print Receipt
			if (activeDialog != null) activeDialog.dispose();
			// Start new Session
			
			System.out.println("Transaction Complete");
			
			
			station.scanner.disable();
			//weightListener.setExpectedWeight(0);
			scanItemButton.setEnabled(true);
			
			productList.clear();
			productView.removeAll();
			productView.revalidate();
			frame.repaint();
		}
	}
	
	/**
	 * Get the current balance
	 * @param Get the current balance
	 */
	public long getBalance() {
		return balance;
	}
}
