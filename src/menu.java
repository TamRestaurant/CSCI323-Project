import java.awt.BorderLayout;
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
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
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


public class menu extends JFrame
{
        private static JTabbedPane menu;
        private JPanel panel;
        private ImageIcon[] menuButtonsImage;
        private static JPanel menuPanel;
        private JPanel subWaitTab,sidesButtonPanel,entreeButtonPanel,drinkButtonPanel,dessertButtonPanel;
        private JButton[] menuButtons;
        private JSeparator separator;
        private boolean openTicket = true;
        private Order order;
        private ArrayList<Item> items;
        private Item item;
        public static ArrayList<Order> orders;
        private int orderNumber;
        private JLabel currentOrder; 
        private JLabel currentTable;
        private Vector<String> food;
        private JList list;
        private ArrayList<Item> menuItems;
		//private MouseListener mouseListener;
        private long time;
        private int table;
      
        private JTextField serverIDTextBox;
        private dbAction db;
        private static JPanel setOrderPanel,openOrders;
        
        private String message;
    	private static JTextArea  messagesList;
    	private JScrollPane scrollMessages;
    	private JTextArea txtMessage;
    	//private String fromKitchen;
    	private JButton btnSend_mesage;
    	private JButton btnClear_message;
    	
        
    	 public menu(String messages)
         {
    		 String fromKitchen = messages;
         }
    	 
