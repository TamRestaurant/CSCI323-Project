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
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Lockscreen extends JFrame {
	private JTabbedPane lockscreen;
	private JPanel lockPanel, subLockpanel;
	private JLabel lblLockWorkstation;
	private static String lockpwd="tam";

	public Lockscreen() {
		JButton unlockBtn = new JButton("Unlock Workstation");
		unlockBtn.setBackground(SystemColor.controlHighlight);
		unlockBtn.setFont(new Font("Calibri", Font.PLAIN, 14));

		unlockBtn.setBounds(28, 170, 445, 23);
		JButton lockBtn = new JButton("Lock Workstation");
		lockBtn.setBackground(SystemColor.controlHighlight);
		lockBtn.setFont(new Font("Calibri", Font.PLAIN, 14));
		lockBtn.addActionListener(new ActionListener() {
			///////////Lock
			public void actionPerformed(ActionEvent arg0) {
				RestWinMake.setEnabledPanel(false);
				JOptionPane.showMessageDialog(null, "Locked");
			}
		});
		lockBtn.setBounds(28, 75, 445, 23);
		lockscreen = new JTabbedPane(JTabbedPane.TOP);
		lockPanel = new JPanel();
		lockPanel.setBackground(SystemColor.windowBorder);
		subLockpanel = new JPanel();
		subLockpanel.setBackground(SystemColor.windowBorder);
		subLockpanel.setLayout(null);
		subLockpanel.setBounds(5, 5, 900, 900);
		subLockpanel.setBorder(BorderFactory.createRaisedBevelBorder());
		lockPanel.setBorder(BorderFactory.createRaisedBevelBorder());

		// lockscreen.addTab("Password tab", null, subLockpanel, null);
		lockscreen.setBorder(BorderFactory.createLoweredBevelBorder());
		lockscreen.add(subLockpanel);
		subLockpanel.add(lockPanel);
		lockPanel.setBounds(275, 106, 500, 250);

		final JPasswordField pwdYourPin = new JPasswordField();
		pwdYourPin.setHorizontalAlignment(SwingConstants.CENTER);
		pwdYourPin.setFont(new Font("Calibri", Font.PLAIN, 14));
		pwdYourPin.setBackground(SystemColor.scrollbar);
		pwdYourPin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				pwdYourPin.setText("");
			}
		});
		pwdYourPin.setText(lockpwd);
		pwdYourPin.setBounds(219, 116, 62, 29);
		lockPanel.setLayout(null);
		lockPanel.add(pwdYourPin);
		lockPanel.add(unlockBtn);
		lockPanel.add(lockBtn);

		lblLockWorkstation = new JLabel("Lock Workstation");
		lblLockWorkstation.setHorizontalAlignment(SwingConstants.CENTER);
		lblLockWorkstation.setForeground(SystemColor.activeCaption);
		lblLockWorkstation.setFont(new Font("Calibri", Font.BOLD, 28));
		lblLockWorkstation.setBounds(275, 38, 500, 36);
		subLockpanel.add(lblLockWorkstation);
		
		JButton changePwdBtn = new JButton("Change Lockscreen Password");
		changePwdBtn.setBackground(SystemColor.controlHighlight);
		changePwdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ChangeRegPassword.main(null,lockpwd,1);
			}
		});
		changePwdBtn.setFont(new Font("Calibri", Font.BOLD, 12));
		changePwdBtn.setBounds(429, 552, 195, 25);
		subLockpanel.add(changePwdBtn);
		unlockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////unlock
				
				char[] enteredPwrd = pwdYourPin.getPassword();
				String enteredStrPwd = "";
				for (char i : enteredPwrd) {
					enteredStrPwd += i;
				}
				System.out.println(enteredStrPwd);
				if (enteredStrPwd.equals(lockpwd)) {
					RestWinMake.setEnabledPanel(true);

					// menu.m().disable();//here
					JOptionPane.showMessageDialog(null, "Unlocked");
				} else {
					JOptionPane.showMessageDialog(null, "Wrong Password.");
				}

			}
		});
	}

	/**
	 * @return the lockpwd
	 */
	public static String getLockpwd() {
		return lockpwd;
	}

	/**
	 * @param lockpwd the lockpwd to set
	 */
	public static void setLockpwd(String  pwd) {
		 lockpwd =  pwd;
	}

	public JTabbedPane lock() {
		return lockscreen;
	}
}
