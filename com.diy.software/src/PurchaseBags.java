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
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;

public class PurchaseBags {

	private double unitcostBag;
	private double totalcostBags;
	private int amountBags;
	
	/**
	 * Opens Customer UI to select the amount of bags client wants to purchase
	 * @param Create Customer UI to select Bag(s) amount
	 * @return 
	 */
	
	public static void mainFrame(CustomerUI customer) {
		
		JDialog dialog = new JDialog(customer.getFrame());
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		
		dialog.setTitle("Bags Menu");
		
		JPanel container = new JPanel(new GridLayout(3,8,1,3));
		JPanel buttons = new JPanel(new GridLayout(1,3));
		
		JButton purchaseButton = new JButton(new AbstractAction("Purchase Bags") {
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
				JLabel msg = new JLabel("Clicked Purchase");
				msg.setHorizontalAlignment(JLabel.CENTER);
				msg.setVerticalAlignment(JLabel.CENTER);
				msg.setFont(new Font("arial", Font.PLAIN, 32));
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DoItYourselfStationAR station = new DoItYourselfStationAR();
		station.plugIn();
		station.turnOn();
		CustomerUI customer = new CustomerUI(station);
		PurchaseBags.mainFrame(customer);
	}

}
