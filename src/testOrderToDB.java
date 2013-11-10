import java.util.ArrayList;


public class testOrderToDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//This class just tests the adding order to DB methods in dbAction
		
		dbAction DBAction = new dbAction();
		ArrayList<Item> item = new ArrayList();
		
		item.add(new Item("", "", "", 1, 1.99, "no ice"));
		item.add(new Item("", "", "", 17, 1.99));
		item.add(new Item("", "", "", 23, 1.99));
		item.add(new Item("", "", "", 22, 1.99));
		item.add(new Item("", "", "", 23, 1.99));
		item.add(new Item("", "", "", 36, 1.99, "Over cooked"));
		
		Order order = new Order(item, 5, 7);
		
		DBAction.addOrder(order);
		System.out.println("Order was successfully added to database.  Program is terminated.");


	}

}
