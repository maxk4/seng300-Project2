package util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.SwingUtilities;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;

import ca.powerutility.PowerGrid;

public class Simulation {
	
	public static final Barcode[] barcodes = new Barcode[] {
		new Barcode(new Numeral[] {Numeral.one}),
		new Barcode(new Numeral[] {Numeral.two}),
		new Barcode(new Numeral[] {Numeral.three}),
		new Barcode(new Numeral[] {Numeral.four}),
		new Barcode(new Numeral[] {Numeral.five}),
		new Barcode(new Numeral[] {Numeral.six}),
		new Barcode(new Numeral[] {Numeral.seven}),
		new Barcode(new Numeral[] {Numeral.eight}),
		new Barcode(new Numeral[] {Numeral.nine}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.two})
	};
	
	public static List<Card> cards = new ArrayList<Card>();

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Missing arguments: <stations>");
			return;
		}
		setup();
		
		// Get number of stations
		int diyStations = Integer.parseInt(args[0]);
		
		// Initialize attendant station and ui
		AttendantStation aStation = new AttendantStation();
		AttendantUI attendant = new AttendantUI(aStation, diyStations);
		AttendantStationListener aStationListener = new AttendantStationListener(attendant);
		aStation.registerListener(aStationListener);
		NoBaggingRequestListener nbrListener = new NoBaggingRequestListener(attendant);
		
		// Initialize diy stations
		List<CustomerUI> uis = new ArrayList<CustomerUI>();
		List<DoItYourselfStationAR> stations = new ArrayList<DoItYourselfStationAR>();
		for (int i = 0; i < diyStations; i++) {
			Customer customer = genCustomer();
			
			DoItYourselfStationAR station = new DoItYourselfStationAR();
			station.plugIn();
			station.turnOn();
			CustomerUI ui = new CustomerUISimulator(station, customer);
			
			ScanItemListener sil = new ScanItemListener(ui);
			station.scanner.register(sil);
			customer.useStation(station);
			
			station.cardReader.register(new PayWithCardListener(ui));
	
			ExpectedWeightListener ewl = new ExpectedWeightListener(ui);
			station.scale.register(ewl);
			ui.setWeightListener(ewl);
			
			DiscrepancyListener dl = new DiscrepancyListener(attendant);
			ui.registerDiscrepancyListener(dl);
			ui.registerNoBaggingRequestListener(nbrListener);
			
			uis.add(ui);
			stations.add(station);
		}
		
		// Register diy stations with the attendant station
		for (CustomerUI cStation : uis) aStation.registerStation(cStation);
		
		// Setup attendant station
		aStation.getTouchScreen().plugIn();
		aStation.getTouchScreen().turnOn();
		aStation.getTouchScreen().enable();
		
	}
	
	private static void setup() {
		int[] banknoteDenominations = {5000,2000,1000,500};
		long[] coinDenominations = {200, 100, 25, 10, 5};
		DoItYourselfStationAR.configureBanknoteDenominations(banknoteDenominations);
		DoItYourselfStationAR.configureCoinDenominations(coinDenominations);
		PowerGrid.engageUninterruptiblePowerSource();
		
		for (int i = 0; i < barcodes.length; i++)
			ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcodes[i], new BarcodedProduct(barcodes[i], "Product " + (i + 1), (i + 1) * 100, 2.3));
		
		for (int i = 0; i < 5; i++) {
			Card card = new Card("credit", "841799260331897" + i, "Sir Fakeman", "564", "0000".intern(), true, true);
			Calendar expiry = Calendar.getInstance();
			expiry.set(2025, 1, 1);
			Bank.CARD_ISSUER.addCardData(card.number, card.cardholder, expiry, card.cvv, Double.MAX_VALUE);
			cards.add(card);
		}
	}

	private static Customer genCustomer() {
		Customer customer = new Customer();
		
		for (int i = 0; i < 10; i++) {
			BarcodedItem item = new BarcodedItem(barcodes[i], 2.3);
			customer.shoppingCart.add(item);
		}
		customer.wallet.cards.addAll(cards);
		return customer;
	}

}
