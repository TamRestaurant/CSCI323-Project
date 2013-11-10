import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

//import RPSGui.buttonListener;


import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class lockscreen extends JFrame 
{
        private JTabbedPane lockscreen;
        private JPanel lockPanel,subLockpanel;
        public lockscreen()
        {
                JButton unlockBtn=new JButton("Unlock Workstation");
                
                unlockBtn.setBounds(28, 170, 445, 23);
                JButton lockBtn=new JButton("Lock Workstation");
                lockBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                kitchen.setEnabledPanel(false);//here
                                menu.setEnabledPanel(false);
                                register.setEnabledPanel(false);
                                admin.setEnabledPanel(false);
                                clockInPanel.setEnabledPanel(false);
                        //menu.m().disable();//here
                                 JOptionPane.showMessageDialog(null, "Locked!!");
                        }
                });
                lockBtn.setBounds(28, 75, 445, 23);
                JLabel passwordLabel=new JLabel();
                passwordLabel.setBounds(111, 23, 200, 226);
                lockscreen = new JTabbedPane(JTabbedPane.TOP);
                lockPanel = new JPanel();
                subLockpanel = new JPanel();
                subLockpanel.setLayout(null);
                subLockpanel.setBounds(5, 5, 900, 900);
                subLockpanel.setBorder(BorderFactory.createLoweredBevelBorder());
                lockPanel.setBorder(BorderFactory.createLoweredBevelBorder());

                
                lockscreen.addTab("Password tab", null, subLockpanel, null);
                lockscreen.setBorder(BorderFactory.createLoweredBevelBorder());
                
                subLockpanel.add(lockPanel);
                lockPanel.setBounds(275, 50, 500, 250);
                
                final JPasswordField pwdYourPin = new JPasswordField();
                pwdYourPin.addFocusListener(new FocusAdapter() {
                	@Override
                	public void focusGained(FocusEvent arg0) {
                		 pwdYourPin.setText("");
                	}
                });
                pwdYourPin.setText("tam");
                pwdYourPin.setBounds(242, 122, 62, 29);
                lockPanel.setLayout(null);
                //subPanel1.add(displayText, BorderLayout.CENTER);
                passwordLabel.setText("Enter unlock code");
                lockPanel.add(passwordLabel);
                lockPanel.add(pwdYourPin);
                lockPanel.add(unlockBtn);
                lockPanel.add(lockBtn);
                unlockBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                
                                 char[] enteredPwrd=pwdYourPin.getPassword();
                                 String enteredStrPwd="";
                                 for(char i : enteredPwrd) {
                                         enteredStrPwd+=i;
                                 }
                                 System.out.println(enteredStrPwd);
                        
//                                  enteredStrPwd=pwdYourPin.getText();

                                 if (enteredStrPwd.equals("tam")) {
                                         
                                	 kitchen.setEnabledPanel(true);//here
                                     menu.setEnabledPanel(true);
                                     register.setEnabledPanel(true);
                                     admin.setEnabledPanel(true);
                                     clockInPanel.setEnabledPanel(true);
                             //menu.m().disable();//here
                                      JOptionPane.showMessageDialog(null, "UNLocked!!");
                                 }else {
                                         JOptionPane.showMessageDialog(null, "Wrong Password.");
                                 }
                                
                        }
                });
        }
        
        

        public JTabbedPane lock()
        {
                return lockscreen;
        }
}
