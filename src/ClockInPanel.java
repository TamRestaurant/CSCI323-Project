import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/***
 * 
 * This class will be utilized for employee clock in/out
 * 
 * @author Austin
 *
 */

public class ClockInPanel extends JPanel implements ActionListener, ItemListener {
	private JButton btnKey1;
	private JButton btnKey2;
	private JButton btnKey3;
	private JButton btnKey4;
	private JButton btnKey5;
	private JButton btnKey6;
	private JButton btnKey7;
	private JButton btnKey8;
	private JButton btnKey9;
	private   JPanel panel;
	private JComboBox comboEmployee;
	private JLabel lblNewLabel;
	private JLabel lblEnterPin;
	private JButton btnKey0;
	private JPasswordField textPin;
	private JButton buttonClockInOut;
	private JLabel lblStatus;
	
	private Vector<employee> employeeList; // I made this thinking that it would poplulate the combo box, but it is an object not a string
	private String[] employeeStringArray;
	//This arraylist holds an array that has two values index0=tempRecordID index1=employeeID
	private DbAction DBAction;
	private ArrayList<int[]> clockedInEmployees;
	private final int CLOCK_IN_RECORD_ID = 0, CLOCK_IN_EMP_ID = 1;
	private JButton buttonClear;
	boolean isClockedIn;
	
	/**
	 * 
	 * @param DBAction
	 */

	public ClockInPanel(DbAction DBAction) {
		setLayout(null);
		this.DBAction = DBAction;
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Employee Clock in/out", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(337, 11, 343, 597);
		add(panel);
		panel.setLayout(null);
		
		
		employeeList = DBAction.getEmployees(true);
		employeeStringArray = new String[employeeList.size()];
		
		//Populate string array to display in combo box
		for (int i = 0; i < employeeStringArray.length; i++){
			employeeStringArray[i] = employeeList.get(i).getEmpId() + "  -  " + employeeList.get(i).getLastName() +", " + employeeList.get(i).getFirstName();
		}
		
		//Instantiation and initialization of all objects
		
		
		btnKey1 = new JButton("1");
		btnKey1.setEnabled(false);
		btnKey1.setBounds(13, 130, 89, 88);
		panel.add(btnKey1);
		btnKey1.setFont(new Font("Calibri", Font.BOLD, 32));
		
		btnKey2 = new JButton("2");
		btnKey2.setEnabled(false);
		btnKey2.addActionListener(this);
		btnKey2.setBounds(125, 130, 89, 88);
		panel.add(btnKey2);
		btnKey2.setFont(new Font("Calibri", Font.BOLD, 32));
		
		btnKey3 = new JButton("3");
		btnKey3.setEnabled(false);
		btnKey3.addActionListener(this);
		btnKey3.setBounds(241, 130, 89, 88);
		panel.add(btnKey3);
		btnKey3.setFont(new Font("Calibri", Font.BOLD, 32));
		
		btnKey4 = new JButton("4");
		btnKey4.setEnabled(false);
		btnKey4.addActionListener(this);
		btnKey4.setBounds(13, 233, 89, 88);
		panel.add(btnKey4);
		btnKey4.setFont(new Font("Calibri", Font.BOLD, 32));
		
		btnKey5 = new JButton("5");
		btnKey5.setEnabled(false);
		btnKey5.addActionListener(this);
		btnKey5.setBounds(125, 233, 89, 88);
		panel.add(btnKey5);
		btnKey5.setFont(new Font("Calibri", Font.BOLD, 32));
		
		btnKey6 = new JButton("6");
		btnKey6.setEnabled(false);
		btnKey6.addActionListener(this);
		btnKey6.setBounds(241, 229, 89, 88);
		panel.add(btnKey6);
		btnKey6.setFont(new Font("Calibri", Font.BOLD, 32));
		
		btnKey7 = new JButton("7");
		btnKey7.setEnabled(false);
		btnKey7.addActionListener(this);
		btnKey7.setBounds(13, 332, 89, 88);
		panel.add(btnKey7);
		btnKey7.setFont(new Font("Calibri", Font.BOLD, 32));
		
		btnKey8 = new JButton("8");
		btnKey8.setEnabled(false);
		btnKey8.addActionListener(this);
		btnKey8.setBounds(125, 332, 89, 88);
		panel.add(btnKey8);
		btnKey8.setFont(new Font("Calibri", Font.BOLD, 32));
		
		btnKey9 = new JButton("9");
		btnKey9.setEnabled(false);
		btnKey9.addActionListener(this);
		btnKey9.setBounds(241, 332, 89, 88);
		panel.add(btnKey9);
		btnKey9.setFont(new Font("Calibri", Font.BOLD, 32));
		
		//Add employee list to combo box
		comboEmployee = new JComboBox(employeeStringArray);
		comboEmployee.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboEmployee.setBounds(10, 49, 317, 20);
		panel.add(comboEmployee);
		comboEmployee.setSelectedIndex(-1); //Uncheck selection
		comboEmployee.addItemListener(this);
		
		lblNewLabel = new JLabel("Select Employee");
		lblNewLabel.setLabelFor(comboEmployee);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 22, 317, 20);
		panel.add(lblNewLabel);
		
		lblEnterPin = new JLabel("Enter Pin");
		lblEnterPin.setFont(new Font("Calibri", Font.BOLD, 20));
		lblEnterPin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPin.setBounds(10, 74, 317, 20);
		panel.add(lblEnterPin);
		
