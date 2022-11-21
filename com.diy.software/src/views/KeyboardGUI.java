package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class KeyboardGUI extends MainGUILauncher {

	private static JFrame frame;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public KeyboardGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
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
