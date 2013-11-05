import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;


public class editTimePunchGui extends JPanel implements ActionListener {
	private JCheckBox chkChangeClockIn;
	private JCheckBox chkChangeClockOut;
	private JButton btnSubmit;
	private JLabel lbl1;
	private JLabel lbl4;
	private JLabel lbl5;
	private JLabel lbl3;
	private JLabel lbl2;
	private JTextField lblClockOut;
	private JTextField lblClockIn;
	private JTextField lblEntryID;
	private JTextField lblEmpId;
	private JComboBox lblEmpName;
	private JButton btnErase;
	private JCheckBox chkEraseEntry;
	private JComboBox comboClockInMonth;
	private JComboBox comboClockInDay;
	private JComboBox comboClockInYear;
	private JComboBox comboClockOutMonth;
	private JComboBox comboClockOutDay;
	private JComboBox comboClockOutYear;
	private DefaultComboBoxModel nameCombo = new DefaultComboBoxModel();
	private dateComboFill fillDate = new dateComboFill();
	private boolean addNewRecord;
	private JButton btnCancel;
	private JPanel contentPane;
	final private int CURR_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	final private int CURR_MONTH = Calendar.getInstance().get(Calendar.MONTH);
	final private int CURR_DAY = Calendar.getInstance().get(Calendar.DATE);
	
	private int YearBusinessStarted = 2010; // This is used for the minimum start year
	private String[] years;
	private JTextField textField;
	private JLabel lblTime;
	private JLabel label;
	private JFormattedTextField textField_1;
	
	
	
	
	
