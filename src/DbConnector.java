import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/***
 * @author Austin
 * 
 * This class is a connector class to provide the means for connecting to the DB
 * This functionality could be moved to whatever ends up being the main class or
 * be kept separate and called once application is started
 * 
 * TODO: Figure out a way to make sure DB connection is still active, if not then re-connect as needed
 * (thoughts: create method that does this that can be called from dbAction as needed)
 * 
 */

public class DbConnector{
	
	//place holder for connection
	Connection conn = null;
	private String username = "csci32320140101", password="lohrishuphei8xoPapai", url="db2.cs.umt.edu/csci_323_exp20140101";
	//Other test connections
	//private String username = "bsc", password="Passw0rd1", url="db3.modwest.com"; // ---> This one may not have correct drivers installed
	//private String username = "cs322", password="Passw0rd!", url="cs322.db.11713522.hostedresource.com/csci_323_exp20140101";
	//private String username = "root", password="Passw0rd1", url="localhost";
	
	//Constructor
	public DbConnector(){
		
/*		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.out.println("Connecting to db...");
		System.out.println("Successfully connected!! :D");
		}
		catch (Exception ex) {
			System.out.println("exception: " + ex);
			System.out.println("Failure to connect to DB!!!");
		}
		*/
		

	}
	
	public Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{


        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");

        //hangs here    
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + url, username, password);
        System.out.println("Connection successful! :D");
        return conn;
		
		
	}
		
		
}