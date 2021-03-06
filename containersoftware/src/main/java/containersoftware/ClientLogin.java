package containersoftware;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Font;

//Screen where a client can login

public class ClientLogin extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton btnClientLoginBack;
	private JButton btnLogin;
	ClientLog c = new ClientLog();
	private JLabel lblHeader;

	/**
	 * Create the frame.
	 */
	public ClientLogin() {
		//Adjust the position of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setBounds(screenWidth/2 - 450/2, screenHeight/2 - 300/2, 450, 300);
		setResizable( false );
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(120, 93, 62, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(120, 118, 62, 14);
		contentPane.add(lblPassword);
		
		userField = new JTextField();
		userField.setBounds(192, 90, 118, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		btnLogin = new JButton("Login");
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean found = false;
				try {
					found = c.Login(userField.getText(), passwordField.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
						
				if(!found)
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
				else {
					dispose();
					ClientMainMenu menu = new ClientMainMenu();
					menu.setVisible(true);
				}
				
			}
		});
		
		btnLogin.setBounds(202, 147, 89, 23);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(192, 115, 118, 20);
		contentPane.add(passwordField);
		
		btnClientLoginBack = new JButton("Back");
		
		btnClientLoginBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try {
					
					dispose();
					MainMenu menu = new MainMenu();
					menu.setVisible(true);
					
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		btnClientLoginBack.setBounds(7, 227, 89, 23);
		contentPane.add(btnClientLoginBack);
		
		lblHeader = new JLabel("Client login");
		lblHeader.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHeader.setBounds(170, 16, 110, 24);
		contentPane.add(lblHeader);
	}
	
	public JButton getLoginButton() {
		return btnLogin;
	}
	
	public JButton getBackButton() {
		return btnClientLoginBack;
	}
	
	public JTextField getUserField() {
		return userField;
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
