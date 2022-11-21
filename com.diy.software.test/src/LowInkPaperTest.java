import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.DoItYourselfStationAR;
import com.jimmyselectronics.EmptyException;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.abagnale.IReceiptPrinter;
import com.jimmyselectronics.abagnale.ReceiptPrinterD;


public class LowInkPaperTest {
	
	public ReceiptPrinterD printer = new ReceiptPrinterD();

	LowInkLowPaper listener = new LowInkLowPaper();
	DoItYourselfStationAR station;
	
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final PrintStream original = System.out;
	
	@Before
	public void setup() {
		printer.plugIn();
		printer.turnOn();
		printer.register(listener);
		
		System.setOut(new PrintStream(output));
		
	}
	
	@Test
	public void testLowInk() throws EmptyException, OverloadException {
		printer.addPaper(5);
		printer.addInk(1);
		String expected = "Less than 10% ink remaining.";
		int i = 0;
		while (i < 500) {
			printer.print('A');
			i++;
		}
		
		assertEquals(output,expected.toString());
		
	}
}
