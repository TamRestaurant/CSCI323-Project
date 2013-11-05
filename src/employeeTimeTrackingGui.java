import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


/**
 * This class populates a table that shows all previous employee time entries
 * 
 * @author Austin
 *
 */

public class employeeTimeTrackingGui extends JPanel implements ActionListener {

	public dbAction DBAction;
	private JTable table;
	private JComboBox comboBox;
	private JLabel lblSelectTimePunch;
	private JButton btnEditRecord;
	private JButton btnPopulateTable;
	private JPanel panel;
	private ResultSet resultSet;
	private JScrollPane scrollPane;
	private DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
	private JButton btnCreateRecord;
	private editTimePunchGui timePanel;
	
	/**
	 * Create the panel.
	 */
	public employeeTimeTrackingGui(dbAction DBAction) {

		
		this.DBAction = DBAction;
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 11, 804, 464);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Edit Time Punch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 65, 163, 156);
		add(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox(comboModel);
		comboBox.setBounds(10, 40, 147, 20);
		panel.add(comboBox);
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		
		lblSelectTimePunch = new JLabel("Select time Punch ID");
		lblSelectTimePunch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTimePunch.setBounds(10, 17, 147, 14);
		panel.add(lblSelectTimePunch);
		lblSelectTimePunch.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		btnEditRecord = new JButton("Edit Record");
		btnEditRecord.addActionListener(this);
		btnEditRecord.setBounds(10, 69, 147, 33);
		panel.add(btnEditRecord);
		btnEditRecord.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		btnCreateRecord = new JButton("Create Record");
		btnCreateRecord.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCreateRecord.setBounds(10, 111, 147, 33);
		panel.add(btnCreateRecord);
		
		btnPopulateTable = new JButton("Populate Table");
		btnPopulateTable.addActionListener(this);
		btnPopulateTable.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnPopulateTable.setBounds(6, 11, 160, 50);
		add(btnPopulateTable);

	}
	

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent arg0) {
		
		//Populate table with records and update combo box with existing records
		if (arg0.getSource() == btnPopulateTable){
			//Get result set from DBAction
			resultSet = DBAction.getTimeRecords();
			ListTableModel model;
			Vector<String> timeID = new Vector();
			String currTimeEntryID;
			
			
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
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		//Popup message box allowing edit to be made to specific record
		else if (arg0.getSource() == btnEditRecord){
				timePanel = new editTimePunchGui("test", "5", "6", "test time", "test time", false);
				JDialog dialog = new JDialog();
				dialog.setContentPane(timePanel.getTimePunchPanel());
				dialog.setBounds(100, 100, 491, 318);
				dialog.setVisible(true);
			
		}
		
	}
}
