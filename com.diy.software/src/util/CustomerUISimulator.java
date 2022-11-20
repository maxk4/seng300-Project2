package util;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.diy.hardware.DoItYourselfStationAR;
import com.diy.simulation.Customer;
import com.jimmyselectronics.Item;
import com.jimmyselectronics.disenchantment.TouchScreen;
import com.jimmyselectronics.opeechee.Card;

public class CustomerUISimulator extends CustomerUI {

	public CustomerUISimulator(DoItYourselfStationAR station, Customer customer) {
		super(station);
		
		JDialog customerSim = new JDialog(getFrame());
		customerSim.setLocationRelativeTo(null);
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(1, 2));
		
		JPanel cart = new JPanel();
		cart.setLayout(new BoxLayout(cart, BoxLayout.PAGE_AXIS));
		
		int i = 0;
		JLabel cartLabel = new JLabel("Cart: Click to Scan");
		cart.add(cartLabel);
		for (Item item : customer.shoppingCart) {
			JButton button = new JButton(String.format("Item %d: Weight: %fkg", ++i, item.getWeight()));
			button.addActionListener(e -> {
				if (station.scanner.isDisabled()) return;
				customer.shoppingCart.add(item);
				customer.selectNextItem();
				customer.scanItem();
				customer.placeItemInBaggingArea();
				System.out.println("Scanned");
			});
			cart.add(button);
		}
		
		JPanel wallet = new JPanel();
		wallet.setLayout(new BoxLayout(wallet, BoxLayout.PAGE_AXIS));
		
		JLabel walletLabel = new JLabel("Wallet: Click to select card pin 0000 will be used");
		wallet.add(walletLabel);
		
		for (Card card : customer.wallet.cards) {
			JButton button = new JButton(String.format("(%s): %s", card.number, card.kind));
			button.addActionListener(e -> {
				customer.wallet.cards.add(card);
				try {
					customer.selectCard(card.kind);
					customer.insertCard("0000".intern());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				customer.wallet.cards.remove(customer.wallet.cards.size() - 1);
			});
			wallet.add(button);
		}
		
		container.add(cart);
		container.add(wallet);
		container.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		customerSim.getContentPane().add(container);
		
		customerSim.pack();
		customerSim.setVisible(true);
		
	}

}
