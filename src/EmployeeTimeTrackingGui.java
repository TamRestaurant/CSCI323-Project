import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


/**
 * This class populates a table that shows all previous employee time entries
 * 
 * @author Austin
 *
 */

public class EmployeeTimeTrackingGui extends JPanel implements ActionListener, ChangeListener {

	public DbAction DBAction;
	private JTable table;
	private JButton btnPopulateTable;
	private JPanel panelEdit;
	private ResultSet resultSet;
	private JScrollPane scrollPane;
	private DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
	private JButton btnCreateRecord;
	private EditTimePunchGui timePanel;
	private JButton btnEditSelected;
	private JLabel lbl_heading_emp_name;
	private JLabel lbl_heading_emp_id;
	private JLabel lnlNew_Label;
	private JLabel lbl_heading_in;
	private JLabel lbl_heading_out;
	private JLabel lblEmpID;
	private JLabel lblEmpName;
	private JLabel lblTimeEntryId;
	private JCheckBox chkEditIn;
	private JCheckBox chkEditOut;
	private JSpinner spinnerClockIn;
	private JSpinner spinnerClockOut;
	private JButton btnDeleteSelected;
	private JButton btnSubmit;
	private boolean saveClockOut = false, saveClockIn = false, clockOutNull = false;
	private JLabel lbl_Header_time_Worked;
	private JLabel lblTimeWorked;
	private JButton btnUpdate;
	private Date dateIn = null, dateOut = null;
	private JLabel lblError;
	private String hoursWorkedString = "";
	private Date origDateIn;
	private Date origDateOut;
	private JButton btnSaveReport;
	
	
	
	/**
	 * Create the panel.
	 */
	public EmployeeTimeTrackingGui(DbAction DBAction) {

		
		this.DBAction = DBAction;
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 11, 832, 350);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panelEdit = new JPanel();
		panelEdit.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Edit Time Punch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEdit.setBounds(3, 91, 163, 210);
		add(panelEdit);
		panelEdit.setLayout(null);
		
		
		
		btnCreateRecord = new JButton("Create Record");
		btnCreateRecord.addActionListener(this);
		btnCreateRecord.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCreateRecord.setBounds(10, 119, 147, 33);
		panelEdit.add(btnCreateRecord);
		
		btnDeleteSelected = new JButton("Delete Selected");
		btnDeleteSelected.addActionListener(this);
		btnDeleteSelected.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnDeleteSelected.setBounds(10, 75, 147, 33);
		panelEdit.add(btnDeleteSelected);
		
		btnEditSelected = new JButton("Edit Selected");
		btnEditSelected.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnEditSelected.setBounds(10, 31, 143, 33);
		panelEdit.add(btnEditSelected);
		btnEditSelected.addActionListener(this);
		
		btnPopulateTable = new JButton("Populate Table");
		btnPopulateTable.addActionListener(this);
		btnPopulateTable.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnPopulateTable.setBounds(6, 11, 160, 50);
		add(btnPopulateTable);
		
		lbl_heading_emp_name = new JLabel("Employee Name:");
		lbl_heading_emp_name.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_heading_emp_name.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_heading_emp_name.setBounds(180, 404, 118, 20);
		add(lbl_heading_emp_name);
		
		lbl_heading_emp_id = new JLabel("Employee ID:");
		lbl_heading_emp_id.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_heading_emp_id.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_heading_emp_id.setBounds(180, 373, 118, 20);
		add(lbl_heading_emp_id);
		
		lnlNew_Label = new JLabel("Time Entry ID:");
		lnlNew_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		lnlNew_Label.setFont(new Font("Calibri", Font.PLAIN, 16));
		lnlNew_Label.setBounds(180, 441, 118, 20);
		add(lnlNew_Label);
		
		lbl_heading_in = new JLabel("Clock-in Time:");
		lbl_heading_in.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_heading_in.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_heading_in.setBounds(454, 373, 111, 20);
		add(lbl_heading_in);
		
