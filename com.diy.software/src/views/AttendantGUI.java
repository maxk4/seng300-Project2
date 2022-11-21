package views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import util.AttendantUI;

public class AttendantGUI extends MainGUILauncher {

	private static JFrame frame;
	
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnLock;
	private JButton btnUnlock;
	private JButton btnNewButton_1;


	/**
	 * Create the frame.
	 */
	public AttendantGUI(AttendantUI attendant) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 593, 298);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(14, 144, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Approve");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		textField = new JTextField();
		textField.setBackground(new Color(144, 211, 255));
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("Station Number: ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		btnLock = new JButton("Lock");
		btnLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLock.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		btnUnlock = new JButton("Unlock");
		btnUnlock.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		btnNewButton_1 = new JButton("Order Screen");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnLock, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
						.addComponent(btnUnlock, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1)
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnLock, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnUnlock, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	}

	
	/**
	 * Set the visibility of the Jframe
	 * 
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible) {
		frame.setVisible(isVisible);
	}
	

}
