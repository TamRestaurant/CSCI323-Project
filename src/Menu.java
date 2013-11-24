import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.FlowLayout;

import javax.swing.JTextArea;

import java.awt.ScrollPane;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;

//import restWinMake.buttonListener;

//import RPSGui.buttonListener;

import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JScrollBar;
import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Menu extends JFrame {
	private JTabbedPane menu;
	private JPanel panel;
	private ImageIcon[] menuButtonsImage;
	private JPanel menuPanel;
	private JPanel subWaitTab, sidesButtonPanel, entreeButtonPanel,
			drinkButtonPanel, dessertButtonPanel;
	private JButton[] menuButtons;
	private JSeparator separator;
	private boolean openTicket = true;
	private Order order;
	private ArrayList<Item> items;
	private Item item;
	public ArrayList<Order> orders;
	private int orderNumber;
	private JLabel currentOrder;
	private JLabel currentTable;
	private Vector<String> food;
	private JList list;
	private ArrayList<Item> menuItems;
	// private MouseListener mouseListener;
	private long time;
	private int table;
	private JList openList;
	private Vector<Order> openFood;
	private JTextField serverIDTextBox;
	private DbAction db;
	private JPanel setOrderPanel, openOrders;

	private String message;
	private static JTextArea messagesList;
	private JScrollPane scrollMessages;
	private JTextArea txtMessage;
	// private String fromKitchen;
	private JButton btnSend_mesage;
	private JButton btnClear_message;
	private JButton edit;
	private JScrollPane scroll;
	private JWindow frame;
	private JTabbedPane orderButtons;
	boolean editing = false;
	private Vector<Item> v;
	private JList editList;
	private JPanel editPanel;
	private JButton add, done;
	private int oldSize;
	private int editOrderNumber;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	public Menu(String messages) {
		String fromKitchen = messages;
	}

	/**
	 * @wbp.parser.constructor
	 */
	public Menu(ArrayList<Item> mItems, DbAction dba) {
		db = dba;
		menu = new JTabbedPane(JTabbedPane.TOP);
		menu.setAutoscrolls(true);
		menuItems = mItems;
		panel = new JPanel();
		menuPanel = new JPanel();
		menuButtons = new JButton[64];
		separator = new JSeparator();
		orders = new ArrayList<Order>();
		orderNumber = db.getMostRecentOrderNum();
		separator.setBounds(432, 259, 1, 12);
		menuPanel.add(separator);
		openFood = orderToVector(db.getOpenOrders());

		frame = new JWindow();
		editPanel = new JPanel();
		add = new JButton("Add an item");
		done = new JButton("Done");
		v = new Vector<Item>();
		editList = new JList(v);
		editList.setBounds(0, 0, 400, 400);
		add.setBounds(50, 450, 120, 20);
		done.setBounds(250, 450, 120, 20);
		panel.setPreferredSize(new Dimension(400, 600));
		panel.setLayout(null);
		panel.add(editList);
		panel.add(add);
		panel.add(done);
		frame.setContentPane(panel);
		frame.setAlwaysOnTop(true);
		frame.pack();
		frame.setVisible(false);
		frame.setLocationRelativeTo(null);
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (; oldSize < v.size(); oldSize++) {
					// TODO find bug and fix it
					int test = v.elementAt(oldSize).getitemID();
					System.out.println("menuItemID: " + test); // This should
																// not be zero,
																// but it is.
																// Causing issue
																// with the
																// database
																// query

					db.addItemExistingOrder(editOrderNumber,
							v.elementAt(oldSize).getitemID(),
							v.elementAt(oldSize).getItemComment());
					openFood = orderToVector(db.getOpenOrders()); // Added this
																	// line to
																	// re-populate
																	// orders
																	// from DB
																	// after
																	// edit is
																	// made
					// Make sure that server list panel is populated with
					// updated price
					rePopulateOpenOrderList();

				}
				frame.setVisible(false);
				orderButtons.setEnabled(true);
				edit.setEnabled(true);
				editing = false;
				openTicket = true;
				v.clear();

			}
		});
		// ----------------------END Display Panel---------------------------
		menu.addTab("Menu", null, menuPanel, null);
		menuPanel.setLayout(null);
		currentOrder = new JLabel("Current Order");
		currentTable = new JLabel("Current Table");
		// -------------------Display Panel-----------------------------------

		orderButtons = new JTabbedPane(JTabbedPane.TOP);
		openOrders = new JPanel();
		openOrders.setBounds(810, 5, 255, 800);//
		openOrders.setLayout(null);
		orderButtons.setBounds(810, 5, 255, 712);//
		setOrderPanel = new JPanel();
		setOrderPanel.setBounds(810, 5, 255, 815);//
		setOrderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		orderButtons.addTab("New Order", null, setOrderPanel, null);
		orderButtons.addTab("Open Orders", null, openOrders, null);
		menuPanel.add(orderButtons);
		setOrderPanel.setLayout(null);
		// ---------------Open Orders Tab--------------------------
		edit = new JButton("Edit");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 20, 230, 300);
		openOrders.add(scrollPane);
		openList = new JList(openFood);
		scrollPane.setViewportView(openList);
		openList.setEnabled(true);
		edit.setBounds(50, 330, 117, 29);
		openOrders.add(edit);
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] sel = openList.getSelectedIndices();
				if (sel.length > 1 || sel.length < 1) {
					JOptionPane.showMessageDialog(openOrders,
							"Please select one order.");
				} else {
					editing = true;
					frame.setVisible(true);
					orderButtons.setEnabled(false);
					edit.setEnabled(false);

					editOrderNumber = openFood.get(sel[0]).getOrderNumber();
					v.addAll(itemToVector(openFood.get(sel[0]).getItems()));
					oldSize = v.size();
					editList.setListData(v);

					add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							openTicket = false;
							menuPanel.grabFocus();
							frame.setVisible(false);

						}
					});
				}
			}
		});
		// -------------------End Open Orders Tab--------------------

		currentOrder.setBounds(20, 130, 500, 20);
		currentTable.setBounds(20, 115, 500, 20);
		setOrderPanel.add(currentOrder);
		setOrderPanel.add(currentTable);
		// ---------------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------------

		// for(JButton b:menuButtons) {
		// b.setFont(new Font("Calibri", Font.PLAIN, 9));
		// }

		// -----------------------------Order
		// buttons-------------------------------

		final JButton closeOrder = new JButton("End Order");
		final JButton newOrder = new JButton("New Order");
		final JButton remove = new JButton("Remove");
		list = new JList();
		newOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] tables = { 1, 2, 3, 4, 5 };
				Object t = JOptionPane.showInputDialog(menu,
						"What is the table number?", "",
						JOptionPane.PLAIN_MESSAGE, null, tables, null);
				if (t == null) {
				} else {
					table = (Integer) t;
					openTicket = false;
					newOrder.setEnabled(openTicket);
					closeOrder.setEnabled(!openTicket);
					orderButtons.setEnabled(false);
					items = new ArrayList<Item>();
					orderNumber++;
					// food.clear();
					// list.setListData(food);
					rePopulateOpenOrderList();
					currentOrder.setText("Current Order: " + orderNumber);
					currentTable.setText("Current Table: " + table);
				}

			}
		});
		list.setBounds(5, 150, 230, 300);
		list.setEnabled(true);
		setOrderPanel.add(list);
		newOrder.setBounds(10, 65, 117, 29);
		setOrderPanel.add(newOrder);
		remove.setEnabled(true);
		remove.setBounds(15, 450, 117, 29);
		closeOrder.setEnabled(!openTicket);
		setOrderPanel.add(remove);
		closeOrder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (items != null) {
					// brings ticket up in window
					// TODO: Replace this employeeNumber with the actual
					// employee number of whoever is taking order
					int employeeID = 1;
					try {
						employeeID = Integer.parseInt(serverIDTextBox.getText());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,
								"Enter numbers only into server ID field.");
						return;
					}
					openTicket = true;
					newOrder.setEnabled(openTicket);
					closeOrder.setEnabled(!openTicket);
					orderButtons.setEnabled(true);
					order = new Order(items, table, employeeID, orderNumber);
					// db.addOrder(order);
					if (db.addOrder(order)) {
						System.out.println("added" + order);
					}
					// refresh database connection
					// db=new dbAction();
					ArrayList<Order> oo = db.getOpenOrders();
					for (Order o : oo) {
						System.out.println(o);
					}
					// orderNumber++;
					rePopulateOpenOrderList();

				}
				// TODO

				else {
					JOptionPane.showMessageDialog(null,
							"You must create an order first.");
				}

			}
		});
		closeOrder.setBounds(10, 90, 117, 29);
		setOrderPanel.add(closeOrder);

		serverIDTextBox = new JTextField();
		serverIDTextBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				serverIDTextBox.setText("");
			}
		});
		serverIDTextBox.setText("1");
		serverIDTextBox.setBounds(10, 34, 86, 20);
		setOrderPanel.add(serverIDTextBox);
		serverIDTextBox.setColumns(10);

		JLabel serverIDlbl = new JLabel("Server ID");
		serverIDlbl.setBounds(10, 11, 67, 14);
		setOrderPanel.add(serverIDlbl);
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] sel = list.getSelectedIndices();
				for (int i = 0; i < sel.length; i++) {
					food.remove(i);
					items.remove(i);
				}
				list.setListData(food);
			}
		});

		scrollPane_1 = new JScrollPane();
		scrollPane_1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setBounds(5, 5, 800, 700);
		menuPanel.add(scrollPane_1);
		// ----------------END load images-----------------------
		// JTabbedPane mainTab = new JTabbedPane(JTabbedPane.TOP);
		// mainTab.setBounds(5, 5, 900, 900);
		// subWaitTab.add(mainTab);
		//
		// JTabbedPane waitTab = new JTabbedPane(JTabbedPane.TOP);
		// mainTab.addTab("Wait Station", null, waitTab, null);

		JPanel subWaitTab_1 = new JPanel();
		scrollPane_1.setViewportView(subWaitTab_1);
		subWaitTab_1.setAutoscrolls(true);
		subWaitTab_1.setLayout(null);

		JPanel entreeButtonPanel_1 = new JPanel();
		entreeButtonPanel_1.setBounds(40, 50, 730, 124);
		subWaitTab_1.setBorder(BorderFactory.createLoweredBevelBorder());
		subWaitTab_1.add(entreeButtonPanel_1);

		entreeButtonPanel_1.setLayout(new GridLayout(3, 7, 2, 2));
		entreeButtonPanel_1.setBorder(BorderFactory.createLoweredBevelBorder());

		JPanel drinkButtonPanel_1 = new JPanel();
		drinkButtonPanel_1.setBounds(40, 217, 730, 80);
		subWaitTab_1.add(drinkButtonPanel_1);
		drinkButtonPanel_1.setLayout(new GridLayout(2, 7, 2, 2));
		drinkButtonPanel_1.setBorder(BorderFactory.createLoweredBevelBorder());

		JPanel dessertButtonPanel_1 = new JPanel();
		dessertButtonPanel_1.setBounds(40, 346, 730, 40);
		subWaitTab_1.add(dessertButtonPanel_1);
		dessertButtonPanel_1.setLayout(new GridLayout(1, 7, 2, 2));
		dessertButtonPanel_1
				.setBorder(BorderFactory.createLoweredBevelBorder());

		JPanel sidesButtonPanel_1 = new JPanel();
		sidesButtonPanel_1.setBounds(40, 435, 730, 40);
		subWaitTab_1.add(sidesButtonPanel_1);
		sidesButtonPanel_1.setLayout(new GridLayout(1, 7, 2, 2));
		sidesButtonPanel_1.setBorder(BorderFactory.createLoweredBevelBorder());
		JPanel MessagePanel = new JPanel();
		MessagePanel.setAutoscrolls(true);
		MessagePanel.setBounds(40, 524, 730, 153);
		subWaitTab_1.add(MessagePanel);
		MessagePanel.setLayout(null);
		MessagePanel.setBorder(BorderFactory.createLoweredBevelBorder());

		btnSend_mesage = new JButton("Send Message");
		btnClear_message = new JButton("Clear Messages");

		btnSend_mesage.setEnabled(true);
		btnSend_mesage.setBounds(15, 20, 110, 20);
		MessagePanel.add(btnSend_mesage);

		btnClear_message.setEnabled(true);
		btnClear_message.setBounds(15, 50, 110, 20);
		MessagePanel.add(btnClear_message);

		txtMessage = new JTextArea();
		txtMessage.setBounds(140, 10, 500, 20);
		MessagePanel.add(txtMessage);

		btnSend_mesage.addActionListener(new buttonListener());
		btnClear_message.addActionListener(new buttonListener());

		scrollMessages = new JScrollPane();
		scrollMessages.setBounds(140, 40, 500, 100);// lower
		MessagePanel.add(scrollMessages);
		messagesList = new JTextArea();

		scrollMessages.setViewportView(messagesList);
		messagesList.setEnabled(false);
		int fontsize = 16;
		JLabel lblMainEntree = new JLabel("Main Entree");
		lblMainEntree.setFont(new Font("Lucida Grande", Font.PLAIN, fontsize));
		lblMainEntree.setBounds(360, 20, 145, 25);
		subWaitTab_1.add(lblMainEntree);

		JLabel lblBeverages = new JLabel("Beverages");
		lblBeverages.setFont(new Font("Lucida Grande", Font.PLAIN, fontsize));
		lblBeverages.setBounds(360, 180, 145, 25);
		subWaitTab_1.add(lblBeverages);

		JLabel lblDesserts = new JLabel("Desserts");
		lblDesserts.setFont(new Font("Lucida Grande", Font.PLAIN, fontsize));
		lblDesserts.setBounds(360, 309, 145, 25);
		subWaitTab_1.add(lblDesserts);

		JLabel lblSideOrders = new JLabel("Side Orders");
		lblSideOrders.setFont(new Font("Lucida Grande", Font.PLAIN, fontsize));
		lblSideOrders.setBounds(360, 398, 145, 25);
		subWaitTab_1.add(lblSideOrders);

		JLabel lbmessages = new JLabel("Messages");
		lbmessages.setFont(new Font("Lucida Grande", Font.PLAIN, fontsize));
		lbmessages.setBounds(360, 487, 145, 25);
		subWaitTab_1.add(lbmessages);

		JLabel lblTicketDisplay = new JLabel("Ticket Display");
		lblTicketDisplay
				.setFont(new Font("Lucida Grande", Font.PLAIN, fontsize));
		lblTicketDisplay.setBounds(830, 6, 145, 25);
		subWaitTab_1.add(lblTicketDisplay);

		// ---------------------------------------------------------------------------------------------

		// ---------------------------------------------------------------------------------------------
		int fsize = 12;

		for (int j = 0; j < 21; j++) {
			menuButtons[j] = new JButton(menuItems.get(j).getItemName());

			entreeButtonPanel_1.add(menuButtons[j]);
			menuButtons[j].addMouseListener(new mouseListener());
			menuButtons[j].setFont(new Font("Calibri", Font.PLAIN, fsize));

		}
		for (int j = 24; j < 38; j++) {
			menuButtons[j] = new JButton(menuItems.get(j).getItemName());

			drinkButtonPanel_1.add(menuButtons[j]);
			menuButtons[j].addMouseListener(new mouseListener());
			menuButtons[j].setFont(new Font("Calibri", Font.PLAIN, fsize));

		}
		for (int j = 40; j < 47; j++) {
			menuButtons[j] = new JButton(menuItems.get(j).getItemName());

			dessertButtonPanel_1.add(menuButtons[j]);
			menuButtons[j].addMouseListener(new mouseListener());
			menuButtons[j].setFont(new Font("Calibri", Font.PLAIN, fsize));
		}
		for (int j = 56; j < 63; j++) {
			menuButtons[j] = new JButton(menuItems.get(j).getItemName());

			sidesButtonPanel_1.add(menuButtons[j]);
			menuButtons[j].addMouseListener(new mouseListener());
			menuButtons[j].setFont(new Font("Calibri", Font.PLAIN, fsize));
		}
		food = new Vector<String>();

		/**
		 * For soeme reason the list is re-ordered after this happens??
		 */
		// ----------------------END order buttons-------------------------

	}

	public void rePopulateOpenOrderList() {
		food.clear();
		list.setListData(food);
		openFood = orderToVector(db.getOpenOrders());
		openList.setListData(openFood);
		repaint();
	}

	// ---------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------

	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == btnSend_mesage) {
				String temp = "";
				temp = messagesList.getText() + "\n";
				temp = temp + txtMessage.getText();
				messagesList.setText(temp);
				setKitchenMassages(temp);
				sendToKitchen();
			} else if (event.getSource() == btnClear_message) {
				messagesList.setText("");
				txtMessage.setText("");
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------

	public JTabbedPane m() {
		return menu;
	}

	private Vector<Order> orderToVector(ArrayList<Order> array) {
		Vector<Order> v = new Vector<Order>();
		for (Order o : array) {
			v.add(o);
		}

		return v;

	}

	private Vector<Item> itemToVector(ArrayList<Item> array) {
		Vector<Item> v = new Vector<Item>();
		for (Item o : array) {
			v.add(o);
		}

		return v;

	}

	private class mouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			if (!openTicket)
				time = System.currentTimeMillis();
		}

		@Override
		public void mouseReleased(MouseEvent event) {

			if (openTicket && editing) {
				frame.setVisible(true);
			}

			else if (!openTicket) {
				String comments = null;
				// This checks to see if button was held down, if so message box
				// will open to allow user to type in comments about item and
				// add it to item
				if ((System.currentTimeMillis() - time) >= 1000) {
					comments = JOptionPane.showInputDialog("Modifications");
				}
				int i = 0;
				while (event.getSource() != menuButtons[i])
					i++;
				if (comments != null) {
					item = new Item(menuItems.get(i).getItemName(), menuItems
							.get(i).getDescription(), menuItems.get(i)
							.getCategory(), menuItems.get(i).getitemID(),
							menuItems.get(i).getItemPrice(), comments);

					if (editing == true) {
						v.add(item);
						editList.setListData(v);
						frame.setVisible(true);
					} else {
						items.add(item);
						food.add(item.getItemName());
						list.setListData(food);

					}
				} else {
					item = new Item(menuItems.get(i).getItemName(), menuItems
							.get(i).getDescription(), menuItems.get(i)
							.getCategory(), menuItems.get(i).getitemID(),
							menuItems.get(i).getItemPrice());
					if (editing == true) {
						v.add(item);
						editList.setListData(v);
						frame.setVisible(true);
					} else {
						items.add(item);
						food.add(item.getItemName());
						list.setListData(food);
					}
				}
			}
		}

	}

	// ---------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------

	public static void setKitchenMassages(String m) {
		messagesList.setText(m);
	}

	public void sendToKitchen() {
		String m = "";
		m = messagesList.getText();
		kitchen.setMenuMassages(m + "\n");
	}
}
