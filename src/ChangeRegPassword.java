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
import java.awt.Font;
import java.awt.SystemColor;


public class ChangeRegPassword extends JFrame {
	private JPasswordField oldpwtxt;
	private JPasswordField newpwtxt1;
	private JPasswordField newpwtxt2;
	private JButton btnOk;
	private String opw="";
	private static int source=0;
	
	public ChangeRegPassword(String oldpw) {
		getContentPane().setBackground(SystemColor.windowBorder);
		setBounds(new Rectangle(350, 150, 400, 400));
		getContentPane().setLayout(null);
		opw=oldpw;
		JLabel lblEnterOldPassword = new JLabel("Enter Old Password");
		lblEnterOldPassword.setForeground(SystemColor.activeCaption);
		lblEnterOldPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		lblEnterOldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterOldPassword.setBounds(117, 35, 149, 14);
		getContentPane().add(lblEnterOldPassword);
		
		oldpwtxt = new JPasswordField();
		oldpwtxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				oldpwtxt.setText("");
			}
		});
		oldpwtxt.setHorizontalAlignment(SwingConstants.CENTER);
		oldpwtxt.setBounds(105, 60, 173, 20);
		getContentPane().add(oldpwtxt);
		
		JLabel lblEnterNewPassword = new JLabel("Enter New Password");
		lblEnterNewPassword.setForeground(SystemColor.activeCaption);
		lblEnterNewPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		lblEnterNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterNewPassword.setBounds(117, 91, 149, 14);
		getContentPane().add(lblEnterNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password");
		lblConfirmNewPassword.setForeground(SystemColor.activeCaption);
		lblConfirmNewPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		lblConfirmNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmNewPassword.setBounds(117, 143, 149, 14);
		getContentPane().add(lblConfirmNewPassword);
		
		newpwtxt1 = new JPasswordField();
		newpwtxt1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				newpwtxt1.setText("");
			}
		});
		newpwtxt1.setHorizontalAlignment(SwingConstants.CENTER);
		newpwtxt1.setBounds(105, 112, 173, 20);
		getContentPane().add(newpwtxt1);
		
		newpwtxt2 = new JPasswordField();
		newpwtxt2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				newpwtxt2.setText("");
			}
		});
		newpwtxt2.setHorizontalAlignment(SwingConstants.CENTER);
		newpwtxt2.setBounds(105, 168, 173, 20);
		getContentPane().add(newpwtxt2);
		
		btnOk = new JButton("OK");
		btnOk.setBackground(SystemColor.inactiveCaption);
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
					break;
				case 3:
					RegisterGui.setManagerPw(newStrPwd1);
					break;
				}
				
				//Lockscreen.setLockpwd(newStrPwd1);
				JOptionPane.showMessageDialog(null, "Password changed.");
				dispose();
				
			}
		});
		btnOk.setBounds(105, 217, 173, 23);
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