	/**
	 * Create the panel.
	 */
	public editTimePunchGui(String name, String empId, String entryId, String clockInDate, String clockOutDate, boolean addNewRecord) {
		//setTitle("Employee Time Entry Modification");
		//editTimePunch frame = new editTimePunch();
		//frame.setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 318);
		contentPane = new JPanel();
		//setContentPane(contentPane);
		
		
		//contentPane.setLayout(null);
		this.addNewRecord = addNewRecord;
		
		// <!-- INITIALIZE ALL COMPONENTS AND GUI ELEMENTS --!>
		chkChangeClockIn = new JCheckBox("Change clock-in time");
		chkChangeClockIn.setFont(new Font("Calibri", Font.PLAIN, 14));
		chkChangeClockIn.setHorizontalAlignment(SwingConstants.CENTER);
		chkChangeClockIn.addActionListener(this);
		contentPane.setLayout(null);
		chkChangeClockIn.setBounds(304, 7, 159, 23);
		contentPane.add(chkChangeClockIn);
		
		chkChangeClockOut = new JCheckBox("Change clock-out time");
		chkChangeClockOut.setFont(new Font("Calibri", Font.PLAIN, 14));
		chkChangeClockOut.setHorizontalAlignment(SwingConstants.CENTER);
		chkChangeClockOut.addActionListener(this);
		chkChangeClockOut.setBounds(304, 141, 159, 23);
		contentPane.add(chkChangeClockOut);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnSubmit.setBounds(129, 193, 153, 40);
		contentPane.add(btnSubmit);
		
		lbl1 = new JLabel("Employee Name:");
		lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl1.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl1.setBounds(10, 11, 109, 20);
		contentPane.add(lbl1);
		
		lbl4 = new JLabel("Clock-in Time:");
		lbl4.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl4.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl4.setBounds(35, 110, 92, 20);
		contentPane.add(lbl4);
		
		lbl5 = new JLabel("Clock-out Time:");
		lbl5.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl5.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl5.setBounds(26, 141, 101, 20);
		contentPane.add(lbl5);
		
		lbl3 = new JLabel("Entree ID:");
		lbl3.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl3.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl3.setBounds(63, 79, 64, 20);
		contentPane.add(lbl3);
		
		lbl2 = new JLabel("EmployeeID:");
		lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl2.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl2.setBounds(46, 48, 81, 20);
		contentPane.add(lbl2);
		
		lblClockOut = new JTextField(clockOutDate);
		lblClockOut.setEditable(false);
		lblClockOut.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		lblClockOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblClockOut.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblClockOut.setBounds(129, 141, 153, 24);
		contentPane.add(lblClockOut);
		
		lblClockIn = new JTextField(clockInDate);
		lblClockIn.setEditable(false);
		lblClockIn.setHorizontalAlignment(SwingConstants.LEFT);
		lblClockIn.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblClockIn.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		lblClockIn.setBounds(129, 108, 153, 24);
		contentPane.add(lblClockIn);
		
		lblEntryID = new JTextField(entryId);
		lblEntryID.setEditable(false);
		lblEntryID.setHorizontalAlignment(SwingConstants.LEFT);
		lblEntryID.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblEntryID.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		lblEntryID.setBounds(129, 77, 153, 24);
		contentPane.add(lblEntryID);
		
		lblEmpId = new JTextField(empId);
		lblEmpId.setEditable(false);
		lblEmpId.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpId.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblEmpId.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		lblEmpId.setBounds(129, 46, 154, 24);
		contentPane.add(lblEmpId);
		
		
		if (!addNewRecord){
			nameCombo.addElement(name);
		}
		else{
			//TODO: method call here to populate nameCombo (or just put functionality here)
		}
		lblEmpName = new JComboBox(nameCombo);
		lblEmpName.setEditable(false);
		lblEmpName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblEmpName.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		lblEmpName.setBounds(129, 7, 153, 28);
		contentPane.add(lblEmpName);
		
		btnErase = new JButton("Erase Entry");
		btnErase.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnErase.addActionListener(this);
		btnErase.setEnabled(false);
		btnErase.setBounds(129, 244, 153, 23);
		contentPane.add(btnErase);
		
		chkEraseEntry = new JCheckBox("Erase Entry");
		chkEraseEntry.setFont(new Font("Calibri", Font.PLAIN, 16));
		chkEraseEntry.setHorizontalAlignment(SwingConstants.CENTER);
		chkEraseEntry.addActionListener(this);
		chkEraseEntry.setForeground(Color.RED);
		chkEraseEntry.setBounds(26, 246, 101, 23);
		contentPane.add(chkEraseEntry);
		
		comboClockInMonth = new JComboBox(new Object[]{});
		comboClockInMonth.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		
		comboClockInMonth.setSelectedIndex(0);
		comboClockInMonth.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboClockInMonth.setBounds(304, 37, 159, 23);
		contentPane.add(comboClockInMonth);
		
		comboClockInDay = new JComboBox(new Object[]{});
		
		comboClockInDay.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboClockInDay.setBounds(304, 69, 68, 23);
		contentPane.add(comboClockInDay);
		
		comboClockInYear = new JComboBox(populateYear());
		
		comboClockInYear.setSelectedIndex(0);
		comboClockInYear.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboClockInYear.setBounds(382, 69, 81, 23);
		contentPane.add(comboClockInYear);
		
		comboClockOutMonth = new JComboBox(new Object[]{});
		comboClockOutMonth.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		
		comboClockOutMonth.setSelectedIndex(0);
		comboClockOutMonth.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboClockOutMonth.setBounds(303, 171, 160, 23);
		contentPane.add(comboClockOutMonth);
		
		comboClockOutDay = new JComboBox(new Object[]{});
		
		comboClockOutDay.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboClockOutDay.setBounds(306, 205, 66, 23);
		contentPane.add(comboClockOutDay);
		
		comboClockOutYear = new JComboBox(populateYear());
		
		comboClockOutYear.setSelectedIndex(0);
		comboClockOutYear.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboClockOutYear.setBounds(382, 205, 81, 23);
		contentPane.add(comboClockOutYear);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancel.setBounds(16, 193, 109, 40);
		contentPane.add(btnCancel);
		
		fillDate.updateDayCombo(comboClockOutMonth, comboClockOutDay, comboClockOutYear);
		fillDate.updateDayCombo(comboClockInMonth, comboClockInDay, comboClockInYear);

		//Add action listeners to month/year (after initial populated)

		comboClockInYear.setSelectedItem(Integer.toString(CURR_YEAR));
		comboClockInMonth.setSelectedIndex(CURR_MONTH);
		comboClockInDay.setSelectedIndex(CURR_DAY - 1);
		
		comboClockOutYear.setSelectedItem(Integer.toString(CURR_YEAR));
		comboClockOutMonth.setSelectedIndex(CURR_MONTH);
		comboClockOutDay.setSelectedIndex(CURR_DAY - 1);
		
		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField.setBounds(371, 236, 92, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblTime = new JLabel("Time:");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTime.setBounds(314, 240, 46, 14);
		contentPane.add(lblTime);
		
		label = new JLabel("Time:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		label.setBounds(314, 104, 46, 14);
		contentPane.add(label);
		
		textField_1 = new JFormattedTextField();
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(371, 100, 92, 20);
		contentPane.add(textField_1);
		
		comboClockInMonth.addActionListener(this);
		comboClockInDay.addActionListener(this);
		comboClockInYear.addActionListener(this);
		comboClockOutMonth.addActionListener(this);
		comboClockOutDay.addActionListener(this);
		comboClockOutYear.addActionListener(this);
		
		// <!-- Fill all labels and setup all actions for form to work -->
		

	}
	
	public JPanel getTimePunchPanel(){
		return contentPane;
	}


	//Check if checkbox was erased
	@Override
	public void actionPerformed(ActionEvent e) {

		//If source is date combo box then update number of days to match month and year
		if ((e.getSource().equals(comboClockInMonth)) || (e.getSource().equals(comboClockInYear))){
			fillDate.updateDayCombo(comboClockInMonth, comboClockInDay, comboClockInYear);
		}
		else if ((e.getSource().equals(comboClockOutMonth)) || (e.getSource().equals(comboClockOutYear))){
			fillDate.updateDayCombo(comboClockOutMonth, comboClockOutDay, comboClockOutYear);
		}
		
		//If adding record these options are not applicable
		if (!addNewRecord){
			if (e.getSource() == chkEraseEntry){
				if (chkEraseEntry.isSelected()){
					btnErase.setEnabled(true);
				}
				else{
					btnErase.setEnabled(false);
				}
			}
			else if (e.getSource() == chkChangeClockIn){
				
			}
			else if (e.getSource() == chkChangeClockOut){
				
			}
		}
		

		
		
		
		
	} // CLOSE ACTION LISTENER
	
	
	private String[] populateYear(){
		int tempNumYears = CURR_YEAR - YearBusinessStarted;
		years = new String[tempNumYears+1];
		
		for (int i = 0; i <= tempNumYears; i++){
			years[i] = Integer.toString(YearBusinessStarted + i);
		}
		
		return years;
		
	}
}
