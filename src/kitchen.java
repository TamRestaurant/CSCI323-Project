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
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class kitchen extends JFrame {
	private static JTabbedPane kitchen;// here
	private JPanel activeOrdersPanel;
	private JLabel lblSelectOrd, lblComments;
	private JButton btnContact;
	private JCheckBox orderUp;
	private JTextField txtComment;
	private JList orderListBox;
	private dbAction DBAct;
	private ArrayList<Order> openOrdersArray;
	private Vector<Order> openOrdersVector = new Vector();
	private DefaultComboBoxModel model = new DefaultComboBoxModel(
			openOrdersVector);
	private Timer refresher;
	private JLabel orderDetail;
	private JTextArea orderDetailTextArea;
	private JScrollPane scrollPane;

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
				orderListBox.setListData(openOrdersVector);
				repaint();
			}
		};
		new Timer(delay, taskPerformer).start();

		// ---------------------------------------------------
		kitchen = new JTabbedPane(JTabbedPane.TOP);
		kitchen.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		activeOrdersPanel = new JPanel();

		kitchen.addTab("Active Orders", null, activeOrdersPanel, null);
		kitchen.setEnabledAt(0, true);
		activeOrdersPanel.setLayout(null);
		activeOrdersPanel.setBounds(10, 10, 900, 900);
		// ----------------------- instantiations ----------------------------
		lblSelectOrd = new JLabel("Select Order");
		lblSelectOrd.setHorizontalAlignment(SwingConstants.CENTER);
		lblComments = new JLabel("Type Comments to Wait Person");
		btnContact = new JButton("Send Notification");
		orderUp = new JCheckBox("Order Up");
		txtComment = new JTextField();

		// ----------------------- settings ----------------------------
		lblSelectOrd.setBounds(315, 11, 298, 31);
		lblComments.setBounds(28, 50, 202, 14);
		btnContact.setBounds(264, 656, 400, 50);
		orderUp.setBounds(0, 436, 170, 50);
		txtComment.setBounds(28, 75, 170, 223);

		// ---------------------- adds to panel -----------------------------

		activeOrdersPanel.add(lblSelectOrd);
		activeOrdersPanel.add(txtComment);
		activeOrdersPanel.add(lblComments);
		activeOrdersPanel.add(btnContact);
		activeOrdersPanel.add(orderUp);

		orderListBox = new JList<Order>(openOrdersVector);
		orderListBox.setValueIsAdjusting(true);
		orderListBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		orderListBox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				///show order details for cooks
				int index = orderListBox.getSelectedIndex();
				if (index >= 0) {
					orderDetailTextArea.setText(openOrdersVector.get(index)
							.wholeOrderString());
				}
			}
		});
		orderListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderListBox.setBounds(264, 50, 400, 248);
		activeOrdersPanel.add(orderListBox);

		orderDetail = new JLabel("Order Details");
		orderDetail.setLabelFor(orderDetail);
		orderDetail.setHorizontalAlignment(SwingConstants.CENTER);
		orderDetail.setVerticalAlignment(SwingConstants.TOP);
		orderDetail.setBounds(264, 315, 400, 14);
		activeOrdersPanel.add(orderDetail);
		 
		 scrollPane = new JScrollPane();
		 scrollPane.setBounds(264, 340, 400, 290);
		 activeOrdersPanel.add(scrollPane);
		
		 orderDetailTextArea = new JTextArea();
		 scrollPane.setViewportView(orderDetailTextArea);

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
			int index = orderListBox.getSelectedIndex();
			if (index >= 0) {
				openOrdersArray.get(index).setServed(true);
				// list.remove(index);
				listToVector();
				orderListBox.setListData(openOrdersVector);
			}
			// //need to update order in DB so that it is not retrieved in new
			// list
			//
			//
			System.out.println("works");
		}
	}
	public JPanel getActiveOrdersPanel() {
		return activeOrdersPanel;
	}
}

