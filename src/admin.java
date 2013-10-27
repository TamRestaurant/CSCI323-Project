import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;


public class admin extends JPanel {
	private JTabbedPane tabbedPane;
	private employeeGui empGui = new employeeGui();
	private orderHistory orderHist = new orderHistory();

	/**
	 * Create the panel.
	 */
	public admin() {
		setBounds(new Rectangle(5, 5, 1100, 550));
		setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1100, 550);
		add(tabbedPane);
		
		
		
		tabbedPane.addTab("Employee Management", empGui.getEmployeeGui());
		tabbedPane.addTab("Order History", orderHist.getOrderHistory());
		//tabbedPane.addTab("Order History", panelOrderHistory);
		

	}
	
	public JTabbedPane getAdminPanel(){
		return tabbedPane;
	}
}
