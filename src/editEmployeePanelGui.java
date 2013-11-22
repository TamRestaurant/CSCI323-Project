

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Choice;

public class editEmployeePanelGui extends JPanel {
	private JTextField textEmployeeID;
	private JTextField textFName;
	private JTextField textLName;
	private JTextField textAddress;
	private JTextField textCity;
	private JTextField textState;
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
	private JButton btnNewButton;
	private JSpinner spinnerTerminate;
	private JComboBox comboBoxRole;
	private JSlider sliderActive;
	private JLabel lblInactive;
	private JSpinner spinnerHire;
	private dbAction DBAction;
	private ArrayList roleList;
	private Date dateIn = null, dateOut = null;
	
	/**
	 * Create the panel.
	 */
	public editEmployeePanelGui(String[] employeeInfo, dbAction DBAction) {
		
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
		
		textState = new JTextField();
		textState.setColumns(10);
		
		lblZip = new JLabel("Zip");
		lblZip.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textZip = new JTextField();
		textZip.setColumns(10);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		
		lblActive = new JLabel("Active");
		lblActive.setHorizontalAlignment(SwingConstants.RIGHT);
		
		sliderActive = new JSlider();
		sliderActive.setMaximum(1);
		sliderActive.setValue(0);
		sliderActive.setSnapToTicks(true);
		sliderActive.setPaintTicks(true);
		sliderActive.setPaintLabels(true);
		
		lblRole = new JLabel("Role");
		lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBoxRole = new JComboBox();
		
		lblHireDate = new JLabel("Hire Date");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblTerminationDate = new JLabel("Termination Date");
		lblTerminationDate.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerTerminate = new JSpinner();
		spinnerTerminate.setModel(new SpinnerDateModel(new Date(1385017200000L), null, null, Calendar.DAY_OF_YEAR));
		
		btnNewButton = new JButton("Submit Changed");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		lblInactive = new JLabel("Inactive");
		lblInactive.setHorizontalAlignment(SwingConstants.LEFT);
		
		spinnerHire = new JSpinner();
		spinnerHire.setModel(new SpinnerDateModel(new Date(1385017200000L), null, null, Calendar.DAY_OF_YEAR));
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
							.addComponent(textState, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
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
									.addComponent(lblActive, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(sliderActive, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblRole, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(comboBoxRole, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblHireDate, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerHire, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(lblTerminationDate)
							.addGap(7)
							.addComponent(spinnerTerminate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(193)
							.addComponent(btnNewButton)))
					.addGap(4)
					.addComponent(lblInactive, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(238))
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
						.addComponent(textState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(lblInactive)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblActive)
								.addComponent(sliderActive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblRole))
								.addComponent(comboBoxRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHireDate)
								.addComponent(spinnerHire, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblTerminationDate))
								.addComponent(spinnerTerminate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addComponent(btnNewButton)))
					.addGap(67))
		);
		setLayout(groupLayout);
		
		populateLabels(employeeInfo);

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
		textState.setText(employeeInfo[5]);
		textZip.setText(employeeInfo[6]);
		textPhone.setText(employeeInfo[7]);
		if (employeeInfo[8].equals("0")){
			sliderActive.setValue(0);
		}
		else{
			sliderActive.setValue(1);
		}
		
		try {
			dateIn = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(employeeInfo[9]);
			spinnerHire.setModel(new SpinnerDateModel(dateIn, null, null, Calendar.MINUTE));
			
			if (employeeInfo[10].length() > arbitrarySmallishNumber){ // This "MUST" mean that there is no termination date, right? <wink>, <wink>
				dateOut = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(employeeInfo[10]);
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
				comboBoxRole.add(new JLabel(resultSet.getString(1)));	
				//Keep track of roleID's in array list
				roleList.add(resultSet.getInt(2));
			}
		
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			
		}

	}//close populate method
	
	
	private void validateNewData(){
		
	}
	
	private void submitToDatabase(){
		
	}
	
	
}
