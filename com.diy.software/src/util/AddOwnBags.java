package util;

import java.util.ArrayList;
import java.util.List;

import com.diy.hardware.DoItYourselfStationAR;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;

public class AddOwnBags {

	private List<BarcodedItem> personalBags;
	private DoItYourselfStationAR station;
	
	public AddOwnBags(DoItYourselfStationAR station) {
		this.station = station;
		this.personalBags = new ArrayList<BarcodedItem>();
	}
	
	public boolean addYourOwnBag(double weightOfBag)
		{
			if (weightOfBag <= 0) 
		{
			return false;
		}
		BarcodedItem ownbag = new BarcodedItem(new Barcode(new Numeral[] {Numeral.nine, Numeral.eight, Numeral.seven, Numeral.six}), weightOfBag);
		this.personalBags.add(ownbag);
		this.station.scale.add(ownbag);
		return true;
	}
		
	  
	public boolean removeYourOwnBag(BarcodedItem ownbag) 
	{
		if (ownbag == null) 
		{
			return false;
		}
		if (!this.personalBags.contains(ownbag)) 
		{
			return false;
		}
		this.personalBags.remove(ownbag);
		this.station.scale.remove(ownbag);
		return true;
	}
	
	
	public List<BarcodedItem> getPersonalBags() 
	{
		return this.personalBags;
	}
}
