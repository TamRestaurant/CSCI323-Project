import javax.swing.JPanel;

import java.awt.Rectangle;

import javax.swing.JDesktopPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JTabbedPane;
/***
 * Admin tab, brings in all the managerial functionality tabs
 * Added comment to test github
 * @author Austin
 *
 */

public class admin extends JPanel {
	private static JTabbedPane tabbedPane;
	private static  employeeGui empGui;
	private static orderHistory orderHist;
	private static employeeTimeTrackingGui empTimeTracking;
	/**
	 * Create the panel.
	 */
	public admin(dbAction DBAction) {
		setBounds(new Rectangle(5, 5, 1100, 550));
		setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1100, 550);
		add(tabbedPane);
		
		empGui = new employeeGui(DBAction);
		orderHist = new orderHistory(DBAction);
		empTimeTracking = new employeeTimeTrackingGui(DBAction);
		
		
		tabbedPane.addTab("Employee Management", empGui.getEmployeeGui());
		tabbedPane.addTab("Order History", orderHist.getOrderHistory());
		tabbedPane.addTab("Employee Time Tracking", empTimeTracking);
		
		//tabbedPane.addTab("Order History", panelOrderHistory);
		

	}
	
	public static void setEnabledPanel(boolean is) {
		
		Component[]comps= empGui.getEmployeeGui().getComponents();
		for(Component c:comps) {
			c.setEnabled(is);
		}
		 comps= orderHist.getOrderHistory().getComponents();
		for(Component c:comps) {
			c.setEnabled(is);
		}
		 comps= empTimeTracking.getComponents();
			for(Component c:comps) {
				c.setEnabled(is);
			}
	}
	
	public JTabbedPane getAdminPanel(){
		return tabbedPane;
	}
}
