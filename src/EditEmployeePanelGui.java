

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.awt.Choice;

import javax.swing.JSlider;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;

/**
 * This class will update employee's info in the database
 * @author Austin
 *
 */

public class EditEmployeePanelGui extends JPanel implements ActionListener, ChangeListener {
	private JTextField textEmployeeID;
	private JTextField textFName;
	private JTextField textLName;
	private JTextField textAddress;
	private JTextField textCity;
	private JComboBox comboState;
	private JTextField textZip;
	private JTextField textPhone;
	private JLabel lblEmployeeId;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblAddress;
	private JLabel lblCity;
	private JLabel lblState;
	private JLabel lblZip;
	private JLabel lblPhone;
	private JLabel lblActive;
	private JLabel lblRole;
	private JLabel lblHireDate;
	private JLabel lblTerminationDate;
	private JLabel lblNewLabel;
	private JButton btnSubmit;
	private JSpinner spinnerTerminate;
	private Choice choiceRoles;
	private JSlider sliderActive;
	private JLabel lblInactive;
	private JSpinner spinnerHire;
	private DbAction DBAction;
	private ArrayList roleList;
	private Date dateIn = null, dateOut = null;
	private String[] states = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
	private JLabel lblNewLabel_1;
	private JLabel labelError;
	
	/**
	 * Create the panel.
	 * @param closeButton 
	 */
	public EditEmployeePanelGui(String[] employeeInfo, DbAction DBAction) {
		this.DBAction = DBAction;
		
		lblNewLabel = new JLabel("Edit Employee");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		
		lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textEmployeeID = new JTextField();
		textEmployeeID.setEditable(false);
		textEmployeeID.setColumns(10);
		
		lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFName = new JTextField();
		textFName.setColumns(10);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textLName = new JTextField();
		textLName.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textAddress = new JTextField();
		textAddress.setColumns(10);
		
		lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textCity = new JTextField();
		textCity.setColumns(10);
		
		lblState = new JLabel("State");
		lblState.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboState = new JComboBox(states);
		
		lblZip = new JLabel("Zip");
		lblZip.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textZip = new JTextField();
		textZip.setColumns(10);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		
		lblActive = new JLabel("Active");
		lblActive.setHorizontalAlignment(SwingConstants.LEFT);
		
		sliderActive = new JSlider();
		
		sliderActive.setMaximum(1);
		sliderActive.setValue(0);
		sliderActive.setSnapToTicks(true);
		sliderActive.setPaintTicks(true);
		sliderActive.setPaintLabels(true);
		
		lblRole = new JLabel("Role");
		lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
		
		choiceRoles = new Choice();
		
		lblHireDate = new JLabel("Hire Date");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblTerminationDate = new JLabel("Termination Date");
		lblTerminationDate.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerTerminate = new JSpinner();
		spinnerTerminate.setModel(new SpinnerDateModel(new Date(1385017200000L), null, null, Calendar.DAY_OF_YEAR));
		
		btnSubmit = new JButton("Submit Changes");
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		lblInactive = new JLabel("Inactive");
		lblInactive.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerHire = new JSpinner();
		spinnerHire.setModel(new SpinnerDateModel(new Date(1385017200000L), null, null, Calendar.DAY_OF_YEAR));
		
		lblNewLabel_1 = new JLabel("");
		
		labelError = new JLabel("");
		labelError.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(127)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblEmployeeId, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(textEmployeeID, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(textFName, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(textLName, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(textAddress, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(textCity, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(comboState, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblZip, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(textZip, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblInactive, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(sliderActive, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblRole, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(choiceRoles, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblHireDate, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerHire, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnSubmit)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblTerminationDate)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(spinnerTerminate, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(labelError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblActive, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(141))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblEmployeeId))
						.addComponent(textEmployeeID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFirstName))
						.addComponent(textFName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblLastName))
						.addComponent(textLName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblAddress))
						.addComponent(textAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCity))
						.addComponent(textCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblState))
						.addComponent(comboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblZip))
						.addComponent(textZip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPhone))
						.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(sliderActive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInactive))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblRole))
								.addComponent(choiceRoles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHireDate)
								.addComponent(spinnerHire, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lblTerminationDate))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addComponent(spinnerTerminate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblActive))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSubmit)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelError, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(15))
		);
		setLayout(groupLayout);
		
		populateLabels(employeeInfo);
		sliderActive.addChangeListener(this);
		checkActive();

	}
	
	/**
	 * This method fills all the items with the employees information
	 * @param employeeInfo
	 */
	private void populateLabels(String[] employeeInfo){
		int arbitrarySmallishNumber = 5;
		
		textEmployeeID.setText(employeeInfo[0]);
		textFName.setText(employeeInfo[1]);
		textLName.setText(employeeInfo[2]);
		textAddress.setText(employeeInfo[3]);
		textCity.setText(employeeInfo[4]);
		
		for (int i = 0; i < states.length; i ++){
			if (employeeInfo[5].equalsIgnoreCase(comboState.getItemAt(i).toString())){
				comboState.setSelectedIndex(i);
				break;
			}
		}
		
		textZip.setText(employeeInfo[6]);
		textPhone.setText(employeeInfo[7]);
		
		if (employeeInfo[9].equals("false")){
			sliderActive.setValue(0);
		}
		else{
			sliderActive.setValue(1);
		}
		
		try {
			dateIn = new SimpleDateFormat("yyyy-MM-dd").parse(employeeInfo[10]);
			spinnerHire.setModel(new SpinnerDateModel(dateIn, null, null, Calendar.MINUTE));
			
			if (employeeInfo[11].length() > arbitrarySmallishNumber){ // This "MUST" mean that there is no termination date, right? <wink>, <wink>
				dateOut = new SimpleDateFormat("yyyy-MM-dd").parse(employeeInfo[11]);
				spinnerTerminate.setModel(new SpinnerDateModel(dateIn, null, null, Calendar.MINUTE));
			}
			
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		/**
		 * populate the role combo and keep track of the roleID in case change is made
		 */
		ResultSet resultSet = DBAction.getRoles();
		roleList = new ArrayList();
		try {
			while (resultSet.next()){
				choiceRoles.add(resultSet.getString(1));	
				//Keep track of roleID's in array list
				roleList.add(resultSet.getInt(2));
				if (resultSet.getString(1).equals(employeeInfo[8])){
					choiceRoles.select(employeeInfo[8]);
				}
				
			}
		
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			
		}
		

	}//close populate method
	
	
	private void UpdateDatabase(){
		String firstName = textFName.getText(), lastName = textLName.getText();
		String address = textAddress.getText(), city = textCity.getText();
		String state = (String)comboState.getSelectedItem(); // cast to string
		String zip = textZip.getText(), phone = textPhone.getText();
		String role = choiceRoles.getSelectedItem(); // Get the index of the selected role
		if (sliderActive.getValue() == 0){
			boolean isActive = false;
		}
		else{
			boolean isActive = true;
		}
		String pattern= "^[a-zA-Z0-9#.\\s]*$";

		
		//Validate input and insert data into table.
		//Make sure name only has letters
		if ((firstName.matches("[a-zA-Z]+") && firstName.length() > 2) && (lastName.matches("[a-zA-Z\\s]+") && lastName.length() > 2)) {
					

					//Address must only contain alpha numeric characters and no special characters aside from #
					if (address.matches(pattern) && address.length() > 4){
					//if (address.length() > 4){	
						
						//Validate city text
						if(city.matches("[a-zA-Z\\s]+") && city.length() > 2 ){
							
							//Validate zip length 
							//This needs to be updated to validate data or text field needs to validate
							if(zip != null && zip.length() == 5) {
								
								//Validate Phone
								//This needs to be updated to validate data or text field needs to validate
								if(phone != null && phone.length() == 12){
									
									//Get dates from spinner
									DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
									Date hDate = (Date)spinnerHire.getValue();
									String hireDate = dateFormat.format(hDate);
									String termDate = "";
									boolean isActive = true;
									if (sliderActive.getValue() == 0){
										Date tDate = (Date)spinnerTerminate.getValue();
										isActive = false;
										termDate = dateFormat.format(tDate);
										
									}
									
		
										
									
									//Attempt to add new employee 
									String[] names = {firstName,lastName};
									String[] addr = {address, city, state, zip, phone};
									
									
									
									
									//Try to add employee to database
									boolean success = DBAction.updateEmployee(textEmployeeID.getText(), names, addr, role, isActive, hireDate, termDate);
									
									if (success){
										
										labelError.setForeground(Color.BLACK);
										labelError.setText("Employee was successfully added.");
										//Pragmatically click clear text button
										
									}

									else{
										updateErrorLabel("Check Data. Update",1);
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
			}
	
	
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
	
	
	public void actionPerformed(ActionEvent e) {
		UpdateDatabase();
	}
	public void stateChanged(ChangeEvent arg0) {
		
		//Check to see what item is selected
		checkActive();

	}
	private void checkActive(){
		if (sliderActive.getValue() == 0){
			lblInactive.setEnabled(true);
			lblActive.setEnabled(false);
			spinnerTerminate.setEnabled(true);
		}
		else{
			lblInactive.setEnabled(false);
			lblActive.setEnabled(true);
			spinnerTerminate.setEnabled(false);
		}
	}
}
