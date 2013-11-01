import java.util.ArrayList;


public class tempDriverTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dbAction DBAction = new dbAction();
		
		ArrayList<Order> myOrders = DBAction.getOpenOrders();
		
		for (Order i : myOrders){
			System.out.println(i.toString());
		}

	}

}
