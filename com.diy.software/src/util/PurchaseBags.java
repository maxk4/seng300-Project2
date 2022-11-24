package util;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.Product;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;

import views.MainGUILauncher;
import views.AttendantGUI;
import views.PayWithCashGUI;
import views.PayWithCreditGUI;
import views.PayWithDebitGUI;
import views.ScanScreenGUI;

/**
 * Class designed to control the purchase of bags Also controls the main bagging
 * menu (namely purchase of bags, use of own bags, or no bags)
 * 
 * @author Shaheryar Syed
 *
 */

public class PurchaseBags {

	private double unitcostBag = 1.00;
	private long totalcostBags;
	private int amountBags;
	private Barcode bagBarcode;
	private BarcodedProduct bag;
	private CustomerUI customer;

/**
 * Opens Customer UI to select bagging options
 * 
 * @param Create Customer UI to select bag options
 * @return
 */
@SuppressWarnings("serial")

	public void mainFrame(CustomerUI customer) {
	  
	  JDialog dialog = new JDialog(customer.getFrame()); dialog.setModal(true);
	  dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
	  
	  dialog.setTitle("Bags Menu");
	  
	  JPanel container = new JPanel(new GridLayout(3,8,1,3)); JPanel buttons = new
	  JPanel(new GridLayout(1,3));
	  
	  JButton purchaseButton = new JButton(new AbstractAction("Purchase Bags") {
	  
	  @Override public void actionPerformed(ActionEvent e) {
	  
	 /** GO TO PURCHASE BAGS FRAME METHOD **/
	
	  
	  purchaseBagsFrame(customer);
	  
	  } });
	  
	  JButton useOwnBagsButton = new JButton(new AbstractAction("Use Own Bag(s)") {
	  
	  @Override public void actionPerformed(ActionEvent e) { JLabel msg = new
	  JLabel("Clicked use own"); msg.setHorizontalAlignment(JLabel.CENTER);
	  msg.setVerticalAlignment(JLabel.CENTER); msg.setFont(new Font("arial",
	  Font.PLAIN, 32)); } });
	  
	  JButton noBagsButton = new JButton(new AbstractAction("No Bag(s)") {
	  
	  @Override public void actionPerformed(ActionEvent e) {
	  dialog.setVisible(false); } });
	  
	  
	  }
	  
	  
 /**
 * Opens Customer UI to select the amount of bags client wants to purchase
 * 
 * @param Create Customer UI to select Bag(s) amount
 * @return
 */
  @SuppressWarnings("serial") 
  
  public void purchaseBagsFrame(CustomerUI customer) {
  
	  this.customer = customer;
	  
	  JDialog dialog = new JDialog(customer.getFrame()); dialog.setModal(true);
	  dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
	  
	  dialog.setTitle("Purchase Bags");
	  
	  JPanel container = new JPanel(new GridLayout(3,8,1,3)); JPanel buttons = new
	  JPanel(new GridLayout(1,3));
	  
	  JLabel mainText = new JLabel("How many bags would you like to purchase?");
	  mainText.setHorizontalAlignment(JLabel.CENTER);
	  mainText.setVerticalAlignment(JLabel.TOP);
	  
	  JButton oneBag = new JButton(new AbstractAction("1") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(1);
	  dialog.setVisible(false); } });
	  
	  JButton twoBag = new JButton(new AbstractAction("2") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(2);
	  dialog.setVisible(false); } });
	  
	  JButton threeBag = new JButton(new AbstractAction("3") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(3);
	  dialog.setVisible(false); } });
	  
	  JButton fourBag = new JButton(new AbstractAction("4") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(4);
	  dialog.setVisible(false); } });
	  
	  JButton fiveBag = new JButton(new AbstractAction("5") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(5);
	  dialog.setVisible(false); } });
	  
	  JButton sixBag = new JButton(new AbstractAction("6") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(6);
	  dialog.setVisible(false); } });
	  
	  JButton sevenBag = new JButton(new AbstractAction("7") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(7);
	  dialog.setVisible(false); } });
	  
	  JButton eightBag = new JButton(new AbstractAction("8") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(8);
	  dialog.setVisible(false); } });
	  
	  JButton nineBag = new JButton(new AbstractAction("9") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(9);
	  dialog.setVisible(false); } });
	  
	  JButton returnBtn = new JButton(new AbstractAction
	  ("Return to Previous Menu") {
	  
	  @Override public void actionPerformed(ActionEvent e) { setAmountBags(0);
	  dialog.setVisible(false); } });
	  
	  /** Error handling for no bags **/
	  
	  int holder;
	  
	  holder = getAmountBags();
	  
	  if (holder == 0) {
		  ;
	  }
	  
	  else {
		  addProductBagToList();
	  }
	  
  }

  	public void setUnitCostBag(double a) {
  		this.unitcostBag = a;
  	}
	public double getUnitCostBag() {
		return unitcostBag;
	}

	public void setAmountBags(int a) {
		this.amountBags = a;
	}

	public int getAmountBags() {
		return amountBags;
	}
	

	public void totalCostBags(int amount, double unitcost) {
		
		this.amountBags = amount;
		this.unitcostBag = unitcost;
		this.totalcostBags = (long) ((long)unitcost * amount);
	}

	public void setTotalCostBags(long a) {
		this.totalcostBags = a;
	}

	public long getTotalCostBags() {
		return totalcostBags;
	}

	public void createProductBag() {

		totalCostBags(amountBags, unitcostBag);
		
		bagBarcode = new Barcode(new Numeral[] { Numeral.zero, Numeral.zero, Numeral.zero, Numeral.zero, Numeral.one });
		BarcodedProduct bag1 = new BarcodedProduct(bagBarcode, "Store Bags Quantity: " + amountBags, totalcostBags, 0.01);
		this.bag = bag1;
	}

	public void addProductBagToList() {

		createProductBag();
		
		/** Error handling for 0 bags, null exception error gets thrown at totalCostBags **/
		if (amountBags == 0) {
			System.out.println("0 bags");
		}

		else {
			System.out.println(totalcostBags);
			
			/** Customer is null error here **/
			//customer.addBarcodedProductToList(bag);
		}

	}

	public static void main(String[] args) {		

		/** TEST **/ 
		PurchaseBags test = new PurchaseBags();
		
		 
		test.setAmountBags(5);
		test.addProductBagToList();
		
		//System.out.println(test.getTotalCostBags());
		 
		 
	}

}