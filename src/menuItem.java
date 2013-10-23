/***
 * 
 * @author Austin
 * 
 * This class represents a menu item
 * that holds the information populated from the database regarding 
 * the menu item attributes
 * 
 * This class may be used or migrated/merged into another appropriate class if anyone else has created this functionality
 * Otherwise it can be utilized
 * 
 */
public class menuItem {

	private int itemID;
	private String name, description, category;
	private double price;
	
	//Default constructor in case needed
	public menuItem(){}
	
	//Constructor passing all items in to initialize object
	public menuItem(String name, String description, String category, double price, int id){
		
		itemID = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
	}

	public String getname() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public String getcategory() {
		return category;
	}


	public double getprice() {
		return price;
	}


	public int getItemID() {
		return itemID;
	}
	
	public String toString(){
		
		return "Item ID: " + itemID + ", Name: " + name + ", Description: " + description + ", Category: " + category + ", Price: $" + price + ".";
	}

	
	
}
