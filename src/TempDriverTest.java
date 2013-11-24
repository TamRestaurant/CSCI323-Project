import java.util.ArrayList;


public class TempDriverTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbAction DBAction = new DbAction();
		
		ArrayList<Order> myOrders = DBAction.getOpenOrders();
		
		for (Order i : myOrders){
			System.out.println(i.toString());
		}

	}

}
