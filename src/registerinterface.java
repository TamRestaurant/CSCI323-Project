import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class registerinterface extends JFrame {

	private JPanel contentPane;
	private JTextField amountTendTxt;
	@SuppressWarnings("rawtypes")
	private JComboBox orderComboBox;
	private ArrayList<Order> openOrders;
	private JButton applyPymtButton;
	private JLabel lblEnterAmountTendered;
	private JLabel lblChange, changelbl;
	private JButton[] mb;
	private int index = 0;
	private dbAction DBAction;
	private JTextField discountTextBox;
	private JTextField tipAmountTxtBox;
	private JLabel lblEnterTipReceived;
	// boooleans to aid keypad in entering correct textbox
	private boolean amtTend = false;
	private boolean amtTip = false;
	private boolean amtDisc = false;
	private boolean totalCalculated = false;
	private JButton totalButon;
	private JLabel totalLabel;
	private JLabel totalLbl;
	private double amountDue = 0;
	private double amountTendered = 0;
	private double tip;
	double discountPercent = 0.0;
	private JButton closeOrderButton;
	private JLabel percentlbl;

	// private NumberFormat fmt=new NumberFormat();.getCurrencyInstance();
	/*
	 * Launch the application.
	 * 
	 * Because this is in its own application, the dbAction is required to be
	 * final
	 */
	public static void main(String[] args, final dbAction DBAction) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerinterface frame = new registerinterface(DBAction);
					frame.setVisible(true);
					// frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param dbAction
	 */
	public registerinterface(dbAction DBAction) {
		// Create instance of DBAction (database interface)
		this.DBAction = DBAction;
		// Get all open orders from the database so that menu can be populated

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(225, 0, 850, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel lblNewLabel = new JLabel("Order");
		JPanel panel = new JPanel();
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(133, 313, 192, 258);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(4, 3, 0, 0));
		mb = new JButton[12];

		// /create keypad buttons
		for (int i = 1; i < mb.length + 1; i++) {
			mb[i - 1] = new JButton("" + i);
			switch (i) {
			case 10:
				mb[i - 1] = new JButton(".");
				break;
			case 11:
				mb[i - 1] = new JButton("0");
				break;
			case 12:
				mb[i - 1] = new JButton("clr");
				break;
			}
			panel_1.add(mb[i - 1]);
			mb[i - 1].addActionListener(new buttonListener());
		}

		// JTextArea list = new JTextArea();
		// list.setBounds(15, 100, 170, 695);
		// list.setEditable(false);
		// JList<Order> list = new JList<Order>();

		// orders = new ArrayList<Order>();
		//
		// for (int i = 0; i < 9; i++) {
		// Order o = new Order();
		// o.setOrderNumber(i * 325);
		// o.setOrderTotal(i + 11);
		// orders.add(o);
		// }

		updateDropBox();
		applyPymtButton = new JButton("Apply Payment");
		applyPymtButton.setEnabled(false);

		applyPymtButton.addActionListener(new ActionListener() {
			// //////accept payment
			public void actionPerformed(ActionEvent e) {
				// check if there is an order to pay

				if (openOrders.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"There is no order to process.");
					// dispose();
					return;
				}
				// /make sure total has been calculated

				if (totalCalculated) {
					index = orderComboBox.getSelectedIndex();
					// get amount to apply
					try {
						amountTendered = Double.parseDouble(amountTendTxt
								.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,
								"Enter numbers only! Try again.");
						// e1.printStackTrace();
						return;
					}
					double diff = amountDue - amountTendered;
					double change = 0.0;
					// if payment is short
					if (diff <= 0) {
						openOrders.get(index).setPaid(true);
						change = diff;
						openOrders.get(index).setTipPaid(tip);
						changelbl.setText(NumberFormat.getCurrencyInstance()
								.format(-change));
					} else if (diff > 0) {
						JOptionPane.showMessageDialog(null,
								"Payment is "
										+ NumberFormat.getCurrencyInstance()
												.format(diff)
										+ " short! Try again.");
					}
					// reset flag for total calculation
					totalCalculated = false;
					applyPymtButton.setEnabled(false);
					closeOrderButton.setEnabled(true);
					// closeOrderButton.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null,
							"Please Calculate Total First.");
				}
			}// endevent
		});
		applyPymtButton.setBounds(133, 582, 192, 23);
		contentPane.add(applyPymtButton);

		lblNewLabel.setBounds(185, 38, 100, 17);
		contentPane.add(lblNewLabel);
		lblNewLabel.setText("Select Order");

		lblEnterAmountTendered = new JLabel("Enter Amount tendered");
		lblEnterAmountTendered.setBounds(125, 225, 200, 14);
		contentPane.add(lblEnterAmountTendered);

		amountTendTxt = new JTextField();
		amountTendTxt.setText("0");
		amountTendTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				amtTend = true;
				amtTip = false;
				amtDisc = false;
			}
		});
		amountTendTxt.setBounds(125, 250, 117, 20);
		contentPane.add(amountTendTxt);
		amountTendTxt.setColumns(10);

		lblChange = new JLabel("Change");
		lblChange.setBounds(155, 624, 80, 20);
		contentPane.add(lblChange);

		changelbl = new JLabel("$0");
		changelbl.setBounds(268, 628, 46, 14);
		contentPane.add(changelbl);

		closeOrderButton = new JButton("Close Order");
		closeOrderButton.setEnabled(false);
		closeOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check if there is an order to close
				if (openOrders.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"There is no order to process.");
					// dispose();
					return;
				} else {
					index = orderComboBox.getSelectedIndex();
					boolean paid = openOrders.get(index).isPaid();

					// if order is paid then we can close order and send to db
					// etc
					if (paid) {
						// ////////reset combo box etc
						// orderComboBox.remove(index);

						/**
						 * TODO: close order in database prior to removing it
						 * from the ArrayList This functionality does not exist
						 * yet, but we will need to send a String representation
						 * of the current date to the database in the following
						 * format: "YYYY-DD-MM hh:mm:ss" Along with the order
						 * number (also in string format) Below will probably be
						 * the method that will be created
						 * DBAction.closeOpenOrder(int orderNumber, String date)
						 */
						//this is not yet implemented... wait for austin 
						closeOrder();
						//this needs to be removed when above method is implemented
						openOrders.remove(index);
						// ////////////////
						dispose();
						// changelbl.setText(NumberFormat.getCurrencyInstance()
						// .format(0));
						// orderComboBox.revalidate();
						// orderComboBox.repaint();
					} else {
						JOptionPane.showMessageDialog(null,
								"Order must be paid before it can be closed.");
						//dispose();
						return;
					}

				}
			}
		});
		closeOrderButton.setBounds(166, 667, 117, 23);
		contentPane.add(closeOrderButton);

		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		closeBtn.setBounds(735, 0, 89, 23);
		contentPane.add(closeBtn);
		// / add discount checkbox and text field and action listener
		discountTextBox = new JTextField();
		discountTextBox.setText("10");
		discountTextBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				amtTend = false;
				amtTip = false;
				amtDisc = true;
			}
		});
		discountTextBox.setEnabled(false);
		discountTextBox.setBounds(227, 97, 173, 20);
		contentPane.add(discountTextBox);
		discountTextBox.setColumns(10);
		final JCheckBox discountCheckBox = new JCheckBox("Apply Discount");
		discountCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (discountCheckBox.isSelected()) {
					// discountTextBox.setVisible(true);
					discountTextBox.setEnabled(true);

				} else {
					// discountTextBox.setVisible(false);
					discountTextBox.setEnabled(false);
				}
			}
		});
		discountCheckBox.setBounds(50, 93, 138, 23);
		contentPane.add(discountCheckBox);

		tipAmountTxtBox = new JTextField();
		tipAmountTxtBox.setText("0");
		tipAmountTxtBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				amtTend = false;
				amtTip = true;
				amtDisc = false;
			}
		});
		tipAmountTxtBox.setBounds(228, 128, 172, 20);
		contentPane.add(tipAmountTxtBox);
		tipAmountTxtBox.setColumns(10);

		lblEnterTipReceived = new JLabel("Enter Tip Received");
		lblEnterTipReceived.setBounds(50, 131, 158, 14);
		contentPane.add(lblEnterTipReceived);

		totalButon = new JButton("Calculate Total");
		totalButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// check if there is an order to pay
				if (openOrders.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"There is no order to process.");
					// dispose();
					return;
				}
				index = orderComboBox.getSelectedIndex();
				amountDue = openOrders.get(index).getOrderTotal();

				// if there is a discount, try to parse the value
				// also add tip amount to order and total
				if (discountCheckBox.isSelected()) {

					try {
						discountPercent = (Double.parseDouble(discountTextBox
								.getText())) / 100;
						amountDue = (1 - discountPercent) * amountDue;

						// openOrders.get(index).
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,
								"Enter numbers only! Try again.");
						// e1.printStackTrace();
						return;
					}
				}// endif
					// get tip and add to total
				try {
					tip = Double.parseDouble(tipAmountTxtBox.getText());
					amountDue += tip;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set flag so that pay,ent can be applied
				totalCalculated = true;
				applyPymtButton.setEnabled(true);
				// display total
				totalLbl.setText(""
						+ NumberFormat.getCurrencyInstance().format(amountDue));
			}
		});
		totalButon.setBounds(50, 169, 158, 23);
		contentPane.add(totalButon);

		totalLabel = new JLabel("Total");
		totalLabel.setBounds(227, 173, 46, 14);
		contentPane.add(totalLabel);

		totalLbl = new JLabel("");
		totalLbl.setBounds(342, 173, 46, 14);
		contentPane.add(totalLbl);

		percentlbl = new JLabel("%");
		percentlbl.setBounds(402, 100, 46, 14);
		contentPane.add(percentlbl);

	}// close constructor

	public void closeOrder() {

		/*
		 * this can be method that will close order and send it to the closed
		 * order part of the database. when that is implemented, the method
		 * updateDropBox() should be called to update the openOrder array list
		 * and repopulate the combo box
		 */

	}

	public void updateDropBox() {

		openOrders = DBAction.getOpenOrders();
		String[] itm = new String[openOrders.size()];
		// line;
		for (int i = 0; i < openOrders.size(); i++) {
			String line = "ServerID: "
					+ openOrders.get(i).getEmpID()
					+ "      Order number:  "
					+ openOrders.get(i).getOrderNumber()
					+ "      SubTotal:   "
					+ NumberFormat.getCurrencyInstance().format(
							openOrders.get(i).getOrderTotal()) + "";
			// comboBox.add(orders.get(i));
			itm[i] = line;
		}
		orderComboBox = new JComboBox(itm);

		orderComboBox.setMaximumRowCount(100);
		orderComboBox.setBounds(50, 66, 350, 20);
		contentPane.add(orderComboBox);

	}

	// public void pullThePlug() {
	// WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	// Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	// }

	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (amtTend) { // enter into amt tendered

				if (event.getSource() == mb[0]) {
					amountTendTxt.setText(amountTendTxt.getText() + "1");
				} else if (event.getSource() == mb[1]) {
					amountTendTxt.setText(amountTendTxt.getText() + "2");
				} else if (event.getSource() == mb[2]) {
					amountTendTxt.setText(amountTendTxt.getText() + "3");
				} else if (event.getSource() == mb[3]) {
					amountTendTxt.setText(amountTendTxt.getText() + "4");
				} else if (event.getSource() == mb[4]) {
					amountTendTxt.setText(amountTendTxt.getText() + "5");
				} else if (event.getSource() == mb[5]) {
					amountTendTxt.setText(amountTendTxt.getText() + "6");
				} else if (event.getSource() == mb[6]) {
					amountTendTxt.setText(amountTendTxt.getText() + "7");
				} else if (event.getSource() == mb[7]) {
					amountTendTxt.setText(amountTendTxt.getText() + "8");
				} else if (event.getSource() == mb[8]) {
					amountTendTxt.setText(amountTendTxt.getText() + "9");
				} else if (event.getSource() == mb[9]) {
					amountTendTxt.setText(amountTendTxt.getText() + ".");
				} else if (event.getSource() == mb[10]) {
					amountTendTxt.setText(amountTendTxt.getText() + "0");
				} else if (event.getSource() == mb[11]) {
					amountTendTxt.setText("");
				}

			}// end amt tendtxt
			else if (amtTip) { // enter into tip box
				if (event.getSource() == mb[0]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "1");
				} else if (event.getSource() == mb[1]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "2");
				} else if (event.getSource() == mb[2]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "3");
				} else if (event.getSource() == mb[3]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "4");
				} else if (event.getSource() == mb[4]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "5");
				} else if (event.getSource() == mb[5]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "6");
				} else if (event.getSource() == mb[6]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "7");
				} else if (event.getSource() == mb[7]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "8");
				} else if (event.getSource() == mb[8]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "9");
				} else if (event.getSource() == mb[9]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + ".");
				} else if (event.getSource() == mb[10]) {
					tipAmountTxtBox.setText(tipAmountTxtBox.getText() + "0");
				} else if (event.getSource() == mb[11]) {
					tipAmountTxtBox.setText("");
				}

			}// end tip box
			else if (amtDisc) {
				if (event.getSource() == mb[0]) {
					discountTextBox.setText(discountTextBox.getText() + "1");
				} else if (event.getSource() == mb[1]) {
					discountTextBox.setText(discountTextBox.getText() + "2");
				} else if (event.getSource() == mb[2]) {
					discountTextBox.setText(discountTextBox.getText() + "3");
				} else if (event.getSource() == mb[3]) {
					discountTextBox.setText(discountTextBox.getText() + "4");
				} else if (event.getSource() == mb[4]) {
					discountTextBox.setText(discountTextBox.getText() + "5");
				} else if (event.getSource() == mb[5]) {
					discountTextBox.setText(discountTextBox.getText() + "6");
				} else if (event.getSource() == mb[6]) {
					discountTextBox.setText(discountTextBox.getText() + "7");
				} else if (event.getSource() == mb[7]) {
					discountTextBox.setText(discountTextBox.getText() + "8");
				} else if (event.getSource() == mb[8]) {
					discountTextBox.setText(discountTextBox.getText() + "9");
				} else if (event.getSource() == mb[9]) {
					discountTextBox.setText(discountTextBox.getText() + ".");
				} else if (event.getSource() == mb[10]) {
					discountTextBox.setText(discountTextBox.getText() + "0");
				} else if (event.getSource() == mb[11]) {
					discountTextBox.setText("");
				}

			}
			// What ever button does
		}
	}
}
