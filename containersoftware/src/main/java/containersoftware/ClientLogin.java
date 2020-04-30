package containersoftware;
import java.awt.BorderLayout;
import java.awt.EventQueue;

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

//Screen where a client can login

public class ClientLogin extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton btnClientLoginBack;
	ClientLog c = new ClientLog();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin frame = new ClientLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(145, 80, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(145, 121, 65, 14);
		contentPane.add(lblPassword);
		
		userField = new JTextField();
		userField.setBounds(237, 77, 109, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		
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
		
		btnLogin.setBounds(169, 162, 109, 35);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 118, 109, 20);
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
		
		btnClientLoginBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnClientLoginBack);
	}
}
