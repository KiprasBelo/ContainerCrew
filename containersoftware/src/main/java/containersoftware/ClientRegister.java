package containersoftware;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Screen allowing the creation of new Clients

public class ClientRegister extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField emailField;
	private JTextField numberField;
	private JTextField passwordField;
	private JTextField confirmPassword;
	private JTextField addressField;
	private JTextField referenceField;
	private JButton btnBack;
	private JButton Register;

	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClientRegister frame = new ClientRegister();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ClientRegister() {
		//Adjust the position of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setBounds(screenWidth/2 - 450/2, screenHeight/2 - 300/2, 450, 300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Register = new JButton("Register");
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				try {
					if(usernameField.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No Username entered");
					}
					else if(passwordField.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No Password entered");
					}
					else if(emailField.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No Email entered");
					}
					else if(numberField.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No Phone Number entered");
					}
					else if(addressField.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No Address entered");
					}
					else if(referenceField.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No Reference Person entered");
					}
					else {
						
						ClientLog log = new ClientLog();
						log.Register(usernameField.getText(), passwordField.getText(), confirmPassword.getText(), nameField.getText(), emailField.getText(), numberField.getText(),
								addressField.getText(),referenceField.getText());
						
						dispose();
						ClientLogin login = new ClientLogin();
						login.setVisible(true);
						
					}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		
		
		
		Register.setBounds(166, 227, 89, 23);
		contentPane.add(Register);
		
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(46, 11, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(46, 36, 58, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(46, 161, 58, 14);
		contentPane.add(lblPassword);
		
		nameField = new JTextField();
		nameField.setBounds(166, 8, 193, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(46, 61, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNumber = new JLabel("Phone Number");
		lblNumber.setBounds(46, 86, 89, 14);
		contentPane.add(lblNumber);
		
		usernameField = new JTextField();
		usernameField.setBounds(166, 33, 193, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(166, 58, 193, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		numberField = new JTextField();
		numberField.setBounds(166, 83, 193, 20);
		contentPane.add(numberField);
		numberField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(166, 158, 193, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		confirmPassword = new JTextField();
		confirmPassword.setBounds(166, 183, 193, 20);
		contentPane.add(confirmPassword);
		confirmPassword.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(46, 186, 89, 14);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(46, 111, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblReference = new JLabel("Reference Person");
		lblReference.setBounds(47, 136, 109, 14);
		contentPane.add(lblReference);
		
		addressField = new JTextField();
		addressField.setBounds(166, 108, 193, 20);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		referenceField = new JTextField();
		referenceField.setBounds(166, 133, 193, 20);
		contentPane.add(referenceField);
		referenceField.setColumns(10);
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JButton getRegisterButton() {
		return Register;
	}
	
	public JTextField getNameField() {
		return nameField;
	}
	
	public JTextField getUserNameField() {
		return usernameField;
	}
	
	public JTextField getEmailField() {
		return emailField;
	}
	
	public JTextField getNumberField() {
		return numberField;
	}
	
	public JTextField getAddressField() {
		return addressField;
	}
	
	public JTextField getReferenceField() {
		return referenceField;
	}
	
	public JTextField getPasswordField() {
		return passwordField;
	}
	
	public JTextField getConfirmPasswordField() {
		return confirmPassword;
	}

}
