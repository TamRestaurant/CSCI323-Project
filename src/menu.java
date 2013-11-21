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


public class menu extends JFrame
{
        private   JTabbedPane menu;
        private JPanel panel;
        private ImageIcon[] menuButtonsImage;
        private  JPanel menuPanel;
        private JPanel subWaitTab,sidesButtonPanel,entreeButtonPanel,drinkButtonPanel,dessertButtonPanel;
        private   JButton[] menuButtons;
        private JSeparator separator;
        private boolean openTicket = true;
        private Order order;
        private ArrayList<Item> items;
        private Item item;
        public   ArrayList<Order> orders;
        private int orderNumber;
        private JLabel currentOrder; 
        private JLabel currentTable;
        private Vector<String> food;
        private JList list;
        private ArrayList<Item> menuItems;
		//private MouseListener mouseListener;
        private long time;
        private int table;
        private JList openList;
        private Vector<Order> openFood;
        private JTextField serverIDTextBox;
        private dbAction db;
        private  JPanel setOrderPanel,openOrders;
        
        private String message;
    	private static JTextArea  messagesList;
    	private JScrollPane scrollMessages;
    	private JTextArea txtMessage;
    	//private String fromKitchen;
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
                
                orderButtons = new JTabbedPane(JTabbedPane.TOP);
                openOrders = new JPanel();
                openOrders.setBounds(810, 6, 255, 810);
                openOrders.setLayout(null);
                orderButtons.setBounds(810, 6, 255, 810);
                 setOrderPanel = new JPanel();
                setOrderPanel.setBounds(810, 6, 255, 810);
                setOrderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
                orderButtons.addTab("New Order", null, setOrderPanel, null);
                orderButtons.addTab("Open Orders", null, openOrders, null);
                menuPanel.add(orderButtons);
                setOrderPanel.setLayout(null);
                //---------------Open Orders Tab--------------------------
                edit = new JButton("Edit");
                openFood = orderToVector(db.getOpenOrders());
                openList = new JList(openFood);
                openList.setBounds(5, 20, 230, 300);
                openList.setEnabled(true);
                openOrders.add(openList);
                edit.setBounds(50, 330, 117, 29);
                openOrders.add(edit);
                
                frame = new JWindow();
   			 	editPanel = new JPanel();
   			 	add = new JButton("Add an item");
   			 	done= new JButton("Done");
   			 	v = new Vector<Item>();
                editList= new JList(v);
                editList.setBounds(0,0,400,400);
                add.setBounds(50, 450, 120, 20);
                done.setBounds(250, 450, 120, 20);
                panel.setPreferredSize(new Dimension(400, 600));
                panel.setLayout(null);
   			 	panel.add(editList);
   			 	panel.add(add);
   			 	panel.add(done);
   			 	frame.setContentPane(panel);
   			 	frame.pack();
   			 	frame.setVisible(false);
   			 	frame.setLocationRelativeTo(null);
                edit.addActionListener(new ActionListener()
                {
                	 public void actionPerformed(ActionEvent e) 
                	 {
                		 frame.setVisible(true);
                		 orderButtons.setEnabled(false);
                		 edit.setEnabled(false);
                		 int[] sel = openList.getSelectedIndices();
                		 if(sel.length >1 || sel.length<1)
                		 {
                			 JOptionPane.showMessageDialog(openOrders, "Please select one order."); 
                		 }
                		 else
                		 {
     
                			 editOrderNumber = openFood.get(sel[0]).getOrderNumber();
                			 v.addAll(itemToVector(openFood.get(sel[0]).getItems()));
                			 oldSize = v.size();
                			 editList.setListData(v);
                			 
                	
             
                			 add.addActionListener(new ActionListener()
                			 {
                				 public void actionPerformed(ActionEvent e)
                				 {
                					 editing = true;
                					 openTicket = false;
                					 menuPanel.grabFocus();
                					 frame.setVisible(false);
                					 
                					 
                				 }
                			 });
                		 }
                	 }
                });
                done.addActionListener(new ActionListener()
                {
                	 public void actionPerformed(ActionEvent e) 
                	 {
                		 for(;oldSize<v.size();oldSize++)
                		 {
                			 db.addItemExistingOrder(editOrderNumber, v.elementAt(oldSize).getOrderMenuItemID(), v.elementAt(oldSize).getItemComment()) ;
                		 }
                		 frame.setVisible(false);
                		 orderButtons.setEnabled(true);
    					 edit.setEnabled(true);
    					 v.clear();
                		 
                	 }
                });
                //-------------------End Open Orders Tab--------------------
                
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
               
//---------------------------------------------------------------------------------------------                
               // subWaitTab.setBounds(6, 6, 810, 810);
                
                
                
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
                
                scroll = new JScrollPane(subWaitTab, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
               // scroll.setLayout(null);
                scroll.setBounds(6, 6, 810, 810);
                menuPanel.add(scroll);
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
  
                             	for(JButton b:menuButtons) {
                         			b.setFont(new Font("Calibri", Font.PLAIN, 9));
                        		}
                              
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
                                	orderButtons.setEnabled(false);
                                	items = new ArrayList<Item>();
                                	food.clear();
                                	list.setListData(food);
                                	currentOrder.setText("Current Order: "+orderNumber);
                                	currentTable.setText("Current Table: "+table);
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
									orderButtons.setEnabled(true);
									order = new Order(items, table, employeeID, orderNumber);
									 // db.addOrder(order);
									if (db.addOrder(order)) {
										System.out.println("added" + order);
									}
									//refresh database connection
									//db=new dbAction();
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

		
        
        public JTabbedPane m()
        {
                return menu;
        }
        private Vector<Order> orderToVector(ArrayList<Order> array)
        {
        	 Vector<Order> v=new Vector<Order>();
			 for(Order o : array)
			 {
				 v.add(o);
			 }
			 
			 return v;
        	
        }
        private Vector<Item> itemToVector(ArrayList<Item> array)
        {
        	 Vector<Item> v=new Vector<Item>();
			 for(Item o : array)
			 {
				 v.add(o);
			 }
			 
			 return v;
        	
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
					{
						item = new Item (menuItems.get(i).getItemName(),menuItems.get(i).getDescription(),menuItems.get(i).getCategory(),menuItems.get(i).getitemID(), menuItems.get(i).getItemPrice(), comments );
						
						if(editing == true)
						{
							v.add(item);
							editList.setListData(v);
							frame.setVisible(true);
						}
						else
						{
							items.add( item);
							food.add(item.getItemName());
							list.setListData(food);
							
						}
					}
					else
					{
						item = new Item (menuItems.get(i).getItemName(),menuItems.get(i).getDescription(),menuItems.get(i).getCategory(),menuItems.get(i).getitemID(), menuItems.get(i).getItemPrice() );
						
						if(editing == true)
						{
							v.add(item);
							editList.setListData(v);
							frame.setVisible(true);
						}
						else
						{
							items.add( item);
							food.add(item.getItemName());
							list.setListData(food);
						}
					}
				}
			}
        	
        }
    	
    	
    	//---------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------

    	public static  void setKitchenMassages(String m)
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
