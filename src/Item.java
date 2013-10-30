

import java.text.NumberFormat;

public class Item {

	private String itemName, description, category;
	private int itemID;
	private double itemPrice;
	private String itemComment = "";
	NumberFormat formatMoney = NumberFormat.getCurrencyInstance();
	
	
//	public Item(String name,int num, double price){
//		setItemName(name);
//		setitemID(num);
//		setItemPrice(price);
//		
//	}
	
	public Item(String name,String description, String category, int num, double price){
		setItemName(name);
		setitemID(num);
		setItemPrice(price);
		setDescription(description);
		setCategory(category);
		
	}
	
	public Item(){}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item: " + itemName + ", item number: " + itemID
				+ ", item price: " + formatMoney.format(itemPrice);
	}


	//Getter and Setters
	
	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public int getitemID() {
		return itemID;
	}


	public void setitemID(int itemID) {
		this.itemID = itemID;
	}


	public double getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItemComment() {
		return itemComment;
	}

	public void setItemComment(String itemComment) {
		this.itemComment = itemComment;
	}

	
}
