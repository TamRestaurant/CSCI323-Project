import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/***
 * This class is the interface that interacts with the database for running queries
 * to obtain/update/insert data.
 * FOR ANY CLASS TO WORK mysql-connector-java-5.0.8-bin.jer MUST BE ADDED TO THE CLASSPATH
 * This class is dependent on dbConnector.java to actual make the connection to the database
 * TODO: add additoinal calls as needed and possibly check DB connection prior to each call??
 * 
 * @author Austin
 *
 */

public class dbAction {
	//Examples from: http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-statements.html
	
	private Connection conn;
	private dbConnector myConnector = null;
	
	
	private Statement stmt = null;
	private ResultSet rs = null;
	
	
	/***
	 * We will only want one instance of this class to actually connect to the database instead
	 * of every class thats need to call this method
	 * TODO: fix this
	 */
	public dbAction(){
		
		// Connect to db
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations

			Class.forName("com.mysql.jdbc.Driver").newInstance();
            myConnector = new dbConnector();
            //Get db connection to use for statements
            conn = myConnector.getConnection();
            
        } catch (Exception ex) {
            // handle the error
        	System.out.println("exception: " + ex);
        	
        
        }
		
		
		
	}
	
	/**
	 * This class returns an array list containing an int[2] array that houses recordID and employeeID of all employees that are currently clocked in
	 * @return
	 */
	public ArrayList<int[]> getClockedInEmployees(){
		final int CLOCK_IN_RECORD_ID = 0;
		final int CLOCK_IN_EMP_ID = 1;
		
		ArrayList<int[]> clockedInEmployees = new ArrayList();
		String sqlQuery = "Select\n  EmployeeTimeRecord.idEmployeeTimeRecord,\n  EmployeeTimeRecord.Employee_idEmployee,\n  EmployeeTimeRecord.ClockOutDateTime\n"
				+ "From\n  EmployeeTimeRecord\n"
				+ "Where\n  EmployeeTimeRecord.ClockOutDateTime Is Null";
		
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery);

			
			//Iterate through result set and create array with record id and emp id
			while (rs.next()){
				int[] empRecord = new int[2];
				empRecord[CLOCK_IN_RECORD_ID] = rs.getInt("idEmployeeTimeRecord");
				empRecord[CLOCK_IN_EMP_ID] = rs.getInt("Employee_idEmployee");
				clockedInEmployees.add(empRecord);
			}
			
		}
		
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}

		return clockedInEmployees;
		
	}
	
	/**
	 * This method allows an employee to get clocked in
	 * This returns and array[2] that contains the record of clock in and the employee id
	 * @param currEmployee
	 */
	public int[] clockInEmployee(employee currEmployee){
		String sqlAddress = "INSERT into csci_323_exp20140101.EmployeeTimeRecord(Employee_idEmployee,ClockInDateTime) VALUES (?,?)";
		int[] clockInRecordArray = new int[2];
		final int CLOCK_IN_RECORD_ID = 0;
		final int CLOCK_IN_EMP_ID = 1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		try{
			//Prepared statement for the address
			PreparedStatement createTimeRecord = conn.prepareStatement(sqlAddress, Statement.RETURN_GENERATED_KEYS);
			//Insert needed values into prepared statement
			createTimeRecord.setString(1, Integer.toString(currEmployee.getEmpId()));
			createTimeRecord.setString(2, dateFormat.format(date).toString());
			//Execute prepared statement (run SQL query)
			createTimeRecord.executeUpdate();
			
			//Get return generated keys
			ResultSet rs = createTimeRecord.getGeneratedKeys();
			
			//add employeeId and generated keys into int[] to be returned
			rs.next();
			clockInRecordArray[CLOCK_IN_RECORD_ID] = rs.getInt(1);
			clockInRecordArray[CLOCK_IN_EMP_ID] = currEmployee.getEmpId();
			
			
		}
		catch(SQLException ex){
			//TODO print to console the exception if occurred
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}
		return clockInRecordArray;
		
	}
	
	public void clockOutEmployee(int recordId){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		//String sqlTimeUpdate= "UPDATE csci_323_exp20140101.EmployeeTimeRecord SET ClockOutDateTime = ? WHERE idEmployeeTimeRecord = ?";
		String sqlTimeUpdate = "UPDATE csci_323_exp20140101.EmployeeTimeRecord SET ClockOutDateTime = \""+ dateFormat.format(date).toString() +"\" WHERE idEmployeeTimeRecord = " + recordId;
	
		
		
		try{
			//Was not working, but exact same query taken from debug worked in  DBMS - WTF
			PreparedStatement updateTimeRecord  = conn.prepareStatement(sqlTimeUpdate);
			//updateTimeRecord.setString(1, dateFormat.format(date).toString());
			//String temp = dateFormat.format(date).toString();
			//updateTimeRecord.setInt(2, recordId);
			updateTimeRecord.executeUpdate(sqlTimeUpdate);

			//stmt = conn.createStatement();
			//stmt.executeQuery(sqlTimeupdate);
			
			
		}
		catch(SQLException ex){
			//TODO print to console the exception if occurred
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}
	}
	
	/**
	 * This method gets employees and populates an array list of employee objects from result set
	 * (with helper method below)  Ability to get all employees or just active employees
	 * 
	 * @param onlyActive
	 * @return
	 */
	public Vector getEmployees(boolean onlyActive){
		
		Vector<employee> employeeList = new Vector();
		String sqlAll = "Select\n  Employee.idEmployee,\n  Employee.FirstName,\n  Employee.LastName,\n  EmployeeRole.RoleName,\n  Employee.Pin,\n  Employee.Active\n"
				+ "From\n  Employee Inner Join\n  EmployeeRole On Employee.EmployeeRole_idEmployeeRole =\n    EmployeeRole.idEmployeeRole"
				+ "Order By\n  Employee.idEmployee";
		String sqlActive = "Select\n  Employee.idEmployee,\n  Employee.FirstName,\n  Employee.LastName,\n  EmployeeRole.RoleName,\n  Employee.Pin,\n  Employee.Active\n"
				+ "From\n  Employee Inner Join\n  EmployeeRole On Employee.EmployeeRole_idEmployeeRole =\n    EmployeeRole.idEmployeeRole\n"
				+ "Where\n  Employee.Active = 1\n"
				+ "Order By\n  Employee.idEmployee";
		try {
			stmt = conn.createStatement();
			if (onlyActive){
				 rs = stmt.executeQuery(sqlActive);
			}
			else{
				 rs = stmt.executeQuery(sqlAll);
			}
			
			//Iterate through arraylist and add employees
			while (rs.next()){
				employeeList.add(new employee(rs.getInt("idEmployee"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("RoleName"), rs.getString("Pin")));
			}
			
		}
		
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}

		return employeeList;	
	}
	
	
	/**
	 * Get employee name list, including address and role name
	 * @return ResultSet
	 */
	public ResultSet getEmployeesFull(){
	try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery("Select \n    "
	    		+ "Employee.idEmployee,\n    "
	    		+ "Employee.FirstName,\n    "
	    		+ "Employee.LastName,\n    "
	    		+ "Address.Address1,\n    "
	    		+ "Address.City,\n    "
	    		+ "Address.State,\n    "
	    		+ "Address.Zip,\n    "
	    		+ "Address.phone,\n    "
	    		+ "EmployeeRole.RoleName,\n    "
	    		+ "Employee.Active,\n    "
	    		+ "Employee.HireDate,\n    "
	    		+ "Employee.TerminationDate\nFrom\n    "
	    		+ "csci_323_exp20140101.Address\n        "
	    		+ "Inner Join\n    "
	    		+ "csci_323_exp20140101.Employee ON csci_323_exp20140101.Employee.Address_idAddress = csci_323_exp20140101.Address.idAddress\n        "
	    		+ "Inner Join\n    "
	    		+ "csci_323_exp20140101.EmployeeRole ON csci_323_exp20140101.Employee.EmployeeRole_idEmployeeRole = csci_323_exp20140101.EmployeeRole.idEmployeeRole\n  "
	    		+ "Order By\n Employee.idEmployee");
	    		
	    		
	    		
	    		/*"SELECT \n    "
	    		+ "Employee.idEmployee AS EmployeeID,\n    "
	    		+ "Employee.FirstName,\n    "
	    		+ "Employee.LastName,\n    "
	    		+ "Address.Address1,\n    "
	    		+ "Address.City,\n    "
	    		+ "Address.State,\n    "
	    		+ "Address.Zip,\n    "
	    		+ "Address.phone AS PhoneNumber,\n    "
	    		+ "EmployeeRole.RoleName AS Role, "
	    		+ "Employee.Active,\n    "
	    		+ "Employee.HireDate,\n    "
	    		+ "Employee.TerminationDate FROM\n "
	    		+ "csci_323_exp20140101.Address\n        "
	    		+ "Inner Join\n    "
	    		+ "csci_323_exp20140101.Employee ON csci_323_exp20140101.Employee.Address_idAddress = Address.idAddress\n        "
	    		+ "Inner Join\n    "
	    		+ "csci_323_exp20140101.EmployeeRole ON csci_323_exp20140101.Employee.EmployeeRole_idEmployeeRole = csci_323_exp20140101.EmployeeRole.idEmployeeRole");
				*/
	    
	}
	
	
	
	
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	
	//Return result set if try was successful
	return rs;
	
	}//methodName
	
	
	
	/**
	 * Get the names and ID of all the roles
	 * @return
	 */
	public ResultSet getRoles(){
		try{
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("SELECT RoleName, idEmployeeRole FROM csci_323_exp20140101.EmployeeRole");
		}
		
		catch(SQLException ex){
			// handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		return rs;
		
	}
	/***
	 * Same as above, but allow user to choose which role to obtain
	 * @param roleName
	 * @return
	 */
	public ResultSet getRoles(String roleName){
		try{
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("SELECT idEmployeeRole, RoleName FROM csci_323_exp20140101.EmployeeRole WHERE EmployeeRole.RoleName = '" + roleName +"'");
		}
		
		catch(SQLException ex){
			// handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		return rs;
		
	} // close getRoles(String)
	
	/***
	 * This method adds an employee to the employee table (but also adds address to address table first, as this is required for employee)
	 * using prepared statement for security
	 *EmployeeName = [first, last]
	 *employeeAddress = [address, city, state, zip(#####), phone(###-###-####)]
	 *Returns boolean to allow calling class to know if succeessful. return[address status, employee status]
	 *
	 * @return
	 */
	public boolean[] addEmployee(String[] employeeName, String[] employeeAddress, String employeeRole, boolean isActive, String hireDate){
		boolean addressSuccessful = false, employeeSuccessful = false;
		boolean returnVal[] = new boolean[2];
		int active;
		int addrKey = -1, roleKey = -1;
		if (isActive){
			active = 1;
		}
		else{
			active = 0;
		}
		
		// SQL QUERY FOR REFERENCE::: INSERT into csci_323_exp20140101.Address(Address1,City,State,Zip,phone) VALUES (address,city,state,zip,phone); // Also will obtain addressKey after added
		String sqlAddress = "INSERT into csci_323_exp20140101.Address(Address1,City,State,Zip,phone) VALUES (?,?,?,?,?)";
		String sqlEmployee = "INSERT into csci_323_exp20140101.Employee(FirstName,LastName,EmployeeRole_idEmployeeRole,Active,Address_idAddress,HireDate) VALUES (?,?,?,?,?,?)";
		
		try{
			//Prepared statement for the address
			PreparedStatement addAddress = conn.prepareStatement(sqlAddress, Statement.RETURN_GENERATED_KEYS);
			
			//Fill in all the required vars for the address
			for (int i = 0; i < employeeAddress.length; i++){
				//Index starts at 1 for the first param (filling in for the ? above)
				addAddress.setString(i+1, employeeAddress[i]);
			}//end for
			
			//Get number of rows returned
			int rows = addAddress.executeUpdate();
			
			//Get newly added row of address key
			if (rows == 1){
				//Address was successfully updated in this case and updated one row
				ResultSet rs = addAddress.getGeneratedKeys();
				rs.next();
				addrKey = rs.getInt(1);
				addressSuccessful = true;
			}
			
			//Get resultSet
			rs = getRoles(employeeRole);
			//Get first item from column (key)
			rs.next();
			roleKey = rs.getInt(1);
			
			//See if roleKey/adddrKey was changed from -1, if so last action was success
			//Don't want to create employee if error happened previously
			if (roleKey != -1 && addrKey != -1){
				//Create employee now that address and role have been identified
				PreparedStatement addEmployee = conn.prepareStatement(sqlEmployee);
				
				//Set ? in prepared statement
				addEmployee.setString(1, employeeName[0]);
				addEmployee.setString(2, employeeName[1]);
				addEmployee.setInt(3, roleKey);
				addEmployee.setInt(4, active);
				addEmployee.setInt(5, addrKey);
				addEmployee.setString(6, hireDate);
				
				//Execute updated statement
				int row = addEmployee.executeUpdate();
				if (row == 1){
					employeeSuccessful = true;
				}
				
			}//end add employee if statement
			
		}
		
		
		catch(SQLException ex){
			//TODO print to console the exception if occurred
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}
		
		// Set values for return boolean[]
		returnVal[0] = addressSuccessful;
		returnVal[1] = employeeSuccessful;
		
		return returnVal;
	} // END addEmployees()
	
	
	/***
	 * This method gets the menu items from the database
	 * @return ResultSet
	 * 
	 */
	public ResultSet getMenuItems(){
		try {
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("Select\n  "
		    		+ "MenuItem.idMenuItem,\n  "
		    		+ "MenuItem.ItemName,\n  "
		    		+ "MenuItem.ItemDescription,\n  "
		    		+ "MenuItem.ItemPrice,\n  "
		    		+ "menuItemCategory.CategoryName\n"
		    		+ "From\n  csci_323_exp20140101.Menu "
		    		+ "Inner Join\n  "
		    		+ "csci_323_exp20140101.MenuItem On csci_323_exp20140101.MenuItem.Menu_idMenu = Menu.idMenu "
		    		+ "Inner Join\n  "
		    		+ "csci_323_exp20140101.menuItemCategory On csci_323_exp20140101.MenuItem.MenuCategory_idCategory =\n    "
		    		+ "csci_323_exp20140101.menuItemCategory.idmenuItemCategory");
		    		
		    		
		    		
		    		
		}
		catch(SQLException ex){
			//TODO print to console the exception if occurred
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		
		return rs;
		
	}//Close get menu items
	//Overloaded MethodCall, allow others to pass in without dates
	public ResultSet getOrderReport(int allDates, int showEmpID, int showQTY, int itemDescription, int groupOrders, int openOrders){
		return getOrderReport(allDates, showEmpID, showQTY, itemDescription, groupOrders, openOrders,  "", "");
	}
	
	
	public ResultSet getOrderReport(int allDates, int showEmpID, int showQTY, int itemDescription, int groupOrders, int openOrders, String dateFrom, String dateTo){


		String sqlQuery = prepareReportSQL(allDates, showEmpID, showQTY, itemDescription, groupOrders, openOrders, dateFrom, dateTo);
		
				try {
				    stmt = conn.createStatement();
				    rs = stmt.executeQuery(sqlQuery);
				    		
				    		
				    		
				    		
				}
				catch(SQLException ex){
					//TODO print to console the exception if occurred
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
				
				return rs;
		
	}// close getReports
	
	
	/***
	 * Get open orders will return the order ID from all open order in a string array
	 */
	public ArrayList<String> getOpenOrderIDs(){
		try {
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM csci_323_exp20140101.Order\n"
		    		+ "WHERE\n"
		    		+ "Order.OrderClose is null");

		}
		catch(SQLException ex){
			//TODO print to console the exception if occurred
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		//Call method to turn RS into string[] and return vale
		//TODO: possible concat table number to each item in array
		return prepareOpenOrderString(rs);
		

	}
	
	
	
	/***
	 * This method prepares string for report depending on what options the user has selected
	 * I attempted to use a prepared statement instead, but if values were not present it was causing major problems
	 * @param allDates
	 * @param showEmpID
	 * @param showQTY
	 * @param itemDescription
	 * @param groupOrders
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	private String prepareReportSQL(int allDates, int showEmpID, int showQTY, int itemDescription, int groupOrders, int openOrders, String dateFrom, String dateTo){
		//int allDates, int showEmpID, int showQTY, int itemDescription, int groupOrders, String dateFrom, String dateTo
		String tempReturn = "";
		

		String[] tempSQL = new String[16];
		tempSQL[0] = "Select\n  ";
		tempSQL[1] = "";
		tempSQL[2] = "Concat(Employee.FirstName, \" \", Employee.LastName) As Server,\n    ";
		tempSQL[3] = "Order.idOrder AS \"Order #\",\n  ";
		tempSQL[4] = "Order.OrderDate As \"Order Date\",\n  Order.OrderClose As \"Order Close Date\",\n";
		tempSQL[5] = "";
		tempSQL[6] = "";
		tempSQL[7] = "";
		tempSQL[8] = "";
		tempSQL[9] = "Employee Inner Join\n  ";
		tempSQL[10] = "csci_323_exp20140101.Order On csci_323_exp20140101.Order.Employee_idEmployee = csci_323_exp20140101.Employee.idEmployee Inner Join\n  ";
		tempSQL[11] = "csci_323_exp20140101.OrderMenuItem On csci_323_exp20140101.OrderMenuItem.Order_idOrder = csci_323_exp20140101.Order.idOrder Inner Join\n  ";
		tempSQL[12] = "csci_323_exp20140101.MenuItem On csci_323_exp20140101.OrderMenuItem.MenuItem_idMenuItem = csci_323_exp20140101.MenuItem.idMenuItem\n  ";
		tempSQL[13] = "";
		tempSQL[14] = "";
		tempSQL[15] = "Order By\n \"Order Date\"";
		
		//Loop through and as options are selected, add them as needed to tempSQL (with tempOptionsIndex[] guiding where to put them)
		if ((allDates == 0) || (openOrders == 1)){
			tempSQL[13] = "WHERE\n    ";
		}
		if (allDates == 0){// Change SQL if we have to use multiple WHERE statements (add comma)
			if (openOrders == 1){
				tempSQL[13] += "Order.OrderDate Between \"" + dateFrom +"\" And \""+ dateTo + "\" And\n  ";
			}
			else{
				tempSQL[13] += "Order.OrderDate Between \"" + dateFrom +"\" And \""+ dateTo + "\"\n  ";
			}
		}
		if (openOrders == 1){
			tempSQL[13] += "Order.OrderClose is null\n  ";
		}
		
		if (showEmpID == 1){
			tempSQL[1] = "Employee.idEmployee As \"Employee ID\",\n  ";
		}
		
		if (groupOrders == 1){
			//If grouped we want to sum the order total and omit qty, item, price, description
			tempSQL[14] = "Group By\n `Order`.idOrder  ";
			tempSQL[8] = "Sum(MenuItem.ItemPrice) As \"Order Total\"\nFrom\n  ";

		}
		else{
			tempSQL[8] = "MenuItem.ItemPrice As \"Item Price\"\nFrom\n  ";
			tempSQL[6] = "MenuItem.ItemName As \"Menu Item\",\n  ";
			//These can only take place if group is not selected
			if (showQTY == 1){
				tempSQL[5] = "OrderMenuItem.OrderMenuItemQTY As QTY,\n  ";
			}

			if (itemDescription == 1){
				tempSQL[7] = "MenuItem.ItemDescription AS \"Item Description\",\n  ";
			}

			
		}
		
		for (int i = 0; i < tempSQL.length; i++){
			if (tempSQL[i].length() > 0){
				tempReturn += tempSQL[i];
			}
		}
		

		
		return tempReturn;
		
	}//close prepare reportSQL
	
	
	private ArrayList<String> prepareOpenOrderString(ResultSet rs){
		ArrayList<String> tempList = new ArrayList();

		try {
			while (rs.next()){
				tempList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return tempList;
		
		
		
	}
	
	
}



	
	


	

