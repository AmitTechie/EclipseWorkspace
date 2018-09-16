package EqualExpert;

public abstract class Item {
	
	protected String itemName;
	protected double itemPrice;
	protected ItemType itemType;
	protected int barCodeNumber;
	
	public Item(ItemType itemType, double itemPrice) {
		super();
		this.itemName = this.getClass().getSimpleName();
		this.itemPrice = itemPrice;
		this.itemType = itemType;
		this.barCodeNumber = this.hashCode();
	}

	public String getItemName() {
		return itemName;
	}
	
	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}	
}
