package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import util.PurchaseBags;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurchaseBagsGUI extends MainGUILauncher {

	private static JFrame frame;
	private JPanel contentPane;
	private JTextField txtNumberOfBags;
	private PurchaseBags main;


	/**
	 * Create the frame.
	 */
	public PurchaseBagsGUI() {
		
		PurchaseBags main = new PurchaseBags();
		this.main = main;
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 342, 388);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 126, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setTitle("Purchase Bags");

		frame.setContentPane(contentPane);
		
		JButton btnNewButton_1 = new JButton("1");
		btnNewButton_1.setBounds(11, 152, 75, 64);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "1";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_2 = new JButton("2");
		btnNewButton_2.setBounds(92, 152, 75, 64);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "2";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_2.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_7 = new JButton("7");
		btnNewButton_7.setBounds(11, 11, 75, 64);
		btnNewButton_7.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "7";
				txtNumberOfBags.setText(currValue);
			}
		});
		
		JButton btnNewButton_8 = new JButton("8");
		btnNewButton_8.setBounds(92, 11, 75, 64);
		btnNewButton_8.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "8";
				txtNumberOfBags.setText(currValue);
			}
		});
		
		JButton btnNewButton_9 = new JButton("9");
		btnNewButton_9.setBounds(173, 11, 75, 64);
		btnNewButton_9.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "9";
				txtNumberOfBags.setText(currValue);
			}
		});
		
		JButton btnNewButton_4 = new JButton("4");
		btnNewButton_4.setBounds(11, 82, 75, 64);
		btnNewButton_4.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "4";
				txtNumberOfBags.setText(currValue);
			}
		});
		
		JButton btnNewButton_6 = new JButton("6");
		btnNewButton_6.setBounds(173, 81, 75, 64);
		btnNewButton_6.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "6";
				txtNumberOfBags.setText(currValue);
			}
		});
		
		JButton btnNewButton_5 = new JButton("5");
		btnNewButton_5.setBounds(92, 82, 75, 64);
		btnNewButton_5.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "5";
				txtNumberOfBags.setText(currValue);
			}
		});
		
		JButton btnNewButton_3 = new JButton("3");
		btnNewButton_3.setBounds(173, 151, 75, 64);
		btnNewButton_3.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "3";
				txtNumberOfBags.setText(currValue);
			}
		});
		
		JButton btnNewButton_0 = new JButton("0");
		btnNewButton_0.setBounds(11, 222, 156, 64);
		btnNewButton_0.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "0";
				txtNumberOfBags.setText(currValue);
				
				if (currValue.equals("0")) {
					txtNumberOfBags.setText("");
					frame.setVisible(false);
				}
				
			}
		});
		
		JButton btnNewButton_decimal = new JButton(".");
		btnNewButton_decimal.setBounds(173, 222, 75, 64);
		btnNewButton_decimal.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_Enter = new JButton("Enter");
		btnNewButton_Enter.setBounds(254, 81, 75, 205);
		btnNewButton_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_Enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				int overall = Integer.parseInt(currValue);
				main.setAmountBags(overall);
				main.addProductBagToList();
			}
		});
		
		JButton btnNewButton_Del = new JButton("Clr");
		btnNewButton_Del.setBounds(254, 11, 75, 64);
		btnNewButton_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		btnNewButton_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				txtNumberOfBags.setText("");
			}
		});
		
		contentPane.setLayout(null);
		
		txtNumberOfBags = new JTextField();
		txtNumberOfBags.setBounds(11, 292, 318, 57);
		txtNumberOfBags.setEditable(false);
		txtNumberOfBags.setText("");
		txtNumberOfBags.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		txtNumberOfBags.setColumns(10);
		contentPane.add(txtNumberOfBags);
		contentPane.add(btnNewButton_4);
		contentPane.add(btnNewButton_5);
		contentPane.add(btnNewButton_6);
		contentPane.add(btnNewButton_7);
		contentPane.add(btnNewButton_8);
		contentPane.add(btnNewButton_9);
		contentPane.add(btnNewButton_0);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton_2);
		contentPane.add(btnNewButton_decimal);
		contentPane.add(btnNewButton_3);
		contentPane.add(btnNewButton_Enter);
		contentPane.add(btnNewButton_Del);
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