		lbl_heading_out = new JLabel("Clock-out Time:");
		lbl_heading_out.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_heading_out.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_heading_out.setBounds(454, 404, 111, 20);
		add(lbl_heading_out);
		
		lblEmpID = new JLabel("");
		lblEmpID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpID.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblEmpID.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblEmpID.setBounds(308, 376, 140, 20);
		add(lblEmpID);
		
		lblEmpName = new JLabel("");
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblEmpName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblEmpName.setBounds(308, 407, 140, 20);
		add(lblEmpName);
		
		lblTimeEntryId = new JLabel("");
		lblTimeEntryId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeEntryId.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTimeEntryId.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblTimeEntryId.setBounds(308, 444, 140, 20);
		add(lblTimeEntryId);
		
		chkEditIn = new JCheckBox("Edit Clock-In Time");
		chkEditIn.addChangeListener(this);
		chkEditIn.addActionListener(this);
		chkEditIn.setBounds(796, 372, 184, 23);
		add(chkEditIn);
		
		chkEditOut = new JCheckBox("Edit Clock-out Time");
		chkEditOut.addChangeListener(this);
		chkEditOut.addActionListener(this);
		chkEditOut.setBounds(796, 403, 184, 23);
		add(chkEditOut);
		
		spinnerClockIn = new JSpinner();
		spinnerClockIn.setModel(new SpinnerDateModel(new Date(1383894000000L), null, null, Calendar.MINUTE));
		spinnerClockIn.setBounds(575, 373, 205, 20);
		spinnerClockIn.setEnabled(false);
		add(spinnerClockIn);
		
		
		spinnerClockOut = new JSpinner();
		spinnerClockOut.setModel(new SpinnerDateModel(new Date(1383894000000L), null, null, Calendar.MINUTE));
		spinnerClockOut.setBounds(575, 404, 205, 20);
		spinnerClockOut.setEnabled(false);
		add(spinnerClockOut);
		
		
		btnSubmit = new JButton("Submit Changes");
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnSubmit.setBounds(575, 475, 205, 23);
		add(btnSubmit);
		
		lbl_Header_time_Worked = new JLabel("Time Worked");
		lbl_Header_time_Worked.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Header_time_Worked.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_Header_time_Worked.setBounds(454, 444, 111, 20);
		add(lbl_Header_time_Worked);
		
		lblTimeWorked = new JLabel("");
		lblTimeWorked.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeWorked.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTimeWorked.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblTimeWorked.setBounds(575, 444, 205, 20);
		add(lblTimeWorked);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnUpdate.setBounds(796, 440, 111, 23);
		add(btnUpdate);
		btnUpdate.setEnabled(false);
		
		lblError = new JLabel("");
		lblError.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblError.setBounds(190, 478, 375, 20);
		add(lblError);
		
		panelEdit.setEnabled(false);
		btnCreateRecord.setEnabled(false);
		btnDeleteSelected.setEnabled(false);
		btnEditSelected.setEnabled(false);
		
