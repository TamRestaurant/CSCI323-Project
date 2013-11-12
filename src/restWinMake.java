import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.ScrollPaneConstants;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;

//import RPSGui.buttonListener;

import java.awt.GridLayout;

import javax.swing.JScrollPane;

public class restWinMake extends JFrame {
	private JPanel contentPane;
	private JPasswordField pwdYourPin;
	private static JTabbedPane tabbedPane;

	// This connects to DB and can be passed into any class that needs to
	// connect to DB
	// (best to config constructor initialization, refer to admin tab for
	// example)
	private dbAction DBAction = new dbAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					restWinMake frame = new restWinMake();
					frame.pack();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	// ------------------------------- Declarations
	// ---------------------------------

	private char[] pWord;
	// private String Fooditems[] = { "", "Burger", "Fry", "hotDog" };
	// private String Drinkitems[] = { "", "Water", "Coffee", "Pepsi" };
	// private String Desertitems[] = { "", "Cake", "Pie", "IceCream" };
	// private String Sides[] = { "", "baccon", "CheeseFries", "Fries" };
	private String Tickets[] = { "", "Table 1", "Table 2", "Table 3" };
	private ArrayList<Object> ticketObject = new ArrayList<Object>();
	private String current_orders = "";

	// ------ Labels ------
	private JLabel lblTicket, lblOrders;
	// -------- Buttons -------

	private JButton btnGetIt, btnPlaceOrder, btnMarkPaid, btnVeiwTicket,
			btnPrintTicket, btnEnter;

	private JComboBox ticketCombo;
	JPanel panel_1, panel_2, panel_3panel_4, panel_5, Passwordpanel;

	// -------------------------------- Main
	// ------------------------------------

	public restWinMake() {// Begin Main
		// printorder for test purpose
		ArrayList<Order> oo = DBAction.getOpenOrders();
		for (Order o : oo)
			System.out.println(o);

		// ResultSet ii=DBAction.getMenuItems();
		// //for(Item o:ii)
		// System.out.println(ii);
		// ------------- Change order of tabs and Panels at own risk
		// --------------------------------

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setBounds(0, 0, 1300, 767);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(new Dimension(1200, 800));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(25, 25, 1100, 950);
		contentPane.add(tabbedPane);
		// -----------------------ADD menu
		// tab-----------------------------------
		menu m = new menu(getMenuItems(), DBAction);
		tabbedPane.addTab("menu", null, m.m(), null);
		// -----------------------ADD wait station
		// tab-----------------------------------
		// waitStation w = new waitStation();
		// tabbedPane.addTab("Wait Station", null, w.w(), null);
		// -----------------------ADD register
		// tab-----------------------------------
		register r = new register(DBAction);
		tabbedPane.addTab("Register", null, r.r(), null);
		// -----------------------ADD kitchen
		// tab-----------------------------------
		kitchen k = new kitchen(DBAction);
		tabbedPane.addTab("Kitchen", null, k.k(), null);

		// locksreen tab

		lockscreen l = new lockscreen();
		tabbedPane.addTab("Lockscreen", null, l.lock(), null);

		// ----------------------ADD employee tab (for managing of menu/past
		// orders/employees)----------------------
		admin a = new admin(DBAction);
		tabbedPane.addTab("Administration", a.getAdminPanel());
		// TODO: Create dbConnector in one place and allow other classes to use
		// it to avoid multiple connections

		// Add employee clock in tab
		clockInPanel c = new clockInPanel(DBAction);
		tabbedPane.addTab("Employee Clock-in", c);

		/*
		 * 
		 * 
		 * 
		 * 
		 * Everything below here is not implemented but I didnt want to erase it
		 */

		pwdYourPin = new JPasswordField();
		pwdYourPin.setText("xxxxxxxx");
		pwdYourPin.setBounds(145, 62, 117, 20);
		// panel.add(pwdYourPin);

		JLabel lblTicket = new JLabel("Orders appear here");
		JLabel lblOrders = new JLabel("Orders");

		// ------------------------------------------- Labels
		// //-------------------------------------------
		// JLabel lblTicket = new JLabel("Orders appear here");
		// JLabel lblOrders = new JLabel("Orders");
		// ------------------------------------------- Buttons
		// //-------------------------------------------

		JButton btnGetIt = new JButton("Add to current Ticket");
		JButton btnNewTicket = new JButton("New Ticket");
		JButton btnEdit = new JButton("Edit");
		JButton btnPlaceOrder = new JButton("Place Order");
		JButton btnMarkPaid = new JButton("Mark Paid");
		JButton btnVeiwTicket = new JButton("Veiw Ticket");
		JButton btnPrintTicket = new JButton("Print Ticket");
		JButton btnEnter = new JButton("Enter");

		// ------------------------------------------- ActionListeners

		btnGetIt.addActionListener(new buttonListener());
		btnNewTicket.addActionListener(new buttonListener());
		btnEdit.addActionListener(new buttonListener());
		btnPlaceOrder.addActionListener(new buttonListener());
		btnMarkPaid.addActionListener(new buttonListener());
		btnVeiwTicket.addActionListener(new buttonListener());
		btnPrintTicket.addActionListener(new buttonListener());
		btnEnter.addActionListener(new buttonListener());

	}// End of Main

