import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.sql.ResultSet;
import java.sql.SQLException;


/***
 * This class allows reports to be run on previous orders
 * 
 * @author Austin
 *
 */

public class orderHistory implements ActionListener {

	//private JFrame frame;
	private JPanel panelOrderHistory;
	private JTable tableOrderReports;
	private JScrollPane scrollPane;
	private JLabel lblOrderReports;
	private JCheckBox chckbxAllOrders;
	private JComboBox comboFromMonth;
	private JComboBox comboFromDay;
	private JComboBox comboFromYear;
	private JComboBox comboToMonth;
	private JComboBox comboToDay;
	private JComboBox comboToYear;
	private JPanel panelStartDate;
	private JPanel panelFromDate;
	private dateComboFill fillDate = new dateComboFill();
	final private int CURR_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	final private int CURR_MONTH = Calendar.getInstance().get(Calendar.MONTH);
	final private int CURR_DAY = Calendar.getInstance().get(Calendar.DATE);
	
	private int YearBusinessStarted = 2010; // This is used for the minimum start year
	private String[] years;
	private JCheckBox chckbxEmployeeID;
	private JCheckBox chckbxQtyOfEach;
	private JCheckBox chckbxItemDescription;
	private JCheckBox chckbxGroupByOrder;
	private JPanel panelReportOptions;
	private JButton btnSubmit;
	private JButton btnSaveReport;
	private dbAction myDBconnection;
	private JLabel lblErrorLabel;
	
	private ResultSet resultSet;

	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OrderHistory window = new OrderHistory();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public orderHistory(dbAction DBAction) {
		initialize(DBAction);
	}
	
	public JPanel getOrderHistory(){
		return panelOrderHistory;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(dbAction DBAction) {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 984, 612);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
		myDBconnection = DBAction;
		panelOrderHistory = new JPanel();
		panelOrderHistory.setBounds(0, 0, 968, 573);
//		frame.getContentPane().add(panel);
		panelOrderHistory.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(180, 18, 778, 521);
		panelOrderHistory.add(scrollPane);
		
		tableOrderReports = new JTable();
		tableOrderReports.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableOrderReports);
		
		lblOrderReports = new JLabel("Order Reports");
		lblOrderReports.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderReports.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblOrderReports.setBounds(10, 18, 164, 18);
		panelOrderHistory.add(lblOrderReports);
		
		chckbxAllOrders = new JCheckBox("All orders");
		chckbxAllOrders.addActionListener(this);
		chckbxAllOrders.setSelected(false);
		chckbxAllOrders.setBounds(6, 43, 112, 18);
		panelOrderHistory.add(chckbxAllOrders);
		
		panelStartDate = new JPanel();
		panelStartDate.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "From Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelStartDate.setBounds(6, 62, 168, 77);
		panelOrderHistory.add(panelStartDate);
		panelStartDate.setLayout(null);
		
		comboFromMonth = new JComboBox(new Object[]{});
		
		comboFromMonth.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboFromMonth.setBounds(6, 16, 156, 20);
		panelStartDate.add(comboFromMonth);
		comboFromMonth.setSelectedIndex(0);
		comboFromMonth.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		comboFromDay = new JComboBox(new Object[]{});
		comboFromDay.setBounds(6, 47, 63, 20);
		panelStartDate.add(comboFromDay);
		comboFromDay.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		comboFromYear = new JComboBox(new Object[]{});
		
		comboFromYear.setModel(new DefaultComboBoxModel(populateYear()));
		comboFromYear.setBounds(79, 47, 83, 23);
		panelStartDate.add(comboFromYear);
		comboFromYear.setSelectedIndex(0);
		comboFromYear.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		panelFromDate = new JPanel();
		panelFromDate.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "To Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFromDate.setBounds(6, 146, 168, 77);
		panelOrderHistory.add(panelFromDate);
		panelFromDate.setLayout(null);
		
		comboToMonth = new JComboBox(new Object[]{});
		
		comboToMonth.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboToMonth.setBounds(6, 16, 156, 20);
		panelFromDate.add(comboToMonth);
		comboToMonth.setSelectedIndex(0);
		comboToMonth.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		comboToDay = new JComboBox(new Object[]{});
		comboToDay.setBounds(6, 47, 63, 20);
		panelFromDate.add(comboToDay);
		comboToDay.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		comboToYear = new JComboBox(new Object[]{});
		
		comboToYear.setModel(new DefaultComboBoxModel(populateYear()));
		comboToYear.setBounds(79, 47, 83, 23);
		panelFromDate.add(comboToYear);
		comboToYear.setSelectedIndex(0);
		comboToYear.setFont(new Font("Calibri", Font.PLAIN, 14));
		//Populate days
		fillDate.updateDayCombo(comboFromMonth, comboFromDay, comboFromYear);
		fillDate.updateDayCombo(comboToMonth, comboToDay, comboToYear);
		
		
		//Add action listeners to month/year (after initial populated)
		comboFromYear.addActionListener(this);
		comboFromMonth.addActionListener(this);
		comboToYear.addActionListener(this);
		comboToMonth.addActionListener(this);
		comboToYear.setSelectedItem(Integer.toString(CURR_YEAR));
		comboToMonth.setSelectedIndex(CURR_MONTH);
		comboToDay.setSelectedIndex(CURR_DAY - 1);
		
		
		panelReportOptions = new JPanel();
		panelReportOptions.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Report Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelReportOptions.setBounds(4, 222, 176, 130);
		panelOrderHistory.add(panelReportOptions);
		panelReportOptions.setLayout(null);
		
		chckbxEmployeeID = new JCheckBox("Employee ID");
		chckbxEmployeeID.setBounds(6, 18, 164, 23);
		panelReportOptions.add(chckbxEmployeeID);
		
