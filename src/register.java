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
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class register extends JFrame {
	private JTabbedPane reg = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panel;
	private JButton[] cashierButtons;
	private dbAction DBAction;
	private JLabel enterPassword;
	private String registerPassword = "tam";
	private JPasswordField passwordBox;

	// pwdYourPin.setText("xxxxxxxx");
//
	public register(dbAction DBAction)

	{

		this.DBAction = DBAction;
		panel = new JPanel();
		passwordBox = new JPasswordField("tam");
		passwordBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				passwordBox.setText("");
			}
		});
		passwordBox.setBounds(294, 196, 121, 20);
		enterPassword = new JLabel("Enter Password");
		enterPassword.setBounds(314, 160, 121, 14);
		JButton btnOpenRegister = new JButton("Open Register");
		btnOpenRegister.setBounds(294, 238, 121, 23);
		btnOpenRegister.addActionListener(new buttonListener());
		panel.setLayout(null);
		panel.add(enterPassword);
		panel.add(passwordBox);
		panel.add(btnOpenRegister);
		reg.add(panel);// , BorderLayout.NORTH);
		// reg.add(btnOpenRegister, BorderLayout.CENTER);
		// reg.add(btnOpenRegister, BorderLayout.SOUTH);
	}

	public JTabbedPane r() {
		return reg;
	}

	// /////////invokes register frame
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
//			JOptionPane.
			
			String enteredpw = "";
			char[] pw = passwordBox.getPassword();
			for (char c : pw) {
				enteredpw += c;
			}
			if (enteredpw.equals(registerPassword)) {
				registerinterface.main(null, DBAction);
			} else {
				JOptionPane.showMessageDialog(null, "Password incorrect!!");
			}
			// What ever button does
		}
	}
}