	// ------------------------------------------- End of
	// main//-------------------------------------------

	// ------------------------------------------- methods
	// //-------------------------------------------

	public void refreshKitchen() {
		System.out.println("weeewefsd");
		lblOrders.setText(current_orders);
	}

	private ArrayList<Item> getMenuItems() {

		ResultSet rs = DBAction.getMenuItems();
		ArrayList<Item> myMenuItems = new ArrayList();
		ArrayList<Item> beverages = new ArrayList();
		ArrayList<Item> sides = new ArrayList();
		ArrayList<Item> desserts = new ArrayList();
		ArrayList<Item> entrees = new ArrayList();

		// Populate arrayList of menuItems from ResultSet
		try {

			while (rs.next()) {
				// Have option of using rs.getString(1) for column numbers or
				// can use names. Opted for names for readability
				String name = rs.getString("ItemName");
				String descr = rs.getString("ItemDescription");
				String category = rs.getString("CategoryName");
				int itemID = rs.getInt("idMenuItem");
				Double price = rs.getDouble("ItemPrice");

				// depending on category, put into array that will hold that
				// specific category
				if (category.equals("Beverage")) {
					beverages
							.add(new Item(name, descr, category, itemID, price));
				} else if (category.equals("Entree")) {
					entrees.add(new Item(name, descr, category, itemID, price));
				} else if (category.equals("Side")) {
					sides.add(new Item(name, descr, category, itemID, price));
				} else if (category.equals("Dessert")) {
					desserts.add(new Item(name, descr, category, itemID, price));
				}

			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		myMenuItems.addAll(entrees);
		myMenuItems.addAll(beverages);
		myMenuItems.addAll(desserts);
		myMenuItems.addAll(sides);

		return myMenuItems;

	}// close getMenuItems()

	// ------------------------------------------- start button listener
	// //-------------------------------------------

	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//

			if (event.getSource() == btnGetIt) {
				for (int i = 0; i < ticketObject.size(); i++) {

					current_orders = current_orders + "\n"
							+ ticketObject.get(i);

				}
				System.out.println("btnGetIt worked");
			} else if (event.getSource() == btnEnter) {

				pWord = (pwdYourPin.getPassword());
				String SpWord = "";
				for (int i = 0; i < pWord.length; i++) {
					SpWord = SpWord + pWord[i];

				}
				System.out.println(SpWord);
				if (SpWord == "12345") {
					System.out.println("it worked" + SpWord);
				}
				System.out.println("btnEnter wrks");
			} else if (event.getSource() == btnVeiwTicket) {
				int intTicket = (ticketCombo.getSelectedIndex());
				String Str_Ticketitems = Tickets[intTicket];
				System.out.println("you selected  " + Str_Ticketitems);
				System.out.println("btnVeiwTicket wrks");
			} else if (event.getSource() == btnPlaceOrder) {
				for (int i = 0; i < ticketObject.size(); i++) {

					current_orders = current_orders + "\n"
							+ ticketObject.get(i);

				}
				// time getter here #################
				refreshKitchen();

				System.out.println("btnPlaceOrder");// btnGetIt
			}

		}

	}// end listener
	public static void setEnabledPanel(boolean is) {
		//get components and set value
		tabbedPane.setEnabled(is);
		Component[]comps= tabbedPane.getComponents();
		for(Component c:comps) {
			c.setEnabled(is);
		}
		
	}
	
	
}// End

