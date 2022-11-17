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

public class ExpectedWeightListenerStub extends ExpectedWeightListener {

	private double expectedWeight, sensitivity, lastWeight;
	private CustomerUI customer;
	private boolean notifyExpectedWeightFlag;
	private boolean approveWeightDiscrepancyFlag;
	
	public ExpectedWeightListenerStub(CustomerUI customer) {
		super(customer);
		this.customer = customer;
	}

	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		if (Math.abs(weightInGrams - expectedWeight) > sensitivity) 
			customer.alertWeightDiscrepancy();
		else customer.resolveWeightDiscrepancy();
		lastWeight = weightInGrams;
	}

	@Override
	public void overload(ElectronicScale scale) {}

	@Override
	public void outOfOverload(ElectronicScale scale) {}

	/**
	 * Set the weight this listener should expect
	 * If doing so will resolve a weight discrepancy the CustomerUI will be alerted such
	 * @param weightInGrams double weight to set the expected weight to in grams
	 */
	public void setExpectedWeight(double weightInGrams) {
		expectedWeight = weightInGrams;
		if (Math.abs(lastWeight - expectedWeight) > sensitivity) 
			customer.alertWeightDiscrepancy();
		else
			customer.resolveWeightDiscrepancy();
		
	}
	
	/**
	 * Increment the weight this listener should expect by the amount provided
	 * If doing so will resolve a weight discrepancy the CustomerUI will be alerted such
	 * @param inc double amount to increment the expected weight by
	 */
	public void updateExpectedWeight(double inc) {
		setExpectedWeight(expectedWeight + inc);
		this.notifyExpectedWeightFlag = true;
	}
	
	/**
	 * Set the sensitivity of this listener
	 * @param sensitivity double the new sensitivity
	 */
	public void setSensitivity(double sensitivity) {
		this.sensitivity = sensitivity;
	}
	
	/**
	 * Force resolve a weight discrepancy
	 * Note: only to be called by the attendant & related classes IMPORTANT!
	 */
	public void approveWeightDiscrepancy() {
		this.approveWeightDiscrepancyFlag = true;
		expectedWeight = lastWeight;
		customer.resolveWeightDiscrepancy();
	}
	
	public boolean getnotifyExpectedWeightFlag() {
		return this.notifyExpectedWeightFlag;
	}
	
	public boolean getapproveWeightDiscrepancyFlag() {
		return this.approveWeightDiscrepancyFlag;
	}

}
