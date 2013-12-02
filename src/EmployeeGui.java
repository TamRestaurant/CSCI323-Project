import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
import javax.swing.ListSelectionModel;

import java.awt.Color;

import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Choice;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;

import java.awt.ComponentOrientation;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.JToggleButton;



/***
 * 
 * 
 * @author Austin
 * 
 * This class populates the gui for the current employees and allows
 * New employees to be added to the DB
 * 
 * TODO : Allow employee status to be edited and information regarding the employee changed
 * 
 * 
 * 
 TODO: This is the USPS API interface that will allow a use to enter in zip and city/state will be returned in XML
 TODO: Need to parse XML and fill in city and state for user
http://production.shippingapis.com/ShippingAPITest.dll?API=CityStateLookup&XML=<CityStateLookupRequest%20USERID="387STUDE4227">
     <ZipCode ID= "0">
         <Zip5>90210</Zip5>
     </ZipCode>
</CityStateLookupRequest>
 * 
 * 
 * 
 */
public class EmployeeGui implements ActionListener {

	//private JFrame frmEmployeeManagement;
	private JTable table_employee;
	private JButton btnPopulateEmployees;
	private ResultSet resultSet;
	private JScrollPane scrollPane;
	private JTextField textFirstName;
	private JTextField textAddress;
	private JTextField textLastName;
	private JTextField textCity;
	private String[] states = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
	private String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	
	private JFormattedTextField textPhoneFMT;
	private JButton btnCreateNewEmployee, btnPopulateRoles;
	private Choice choiceRoles;
	private JPanel panelAddEmployee;
	private ArrayList<Integer> roleList;
	private JFormattedTextField textZipFMT;
	private JComboBox comboStates;
	private JCheckBox chkIsActive;
	private JLabel labelError;
	private JButton buttonClearText;
	private JPanel panelEmpGui;
	
	//Connect to database on load

	private DbAction myDBconnection;

	private DbAction DBAction;

	private JLabel lblHireDate;
	private JComboBox comboBoxMonth;
	private JComboBox comboBoxDay;
	private JComboBox comboBoxYear;
	private JPanel panel_1;
	private JButton btnEditEmployee;
	private ListTableModel model;
	private JButton closeButton;
	private JButton btnSaveReportTo;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					employeeGui window = new employeeGui();
//					window.frmEmployeeManagement.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}/// end main
	
	

