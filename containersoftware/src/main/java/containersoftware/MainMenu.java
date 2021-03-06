package containersoftware;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	JButton btnAdminLogin;
	JButton goToClientLogin;
	JButton btnClientRegister;
	
	/**
	 * Create the frame.
	 */
	public MainMenu() {
		//Adjust the position of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setBounds(screenWidth/2 - 450/2, screenHeight/2 - 300/2, 450, 300);
		setResizable( false );
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AdminLogin admin = new AdminLogin();
				admin.setVisible(true);
				
			}
		});
		btnAdminLogin.setBounds(120, 179, 210, 55);
		getContentPane().add(btnAdminLogin);
		
		btnClientRegister = new JButton("Client Register");
		
		
		btnClientRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				
				
				try {
					
					dispose();
					ClientRegister register = new ClientRegister();
					register.setVisible(true);
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		
		
		btnClientRegister.setBounds(120, 88, 210, 55);
		getContentPane().add(btnClientRegister);
		
		goToClientLogin = new JButton("Client Login");
		
		goToClientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try {
					
					dispose();
					ClientLogin login = new ClientLogin();
					login.setVisible(true);
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		goToClientLogin.setBounds(120, 30, 210, 55);
		getContentPane().add(goToClientLogin);
		goToClientLogin.setName("ClientLogin");
		
	}
	
	public JButton getClientLoginButton() {
		return goToClientLogin;
	}
	
	public JButton getAdminLoginButton() {
		return btnAdminLogin;
	}
	
	public JButton getClientRegisterButton() {
		return btnClientRegister;
	}

}
