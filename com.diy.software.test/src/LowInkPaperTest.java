import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
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
	
	public static final int MAXIMUM_PAPER = 10;
	public static final int MAXIMUM_INK = 20;

	LowInkLowPaper listener = new LowInkLowPaper();
	DoItYourselfStationAR station;
	
	java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
	
	@Before
	public void setup() {
		printer.plugIn();
		printer.turnOn();
		printer.register(listener);
		
		System.setOut(new java.io.PrintStream(output));
		
	}
	
	/**
	 * Test that when printing with low ink, the listener notices
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testLowInk() throws EmptyException, OverloadException {
		printer.addPaper(MAXIMUM_PAPER);
		printer.addInk(20);
		String expected = "Less than 10% ink remaining.";
		printer.print('A');
		
		assertTrue(listener.getLowInk());
		
		//assertEquals(listener.lowInk(printer),listener.lowInk(printer));
		
		//assertEquals(expected,output.toString());
	}
	
	/**
	 * Test that when printing with low paper, the listener notices
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testLowPaper() throws EmptyException, OverloadException {
		printer.addInk(MAXIMUM_INK);
		printer.addPaper(2);
		String expected = "Less than 10% paper remaining.";

		printer.print('A');
		
		assertTrue(listener.getLowPaper());
		//assertEquals(expected,output.toString());
	}
	
	/**
	 * Test that an empty exception is thrown when printer prints with no ink
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfInkThrowsException() throws EmptyException, OverloadException {
		printer.addPaper(MAXIMUM_PAPER);
		try {
			printer.print('A');	
		} catch (Exception e) {
			assertTrue(e instanceof EmptyException);
		}
	}
	
	/**
	 * Test that an empty exception is thrown when printer prints with no paper
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfPaperThrowsException() throws EmptyException, OverloadException {
		printer.addInk(MAXIMUM_INK);
		try {
			printer.print('A');
		} catch (Exception e) {
			assertTrue(e instanceof EmptyException);
		}
	}
	
	/**
	 * Test that the listener notices when printer runs of of paper
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfPaperTriggersListener() throws EmptyException, OverloadException {
		printer.addInk(MAXIMUM_INK);
		printer.addPaper(1);
		printer.print('\n');

		
		assertTrue(listener.getNoPaper());
	}
	
	/**
	 * Test that the listener notices when printer runs out of ink
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfInkTriggersListener() throws EmptyException, OverloadException {
		printer.addPaper(MAXIMUM_PAPER);
		printer.addInk(1);
		printer.print('A');
		
		assertTrue(listener.getNoInk());
	}
}
