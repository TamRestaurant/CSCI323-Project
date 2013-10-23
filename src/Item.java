

import java.text.NumberFormat;

public class Item {

	private String itemName;
	private int itemNumber;
	private double itemPrice;
	NumberFormat formatMoney = NumberFormat.getCurrencyInstance();
	
	
	public Item(String name,int num, double price){
		setItemName(name);
		setItemNumber(num);
		setItemPrice(price);
	}
	
	public Item(){}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item: " + itemName + ", item number: " + itemNumber
				+ ", item price: " + formatMoney.format(itemPrice);
	}


	//Getter and Setters
	
	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public int getItemNumber() {
		return itemNumber;
	}


	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}


	public double getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
}
