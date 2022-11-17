
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class controlling the UI of the attendant station
 * @author Taylor Wong
 *
 */
public class AttendantUI {
	
	private JFrame frame;
	private JPanel panel;
	private JPanel stations;
	private AttendantStation station;
	private List<JButton> stationControls;
	
	private static final int STATION_WIDTH = 200, STATION_HEIGHT = 200;
	
	/**
	 * Make a new AttendantUI attached to the touch screen of the provided AttendantStation
	 * @param station AttendantStation who's touch screen the new AttendantUI will be attached to
	 */
	public AttendantUI(AttendantStation station, int maxStations) {
		this.station = station;
		this.stationControls = new ArrayList<JButton>();
		
		frame = station.getTouchScreen().getFrame();
		panel = new JPanel();
		 
		panel.setLayout(new BorderLayout());
		 
		stations = new JPanel();
		stations.setLayout(new GridLayout((int) Math.ceil(maxStations / 2d), 2));
		panel.add(stations, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Attendant Controls:");
		label.setFont(new Font("arial", Font.PLAIN, 24));
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label, BorderLayout.PAGE_START);
		
		JButton shutdown = new JButton("Shutdown");
		shutdown.addActionListener(e -> {
			frame.dispose();
		});
		panel.add(shutdown, BorderLayout.PAGE_END);
		 
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setTitle("Attendant UI");
		frame.setMinimumSize(new Dimension(STATION_WIDTH * 2, STATION_HEIGHT * (int) Math.ceil(maxStations / 2d)));
		//frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Notify the attendant that the customer is making a no bagging request
	 * @param customer CustomerUI that is making the request
	 */
	public void notifyNoBaggingRequest(CustomerUI customer) {
		JDialog noBaggingRequest = genNoBaggingRequestPanel(customer);
		noBaggingRequest.setVisible(true);
		noBaggingRequest.setLocationRelativeTo(null);
	}
	
	/**
	 * Generate a new panel containing the necessary elements to represent a no bagging request
	 * @return JPanel representing the no bagging request
	 */
	private JDialog genNoBaggingRequestPanel(CustomerUI customer) {
		JDialog frame = new JDialog(this.frame, Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel panel = new JPanel();
		JPanel buttons = new JPanel();
		
		int id = getId(customer);
		
		JLabel label = new JLabel(String.format("<html><center>No Bagging Request<br>Station: %d</center></html>", id));
		label.setPreferredSize(new Dimension(300, 50));
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("arial", Font.PLAIN, 24));
		
		buttons.setLayout(new GridLayout(1, 2));
		
		JButton approve = new JButton("Approve");
		approve.addActionListener(e -> {
			customer.notifyNoBaggingRequestApproved();
			frame.dispose();
		});
		
		JButton deny = new JButton("Deny");
		deny.addActionListener(e -> {
			customer.notifyNoBaggingRequestDenied();
			frame.dispose();
		});

		buttons.add(approve);
		buttons.add(deny);
		
		panel.setLayout(new GridLayout(2, 1, 16, 16));
		panel.add(label);
		panel.add(buttons);
		panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("No Bagging Request");
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.pack();
		
		return frame;
	}
	
	/**
	 * Notify the attendant that there is a weight discrepancy
	 * @param customer CustomerUI who has a weight discrepancy
	 */
	public void notifyWeightDiscrepancyDetected(CustomerUI customer) {
		JButton control = stationControls.get(getId(customer));
		String text = control.getText();
		control.setEnabled(true);
		control.setText(String.format("<html><center>%s<br> Weight Discrepancy</center></html>", text));
	}
	
	/**
	 * Notify the attendant that the discrepancy was resolved;
	 * @param customer CustomerUI that had the discrepancy
	 */
	public void notifyWeightDiscrepancyResolved(CustomerUI customer) {
		int id = getId(customer);
		JButton control = stationControls.get(id);
		control.setText("Station: " + (id  + 1));
		control.setEnabled(false);
	}
	
	/**
	 * Add a customer to the AttendantUI
	 * @param customer CustomerUI to add
	 */
	public void addCustomerUI(CustomerUI customer) {
		JButton widget = genCustomerWidget(customer);
		stationControls.add(widget);
		stations.add(widget);
		
	}
	
	/**
	 * Remove a customer from the AttendantUI
	 * @param customer CustomerUI to remove
	 */
	public void removeCustomerUI(CustomerUI customer) {
		stationControls.remove(getId(customer));
	}
	
	/**
	 * Generate customer widget
	 * @param customer CustomerUI being represented
	 * @return JButton representing the customer
	 */
	private JButton genCustomerWidget(CustomerUI customer) {
		JButton res = new JButton("Station: " + (stationControls.size() + 1));
		res.setEnabled(false);
		res.addActionListener(e -> customer.approveWeightDiscrepancy());
		return res;
	}
	
	/**
	 * Find the id of the customer
	 * @param customer CustomerUI customer who's id is requested
	 * @return int the id of the customer or -1 if not registered
	 */
	private int getId(CustomerUI customer) {
		for (int i = 0; i < station.getRegisteredStations().size(); i++) {
			if (station.getRegisteredStations().get(i).equals(customer)) {
				return i;
			}
		}
		return -1;
	}
}
