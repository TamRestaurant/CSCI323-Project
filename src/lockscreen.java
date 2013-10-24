import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;

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


public class lockscreen extends JFrame 
{
	private JTabbedPane lockscreen;
	private JPanel panel_4;
	public lockscreen()
	{
		JButton unlockBtn=new JButton("Unlock Workstation");
		
		unlockBtn.setBounds(0, 170, 445, 23);
		JButton lockBtn=new JButton("Lock Workstation");
		lockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kitchen.k().setEnabled(false);//here
			//menu.m().disable();//here
				 JOptionPane.showMessageDialog(null, "yay!!");
			}
		});
		lockBtn.setBounds(0, 75, 445, 23);
		JLabel passwordLabel=new JLabel();
		passwordLabel.setBounds(111, 23, 89, 226);
		lockscreen = new JTabbedPane(JTabbedPane.TOP);
		panel_4 = new JPanel();
		
		lockscreen.addTab("New tab", null, panel_4, null);
		
		final JPasswordField pwdYourPin = new JPasswordField();
		pwdYourPin.setText("xxxxxxxx");
		pwdYourPin.setBounds(242, 122, 62, 29);
		panel_4.setLayout(null);
		//subPanel1.add(displayText, BorderLayout.CENTER);
		passwordLabel.setText("Enter unlock code");
		panel_4.add(passwordLabel);
		panel_4.add(pwdYourPin);
		panel_4.add(unlockBtn);
		panel_4.add(lockBtn);
		unlockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 char[] enteredPwrd=pwdYourPin.getPassword();
				 String enteredStrPwd="";
				 for(char i : enteredPwrd) {
					 enteredStrPwd+=i;
				 }
				 System.out.println(enteredStrPwd);
			
//				  enteredStrPwd=pwdYourPin.getText();

				 if (enteredStrPwd.equals("rest")) {
					 
					 kitchen.k().setVisible(false);
				JOptionPane.showMessageDialog(null, "yay!!");
				 }else {
					 JOptionPane.showMessageDialog(null, "nay");
				 }
				
			}
		});
	}
	
	

	public JTabbedPane lock()
	{
		return lockscreen;
	}
}