		chckbxQtyOfEach = new JCheckBox("QTY of Each Item Ordered");
		chckbxQtyOfEach.setEnabled(false);
		chckbxQtyOfEach.setBounds(6, 45, 164, 23);
		panelReportOptions.add(chckbxQtyOfEach);
		
		chckbxItemDescription = new JCheckBox("Item Description");
		chckbxItemDescription.setEnabled(false);
		chckbxItemDescription.setBounds(6, 72, 164, 23);
		panelReportOptions.add(chckbxItemDescription);
		
		chckbxGroupByOrder = new JCheckBox("Group By Order");
		chckbxGroupByOrder.setSelected(true);
		chckbxGroupByOrder.addActionListener(this);
		chckbxGroupByOrder.setBounds(6, 99, 164, 23);
		panelReportOptions.add(chckbxGroupByOrder);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setMnemonic('B');
		btnSubmit.addActionListener(this);
		btnSubmit.setBounds(10, 363, 160, 23);
		panelOrderHistory.add(btnSubmit);
		
		btnSaveReport = new JButton("Save Report to File");
		btnSaveReport.setMnemonic('S');
		btnSaveReport.setBounds(10, 397, 160, 23);
		panelOrderHistory.add(btnSaveReport);
		
		lblErrorLabel = new JLabel("");
		lblErrorLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblErrorLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblErrorLabel.setBounds(180, 546, 778, 18);
		panelOrderHistory.add(lblErrorLabel);
		

		
	}
	
	
	/***
	 * This method populates array for years (from started year to current year)
	 */
	private String[] populateYear(){
		int tempNumYears = CURR_YEAR - YearBusinessStarted;
		years = new String[tempNumYears+1];
		
		for (int i = 0; i <= tempNumYears; i++){
			years[i] = Integer.toString(YearBusinessStarted + i);
		}
		
		return years;
		
	}
	
	/***
	 * Method called to disable all date buttons
	 */
	
	private void updateDateEnabled(boolean isChecked){
		if (isChecked){
			comboFromDay.setEnabled(false);
			comboFromMonth.setEnabled(false);
			comboFromYear.setEnabled(false);
			comboToDay.setEnabled(false);
			comboToMonth.setEnabled(false);
			comboToYear.setEnabled(false);
		}
		else{
			comboFromDay.setEnabled(true);
			comboFromMonth.setEnabled(true);
			comboFromYear.setEnabled(true);
			comboToDay.setEnabled(true);
			comboToMonth.setEnabled(true);
			comboToYear.setEnabled(true);
		}
		
		
	}
	
	/***
	 * This method populates the table with the report based on the options selected
	 */
	private void populateReport(){
		
		int allDates = chckbxAllOrders.isSelected()? 1 : 0;
		int showEmpID = chckbxEmployeeID.isSelected()? 1 : 0;
		int showQTY = chckbxQtyOfEach.isSelected()? 1 : 0;
		int itemDescription = chckbxItemDescription.isSelected()? 1 : 0;
		int groupOrders = chckbxGroupByOrder.isSelected()? 1 : 0;
		String dateFrom = comboFromYear.getSelectedItem().toString() +"/" + Integer.toString((comboFromMonth.getSelectedIndex()) + 1) + "/" + comboFromDay.getSelectedItem().toString();
		String dateTo = comboToYear.getSelectedItem().toString() +"/" + Integer.toString((comboToMonth.getSelectedIndex()) + 1) + "/" + comboToDay.getSelectedItem().toString();
		
		if (allDates == 1){
			resultSet = myDBconnection.getOrderReport(allDates, showEmpID, showQTY, itemDescription, groupOrders);
		}
		else{
			resultSet = myDBconnection.getOrderReport(allDates, showEmpID, showQTY, itemDescription, groupOrders, dateFrom, dateTo);
		}
		

			
		try {
			//create model from db result and add to table
			ListTableModel model = ListTableModel.createModelFromResultSet(resultSet);
			tableOrderReports.setModel(model);

			lblErrorLabel.setForeground(Color.BLACK);
			lblErrorLabel.setText("Connection Successful :D");
			
	} catch (SQLException e) {
			// TODO ADD EXCEPTION MESSAGE HERE
		lblErrorLabel.setForeground(Color.RED);
		lblErrorLabel.setText("Could Not Connect:\n"+e);
			
		}
		
		
	}// end employee populate
	
	
	
	/***
	 * Event handler for all buttons on page
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		//If source is date combo box then update number of days to match month and year
		if ((e.getSource().equals(comboFromMonth)) || (e.getSource().equals(comboFromYear))){
			fillDate.updateDayCombo(comboFromMonth, comboFromDay, comboFromYear);
		}
		else if ((e.getSource().equals(comboToMonth)) || (e.getSource().equals(comboToYear))){
			fillDate.updateDayCombo(comboToMonth, comboToDay, comboToYear);
		}
		else if(e.getSource().equals(chckbxAllOrders)){
			//Enable/Disable date fields
			updateDateEnabled(chckbxAllOrders.isSelected());
		}
		//If group by option is selected, then disable non-relevant check boxes
		else if(e.getSource().equals(chckbxGroupByOrder)){
			if (chckbxGroupByOrder.isSelected()){
				chckbxItemDescription.setEnabled(false);
				chckbxQtyOfEach.setEnabled(false);
			}
			else{
				chckbxItemDescription.setEnabled(true);
				chckbxQtyOfEach.setEnabled(true);
			}
			
		}
		
		//BUTTONS SECTION
		
		else if(e.getSource().equals(btnSubmit)){
			
			populateReport();

		}

		
		else if(e.getSource().equals(btnSaveReport)){
			
		}
		
	}
}