	/**
	 * Create the application.
	 */
	public EmployeeGui(DbAction DBAction) {
		initialize(DBAction);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public JPanel getEmployeeGui(){
		return panelEmpGui;
	}
	
	@SuppressWarnings("serial")
	private void initialize(DbAction DBAction) {
//		frmEmployeeManagement = new JFrame();
//		frmEmployeeManagement.setTitle("Employee Management");
//		frmEmployeeManagement.setBounds(100, 100, 1093, 571);
//		frmEmployeeManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frmEmployeeManagement.getContentPane().setLayout(null);
		this.DBAction = DBAction;
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		
		panelEmpGui = new JPanel();

		panelEmpGui.setBounds(0, 0, 1077, 532);
		//frmEmployeeManagement.getContentPane().add(panel);
		panelEmpGui.setLayout(null);
		
		btnPopulateEmployees = new JButton("Populate Employees");
		btnPopulateEmployees.addActionListener(new ActionListener() {
			//Action listener for populating employees
			public void actionPerformed(ActionEvent e) {
				
				//Populate table
				
				populateEmployees();
				
			}
			
		});
		btnPopulateEmployees.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnPopulateEmployees.setBounds(10, 332, 200, 51);
		panelEmpGui.add(btnPopulateEmployees);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1070, 291);
		panelEmpGui.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 5, 1070, 286);
		panel_1.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		//Create employee JTable
		table_employee = new JTable();
		table_employee.setDropMode(DropMode.ON);
		table_employee.setFillsViewportHeight(true);
		table_employee.setName("Employee Table");
		table_employee.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table_employee.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_employee.setFont(new Font("Calibri", Font.PLAIN, 14));
		table_employee.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table_employee);
		table_employee.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table_employee.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table_employee.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Employee ID", "First Name", "Last Name", "Address", "City", "State", "Zip", "Phone", "Active", "Role", "Hire Date", "Termination Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_employee.getColumnModel().getColumn(11).setPreferredWidth(93);
		
		panelAddEmployee = new JPanel();
		panelAddEmployee.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add New Employee", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelAddEmployee.setBounds(511, 302, 556, 219);
		panelEmpGui.add(panelAddEmployee);
		panelAddEmployee.setLayout(null);
		
		textFirstName = new JTextField();
		textFirstName.setToolTipText("Name must be at least TWO characters\r\nand only contain letters and NO spaces.");
		textFirstName.setBounds(121, 16, 141, 20);
		panelAddEmployee.add(textFirstName);
		textFirstName.setColumns(10);
		
		textAddress = new JTextField();
		textAddress.setToolTipText("Address can only contain the following characters:\r\nA-Z\r\nNumbers\r\nSpaces\r\n.\r\n#\r\n");
		textAddress.setBounds(121, 47, 309, 20);
		panelAddEmployee.add(textAddress);
		textAddress.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setToolTipText("Name must be at least TWO characters\r\nand only contain letters.");
		textLastName.setBounds(289, 16, 141, 20);
		panelAddEmployee.add(textLastName);
		textLastName.setColumns(10);
		
		textCity = new JTextField();
		textCity.setToolTipText("City must only contain letters and spaces");
		textCity.setBounds(121, 78, 141, 20);
		panelAddEmployee.add(textCity);
		textCity.setColumns(10);
		
		comboStates = new JComboBox(states);
		comboStates.setBounds(271, 78, 63, 20);
		panelAddEmployee.add(comboStates);
		comboStates.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		textZipFMT = new JFormattedTextField(createFormatter("#####"));
		textZipFMT.setToolTipText("Five digit numeric zip code");
		textZipFMT.setBounds(344, 78, 86, 20);
		panelAddEmployee.add(textZipFMT);
		
		textPhoneFMT = new JFormattedTextField(createFormatter("###-###-####"));
		textPhoneFMT.setToolTipText("Phone number must be in format xxx-xxx-xxxx");
		textPhoneFMT.setBounds(121, 109, 97, 20);
		panelAddEmployee.add(textPhoneFMT);
		
		chkIsActive = new JCheckBox("Active?");
		chkIsActive.setBounds(237, 105, 97, 23);
		panelAddEmployee.add(chkIsActive);
		chkIsActive.setFont(new Font("Calibri", Font.PLAIN, 14));
		chkIsActive.setSelected(true);
		
		
		
		btnPopulateRoles = new JButton("Populate Roles");
		btnPopulateRoles.setBounds(230, 139, 132, 23);
		panelAddEmployee.add(btnPopulateRoles);
		btnPopulateRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//GET ROLES FROM DATABASE AND POPULATE THEM INTO ROLES COMBO
				//Disable populate roles and enable add employee
				populateRoles();
				
				
			}
		});
		btnPopulateRoles.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblNameFirstLast = new JLabel("Name: First, Last");
		lblNameFirstLast.setBounds(6, 19, 105, 20);
		panelAddEmployee.add(lblNameFirstLast);
		lblNameFirstLast.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNameFirstLast.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(6, 50, 105, 20);
		panelAddEmployee.add(lblAddress);
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnCreateNewEmployee = new JButton("Add New Employee");
		btnCreateNewEmployee.setBounds(372, 150, 165, 43);
		panelAddEmployee.add(btnCreateNewEmployee);
		btnCreateNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/**
				 * Create new employee
				 * All fields will be validated prior to entry
				 */
				labelError.setText("");
				createNewEmployee();
				
				
			}
		});
		btnCreateNewEmployee.setEnabled(false);
		btnCreateNewEmployee.setFont(new Font("Calibri", Font.BOLD, 14));
		
		choiceRoles = new Choice();
		choiceRoles.setBounds(121, 142, 97, 20);
		panelAddEmployee.add(choiceRoles);
		choiceRoles.setEnabled(false);
		
		JLabel lblCityStZip = new JLabel("City, ST, Zip");
		lblCityStZip.setBounds(6, 78, 105, 20);
		panelAddEmployee.add(lblCityStZip);
		lblCityStZip.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblCityStZip.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(6, 109, 105, 20);
		panelAddEmployee.add(lblPhone);
		lblPhone.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(6, 142, 105, 20);
		panelAddEmployee.add(lblRole);
		lblRole.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
		
		buttonClearText = new JButton("Clear");
		buttonClearText.addActionListener(new ActionListener() {
			
			/***
			 * Button listener to clear all fields
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				textAddress.setText("");
				textCity.setText("");
				textFirstName.setText("");
				textLastName.setText("");
				textZipFMT.setValue(null);
				textPhoneFMT.setValue(null);
				comboStates.setSelectedIndex(0);
				labelError.setText("");
			}
		});
		buttonClearText.setBounds(440, 16, 97, 43);
		panelAddEmployee.add(buttonClearText);
		buttonClearText.setFont(new Font("Calibri", Font.BOLD, 14));
		
		lblHireDate = new JLabel("Hire Date");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHireDate.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblHireDate.setBounds(6, 173, 105, 20);
		panelAddEmployee.add(lblHireDate);
		Calendar.getInstance().getTime();
		
		comboBoxMonth = new JComboBox(new Object[]{});
		comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBoxMonth.setSelectedIndex(0);
		comboBoxMonth.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBoxMonth.setBounds(121, 173, 97, 20);
		comboBoxMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDayCombo();
			}
		});

		panelAddEmployee.add(comboBoxMonth);
		
		comboBoxDay = new JComboBox(new Object[]{});
		//comboBoxDay.setSelectedIndex(0);
		comboBoxDay.setFont(new Font("Calibri", Font.PLAIN, 14));
		//Update comboBoxDay
		updateDayCombo();
		
		comboBoxDay.setBounds(227, 173, 63, 20);
		
		panelAddEmployee.add(comboBoxDay);
		
		comboBoxYear = new JComboBox(new Object[]{});

		comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"2013"}));
		comboBoxYear.setSelectedIndex(0);
		comboBoxYear.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBoxYear.setBounds(300, 173, 63, 20);
		int tempInt = Integer.parseInt(comboBoxYear.getSelectedItem().toString());
		
		for (int i = 0; i < 100; i++){
			tempInt ++;
			comboBoxYear.addItem(Integer.toString(tempInt));
			
		}
		
		// Run update if month is feb in case leap status year changed
		comboBoxYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempMonth = comboBoxMonth.getSelectedItem().toString();
				
				if (tempMonth == "February"){
					updateDayCombo();
				}
				
			}
		});
		
		panelAddEmployee.add(comboBoxYear);
		



		
		labelError = new JLabel("");
		labelError.setFocusable(false);
		labelError.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelError.setHorizontalTextPosition(SwingConstants.LEADING);
		labelError.setForeground(Color.BLACK);
		labelError.setFont(new Font("Calibri", Font.PLAIN, 14));
		labelError.setBounds(10, 302, 491, 24);
		panelEmpGui.add(labelError);
		
		btnEditEmployee = new JButton("Edit selected employee");
		btnEditEmployee.setEnabled(false);
		btnEditEmployee.addActionListener(this);
		btnEditEmployee.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnEditEmployee.setBounds(10, 394, 200, 51);
		panelEmpGui.add(btnEditEmployee);
		
		btnSaveReportTo = new JButton("Save Report to File");
		btnSaveReportTo.addActionListener(this);
		btnSaveReportTo.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnSaveReportTo.setEnabled(false);
		btnSaveReportTo.setBounds(10, 458, 200, 51);
		panelEmpGui.add(btnSaveReportTo);
	} // end initialize
	
	
	
	/**
	 * 	//Populate employees from db on button click
	 */

	private void populateEmployees(){
		
		
		//dbAction DBAction = new dbAction();
		resultSet = DBAction.getEmployeesFull();
		
		//I was using the to troubleshoot based off of: http://www.youtube.com/watch?v=hg1S3QHFNrE
		//requires external jar
		//table_employee.setModel(DbUtils.resultSetToTableModel(resultSet));
		
		
		try {
			//create model from db result and add to table
			model = ListTableModel.createModelFromResultSet(resultSet);

			table_employee.setModel(model);
			populateRoles();
			labelError.setForeground(Color.BLACK);
			labelError.setText("Successful :D");
			btnEditEmployee.setEnabled(true);
			btnSaveReportTo.setEnabled(true);
			
			
	} catch (SQLException e) {
			// TODO ADD EXCEPTION MESSAGE HERE
		labelError.setForeground(Color.RED);
		labelError.setText("Could Not Connect:\n"+e);
			
		}
		
		
	}// end employee populate
	
	/**
	 * Populate roles into choice box from DB
	 */
	private void populateRoles(){
		
		//dbAction DBAction = new dbAction();
		resultSet = DBAction.getRoles();
		roleList = new ArrayList();
		try {
			while (resultSet.next()){
				choiceRoles.add(resultSet.getString(1));	
				//change what is enabled after query DB
				choiceRoles.setEnabled(true);
				btnPopulateRoles.setEnabled(false);
				btnCreateNewEmployee.setEnabled(true);
				//Keep track of roleID's in array list
				roleList.add(resultSet.getInt(2));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
	}
	
	
	/***
	 * Create new employee after validating fields
	 * Must add address first, and get # of empolyee role
	 * @param s
	 * @return
	 */
	private void createNewEmployee(){
		
		
		
		String firstName = textFirstName.getText(), lastName = textLastName.getText();
		String address = textAddress.getText(), city = textCity.getText();
		String state = (String)comboStates.getSelectedItem(); // cast to string
		String zip = (String)textZipFMT.getValue(), phone = (String)textPhoneFMT.getValue();
		String role = choiceRoles.getSelectedItem(); // Get the index of the selected role
		boolean isActive = chkIsActive.isSelected();
		String pattern= "^[a-zA-Z0-9#.\\s]*$";
		
		String tempMonth = Integer.toString(comboBoxMonth.getSelectedIndex()) + 1; // add one to the index because months start @ 1 not 0
		String tempDay = comboBoxDay.getSelectedItem().toString();
		String tempYear = comboBoxYear.getSelectedItem().toString();
		String hireDate = tempYear + "-" + tempMonth + "-" + tempDay;
		
		
		//Validate input and insert data into table.
		//Make sure name only has letters
		if ((firstName.matches("[a-zA-Z]+") && firstName.length() > 2) && (lastName.matches("[a-zA-Z\\s]+") && lastName.length() > 2)) {
			

			//Address must only contain alpha numeric characters and no special characters aside from #
			if (address.matches(pattern) && address.length() > 4){
			//if (address.length() > 4){	
				
				//Validate city text
				if(city.matches("[a-zA-Z\\s]+") && city.length() > 2 ){
					
					//Validate zip length (input is already restricted)
					if(zip != null && zip.length() == 5) {
						
						//Validate Phone (input is already restricted)
						if(phone != null && phone.length() == 12){
							
							//Validate date (input is already restricted)

								
							
							//Attempt to add new employee 
							String[] names = {firstName,lastName};
							String[] addr = {address, city, state, zip, phone};
							
							//Try to add employee to database
							boolean[] employeeOutput = DBAction.addEmployee(names, addr, role, isActive, hireDate);
							
							if (employeeOutput[0] == true && employeeOutput[1] == true){
								populateEmployees();
								buttonClearText.doClick(0);
								labelError.setForeground(Color.BLACK);
								labelError.setText("Employee was successfully added.");
								//Pragmatically click clear text button
								
							}
							
							
							//Give user output to let them know what error occurred
							else if (employeeOutput[0] == true){
								updateErrorLabel("Address added. Employee",1);
							}
							else{
								updateErrorLabel("Address and employee",1);
							}
							
							//Update output
							
							
						}
						else{//Invalid phone number
							updateErrorLabel("phone number",0);
							
						}
					}
					else{//Invlaid Zip
						updateErrorLabel("zip code",0);

					}	
						
				}
				else{//Invalid City
					updateErrorLabel("city",0);
					
				}
			}
			else{ // ERROR IF ADDRESS IS INVALID
				updateErrorLabel("address",0);
				
			}	
		}
		else{// ERROR IF NAME IS INVALID
			updateErrorLabel("first and last name",0);
		}
		
		
		
		
		
	}//Close create employee method
	
	
	//****************Update error label
	private void updateErrorLabel(String error, int errorType){
		labelError.setForeground(Color.RED);
		if (errorType == 0){
			labelError.setText("Please enter a valid " + error +".");
		}
		else
		{
			labelError.setText(error +" not added to database.");
		}
	}
	
	
	//*****Mask formatter to make sure zip codes only are allowed to have 5 numbers entered in the text field
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}//Close mask formatter
	

	//************************Update days combo box
	
	private void updateDayCombo(){
		String tempMonth = comboBoxMonth.getSelectedItem().toString();

		if (tempMonth == "January" || tempMonth == "March" || tempMonth == "May" || tempMonth == "July" || tempMonth == "August " || tempMonth == "October" || tempMonth == "December"){	
			comboBoxDay.removeAllItems();
			for (int i = 0; i < 31; i++){
				comboBoxDay.addItem(day[i]);
			}

		}
		else if(tempMonth == "April" || tempMonth == "June" || tempMonth == "September" || tempMonth == "Novermber"){
			comboBoxDay.removeAllItems();

			for (int i = 0; i < 30; i++){
				comboBoxDay.addItem(day[i]);
			}
		}
		else{//FEB
			try{
			int year = Integer.parseInt(comboBoxYear.getSelectedItem().toString());
			
			if (isLeapYear(year)){
				for (int i = 0; i < 29; i++){
					comboBoxDay.addItem(day[i]);
				}
			}
			else{//Not leap year
				for (int i = 0; i < 28; i++){
					comboBoxDay.addItem(day[i]);
				}
			}
			
			}//close try
			
			catch(Exception e){

				//comboBoxDay = new JComboBox(day);
				comboBoxDay.removeAllItems();
				labelError.setText("Year Exception: non-leap year selected without calculation.");
				for (int i = 0; i < 28; i++){
					comboBoxDay.addItem(day[i]);
				}
			}//close catch

		}//close Feb
		
	}// close update month
	
	
	private boolean isLeapYear(int year){
		boolean temp = false;
		
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
			temp = true;
		}
		
		return temp;
		
	}
	/**
	 * This method allows edits to the table to specific rows
	 * 
	 */
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == btnPopulateEmployees){
			if (table_employee.getSelectedRow() > 0){
				JDialog w = new JDialog();
				int Y = table_employee.getSelectedRow();
				String[] employeeInfo = new String[12];
				int EID = 0, FNAME=1,LNAME=2,ADDR=3,CITY=4,ST=5,ZIP=6,PH=7,ROLE=8,ACTIVE=9,HDATE=10,TDATE=11;
				employeeInfo[EID]= table_employee.getValueAt(Y, EID).toString();
				employeeInfo[FNAME]= table_employee.getValueAt(Y, FNAME).toString();
				employeeInfo[LNAME]= table_employee.getValueAt(Y, LNAME).toString();
				employeeInfo[ADDR]= table_employee.getValueAt(Y, ADDR).toString();
				employeeInfo[CITY]= table_employee.getValueAt(Y, CITY).toString();
				employeeInfo[ST]= table_employee.getValueAt(Y, ST).toString();
				employeeInfo[ZIP]= table_employee.getValueAt(Y, ZIP).toString();
				employeeInfo[PH]= table_employee.getValueAt(Y, PH).toString();
				employeeInfo[ACTIVE]= table_employee.getValueAt(Y, ACTIVE).toString();
				employeeInfo[ROLE]= table_employee.getValueAt(Y, ROLE).toString();
				employeeInfo[HDATE]= table_employee.getValueAt(Y, HDATE).toString();
				try{
					employeeInfo[TDATE]= table_employee.getValueAt(Y, TDATE).toString();
				} catch (NullPointerException e){
					System.out.println("here");
					employeeInfo[TDATE]="0";
				}
				w.setContentPane(new EditEmployeePanelGui(employeeInfo, DBAction));
				w.pack();
				w.setVisible(true);
				labelError.setText("Make sure to click populate again to show updated changes");
			}
		}
		else if (arg0.getSource() == btnSaveReportTo){

			if (WriteTableToFile.tableToFile(table_employee, "employeeHistory")){
				labelError.setText("File Creation Successful!");
			}
			else{
				labelError.setText("File Creation Failed!");
				}
		}


		
		
	}
}
