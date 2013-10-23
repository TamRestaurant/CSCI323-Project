import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/***
 * This class is the driver to test and fill ArrayList<menuItem> with
 * menu items so the can be populated
 * TODO: This class should be removed, but the functionality can be moved into other classes that need to populate
 * data from the DB (or adapted as necessary)
 * 
 * @author Austin
 *
 */
public class menuItemDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/***
		 * The following code can be copied and pasted to populate all of the menuItems
		 * Make sure you set  mysql-connector.......-bin.jar as a classpath or else this wont work
		 * 
		 */
		dbAction myDBconnection = new dbAction();
		ResultSet rs = myDBconnection.getMenuItems();
		ArrayList<menuItem> myMenu = new ArrayList();
		
		
		//Populate arrayList of menuItems from ResultSet
		try{
			int counter = 0;
		while (rs.next()){
			//Have option of using rs.getString(1) for column numbers or can use names. Opted for names for readability
			myMenu.add(new  menuItem(rs.getString("ItemName"), rs.getString("ItemDescription"), rs.getString("CategoryName"), rs.getDouble("ItemPrice"), rs.getInt("idMenuItem")));
			System.out.println(myMenu.get(counter).toString());
			counter ++;
			
		}
		}
		catch (SQLException e ) {
	        System.out.println(e);
		}
		

	}

}
