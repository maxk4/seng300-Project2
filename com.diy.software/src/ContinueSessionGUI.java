

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.diy.hardware.DoItYourselfStationAR;

public class ContinueSessionGUI {
	
	private static final int AUTO_CLOSE = 60;
	
	/**
	 * Prompt the user if they want to continue their session.
	 * Notifies the provided CustomerUI when a selection is made.
	 * Auto closes and ends session after AUTO_CLOSE seconds
	 * @param customer CustomerUI to bind to
	 */
	public static void prompt(CustomerUI customer) {
		JDialog dialog = new JDialog(customer.getFrame());
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		
		dialog.setTitle("Continue?");
		
		JPanel container = new JPanel(new GridLayout(3, 1));
		
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		
		JLabel msg = new JLabel("Do you want to continue?");
		msg.setHorizontalAlignment(JLabel.CENTER);
		msg.setVerticalAlignment(JLabel.CENTER);
		msg.setFont(new Font("arial", Font.PLAIN, 32));
		
		JLabel countdown = new JLabel();
		countdown.setHorizontalAlignment(JLabel.CENTER);
		countdown.setVerticalAlignment(JLabel.CENTER);
		countdown.setFont(new Font("arial", Font.PLAIN, 24));
		
		Timer timer = new Timer(1000, null);
		timer.addActionListener(new ActionListener() {
			int count = AUTO_CLOSE;
			@Override
			public void actionPerformed(ActionEvent e) {
				countdown.setText(String.format("Auto closing in: %ds", count--));
				if (count < 0) {
					timer.stop();
					dialog.dispose();
					customer.endSession();
				}
			}
		});
		timer.setInitialDelay(0);
		timer.start();
		
		JButton cont = new JButton("Continue");
		cont.addActionListener(e -> {
			timer.stop();
			dialog.dispose();
		});
		cont.setMaximumSize(new Dimension(200, 50));
		
		JButton end = new JButton("Finish Session");
		end.addActionListener(e -> {
			timer.stop();
			dialog.dispose();
			customer.endSession();
		});
		end.setMaximumSize(new Dimension(200, 50));
		
		buttons.add(cont);
		buttons.add(end);
		buttons.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		container.add(msg);
		container.add(countdown);
		container.add(buttons);
		
		dialog.getContentPane().add(container);
		dialog.setSize(600, 300);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				timer.stop();
				customer.getFrame().dispose();
			}
		});
		dialog.setVisible(true);
	}
	
	// testing only
	public static void main(String[] args) {
		DoItYourselfStationAR station = new DoItYourselfStationAR();
		station.plugIn();
		station.turnOn();
		CustomerUI customer = new CustomerUI(station);
		ContinueSessionGUI.prompt(customer);
	}
	
}
