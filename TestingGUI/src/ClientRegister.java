import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientRegister extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField emailField;
	private JTextField numberField;
	private JTextField passwordField;
	private JTextField confirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientRegister frame = new ClientRegister();
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
	public ClientRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Register = new JButton("Register");
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
					else {
					
						String password = "";
						
						if(passwordField.getText() != null && passwordField.getText().contentEquals(confirmPassword.getText())) {
							password = passwordField.getText();
						}
						else {
							JOptionPane.showMessageDialog(null, "Passwords do not match!");
						}
						
						ClientLog log = new ClientLog();
						Client client = new Client(usernameField.getText(), password);
						log.addClients(client);
						
						client.setName(nameField.getText());
						client.setEmail(emailField.getText());
						client.setPhoneNumber(numberField.getText());
						
						dispose();
						ClientLogin login = new ClientLogin();
						login.setVisible(true);
						
					}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		
		
		
		Register.setBounds(176, 208, 89, 23);
		contentPane.add(Register);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(0, 238, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(46, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(46, 46, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(46, 147, 58, 14);
		contentPane.add(lblNewLabel_2);
		
		nameField = new JTextField();
		nameField.setBounds(166, 8, 193, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(46, 78, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Phone Number");
		lblNewLabel_4.setBounds(46, 122, 89, 14);
		contentPane.add(lblNewLabel_4);
		
		usernameField = new JTextField();
		usernameField.setBounds(166, 43, 193, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(166, 75, 193, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		numberField = new JTextField();
		numberField.setBounds(166, 119, 193, 20);
		contentPane.add(numberField);
		numberField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(166, 144, 193, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		confirmPassword = new JTextField();
		confirmPassword.setBounds(166, 175, 193, 20);
		contentPane.add(confirmPassword);
		confirmPassword.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Confirm Password");
		lblNewLabel_5.setBounds(46, 178, 89, 14);
		contentPane.add(lblNewLabel_5);
	}

}
