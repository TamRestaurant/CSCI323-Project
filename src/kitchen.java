import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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

public class kitchen extends JFrame {
	private static JTabbedPane kitchen;// here
	private JPanel activeOrdersPanel;
	private JLabel lblSelectOrd, lblComments;
	private JButton btnContact;
	private JCheckBox orderUp;
	private JTextArea display;
	private JTextField txtComment;
	private JComboBox orderComboBox;


	public kitchen() {

		// ---------------------------------------------------
		kitchen = new JTabbedPane(JTabbedPane.TOP);
		activeOrdersPanel = new JPanel();

		kitchen.addTab("Active Orders", null, activeOrdersPanel, null);
		activeOrdersPanel.setLayout(null);
		activeOrdersPanel.setBounds(10, 10, 900, 900);
		// ----------------------- instantiations ----------------------------
		lblSelectOrd = new JLabel("Select Order");
		lblComments = new JLabel("Type Comments to Wait Person");
		btnContact = new JButton("Send Notification");
		orderUp = new JCheckBox("Order Up");
		display = new JTextArea();
		txtComment = new JTextField();
		orderComboBox = new JComboBox();

		// ----------------------- settings ----------------------------
		lblSelectOrd.setBounds(15, 80, 170, 50);
		lblComments.setBounds(15, 180, 170, 50);
		btnContact.setBounds(15, 400, 170, 50);
		orderUp.setBounds(15, 300, 170, 50);
		txtComment.setBounds(15, 220, 170, 50);
		orderComboBox.setBounds(15, 120, 170, 50);
		display.setBounds(400, 100, 300, 695);
		display.setEditable(false);

		// ---------------------- adds to panel -----------------------------

		activeOrdersPanel.add(lblSelectOrd);
		activeOrdersPanel.add(txtComment);
		activeOrdersPanel.add(lblComments);
		activeOrdersPanel.add(btnContact);
		activeOrdersPanel.add(orderUp);
		activeOrdersPanel.add(display);
		activeOrdersPanel.add(orderComboBox);

		
		btnContact.addActionListener(new buttonListener());

		// ---------------------------------------------------

	

		// ---------------------------------------------------

	}

	public static JTabbedPane k()// here
	{
		return kitchen;
	}
	 ///////////invokes register frame
	 private class buttonListener implements ActionListener
	 {
		 public void actionPerformed(ActionEvent event)
	 {
	 System.out.println("works");
	 }
	 }
	 
}
