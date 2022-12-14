package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Bag;
import util.CustomerUI;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurchaseBagsGUI extends JFrame{

	private JPanel contentPane;
	private JTextField txtNumberOfBags;


	/**
	 * Create the 
	 */
	public PurchaseBagsGUI(CustomerUI customer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 388);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 126, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
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
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "7";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_7.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_8 = new JButton("8");
		btnNewButton_8.setBounds(92, 11, 75, 64);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "8";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_8.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_9 = new JButton("9");
		btnNewButton_9.setBounds(173, 11, 75, 64);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "9";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_9.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_4 = new JButton("4");
		btnNewButton_4.setBounds(11, 82, 75, 64);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "4";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_4.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_6 = new JButton("6");
		btnNewButton_6.setBounds(173, 81, 75, 64);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "6";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_6.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_5 = new JButton("5");
		btnNewButton_5.setBounds(92, 82, 75, 64);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "5";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_5.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_3 = new JButton("3");
		btnNewButton_3.setBounds(173, 151, 75, 64);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "3";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_3.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_0 = new JButton("0");
		btnNewButton_0.setBounds(11, 222, 156, 64);
		btnNewButton_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += "0";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_0.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_decimal = new JButton(".");
		btnNewButton_decimal.setBounds(173, 222, 75, 64);
		btnNewButton_decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue += ".";
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_decimal.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_Enter = new JButton("Enter");
		btnNewButton_Enter.setBounds(254, 81, 75, 205);
		btnNewButton_Enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				int bags = (int) Double.parseDouble(currValue);
				
				for (int i = 0; i < bags; i++) {
					customer.addBarcodedProductToList(new Bag());
				}
				customer.startScanning();
			}
		});
		btnNewButton_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_Del = new JButton("Del");
		btnNewButton_Del.setBounds(254, 11, 75, 64);
		btnNewButton_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtNumberOfBags.getText();
				currValue = currValue.substring(0, currValue.length() - 1);
				txtNumberOfBags.setText(currValue);
			}
		});
		btnNewButton_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
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

}