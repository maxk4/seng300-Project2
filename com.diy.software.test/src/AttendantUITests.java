import static org.junit.Assert.*;

import java.util.*;

import javax.swing.SwingUtilities;

import org.junit.*;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.external.ProductDatabases;
import com.jimmyselectronics.Item;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodeScanner;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
public class AttendantUITests {
	
	AttendantUI attendant;
	AttendantStation aStation;
	AttendantStationListener listener;

	@Before
	public void setup() {
		aStation = new AttendantStation();
		attendant = new AttendantUI(aStation, 10);
		listener = new AttendantStationListener(attendant);
		aStation.registerListener(listener);
	}
	
	
	@Test
	public void add() {
		List<CustomerUI> uis = new ArrayList<CustomerUI>();
		List<DoItYourselfStationAR> stations = new ArrayList<DoItYourselfStationAR>();
		for (int i = 0; i < 7; i++) {
			DoItYourselfStationAR station = new DoItYourselfStationAR();
			station.plugIn();
			station.turnOn();
			CustomerUI ui = new CustomerUI(station);
			
			ScanItemListener sil = new ScanItemListener(ui);
			station.scanner.register(sil);
	
			ExpectedWeightListener ewl = new ExpectedWeightListener(ui);
			station.scale.register(ewl);
			ui.setWeightListener(ewl);
			
			DiscrepancyListener dl = new DiscrepancyListener(attendant);
			ui.registerDiscrepancyListener(dl);
			
			uis.add(ui);
			stations.add(station);
		}
		for (CustomerUI cStation : uis) aStation.registerStation(cStation);
		aStation.getTouchScreen().plugIn();
		aStation.getTouchScreen().turnOn();
		aStation.getTouchScreen().enable();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				aStation.getTouchScreen().setVisible(true);
			}
		});
		BarcodeScanner testScanner = stations.get(3).scanner;
		Barcode bc = new Barcode(new Numeral[] {Numeral.one});
		BarcodedProduct prod = new BarcodedProduct(bc, "Test Item", 11, 12.0);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc, prod);
		testScanner.enable();
		testScanner.scan(new BarcodedItem(bc, 10));
		
		stations.get(3).scale.add(new Item(1) {});
		testScanner.scan(new BarcodedItem(bc, 10));
		//stations.get(3).baggingArea.add(new Item(11) {});
		attendant.notifyNoBaggingRequest(uis.get(3));
		while (aStation.getTouchScreen().getFrame().isDisplayable());
	}

}
