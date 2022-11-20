package util;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.diy.hardware.BarcodedProduct;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.Numeral;

/**
 * Class designed to control the purchase of bags
 * Also controls the main bagging menu (namely purchase of bags, use of own bags, or no bags)
 * @author Shaheryar Syed
 *
 */

public class PurchaseBags {

	private double unitcostBag;
	private long totalcostBags;
	private int amountBags;
	private Barcode bagBarcode;
	private BarcodedProduct bag;
	private CustomerUI customer;
	
	/**
	 * Opens Customer UI to select bagging options
	 * @param Create Customer UI to select bag options
	 * @return 
	 */
	@SuppressWarnings("serial")
	
	public void mainFrame(CustomerUI customer) {
		
		JDialog dialog = new JDialog(customer.getFrame());
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		
		dialog.setTitle("Bags Menu");
		
		JPanel container = new JPanel(new GridLayout(3,8,1,3));
		JPanel buttons = new JPanel(new GridLayout(1,3));
		
		JButton purchaseButton = new JButton(new AbstractAction("Purchase Bags") {
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	/** GO TO PURCHASE BAGS FRAME METHOD **/
		    	
		    	purchaseBagsFrame(customer);
		    	
		        }
		    });
		
		JButton useOwnBagsButton = new JButton(new AbstractAction("Use Own Bag(s)") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	    		JLabel msg = new JLabel("Clicked use own");
	    		msg.setHorizontalAlignment(JLabel.CENTER);
	    		msg.setVerticalAlignment(JLabel.CENTER);
	    		msg.setFont(new Font("arial", Font.PLAIN, 32));
	        }
	    });
		
		JButton noBagsButton = new JButton(new AbstractAction("No Bag(s)") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           dialog.setVisible(false);
	        }
	    });
		
		
	}
	
	/**
	 * Opens Customer UI to select the amount of bags client wants to purchase
	 * @param Create Customer UI to select Bag(s) amount
	 * @return 
	 */
	@SuppressWarnings("serial")
	public void purchaseBagsFrame(CustomerUI customer) {
		
		JDialog dialog = new JDialog(customer.getFrame());
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		
		dialog.setTitle("Purchase Bags");
		
		JPanel container = new JPanel(new GridLayout(3,8,1,3));
		JPanel buttons = new JPanel(new GridLayout(1,3));
		
		JLabel mainText = new JLabel("How many bags would you like to purchase?");
		mainText.setHorizontalAlignment(JLabel.CENTER);
		mainText.setVerticalAlignment(JLabel.TOP);
		
		JButton oneBag = new JButton(new AbstractAction("1") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 1;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton twoBag = new JButton(new AbstractAction("2") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 2;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton threeBag = new JButton(new AbstractAction("3") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 3;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton fourBag = new JButton(new AbstractAction("4") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 4;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton fiveBag = new JButton(new AbstractAction("5") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 5;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton sixBag = new JButton(new AbstractAction("6") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 5;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton sevenBag = new JButton(new AbstractAction("7") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 7;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton eightBag = new JButton(new AbstractAction("8") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 8;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton nineBag = new JButton(new AbstractAction("9") {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	amountBags = 9;
	            dialog.setVisible(false);
	        }
	    });
		
		JButton returnBtn = new JButton(new AbstractAction ("Return to Previous Menu") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				amountBags = 0;
				dialog.setVisible(false);
			}
		});
		
	}
	
	public void setUnitCostBag() {
		unitcostBag = 1.00;
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
		
		totalcostBags = (long)unitcost * amount;
	}
	
	public void setTotalCostBags(long a) {
		this.totalcostBags = a;
	}
	
	public long getTotalCostBags() {
		return totalcostBags;
	}
	
	public void createProductBag() {
		
		bagBarcode = new Barcode(new Numeral[] { Numeral.zero, Numeral.zero, Numeral.zero, Numeral.zero, Numeral.one });
		BarcodedProduct bag = new BarcodedProduct(bagBarcode, "Purchase Store Bags", totalcostBags, 0.01);
		
	}
	
	public void addProductBagToList() {
		
		if (amountBags == 0) {
			;
		}
		
		else {
			customer.addBarcodedProductToList(bag);
		}
		
	}
	
	public static void main(String[] args) {

		/** TEST TO SEE IF IT WORKS
		DoItYourselfStationAR station = new DoItYourselfStationAR();
		station.plugIn();
		station.turnOn();
		CustomerUI customer = new CustomerUI(station);
		PurchaseBags.mainFrame(customer); **/
		
	}

}
