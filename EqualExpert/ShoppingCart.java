package EqualExpert;
import java.util.*;

public class ShoppingCart {
	
	private Map<String, Set<Item>> cart;
	private PaymentBill paymentBill;

	public ShoppingCart(Tax tax) {
		super();
		cart = new HashMap<String, Set<Item>>();
		paymentBill = new PaymentBill(tax);
	}

	public ShoppingCart() {
		super();
		cart = new HashMap<String, Set<Item>>();
		paymentBill = new PaymentBill(null);
	}

	public void addItem(Item item) {
		
		if(item == null)
			return;
		
		Set<Item> itemList = cart.getOrDefault(item.getItemName(),new HashSet<Item>());
		itemList.add(item);
		cart.put(item.getItemName(), itemList);
		paymentBill.updateBill(item);
	}

	public void addItem(List <Item> items) {
		
		if(items == null || items.isEmpty())
			return;
		
		for (Item item: items) {
			addItem(item);
		}
	}

	public double getTotalPrice() {
		return paymentBill.getTotalAmount();
	}
	
	public double getTotalTax() {
		return paymentBill.getTotalTax();
	}	
	
	public int getItemQuantity(Item item) {
		if(item == null) {
			return 0;
		}
		return getItemQuantity(item.getItemName());
	}	

	public int getItemQuantity(String itemName) {
		Set<Item> itemList = cart.get(itemName);
		if(itemList == null) {
			return 0;
		}
		return itemList.size();
	}	
	
	public Set<Item> getItemList(Item item){
		return cart.get(item.getItemName());
	}
	
	public Set<Item> getItemList(String itemName){
		return cart.get(itemName);
	}
}
