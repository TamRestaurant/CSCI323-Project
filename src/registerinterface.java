import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;

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
import javax.swing.SwingConstants;
import java.awt.Font;

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
	// private JTextField discountTextBox;
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
	private Vector<Order> openOrdersVector = new Vector<Order>();
	private JTextField discountTextBox = new JTextField();
	private JCheckBox discountCheckBox = new JCheckBox("Apply Discount");
	private int ordernum = 0;
	private JLabel label;
	private JLabel label_1;

	// private NumberFormat fmt=new NumberFormat();.getCurrencyInstance();
	/*
	 * Launch the application.
	 * 
	 * Because this is in its own application, the dbAction is required to be
	 * final
	 */
	public static void main(String[] args, final dbAction DBAct) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerinterface frame = new registerinterface(DBAct);
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
	public registerinterface(dbAction DBAct) {
		// Create instance of DBAction (database interface)
		DBAction = DBAct;
		// Get all open orders from the database so that menu can be populated
		openOrders = DBAction.getOpenOrders();
		// get vector of orders
		listToVector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(185, 0, 800, 767);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel lblNewLabel = new JLabel("Order");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		JPanel panel = new JPanel();
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(241, 266, 350, 305);
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
		orderComboBox = new JComboBox<Order>(openOrdersVector);
		orderComboBox.setFont(new Font("Calibri", Font.PLAIN, 12));
		orderComboBox.setMaximumRowCount(100);
		orderComboBox.setBounds(241, 67, 350, 20);
		contentPane.add(orderComboBox);
		// updateDropBox();
		applyPymtButton = new JButton("Apply Payment");
		applyPymtButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		applyPymtButton.setEnabled(false);

		applyPymtButton.addActionListener(new applyPaymentAction());

		applyPymtButton.setBounds(241, 594, 350, 23);
		contentPane.add(applyPymtButton);

		lblNewLabel.setBounds(366, 39, 100, 17);
		contentPane.add(lblNewLabel);
		lblNewLabel.setText("Select Order");

		lblEnterAmountTendered = new JLabel("Enter Amount tendered");
		lblEnterAmountTendered.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEnterAmountTendered.setBounds(241, 226, 138, 14);
		contentPane.add(lblEnterAmountTendered);

		amountTendTxt = new JTextField();
		amountTendTxt.setFont(new Font("Calibri", Font.PLAIN, 12));
		amountTendTxt.setText("0");
		amountTendTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				amountTendTxt.setText("");
				amtTend = true;
				amtTip = false;
				amtDisc = false;
			}
		});
		amountTendTxt.setBounds(418, 223, 173, 20);
		contentPane.add(amountTendTxt);
		amountTendTxt.setColumns(10);

		lblChange = new JLabel("Change");
		lblChange.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblChange.setBounds(319, 628, 80, 20);
		contentPane.add(lblChange);

		changelbl = new JLabel("$0");
		changelbl.setFont(new Font("Calibri", Font.PLAIN, 12));
		changelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		changelbl.setBounds(418, 631, 94, 14);
		contentPane.add(changelbl);

		closeOrderButton = new JButton("Close Order");
		closeOrderButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		closeOrderButton.setEnabled(false);
		closeOrderButton.addActionListener(new closeOrderAction());
		closeOrderButton.setBounds(241, 659, 350, 23);
		contentPane.add(closeOrderButton);

		// /////close button
		JButton closeBtn = new JButton("Close");
		closeBtn.setFont(new Font("Calibri", Font.PLAIN, 12));
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeBtn.setBounds(700, 11, 74, 23);
		contentPane.add(closeBtn);
		discountTextBox.setFont(new Font("Calibri", Font.PLAIN, 12));
		// / add discount checkbox and text field and action listener

		discountTextBox.setText("10");
		discountTextBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				discountTextBox.setText("");
				amtTend = false;
				amtTip = false;
				amtDisc = true;
			}
		});
		discountTextBox.setEnabled(false);
		discountTextBox.setBounds(418, 102, 173, 20);
		contentPane.add(discountTextBox);
		discountTextBox.setColumns(10);
		discountCheckBox.setFont(new Font("Calibri", Font.PLAIN, 12));

		discountCheckBox.addActionListener(new discountCheckBoxAction());
		discountCheckBox.setBounds(241, 98, 138, 23);
		contentPane.add(discountCheckBox);

		tipAmountTxtBox = new JTextField();
		tipAmountTxtBox.setFont(new Font("Calibri", Font.PLAIN, 12));
		tipAmountTxtBox.setText("0");
		tipAmountTxtBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tipAmountTxtBox.setText("");
				amtTend = false;
				amtTip = true;
				amtDisc = false;
			}
		});
		tipAmountTxtBox.setBounds(419, 133, 172, 20);
		contentPane.add(tipAmountTxtBox);
		tipAmountTxtBox.setColumns(10);

		lblEnterTipReceived = new JLabel("Enter Tip Received");
		lblEnterTipReceived.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEnterTipReceived.setBounds(241, 135, 151, 14);
		contentPane.add(lblEnterTipReceived);

		totalButon = new JButton("Calculate Total");
		totalButon.setFont(new Font("Calibri", Font.PLAIN, 12));
		totalButon.addActionListener(new calculateTotalAction());
		totalButon.setBounds(241, 164, 350, 23);
		contentPane.add(totalButon);

		totalLabel = new JLabel("Total Due");
		totalLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setBounds(241, 201, 138, 14);
		contentPane.add(totalLabel);

		totalLbl = new JLabel("$ 0");
		totalLbl.setFont(new Font("Calibri", Font.PLAIN, 12));
		totalLbl.setBounds(418, 201, 173, 14);
		contentPane.add(totalLbl);

		percentlbl = new JLabel("%");
		percentlbl.setFont(new Font("Calibri", Font.PLAIN, 12));
		percentlbl.setBounds(593, 105, 46, 14);
		contentPane.add(percentlbl);

		label = new JLabel("$");
		label.setFont(new Font("Calibri", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(394, 136, 18, 14);
		contentPane.add(label);

		label_1 = new JLabel("$");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_1.setBounds(394, 227, 18, 14);
		contentPane.add(label_1);

	}// close constructor

	// //create open order vector from openorder arraylist
	public void listToVector() {
		openOrdersVector.clear();// = new Vector<Order>();
		for (Order o : openOrders) {
			openOrdersVector.add(o);
		}
	}

	public class discountCheckBoxAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (discountCheckBox.isSelected()) {
				// discountTextBox.setVisible(true);
				discountTextBox.setEnabled(true);

			} else {
				// discountTextBox.setVisible(false);
				discountTextBox.setEnabled(false);
			}
		}

	}

	public class calculateTotalAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			// check if there is an order to pay
			if (openOrders.size() == 0) {
				JOptionPane.showMessageDialog(null,
						"There is no order to process.");
				// dispose();
				return;
			} else {
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
								"Enter numbers only in discount percentage field!");
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
					JOptionPane.showMessageDialog(null,
							"Enter numbers only in tip field!");
					return;
				}
				// set flag so that pay,ent can be applied
				totalCalculated = true;
				applyPymtButton.setEnabled(true);
				// display total
				totalLbl.setText(""
						+ NumberFormat.getCurrencyInstance().format(amountDue));
			}
		}
	}

	public class applyPaymentAction implements ActionListener {
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
					amountTendered = Double
							.parseDouble(amountTendTxt.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,
							"Enter numbers only into amount tendered field!");
					// e1.printStackTrace();
					return;
				}
				double diff = amountDue - amountTendered;
				double change = 0.0;
				// if payment is short
				// //get order #
				ordernum = openOrders.get(index).getOrderNumber();
				if (diff <= 0) {
					openOrders.get(index).setPaid(true);
					change = diff;
					openOrders.get(index).setTipPaid(tip);
					changelbl.setText(NumberFormat.getCurrencyInstance()
							.format(-change));
				} else if (diff > 0) {
					JOptionPane.showMessageDialog(null, "Payment is "
							+ NumberFormat.getCurrencyInstance().format(diff)
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
		}
	}// endevent

	public class closeOrderAction implements ActionListener {

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
					 * TODO: close order in database prior to removing it from
					 * the ArrayList This functionality does not exist yet, but
					 * we will need to send a String representation of the
					 * current date to the database in the following format:
					 * "YYYY-DD-MM hh:mm:ss" Along with the order number (also
					 * in string format) Below will probably be the method that
					 * will be created DBAction.closeOpenOrder(int orderNumber,
					 * String date)
					 */
					// this is not yet implemented... wait for austin
					closeOrder();
					// this needs to be removed when above method is implemented
					// listToVector();
					// updateDropBox();
					// ////////////////
					// dispose();
					// changelbl.setText(NumberFormat.getCurrencyInstance()
					// .format(0));
					// orderComboBox.revalidate();
					// orderComboBox.repaint();
				} else {
					JOptionPane.showMessageDialog(null,
							"Order must be paid before it can be closed.");
					// dispose();
					return;
				}
				applyPymtButton.setEnabled(false);
				closeOrderButton.setEnabled(false);
			}
		}
	}

	public void closeOrder() {
		// index = orderComboBox.getSelectedIndex();
		// int ordernum=openOrders.get(index).getOrderNumber();
		openOrders.remove(index);
		openOrdersVector.remove(index);
		// orderComboBox.remove(index);
		orderComboBox.validate();
		// for(int i=0;i<openOrders.size();i++) {
		// System.out.println("list    "+openOrders.get(i)+" \n  vector  "+openOrdersVector.get(i)+"\n    combo   "+orderComboBox.getItemAt(i)+"\n");
		// }
		//
		/*
		 * this can be method that will close order and send it to the closed
		 * order part of the database. when that is implemented, the method
		 * updateDropBox() should be called to update the openOrder array list
		 * and repopulate the combo box
		 */
		JOptionPane.showMessageDialog(null, "Order number " + ordernum
				+ " has been closed successfully.\nSelect a new order to process.");
	}

	// public void updateDropBox() {

	// openOrders = DBAction.getOpenOrders();
	// listToVector();
	// orderComboBox = new JComboBox<Order>(openOrdersVector);

	// orderComboBox.setMaximumRowCount(100);
	// orderComboBox.setBounds(242, 102, 350, 20);
	// contentPane.add(orderComboBox);

	// }

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