		btnKey0 = new JButton("0");
		btnKey0.setEnabled(false);
		btnKey0.addActionListener(this);
		btnKey0.setFont(new Font("Calibri", Font.BOLD, 32));
		btnKey0.setBounds(13, 431, 89, 88);
		panel.add(btnKey0);


		textPin = new JPasswordField(10);
		textPin.setFont(new Font("Calibri", Font.BOLD, 14));
		textPin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPin.setLabelFor(textPin);
		textPin.setBounds(10, 100, 317, 20);
		panel.add(textPin);
		
		buttonClockInOut = new JButton("Clock in/out");
		buttonClockInOut.setForeground(new Color(0, 153, 51));
		buttonClockInOut.addActionListener(this);
		buttonClockInOut.setBounds(125, 431, 204, 63);
		panel.add(buttonClockInOut);
		buttonClockInOut.setFont(new Font("Calibri", Font.BOLD, 32));
		buttonClockInOut.setEnabled(false);
		
		lblStatus = new JLabel("");
		lblStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblStatus.setBounds(13, 556, 317, 29);
		panel.add(lblStatus);
		
		buttonClear = new JButton("Clear");
		buttonClear.addActionListener(this);
		buttonClear.setForeground(new Color(255, 102, 102));
		buttonClear.setFont(new Font("Calibri", Font.BOLD, 32));
		buttonClear.setBounds(125, 505, 204, 43);
		panel.add(buttonClear);
		btnKey1.addActionListener(this);
		buttonClear.setEnabled(false);

		//Call populate Clock in list
		populateClockInList();
		
		
	}
	
	
	/**
	 * This method updates
	 */
	public void populateClockInList(){
		//Create new array list every time this method is called in case employees punched in/out since last check
		clockedInEmployees = new ArrayList(DBAction.getClockedInEmployees());

	}
	
	/**
	 * Button listeners for input pad
	 * 
	 */
	@SuppressWarnings("deprecation") // We are only using 4 digit pin, .getText() is fine
	public void actionPerformed(ActionEvent e) {
		
		String fName = employeeList.get(comboEmployee.getSelectedIndex()).getFirstName();
		String lName = employeeList.get(comboEmployee.getSelectedIndex()).getLastName();
		int empId = employeeList.get(comboEmployee.getSelectedIndex()).getEmpId();
		int tempRecordId = -1; //Put neg one to make sure value is not in DB in case error occurs on loop
		
		if (e.getSource() == buttonClockInOut){
			String tempPin = employeeList.get(comboEmployee.getSelectedIndex()).getPin();
			if (textPin.getText().equals(tempPin)){
				//User must have typed correct pin, check to see if logged in or out and complete appropriate action
				if (isClockedIn){
					lblStatus.setText(fName + " " + lName + " is now clocked out.");
					isClockedIn = false;
					//Loop through to get recordID from selected employee
					for (int i = 0; i < clockedInEmployees.size(); i++){
						if (clockedInEmployees.get(i)[CLOCK_IN_EMP_ID] == empId){
							tempRecordId = clockedInEmployees.get(i)[CLOCK_IN_RECORD_ID];
							//Remove employee from the clocked in list after the employee has been found
							clockedInEmployees.remove(i);
							//Break after found so searching doesn't continue
							break;
						}
					}
					//Update record to clock employee out (this adds date on record)
					DBAction.clockOutEmployee(tempRecordId);
					textPin.setText("");
				
				}
				else{
					lblStatus.setText(fName + " " + lName + " successfully clocked in.");
					isClockedIn = true;
					//create time record in DB for employee
					int[] temp = DBAction.clockInEmployee(employeeList.get(comboEmployee.getSelectedIndex()));
					clockedInEmployees.add(temp);
					textPin.setText("");
				}
			}
			else{
				
				lblStatus.setText("INCORRECT PIN, PLEASE TRY AGAIN");
				textPin.setText("");
			}
		}
		else if (e.getSource() == buttonClear){
			textPin.setText("");
		}
		else{
			//Only allow 4 characters
			if (textPin.getText().length() <= 3){
				textPin.setText(textPin.getText() + e.getActionCommand());
				System.out.println(textPin.getText());
			}
			
			
			
			
		}
		

		
		
	}

	/**
	 * Listener for combo box
	 */
	public void itemStateChanged(ItemEvent arg0) {
		buttonClockInOut.setEnabled(true);
		buttonClear.setEnabled(true);
		btnKey0.setEnabled(true);
		btnKey1.setEnabled(true);
		btnKey2.setEnabled(true);
		btnKey3.setEnabled(true);
		btnKey4.setEnabled(true);
		btnKey5.setEnabled(true);
		btnKey6.setEnabled(true);
		btnKey7.setEnabled(true);
		btnKey8.setEnabled(true);
		btnKey9.setEnabled(true);
		
		
		String fName = employeeList.get(comboEmployee.getSelectedIndex()).getFirstName();
		String lName = employeeList.get(comboEmployee.getSelectedIndex()).getLastName();
		int empId = employeeList.get(comboEmployee.getSelectedIndex()).getEmpId();
		isClockedIn = false;
		
		for (int i = 0; i < clockedInEmployees.size(); i++){
			if (clockedInEmployees.get(i)[CLOCK_IN_EMP_ID] == empId){
				isClockedIn = true;
			}
		}
		
		if (isClockedIn){
			lblStatus.setText(fName + " " + lName + " is currently clocked in.");
		}
		else{
			lblStatus.setText(fName + " " + lName + " is not clocked in.");
		}
	}

	}
	




