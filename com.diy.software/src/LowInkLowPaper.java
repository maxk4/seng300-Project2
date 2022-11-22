import javax.swing.JOptionPane;

import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.abagnale.IReceiptPrinter;
import com.jimmyselectronics.abagnale.ReceiptPrinterListener;
import com.jimmyselectronics.abagnale.ReceiptPrinterD;

public class LowInkLowPaper implements ReceiptPrinterListener{
	
	boolean lowPaper;
	boolean lowInk;
	boolean noPaper;
	boolean noInk;
	
	public LowInkLowPaper() {
		this.lowPaper = false;
		this.lowInk = false;
		this.noPaper = false;
		this.noInk = false;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void outOfPaper(IReceiptPrinter printer) {
		abortPrinting();
		noPaper = true;
	}

	@Override
	public void outOfInk(IReceiptPrinter printer) {
		abortPrinting();
		noInk = true;
	}

	@Override
	public void lowInk(IReceiptPrinter printer) {
		notifyLowInk();
		lowInk = true;
	}

	@Override
	public void lowPaper(IReceiptPrinter printer) {
		notifyLowPaper();
		lowPaper = true;
	}

	@Override
	public void paperAdded(IReceiptPrinter printer) {}

	@Override
	public void inkAdded(IReceiptPrinter printer) {}
	
	public boolean getLowInk() {
		return lowInk;
	}
	
	public boolean getLowPaper() {
		return lowPaper;
	}
	
	public boolean getNoInk() {
		return noInk;
	}
	
	public boolean getNoPaper() {
		 return noPaper;
	 }
	
	
	/** If printer runs of ink/paper while printing
	 *  Abort printing, suspend station, inform attendant that duplicate receipt must be printed and station needs maintenance
	 */
	public void abortPrinting() {
		
		JOptionPane maintenanceWarning = new JOptionPane();
		JOptionPane.showMessageDialog(maintenanceWarning,"Duplicate receipt must printed. Station needs maintenance. (No Ink/Paper)");
	}
	
	public void notifyLowInk() {
		System.out.println("Less than 10% ink remaining.");
	}
	
	public void notifyLowPaper() {
		System.out.println("Less than 10% paper remaining.");
	}
}
