
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

import java.awt.GridBagLayout;


public class register extends JFrame 
{
        private JTabbedPane reg= new JTabbedPane(JTabbedPane.TOP);
        private JPanel panel_2;
        private JButton [] cashierButtons;

        public register()
        
        {
        	
        	JButton btnOpenRegister = new JButton("Open Register");
        	btnOpenRegister.addActionListener(new buttonListener());
        	reg.add(btnOpenRegister, BorderLayout.CENTER);
        }
        
        public JTabbedPane r()
        {
                return reg;
        }
        
        ///////////invokes register frame
        private class buttonListener implements ActionListener 
        {
                public void actionPerformed(ActionEvent event) 
                {
                	registerinterface.main(null);
//                        What ever button does
                }
        }
}
