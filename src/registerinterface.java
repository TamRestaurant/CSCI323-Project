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

public class registerinterface extends JFrame {

	private JPanel contentPane;
	private JTextField amountTendTxt;
	@SuppressWarnings("rawtypes")
	private JComboBox orderComboBox;
	private ArrayList<Order> orders;
	private JButton btnNewButton;
	private JLabel lblEnterAmountTendered;
	private JLabel lblChange, changelbl;
	private JButton[] mb;
	private int index = 0;

	// private NumberFormat fmt=new NumberFormat();.getCurrencyInstance();
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerinterface frame = new registerinterface();
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
	 */
	public registerinterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(225, 0, 850, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel lblNewLabel = new JLabel("Order");
		JPanel panel = new JPanel();
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(320, 197, 192, 258);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(4, 3, 0, 0));
		mb = new JButton[12];
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

		// ArrayList<Order> orders=menu.getOrders();
		orders = menu.getOrders();
		// orders = new ArrayList<Order>();
		//
		// for (int i = 0; i < 9; i++) {
		// Order o = new Order();
		// o.setOrderNumber(i * 325);
		// o.setOrderTotal(i + 11);
		// orders.add(o);
		// }
		String[] itm = new String[orders.size()];
		// line;
		for (int i = 0; i < orders.size(); i++) {
			String line = "        Order number:  "
					+ orders.get(i).getOrderNumber()
					+ "      Total Due:   "
					+ NumberFormat.getCurrencyInstance().format(
							orders.get(i).getOrderTotal()) + "";
			// comboBox.add(orders.get(i));
			itm[i] = line;
		}
		orderComboBox = new JComboBox(itm);

		orderComboBox.setMaximumRowCount(100);
		orderComboBox.setBounds(258, 104, 300, 20);
		contentPane.add(orderComboBox);

		btnNewButton = new JButton("Apply Payment");

		btnNewButton.addActionListener(new ActionListener() {
			// //////accept payment
			public void actionPerformed(ActionEvent e) {
				// check if there is an order to pay
				if (orders.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"There is no order to process.");
					dispose();
					return;
				}
				index = orderComboBox.getSelectedIndex();
				double amountDue = orders.get(index).getOrderTotal();
				double amountTendered = 0;
				try {
					amountTendered = Double.parseDouble(amountTendTxt.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,
							"Enter numbers only! Try again.");
					return;
				}
				double diff = amountDue - amountTendered;
				boolean paid = true;
				double change = 0.0;
				if (diff <= 0) {
					orders.get(index).setPaid(true);
					change = -diff;
					changelbl.setText(NumberFormat.getCurrencyInstance()
							.format(change));
				} else if (diff > 0) {
					JOptionPane.showMessageDialog(null, "Payment is "
							+ NumberFormat.getCurrencyInstance().format(diff)
							+ " short! Try again.");
				}

			}
		});
		btnNewButton.setBounds(320, 466, 192, 23);
		contentPane.add(btnNewButton);

		lblNewLabel.setBounds(372, 79, 74, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setText("Select Order");

		lblEnterAmountTendered = new JLabel("      Amount tendered");
		lblEnterAmountTendered.setBounds(353, 135, 132, 14);
		contentPane.add(lblEnterAmountTendered);

		amountTendTxt = new JTextField();
		amountTendTxt.setBounds(353, 160, 117, 20);
		contentPane.add(amountTendTxt);
		amountTendTxt.setColumns(10);

		lblChange = new JLabel("Change");
		lblChange.setBounds(342, 515, 46, 14);
		contentPane.add(lblChange);

		changelbl = new JLabel("$0");
		changelbl.setBounds(455, 512, 46, 14);
		contentPane.add(changelbl);

		JButton btnNewButton_1 = new JButton("Close Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check if there is an order to close
				if (orders.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"There is no order to process.");
					dispose();
					return;
				}
				index = orderComboBox.getSelectedIndex();
				boolean paid = orders.get(index).isPaid();

				// if order is paid then we can close order and send to db etc
				if (paid) {
					// ////////reset combo box etc
					// orderComboBox.remove(index);
					orders.remove(index);
					// ////////////////
					dispose();
					// changelbl.setText(NumberFormat.getCurrencyInstance()
					// .format(0));
					// orderComboBox.revalidate();
					// orderComboBox.repaint();
				} else {
					JOptionPane.showMessageDialog(null,
							"Order must be paid before it can be closed.");
					dispose();
				}

			}
		});
		btnNewButton_1.setBounds(353, 551, 117, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("_________________________________________________________________________________________________________________________________________");
		lblNewLabel_1.setBounds(0, 54, 834, 14);
		contentPane.add(lblNewLabel_1);

	}

	public void pullThePlug() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
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

			// What ever button does
		}
	}
}
