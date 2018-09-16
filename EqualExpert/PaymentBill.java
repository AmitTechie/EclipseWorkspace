package EqualExpert;
import java.text.DecimalFormat;

public class PaymentBill {

	private DecimalFormat df;
	private double totalAmount = 0.0;
	private double totalTax = 0.0;
	private Tax tax;
	
	public PaymentBill(Tax tax) {
		super();
		this.tax = tax;
		df = new DecimalFormat(".##");
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	
	public void updateBill(Item item) {
		this.totalAmount += item.itemPrice;
		if(tax != null) {
			double taxValue = item.itemPrice*tax.getTax(item.getItemType())/100;
			this.totalTax += taxValue;
			this.totalAmount += taxValue;
			this.totalTax = (double)Math.round(this.totalTax*100.0)/100.0;
			this.totalTax = Double.parseDouble(df.format(this.totalTax));
		}
		
		this.totalAmount = (double)Math.round(this.totalAmount*100.0)/100.0;
		this.totalAmount = Double.parseDouble(df.format(this.totalAmount));
	}
}
