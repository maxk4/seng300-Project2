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


public class NoBaggingRequestListenerStub extends NoBaggingRequestListener  {

	private AttendantUI attendant;
	
	public NoBaggingRequestListenerStub(AttendantUI attendant) {
		super(attendant);
		this.attendant = attendant;
	}

	public void notifyNoBaggingRequest(CustomerUI customer) {
		attendant.notifyNoBaggingRequest(customer);
	}
	
	
	
}
