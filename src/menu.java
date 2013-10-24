import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;

//import restWinMake.buttonListener;

//import RPSGui.buttonListener;




import java.awt.GridLayout;

public class menu extends JFrame
{
	private JTabbedPane menu;
	private JPanel panel;
	private ImageIcon[] menuButtonsImage;
	private JPanel menuPanel;
	private JPanel subWaitTab,sidesButtonPanel,entreeButtonPanel,drinkButtonPanel,dessertButtonPanel;
	private JButton[] menuButtons;
	private JSeparator separator;
	private boolean openTicket = true;
	private Order order;
	private ArrayList<Item> items;
	private Item item;
	public ArrayList<Order> orders;
	private JTextArea display;
	private int orderNumber;
	private JLabel currentOrder; 
	
	public menu()
	{
		menu = new JTabbedPane(JTabbedPane.TOP);
		panel = new JPanel();
		menuPanel = new JPanel();
		subWaitTab = new JPanel();
		menuButtons = new JButton[64];
		separator = new JSeparator();
		orders= new ArrayList<Order>();
		display = new JTextArea();
		orderNumber = 1;
		currentOrder = new JLabel("Current Order");
		separator.setBounds(432, 259, 1, 12);
		menuPanel.add(separator);
		//-------------------Display Panel-----------------------------------
		display = new JTextArea();
		display.setBounds(15, 100, 170, 695);
		display.setEditable(false);
		
		JPanel setOrderPanel = new JPanel();
		setOrderPanel.setBounds(830, 6, 200, 810);
		setOrderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		menuPanel.add(setOrderPanel);
		setOrderPanel.setLayout(null);
		setOrderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		
		currentOrder.setBounds(20, 75, 500, 20);
		setOrderPanel.add(currentOrder);
		setOrderPanel.add(display);
		//----------------------END Display Panel---------------------------
		menu.addTab("Menu", null, menuPanel, null);
		menuPanel.setLayout(null);
		//----------------END load images-----------------------
		JTabbedPane mainTab = new JTabbedPane(JTabbedPane.TOP);
		mainTab.setBounds(5, 5, 1100, 1100);
		subWaitTab.add(mainTab);
		
		JTabbedPane waitTab = new JTabbedPane(JTabbedPane.TOP);
		mainTab.addTab("Wait Station", null, waitTab, null);
		
		JPanel subWaitTab = new JPanel();
		waitTab.addTab("Ticket Maker", null, subWaitTab, null);
		subWaitTab.setLayout(null);
		// ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈ Load images
		// ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
		
		
		menuButtonsImage = new ImageIcon[64];
		for (int i = 0; i < menuButtonsImage.length; i++) 
		{
			menuButtonsImage[i] = new ImageIcon("./src/Unknown.jpeg");

		}
		//≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈ Start ButtonSubPanels ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
		
		subWaitTab.setBounds(6, 6, 810, 810);
		menuPanel.add(subWaitTab);
		
		JPanel entreeButtonPanel = new JPanel();
		entreeButtonPanel.setBounds(40, 32, 730, 199);
		subWaitTab.setBorder(BorderFactory.createLoweredBevelBorder());
		subWaitTab.add(entreeButtonPanel);
		entreeButtonPanel.setLayout(new GridLayout(3, 8, 2, 2));
		entreeButtonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		JPanel drinkButtonPanel = new JPanel();
		drinkButtonPanel.setBounds(40, 270, 730, 184);
		subWaitTab.add(drinkButtonPanel);
		drinkButtonPanel.setLayout(new GridLayout(2, 8, 2, 2));
		drinkButtonPanel.setBorder(BorderFactory.createLoweredBevelBorder());

		JPanel dessertButtonPanel = new JPanel();
		dessertButtonPanel.setBounds(40, 516, 730, 116);
		subWaitTab.add(dessertButtonPanel);
		dessertButtonPanel.setLayout(new GridLayout(2, 8, 2, 2));
		dessertButtonPanel.setBorder(BorderFactory.createLoweredBevelBorder());

		JPanel sidesButtonPanel = new JPanel();
		sidesButtonPanel.setBounds(40, 677, 730, 63);
		subWaitTab.add(sidesButtonPanel);
		sidesButtonPanel.setLayout(new GridLayout(1, 8, 2, 2));
		sidesButtonPanel.setBorder(BorderFactory.createLoweredBevelBorder());

//		
		//≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈ End ButtonSubPanels ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
		//≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈ Start ButtonLabels ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈


		JLabel lblMainEntree = new JLabel("Main Entree");
		lblMainEntree.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblMainEntree.setBounds(360, 6, 145, 25);
		subWaitTab.add(lblMainEntree);
		
		JLabel lblBeverages = new JLabel("Beverages");
		lblBeverages.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblBeverages.setBounds(360, 243, 145, 25);
		subWaitTab.add(lblBeverages);
		
		JLabel lblDesserts = new JLabel("Desserts");
		lblDesserts.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDesserts.setBounds(360, 466, 145, 25);
		subWaitTab.add(lblDesserts);
		
		JLabel lblSideOrders = new JLabel("Side Orders");
		lblSideOrders.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSideOrders.setBounds(360, 640, 145, 25);
		subWaitTab.add(lblSideOrders);
		
		JLabel lblTicketDisplay = new JLabel("Ticket Display");
		lblTicketDisplay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTicketDisplay.setBounds(830, 6, 145, 25);
		subWaitTab.add(lblTicketDisplay);
		//≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈ End ButtonLabels ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
		//≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈ Start Load Buttons ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈

	
				for (int j = 0; j < 24; j++) {
					menuButtons[j] = new JButton(menuButtonsImage[j]);
					
					entreeButtonPanel.add(menuButtons[j]);
					menuButtons[j].addActionListener(new buttonListener());
				}
				for (int j = 24; j < 40; j++) {
					menuButtons[j] = new JButton(menuButtonsImage[j]);
					
					drinkButtonPanel.add(menuButtons[j]);
					menuButtons[j].addActionListener(new buttonListener());
				}
				for (int j = 40; j < 56; j++) {
					menuButtons[j] = new JButton(menuButtonsImage[j]);
					
					dessertButtonPanel.add(menuButtons[j]);
					menuButtons[j].addActionListener(new buttonListener());
				}
				for (int j = 56; j < 64; j++) {
					menuButtons[j] = new JButton(menuButtonsImage[j]);
					
					sidesButtonPanel.add(menuButtons[j]);
					menuButtons[j].addActionListener(new buttonListener());
				}			
	//≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈ End Load Buttons ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈

				
		
		
		//-----------------------------Order buttons-------------------------------
		
		
		final JButton closeOrder = new JButton("Close Order");
		final JButton newOrder = new JButton("New Order");
		newOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openTicket=false;
				newOrder.setEnabled(openTicket);
				closeOrder.setEnabled(!openTicket);
				items = new ArrayList<Item>();
				display.selectAll();
				display.replaceSelection("");
				currentOrder.setText("Current Order: "+orderNumber);
				
			}
		});
		
		
		newOrder.setBounds(10, 15, 117, 29);
		setOrderPanel.add(newOrder);
		
		closeOrder.setEnabled(!openTicket);
		closeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//brings ticket up in window
				openTicket=true;
				newOrder.setEnabled(openTicket);
				closeOrder.setEnabled(!openTicket);
				order = new Order(items, 1, 1);
				orders.add(order);
				orderNumber++;
				
			}
		});
		closeOrder.setBounds(10, 40, 117, 29);
		setOrderPanel.add(closeOrder);
		//----------------------END order buttons-------------------------
		
	}
	
	public JTabbedPane m()
	{
		return menu;
	}
	
	private class buttonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			if (event.getSource() == menuButtons[0]) {

				if(openTicket == false)
				{
					display.insert("burger\n", 0);
					item = new Item("burger", 1, 6.00);
					items.add( item);
				}
			} else if (event.getSource() == menuButtons[1]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[2]) {
				System.out.println("button 2 wrks");
			} else if (event.getSource() == menuButtons[3]) {
				System.out.println("button 3 wrks");
			} else if (event.getSource() == menuButtons[4]) {
				System.out.println("button 4 wrks");
			} else if (event.getSource() == menuButtons[5]) {
				System.out.println("button 5 wrks");
			} else if (event.getSource() == menuButtons[6]) {
				System.out.println("button 6 wrks");
			} else if (event.getSource() == menuButtons[7]) {
				System.out.println("button 7 wrks");
			} else if (event.getSource() == menuButtons[8]) {
				System.out.println("button 8 wrks");
			} else if (event.getSource() == menuButtons[9]) {
				System.out.println("button 9 wrks");
			} else if (event.getSource() == menuButtons[10]) {
				System.out.println("button 10 wrks");
			} else if (event.getSource() == menuButtons[11]) {
				System.out.println("button 11 wrks");
			} else if (event.getSource() == menuButtons[12]) {
				System.out.println("button 12 wrks");
			} else if (event.getSource() == menuButtons[13]) {
				System.out.println("button 13 wrks");
			} else if (event.getSource() == menuButtons[14]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[15]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[16]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[17]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[18]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[19]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[20]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[21]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[22]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[23]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[24]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[25]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[26]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[27]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[28]) {
				System.out.println("button 1 wrks");
			} else if (event.getSource() == menuButtons[29]) {
				System.out.println("button 1 wrks");
			}
		}
	}

}
