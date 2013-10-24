import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;

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



//import RPSGui.buttonListener;

import java.awt.GridLayout;


public class register extends JFrame 
{
	private JTabbedPane reg;
	private JPanel panel_2;
	private JButton [] cashierButtons;

	public register()
	
	{
		panel_2 = new JPanel();
		cashierButtons = new JButton[36];

		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 600, 600);
		panel_2.add(panel);
		panel.setLayout(new GridLayout(6, 6, 3, 3));
		reg = new JTabbedPane(JTabbedPane.TOP);
		
		
		reg.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
		
		
		
		for (int j = 0; j < 36; j++) {
			
			cashierButtons[j] = new JButton(""+j+1);
			panel.add(cashierButtons[j]);
			cashierButtons[j].addActionListener(new buttonListener());
		}
	}
	
	public JTabbedPane r()
	{
		return reg;
	}
	private class buttonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			if (event.getSource() == cashierButtons[0]) {
			System.out.println("button 1 works");
			}

//			What ever button does
		}
	}
}
