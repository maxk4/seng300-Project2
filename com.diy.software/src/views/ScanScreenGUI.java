package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ScanScreenGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScanScreenGUI frame = new ScanScreenGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScanScreenGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ScanScreenGUI.class.getResource("/resources/icons8-pc-on-desk-100.png")));
		setTitle("-- DoItYourselfStation --");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 794);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(14, 144, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("$");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Debit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-debit-card-100.png")));
		
		JButton btnNewButton_1 = new JButton("Complete/Print Receipt");
		btnNewButton_1.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-receipt-100.png")));
		
		JButton btnNewButton_2 = new JButton("Credit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-mastercard-credit-card-100.png")));
		
		JButton btnNewButton_3 = new JButton("Cash");
		btnNewButton_3.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-cash-100.png")));
		
		JButton btnNewButton_4 = new JButton("Start Scanning");
		btnNewButton_4.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-barcode-100.png")));
		
		JButton btnNewButton_5 = new JButton("Attendant");
		
		JButton btnNewButton_6 = new JButton("Enter Member #");
		
		JButton btnNewButton_7 = new JButton("Use Personal Bags");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("PAY");
		lblNewLabel_1.setFont(new Font("Telugu MN", Font.BOLD, 23));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnNewButton_4, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
										.addContainerGap())
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 271, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(100)
										.addComponent(btnNewButton_5)
										.addContainerGap())
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(6)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(btnNewButton_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
											.addComponent(btnNewButton_6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
										.addContainerGap())))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGap(58)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
									.addComponent(btnNewButton_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 115, Short.MAX_VALUE)
									.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
								.addGap(52)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(112))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(btnNewButton_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_7)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_6)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(btnNewButton_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(63))
		);
		
		JTextArea scannedPricesArea = new JTextArea();
		scannedPricesArea.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		scrollPane.setRowHeaderView(scannedPricesArea);
		scannedPricesArea.setColumns(10);
		
		JTextArea scannedItemsArea = new JTextArea();
		scannedItemsArea.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		scrollPane.setViewportView(scannedItemsArea);
		contentPane.setLayout(gl_contentPane);
	}
}
