package EqualExpert;

import java.util.HashMap;
import java.util.Map;

public class Tax {
	
	private Map<ItemType, Double> taxSheet;

	public Tax() {
		super();
		this.taxSheet = new HashMap<ItemType, Double>();
	}
	
	public void addTax(ItemType itemType, Double taxValue) {
		
		if(taxValue < 0) {
			return;
		}
		
		taxSheet.put(itemType, taxValue);
	}
	
	public double getTax(ItemType itemType) {
		return taxSheet.getOrDefault(itemType, 0.0);
	}
}
