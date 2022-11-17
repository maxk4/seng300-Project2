import static org.junit.Assert.*;

import java.util.*;

import javax.swing.SwingUtilities;

import org.junit.*;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.external.ProductDatabases;
import com.jimmyselectronics.Item;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodeScanner;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.virgilio.ElectronicScale;
import com.diy.hardware.Product;

public class CustomerUITest {

	private DoItYourselfStationAR station;
	private ExpectedWeightListenerStub listener;
	private CustomerUI customer;
	private AttendantStation attstation;
	private AttendantUI attendant;
	private BarcodedProduct product;
	private DiscrepancyListener dlistener;
	private DiscrepancyListener dlistener1;
	private ElectronicScale scale;
	private NoBaggingRequestListenerStub nbrlistener;
	
	@Before
	public void setUp() throws Exception {
		this.station = new DoItYourselfStationAR();
		station.plugIn();
		station.turnOn();
		
		attstation = new AttendantStation();
		
		attendant = new AttendantUI(attstation, 1);
		attstation.getTouchScreen().plugIn();
		attstation.getTouchScreen().turnOn();
		attstation.getTouchScreen().enable();
		
		this.customer = new CustomerUI(station);
		attendant.addCustomerUI(customer);
		attstation.registerStation(customer);
		
		/** Diagnose stub and check with regular listener**/
		listener = new ExpectedWeightListenerStub(customer);
		customer.setWeightListener(listener);
		
		scale = new ElectronicScale(250.0, 100.0);
		scale.plugIn();
		scale.turnOn();
		scale.enable();
		
		product = new BarcodedProduct(new Barcode(new Numeral[] {Numeral.one}), "Test Product", 12, 2.3);
	}
	
	@Test
	public void notifyExpectedWeightTest() {

		double weight = 10.0;
		customer.notifyExpectedWeight(weight);	
		assertTrue(listener.getnotifyExpectedWeightFlag());
		
	}
	
	@Test
	public void addProductToListTest() {
		
		customer.addBarcodedProductToList(product);
		assertTrue(customer.checkProductList(product));
	}
	
	@Test
	public void registerDiscrepancyListenerTest0() {
		dlistener = new DiscrepancyListener(attendant);
		customer.registerDiscrepancyListener(dlistener);
		assertTrue(customer.checkDiscrepancyListener(dlistener));
	}
	
	@Test(expected = IllegalStateException.class)
	public void registerDiscrepancyListenerTest1() {
		dlistener = new DiscrepancyListener(attendant);
		customer.registerDiscrepancyListener(dlistener);
		customer.registerDiscrepancyListener(dlistener);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void registerDiscrepancyListenerTest2() {
		
		customer.registerDiscrepancyListener(dlistener);
		
	}
	
	@Test
	public void deregisterDiscrepancyListenerTest0() {
		
		dlistener = new DiscrepancyListener(attendant);
		
		customer.registerDiscrepancyListener(dlistener);
		
		customer.deregisterDiscrepancyListener(dlistener);
		assertFalse(customer.checkDiscrepancyListener(dlistener));
	}
	
	@Test(expected = IllegalStateException.class)
	public void deregisterDiscrepancyListenerTest1() {
		
		dlistener = new DiscrepancyListener(attendant);
		
		customer.deregisterDiscrepancyListener(dlistener);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deregisterDiscrepancyListenerTest2() {
		
		customer.registerDiscrepancyListener(dlistener);
	}
	
	@Test
	public void alertWeightDiscrepancyTest() {
		customer.alertWeightDiscrepancy();
		assertTrue(station.scanner.isDisabled());
	}
	
	@Test
	public void resolveWeightDiscrepancyTest() {
		customer.resolveWeightDiscrepancy();
		assertFalse(station.scanner.isDisabled());
	}
	
	@Test
	public void notifyNoBaggingRequestApprovedTest() {
		customer.notifyNoBaggingRequestApproved();
		assertTrue(listener.getapproveWeightDiscrepancyFlag());
	}
	
	@Test
	public void approveWeightDiscrepancy() {
		customer.approveWeightDiscrepancy();
		assertTrue(listener.getapproveWeightDiscrepancyFlag());
	}
	
	@Test
	public void productCountTest() {
		customer.addBarcodedProductToList(product);
		customer.addBarcodedProductToList(product);
		
		assertEquals(customer.productCount(), 2);
	}
	
	@Test
	public void notifyPaymentTest() {
		
		customer.addBarcodedProductToList(product);
		customer.setBalance(80);
		customer.notifyPayment(80);
		
		assertEquals(0,customer.productCount());
	}
	
	@Test
	public void registerNoBaggingRequestListenerTest0() {
		
		nbrlistener = new NoBaggingRequestListenerStub(attendant);
		customer.registerNoBaggingRequestListener(nbrlistener);
		assertTrue(customer.checkNoBaggingRequestListener(nbrlistener));
	}
	
	@Test(expected = IllegalStateException.class)
	public void registerNoBaggingRequestListenerTest1() {
	
		nbrlistener = new NoBaggingRequestListenerStub(attendant);
		customer.registerNoBaggingRequestListener(nbrlistener);
		customer.registerNoBaggingRequestListener(nbrlistener);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void registerNoBaggingRequestListenerTest2() {
		
		customer.registerNoBaggingRequestListener(nbrlistener);
	}
	
	@Test
	public void deregisterNoBaggingRequestListenerTest0() {
		nbrlistener = new NoBaggingRequestListenerStub(attendant);
		customer.registerNoBaggingRequestListener(nbrlistener);
		customer.deregisterNoBaggingRequestListener(nbrlistener);
		assertFalse(customer.checkNoBaggingRequestListener(nbrlistener));
	}
	
	@Test(expected = IllegalStateException.class)
	public void deregisterNoBaggingRequestListenerTest1() {
		
		nbrlistener = new NoBaggingRequestListenerStub(attendant);
		customer.deregisterNoBaggingRequestListener(nbrlistener);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deregisterNoBaggingRequestListenerTest2() {
		
		customer.deregisterNoBaggingRequestListener(nbrlistener);
	}
	
	@Test
	public void getBalanceTest() {
		customer.setBalance(10);
		assertEquals(10, customer.getBalance());
	}
	
	
}




