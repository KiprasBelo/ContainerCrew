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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Uses a hard coded admin login to access admin features

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnBack;
	
	/**
	 * Create the frame.
	 */
	public AdminLogin() {
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
		
		JLabel lblAdmin = new JLabel("This page is for Administrator login only");
		lblAdmin.setBounds(113, 11, 249, 14);
		contentPane.add(lblAdmin);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setBounds(120, 93, 62, 14);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(120, 118, 62, 14);
		contentPane.add(lblPassword);
		
		userField = new JTextField();
		userField.setBounds(192, 90, 118, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(192, 115, 118, 20);
		contentPane.add(passwordField);
		
		//Button for admin Login
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(userField.getText().contentEquals("Admin") && passwordField.getText().contentEquals("Password")) {
					dispose();
					AdminMainMenu menu = new AdminMainMenu();
					menu.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
				}
				
			}
		});
		btnLogin.setBounds(174, 199, 89, 23);
		contentPane.add(btnLogin);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnBack);
	}
	
	public JButton getLoginButton() {
		return btnLogin;
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JTextField getUserField() {
		return userField;
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
