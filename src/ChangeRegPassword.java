import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class ChangeRegPassword extends JFrame {
	private JPasswordField oldpwtxt;
	private JPasswordField newpwtxt1;
	private JPasswordField newpwtxt2;
	private JButton btnOk;
	private String opw="";
	private static int source=0;
	
	public ChangeRegPassword(String oldpw) {
		setBounds(new Rectangle(350, 150, 400, 400));
		getContentPane().setLayout(null);
		opw=oldpw;
		JLabel lblEnterOldPassword = new JLabel("Enter Old Password");
		lblEnterOldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterOldPassword.setBounds(130, 60, 123, 14);
		getContentPane().add(lblEnterOldPassword);
		
		oldpwtxt = new JPasswordField();
		oldpwtxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				oldpwtxt.setText("");
			}
		});
		oldpwtxt.setHorizontalAlignment(SwingConstants.CENTER);
		oldpwtxt.setBounds(140, 81, 104, 20);
		getContentPane().add(oldpwtxt);
		
		JLabel lblEnterNewPassword = new JLabel("Enter New Password");
		lblEnterNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterNewPassword.setBounds(130, 108, 123, 14);
		getContentPane().add(lblEnterNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password");
		lblConfirmNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmNewPassword.setBounds(130, 156, 123, 14);
		getContentPane().add(lblConfirmNewPassword);
		
		newpwtxt1 = new JPasswordField();
		newpwtxt1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				newpwtxt1.setText("");
			}
		});
		newpwtxt1.setHorizontalAlignment(SwingConstants.CENTER);
		newpwtxt1.setBounds(140, 129, 104, 20);
		getContentPane().add(newpwtxt1);
		
		newpwtxt2 = new JPasswordField();
		newpwtxt2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				newpwtxt2.setText("");
			}
		});
		newpwtxt2.setHorizontalAlignment(SwingConstants.CENTER);
		newpwtxt2.setBounds(140, 177, 104, 20);
		getContentPane().add(newpwtxt2);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				
				char[] oldEnteredPwrd = oldpwtxt.getPassword();
				String oldEnteredStrPwd = "";
				for (char i : oldEnteredPwrd) {
					oldEnteredStrPwd += i;
				}
				
				char[] newPwrd1 = newpwtxt1.getPassword();
				String newStrPwd1 = "";
				for (char i : newPwrd1) {
					newStrPwd1 += i;
				}
				
				char[] newPwrd2 = newpwtxt2.getPassword();
				String newStrPwd2 = "";
				for (char i : newPwrd2) {
					newStrPwd2 += i;
				}
				
				///check old pw
				if(!opw.equals(oldEnteredStrPwd)) {
					JOptionPane.showMessageDialog(null, "Old password incorrect!!");
					return;
				}
				if(!newStrPwd1.equals(newStrPwd2)) {
					JOptionPane.showMessageDialog(null, "New passwords don't match!!");
					return;
				}
				opw=newStrPwd1;
				
				switch(source) {
				case 1:
					Lockscreen.setLockpwd(newStrPwd1);
					break;
				case 2:
					Register.setRegisterPassword(newStrPwd1);
				}
				
				//Lockscreen.setLockpwd(newStrPwd1);
				JOptionPane.showMessageDialog(null, "Password changed.");
				dispose();
				
			}
		});
		btnOk.setBounds(140, 208, 104, 23);
		getContentPane().add(btnOk);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args, final String oldpw,int src) {
		source=src;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeRegPassword frame = new ChangeRegPassword(oldpw);
					frame.setVisible(true);
					// frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
