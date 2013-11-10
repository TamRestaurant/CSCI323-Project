import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.FlowLayout;

import javax.swing.JTextArea;

import java.awt.ScrollPane;
import java.awt.CardLayout;

import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;

//import RPSGui.buttonListener;

import java.awt.GridLayout;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;

public class kitchen extends JFrame {
	private static JTabbedPane kitchen;// here
	private JPanel activeOrdersPanel;
	private JLabel lblSelectOrd, lblComments;
	private JButton btnContact;
	private JCheckBox orderUp;
	private JTextField txtComment;
	private JList list;
	private dbAction DBAct;
	private ArrayList<Order> openOrdersArray;
	private Vector<Order> openOrdersVector = new Vector();
	private DefaultComboBoxModel model = new DefaultComboBoxModel(
			openOrdersVector);
	private Timer refresher;
	private JLabel orderDetail;
	private JTextArea orderDetailTextArea;

	public kitchen(dbAction DBAction) {

		// Connect to DB (passed in as parameter)
		DBAct = DBAction;
		// ---------------------------------------------------
		openOrdersArray = DBAct.getOpenOrders();
		listToVector();
		// ////////timer to refresh kitchen order list every 30 secs
		int delay = 10000; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Connect to DB (passed in as parameter)

				// ---------------------------------------------------
				/**
				 * THIS GETS OPEN ORDERS FROM THE DB AND ADDS THEM TO THE
				 * COMBOBOX
				 * 
				 */

				openOrdersArray = DBAct.getOpenOrders();
				listToVector();
				list.setListData(openOrdersVector);
				repaint();
			}
		};
		new Timer(delay, taskPerformer).start();

		// ---------------------------------------------------
		kitchen = new JTabbedPane(JTabbedPane.TOP);
		activeOrdersPanel = new JPanel();

		kitchen.addTab("Active Orders", null, activeOrdersPanel, null);
		kitchen.setEnabledAt(0, true);
		activeOrdersPanel.setLayout(null);
		activeOrdersPanel.setBounds(10, 10, 900, 900);
		// ----------------------- instantiations ----------------------------
		lblSelectOrd = new JLabel("Select Order");
		lblComments = new JLabel("Type Comments to Wait Person");
		btnContact = new JButton("Send Notification");
		orderUp = new JCheckBox("Order Up");
		txtComment = new JTextField();

		// ----------------------- settings ----------------------------
		lblSelectOrd.setBounds(225, 180, 99, 31);
		lblComments.setBounds(15, 180, 170, 50);
		btnContact.setBounds(15, 400, 170, 50);
		orderUp.setBounds(15, 300, 170, 50);
		txtComment.setBounds(15, 220, 170, 50);

		// ---------------------- adds to panel -----------------------------

		activeOrdersPanel.add(lblSelectOrd);
		activeOrdersPanel.add(txtComment);
		activeOrdersPanel.add(lblComments);
		activeOrdersPanel.add(btnContact);
		activeOrdersPanel.add(orderUp);

		list = new JList<Order>(openOrdersVector);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				///show order details for cooks
				int index = list.getSelectedIndex();
				if (index >= 0) {
					orderDetailTextArea.setText(openOrdersVector.get(index)
							.wholeOrderString());
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(322, 180, 298, 326);
		activeOrdersPanel.add(list);

		orderDetail = new JLabel("Order Details");
		orderDetail.setLabelFor(orderDetail);
		orderDetail.setHorizontalAlignment(SwingConstants.CENTER);
		orderDetail.setVerticalAlignment(SwingConstants.TOP);
		orderDetail.setBounds(15, 11, 400, 14);
		activeOrdersPanel.add(orderDetail);
		
		 orderDetailTextArea = new JTextArea();
		orderDetailTextArea.setBounds(15, 41, 400, 128);
		activeOrdersPanel.add(orderDetailTextArea);

		btnContact.addActionListener(new buttonListener());

		// ---------------------------------------------------

	}

	public void listToVector() {
		openOrdersVector.clear();
		for (Order o : openOrdersArray) {
			// add order to list if it has NOT been served
			if (!o.isServed()) {
				openOrdersVector.add(o);
			}
		}
	}

	public static JTabbedPane k()// here
	{
		return kitchen;
	}

	// /////////invokes register frame
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// set order to served and it will be removed from list
			int index = list.getSelectedIndex();
			if (index >= 0) {
				openOrdersArray.get(index).setServed(true);
				// list.remove(index);
				listToVector();
				list.setListData(openOrdersVector);
			}
			// //need to update order in DB so that it is not retrieved in new
			// list
			//
			//
			System.out.println("works");
		}
	}
}

