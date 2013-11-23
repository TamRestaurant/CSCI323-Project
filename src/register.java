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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class register extends JFrame {
	private JTabbedPane reg = new JTabbedPane(JTabbedPane.TOP);
	private static JPanel panel;
	private JButton[] cashierButtons;
	private DbAction DBAction;
	private JLabel enterPassword;
	private String registerPassword = "tam";
	private JPasswordField passwordBox;
	private JPanel panel_1;

	// pwdYourPin.setText("xxxxxxxx");
//
	public register(DbAction DBAction)

	{

		this.DBAction = DBAction;
		panel = new JPanel();
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(355, 128, 301, 221);
		panel.add(panel_1);
		panel_1.setLayout(null);
		enterPassword = new JLabel("Enter Password");
		enterPassword.setBounds(90, 61, 121, 14);
		panel_1.add(enterPassword);
		enterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		passwordBox = new JPasswordField("tam");
		passwordBox.setBounds(90, 97, 121, 20);
		panel_1.add(passwordBox);
		passwordBox.setHorizontalAlignment(SwingConstants.CENTER);
		JButton btnOpenRegister = new JButton("Open Register");
//		btnOpenRegister.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//			}
//		});
		btnOpenRegister.setBounds(90, 139, 121, 23);
		panel_1.add(btnOpenRegister);
		panel_1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{passwordBox, btnOpenRegister}));
		btnOpenRegister.addActionListener(new buttonListener());
		passwordBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				passwordBox.setText("");
			}
		});
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
	public static void setEnabledPanel(boolean is) {
		
		Component[]comps= panel.getComponents();
		for(Component c:comps) {
			c.setEnabled(is);
		}
	}
}