		btnSaveReport = new JButton("Save Report");
		btnSaveReport.addActionListener(this);
		btnSaveReport.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnSaveReport.setEnabled(false);
		btnSaveReport.setBounds(10, 163, 147, 33);
		panelEdit.add(btnSaveReport);
		btnSubmit.setEnabled(false);
		spinnerClockIn.setEnabled(false);
		spinnerClockOut.setEnabled(false);
		chkEditIn.setEnabled(false);
		chkEditOut.setEnabled(false);
	}
	
	private String getDateFromLong(Long l){
		
		return String.format("%02d:%02d:%02d", 
			    TimeUnit.MILLISECONDS.toHours(l),
			    TimeUnit.MILLISECONDS.toMinutes(l) - 
			    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(l)),
			    TimeUnit.MILLISECONDS.toSeconds(l) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
	}
	
	/**
	 * This method handles all the actions taking place
	 */
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent arg0) {
		
		/**
		 * This first block fires if the user wants to updated selected row
		 * It gets the values and sets labels and spinners for editing
		 */
		if (arg0.getSource() == btnEditSelected){
			if (table.getSelectedRow() < 0){
				lblError.setText("Please select a record");
			}
			else{
				lblError.setText("");
				btnSubmit.setEnabled(false);
				lblEmpID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				lblEmpName.setText(table.getValueAt(table.getSelectedRow(), 2).toString() + ", " + table.getValueAt(table.getSelectedRow(), 1).toString());
				lblTimeEntryId.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				//lblClockIn.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				//lblClockOut.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
				chkEditIn.setEnabled(true);
				chkEditOut.setEnabled(true);
				btnUpdate.setEnabled(true);
				
				try {
					
					dateIn = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(table.getValueAt(table.getSelectedRow(), 5).toString());
					spinnerClockIn.setModel(new SpinnerDateModel(dateIn, null, null, Calendar.MINUTE));
					
					//This really will throw exception instead of using if, but it was being dumb, oh well....
					if (!table.getValueAt(table.getSelectedRow(), 6).equals(null)){
						
						clockOutNull = false;
						dateOut = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(table.getValueAt(table.getSelectedRow(), 6).toString());
						spinnerClockOut.setModel(new SpinnerDateModel(dateOut, null, null, Calendar.MINUTE));
						//Get time in milli and to provide to user
						long hoursWorked = dateOut.getTime() - dateIn.getTime();
						lblTimeWorked.setText(getDateFromLong(hoursWorked));
						//Set global dates to see if values were changed when validating
						origDateIn = ((SpinnerDateModel)spinnerClockIn.getModel()).getDate();
						origDateOut = ((SpinnerDateModel)spinnerClockOut.getModel()).getDate();
					}
	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e){
					//I KNOW I SHOULD NOT USE TRY/CATCH FOR FLOW CONTROL, BUT I COULDN'T FIGURE OUT ANOTHER WAY TO TELL IF CELL WAS NULL!!! :(
					lblTimeWorked.setText("");
					spinnerClockOut.setEnabled(false);
					clockOutNull = true;
				}
			}

		} // End label fill if steatement
		
		//Populate table with records and update combo box with existing records
		else if (arg0.getSource() == btnPopulateTable){
			clearValues();
			//Get result set from DBAction
			resultSet = DBAction.getTimeRecords();
			ListTableModel model;
			Vector<String> timeID = new Vector();
			String currTimeEntryID;
			btnSaveReport.setEnabled(true);
			
			//Try to populate table with resultSet
			try {
				
				model = ListTableModel.createModelFromResultSet(resultSet);
				table.setModel(model);
				
				//Move cursor back to beginning and re-iterate to fill combobox
				resultSet.first();
				comboModel.removeAllElements();
				
				while (resultSet.next()){
					currTimeEntryID = resultSet.getString(5);
					comboModel.addElement(currTimeEntryID);
					
				}
				
				panelEdit.setEnabled(true);
				btnCreateRecord.setEnabled(true);
				btnDeleteSelected.setEnabled(true);
				btnEditSelected.setEnabled(true);


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		
		//Popup window allowing edits to be entered
		else if (arg0.getSource() == btnCreateRecord){

			
		}
		
		//Checkboxed control what is submitted to the DB
		else if (arg0.getSource() == chkEditIn){
			//Check if check box is checked and set global flag accordingly
			if (chkEditIn.isSelected()){
				saveClockIn = true;
			}
			else{
				saveClockIn = false;
			}
			
		}
		else if (arg0.getSource() == chkEditOut){
			if (chkEditOut.isSelected()){
				saveClockOut = true;
			}
			else{
				saveClockOut = false;
				// If clockout field it null and user does not want to edit the date, then disable the field

			}
		}
		
		else if(arg0.getSource() == btnSubmit){
			Date dateIn = ((SpinnerDateModel)spinnerClockIn.getModel()).getDate();
			Date dateOut = ((SpinnerDateModel)spinnerClockOut.getModel()).getDate();
			
			if ((chkEditIn.isSelected() == false) && (chkEditOut.isSelected() == false)){
				lblError.setText("Please select at least one time to change");
			}
			else if ((origDateIn.equals(dateIn)) &&(origDateOut.equals(dateOut))){
				lblError.setText("No time change was made.");
			}
			else{
				final int OK_OPTION = 0;
				if (OK_OPTION == JOptionPane.showConfirmDialog(this, "Are you sure you want to update this time entry?\n Clock in: "+ spinnerClockIn.getValue().toString()
						+ "\nClock Out: " + spinnerClockIn.getValue().toString()
						+ "\nTotal Time: " + lblTimeWorked.getText(), "Confirm Update Time Record Details", JOptionPane.OK_CANCEL_OPTION)){
					//TODO: update the database from this call.... Get entry ID and the time/date that is being updated and update record in the database.
					//After that, update the table and clear the fields and disable spinners and uncheck bockes
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					
					
					if (DBAction.updateTimeEntry(lblTimeEntryId.getText(), dateFormat.format(dateIn), dateFormat.format(dateOut))){
						lblError.setText("Your record was updated successfully. :D");
						btnPopulateTable.doClick();
					}
					else{
						lblError.setText("Your record was not updated. Check values.");
					}
					
					
					
				}
	
				
			}
		}
		
		
		//Popup confirming that delete wants to be completed (maybe just null values and keep records?????)
		else if (arg0.getSource() == btnDeleteSelected){
			
				if (table.getSelectedRow() < 0){
					lblError.setText("Please select a record");
				}
				else{
					lblTimeEntryId.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					final int OK_OPTION = 0;
					if (OK_OPTION == JOptionPane.showConfirmDialog(this, "CAUTION, THIS CANNOT BE UNDONE! Are you sure you want to DELETE this time entry # " + lblTimeEntryId.getText() +"?!?","!!Confirm Delete Record!!", JOptionPane.OK_CANCEL_OPTION)){
						if (DBAction.deleteTimeRecord(Integer.parseInt(lblTimeEntryId.getText()))){
							lblError.setText("Time punch successfully deleted.");
							btnPopulateTable.doClick();
						}
						else{
							lblError.setText("Time punch was not deleted.");
						}
					}
				}
		}
		
		else if(arg0.getSource() == btnUpdate){
			//Correct the hours worked label to updated date/time
			try{
				Date dateIn = ((SpinnerDateModel)spinnerClockIn.getModel()).getDate();
				Date dateOut = ((SpinnerDateModel)spinnerClockOut.getModel()).getDate();
				
				//dateIn = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				//dateOut = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(test1);
						//new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(spinnerClockIn.getModel().getValue().toString());
				//dateOut = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(spinnerClockOut.getModel().getValue().toString());

				long hoursWorked = dateOut.getTime() - dateIn.getTime();
				lblTimeWorked.setText(getDateFromLong(hoursWorked));
				lblError.setText("");
				btnSubmit.setEnabled(true);
				
			}
			catch (Exception e){
				lblError.setText("Sorry, could not update hours worked.  Are both date fields enabled?");
			}

			
		}
		
		else if(arg0.getSource() == btnSaveReport){
			if (WriteTableToFile.tableToFile(table, "timeEntryReport")){
				lblError.setText("File Creation Successful!");
			}
			else{
				lblError.setText("File Creation Failed!");
				}
		}
		
		
		
	}

	/**
	 * Clear values and reset to orig state to prep for next time entry
	 */
	private void clearValues() {
		
		lblEmpID.setText("");
		lblEmpName.setText("");
		lblTimeEntryId.setText("");
		lblTimeWorked.setText("");
		if (chkEditIn.isSelected()){
			chkEditIn.doClick();
		}
		if (chkEditOut.isSelected()){
			chkEditOut.doClick();
		}
		chkEditIn.setEnabled(false);
		chkEditOut.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnSubmit.setEnabled(false);
		
		
	}

	public void stateChanged(ChangeEvent arg0) {
		
			//Enable/disable spinner as button is checked
			spinnerClockIn.setEnabled(chkEditIn.isSelected());
			spinnerClockOut.setEnabled(chkEditOut.isSelected());

	}
}