    	 /**
 		 * @wbp.parser.constructor
 		 */
        public menu(ArrayList<Item> mItems,dbAction dba)
        {
        		db=dba;
                menu = new JTabbedPane(JTabbedPane.TOP);
                menuItems = mItems;
                panel = new JPanel();
                menuPanel = new JPanel();
                menuButtons = new JButton[64];
                separator = new JSeparator();
                orders= new ArrayList<Order>();
                orderNumber = 1;
                currentOrder = new JLabel("Current Order");
                currentTable = new JLabel("Current Table");
                separator.setBounds(432, 259, 1, 12);
                menuPanel.add(separator);
                //-------------------Display Panel-----------------------------------
                
                JTabbedPane orderButtons = new JTabbedPane(JTabbedPane.TOP);
                openOrders = new JPanel();
                openOrders.setBounds(830, 6, 200, 810);
                openOrders.setLayout(null);
                orderButtons.setBounds(830, 6, 200, 810);
                 setOrderPanel = new JPanel();
                setOrderPanel.setBounds(830, 6, 200, 810);
                setOrderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
                orderButtons.addTab("New Order", null, setOrderPanel, null);
                orderButtons.addTab("Open Orders", null, openOrders, null);
                menuPanel.add(orderButtons);
                setOrderPanel.setLayout(null);
                
                
                currentOrder.setBounds(20, 130, 500, 20);
                currentTable.setBounds(20, 115, 500, 20);
                setOrderPanel.add(currentOrder);
                setOrderPanel.add(currentTable);
                //----------------------END Display Panel---------------------------
                menu.addTab("Menu", null, menuPanel, null);
                menuPanel.setLayout(null);
                //----------------END load images-----------------------
//                JTabbedPane mainTab = new JTabbedPane(JTabbedPane.TOP);
//                mainTab.setBounds(5, 5, 900, 900);
//                subWaitTab.add(mainTab);
//                
//                JTabbedPane waitTab = new JTabbedPane(JTabbedPane.TOP);
//                mainTab.addTab("Wait Station", null, waitTab, null);
                
                JPanel subWaitTab = new JPanel();
               // menu.addTab("Ticket Maker", subWaitTab);
                subWaitTab.setLayout(null);
                //---------------------------------------------------------------------------------------------                
                     /*
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 */
//                orders = new ArrayList<Order>();
//
//                        for (int i = 0; i < 9; i++) {
//                                Order o = new Order();
//                                o.setOrderNumber(i * 325);
//                                o.setOrderTotal(i + 11);
//                                orders.add(o);
//                        }
                        /*
                        *
                        *
                        *
                        *
                        *
                        *
                        */
                menuButtonsImage = new ImageIcon[64];
                for (int i = 0; i < menuButtonsImage.length; i++) 
                {
                        menuButtonsImage[i] = new ImageIcon("./src/Unknown.jpeg");

                }
//---------------------------------------------------------------------------------------------                
                subWaitTab.setBounds(6, 6, 810, 810);
                menuPanel.add(subWaitTab);
                
                JPanel entreeButtonPanel = new JPanel();
                entreeButtonPanel.setBounds(40, 32, 730, 199);
                subWaitTab.setBorder(BorderFactory.createLoweredBevelBorder());
                subWaitTab.add(entreeButtonPanel);
                entreeButtonPanel.setLayout(new GridLayout(3, 8, 2, 2));
                entreeButtonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
                
                JPanel drinkButtonPanel = new JPanel();
                drinkButtonPanel.setBounds(40, 270, 730, 125);
                subWaitTab.add(drinkButtonPanel);
                drinkButtonPanel.setLayout(new GridLayout(2, 8, 2, 2));
                drinkButtonPanel.setBorder(BorderFactory.createLoweredBevelBorder());

                JPanel dessertButtonPanel = new JPanel();
                dessertButtonPanel.setBounds(40, 430, 730, 116);
                subWaitTab.add(dessertButtonPanel);
                dessertButtonPanel.setLayout(new GridLayout(2, 8, 2, 2));
                dessertButtonPanel.setBorder(BorderFactory.createLoweredBevelBorder());

                JPanel sidesButtonPanel = new JPanel();
                sidesButtonPanel.setBounds(40, 581, 730, 63);
                subWaitTab.add(sidesButtonPanel);
                sidesButtonPanel.setLayout(new GridLayout(1, 8, 2, 2));
                sidesButtonPanel.setBorder(BorderFactory.createLoweredBevelBorder());

//                
            	// //---------------------------------- New to
        		// add-----------------------------------------------------------
        		JPanel MessagePanel = new JPanel();
        		MessagePanel.setBounds(40, 680, 730, 100);
        		subWaitTab.add(MessagePanel);
        		MessagePanel.setLayout(null);
        		MessagePanel.setBorder(BorderFactory.createLoweredBevelBorder());
        		 //---------------------------------------------------------------------------------------------                
                //---------------------------------------------------------------------------------------------  
        		 btnSend_mesage = new JButton("Send Message");
        		 btnClear_message = new JButton("Clear Messages");
        		 //---------------------------------------------------------------------------------------------                
                 //---------------------------------------------------------------------------------------------  

        		btnSend_mesage.setEnabled(true);
        		btnSend_mesage.setBounds(15, 20, 110, 20);
        		MessagePanel.add(btnSend_mesage);

        		btnClear_message.setEnabled(true);
        		btnClear_message.setBounds(15, 50, 110, 20);
        		MessagePanel.add(btnClear_message);

        		txtMessage = new JTextArea();
        		txtMessage.setBounds(140, 10, 500, 20);
        		MessagePanel.add(txtMessage);

        		 //---------------------------------------------------------------------------------------------                
                //---------------------------------------------------------------------------------------------                

        		btnSend_mesage.addActionListener(new buttonListener());
        		btnClear_message.addActionListener(new buttonListener());

        		scrollMessages = new JScrollPane();
        		scrollMessages.setBounds(140, 40, 500, 50);//lower
        		MessagePanel.add(scrollMessages);
        		messagesList = new JTextArea();
        		//MessagePanel.add(messagesList);
        		MessagePanel.add(scrollMessages);
        		scrollMessages.setViewportView(messagesList);
        		messagesList.setEnabled(true);
                //---------------------------------------------------------------------------------------------                
                //---------------------------------------------------------------------------------------------                

        		
        		
                //---------------------------------------------------------------------------------------------                

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
                lblDesserts.setBounds(360, 400, 145, 25);
                subWaitTab.add(lblDesserts);
                
                JLabel lblSideOrders = new JLabel("Side Orders");
                lblSideOrders.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
                lblSideOrders.setBounds(360, 555, 145, 25);
                subWaitTab.add(lblSideOrders);
                
                JLabel lblTicketDisplay = new JLabel("Ticket Display");
                lblTicketDisplay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
                lblTicketDisplay.setBounds(830, 6, 145, 25);
                subWaitTab.add(lblTicketDisplay);
                //---------------------------------------------------------------------------------------------                

        
                                for (int j = 0; j < 24; j++) {
                                        menuButtons[j] = new JButton(menuItems.get(j).getItemName());
                                        
                                        entreeButtonPanel.add(menuButtons[j]);
                                        menuButtons[j].addMouseListener(new mouseListener());
                                }
                                for (int j = 24; j < 40; j++) {
                                        menuButtons[j] = new JButton(menuItems.get(j).getItemName());
                                        
                                        drinkButtonPanel.add(menuButtons[j]);
                                        menuButtons[j].addMouseListener(new mouseListener());
                                }
                                for (int j = 40; j < 56; j++) {
                                        menuButtons[j] = new JButton(menuItems.get(j).getItemName());
                                        
                                        dessertButtonPanel.add(menuButtons[j]);
                                        menuButtons[j].addMouseListener(new mouseListener());
                                }
                                for (int j = 56; j < 64; j++) {
                                        menuButtons[j] = new JButton(menuItems.get(j).getItemName());
                                        
                                        sidesButtonPanel.add(menuButtons[j]);
                                        menuButtons[j].addMouseListener(new mouseListener());
                                }                        
                                //---------------------------------------------------------------------------------------------                
        
                
                             
                //-----------------------------Order buttons-------------------------------
                
                             
                final JButton closeOrder = new JButton("End Order");
                final JButton newOrder = new JButton("New Order");
                final JButton remove = new JButton("Remove");
                list = new JList(); 
                food = new Vector<String>();
                final Object[] tables = {1, 2, 3, 4, 5};
                newOrder.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                
                                Object t = JOptionPane.showInputDialog(menu,"What is the table number?","",JOptionPane.PLAIN_MESSAGE, null,tables,null);
                                if(t == null)
                                {}
                                else
                                {
                                	table = (Integer)t;
                                	openTicket=false;
                                	newOrder.setEnabled(openTicket);
                                	closeOrder.setEnabled(!openTicket);
                                	items = new ArrayList<Item>();
                                	food.clear();
                                	list.setListData(food);
                                	currentOrder.setText("Current Order: "+orderNumber);
                                	currentTable.setText("Current Table: "+table);
                                }
                                 
                                
                                
                        }
                });
                list.setBounds(15, 150, 170, 300);
                list.setEnabled(true);
                setOrderPanel.add(list);
                newOrder.setBounds(10, 65, 117, 29);
                setOrderPanel.add(newOrder);
                remove.setEnabled(true);
                remove.setBounds(15, 450, 117, 29);
                closeOrder.setEnabled(!openTicket);
                setOrderPanel.add(remove);
                closeOrder.addActionListener(new ActionListener() {
                	
                	/**
                	 * TODO: We need to know what employee is processing the order
                	 */
                        public void actionPerformed(ActionEvent e) {
                                if (items!=null) {
									//brings ticket up in window
									//TODO: Replace this employeeNumber with the actual employee number of whoever is taking order
									int employeeID = 1;
									try {
										employeeID = Integer
												.parseInt(serverIDTextBox
														.getText());
									} catch (NumberFormatException e1) {
										// TODO Auto-generated catch block
										JOptionPane
												.showMessageDialog(null,
														"Enter numbers only into server ID field.");
										return;
									}
									openTicket = true;
									newOrder.setEnabled(openTicket);
									closeOrder.setEnabled(!openTicket);
									order = new Order(items, table, employeeID);
									 // db.addOrder(order);
									if (db.addOrder(order)) {
										System.out.println("added" + order);
									}
									//refresh database connection
									 //  db=new dbAction();
									ArrayList<Order> oo = db.getOpenOrders();
									for (Order o : oo) {
										System.out.println(o);
									}
									orderNumber++;
								}
                                
                                else {
                                	JOptionPane
									.showMessageDialog(null,
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

                /**
                 * For soeme reason the list is re-ordered after this happens??
                 */
                remove.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                int[] sel = list.getSelectedIndices();
                                for(int i = 0; i<sel.length; i++)
                                {
                                        food.remove(i);
                                        items.remove(i);
                                }
                                list.setListData(food);
                        }
                });
                //----------------------END order buttons-------------------------
                
        }
        
//---------------------------------------------------------------------------------------------------------------
      //---------------------------------------------------------------------------------------------------------------

        private class buttonListener implements ActionListener {
    		public void actionPerformed(ActionEvent event) {
    			
    			if (event.getSource() == btnSend_mesage) {
    				String temp="";
    				temp=messagesList.getText()+"\n";
    				temp = temp +txtMessage.getText();
    				messagesList.setText(temp);
    				setKitchenMassages(temp);
    				sendToKitchen();
    			}
    			else if (event.getSource() == btnClear_message)
    			{
    				messagesList.setText("");
    				txtMessage.setText("");
    			}
    		}
    		}
      //---------------------------------------------------------------------------------------------------------------
      //---------------------------------------------------------------------------------------------------------------

		
				public static ArrayList<Order> getOrders(){
                
                return orders;
        }
        public JTabbedPane m()
        {
                return menu;
        }
        
        private class mouseListener implements MouseListener
        {
        	
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
				if(!openTicket)
					time = System.currentTimeMillis();
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				if(!openTicket)
				{
					String comments=null ;
					//This checks to see if button was held down, if so message box will open to allow user to type in comments about item and add it to item
					if(( System.currentTimeMillis()-time)>=1000)
					{
						comments = JOptionPane.showInputDialog( "Modifications" );
					}
					int i =0;
					while(event.getSource()!=menuButtons[i])
						i++;
					if(comments != null)
						item = new Item (menuItems.get(i).getItemName(),menuItems.get(i).getDescription(),menuItems.get(i).getCategory(),menuItems.get(i).getitemID(), menuItems.get(i).getItemPrice(), comments );
					else
						item = new Item (menuItems.get(i).getItemName(),menuItems.get(i).getDescription(),menuItems.get(i).getCategory(),menuItems.get(i).getitemID(), menuItems.get(i).getItemPrice() );
					items.add( item);
					food.add(item.getItemName());
					list.setListData(food);
				}
			}
        	
        }
    	public static void setEnabledPanel(boolean is) {
    		//get components and set value
    		Component[]comps= menuPanel.getComponents();
    		for(Component c:comps) {
    			c.setEnabled(is);
    		}
    		comps= menu.getComponents();    		
    		for(Component c:comps) {
    			c.setEnabled(is);
    		}
    		comps= setOrderPanel.getComponents();    		
    		for(Component c:comps) {
    			c.setEnabled(is);
    		} 	comps= openOrders.getComponents();    		
    		for(Component c:comps) {
    			c.setEnabled(is);
    		} 
    	}
    	
    	//---------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------

    	public static void setKitchenMassages(String m)
    	{
    		messagesList.setText(m);
    	}
    	public void sendToKitchen()
    	{
    		String m ="";
    		m=messagesList.getText();
    		kitchen.setMenuMassages(m+"\n");
    	}
    	//---------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------


}
