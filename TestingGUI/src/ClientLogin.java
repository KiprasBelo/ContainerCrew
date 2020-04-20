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
import java.awt.event.ActionEvent;

public class ClientLogin extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton btnClientLoginBack;

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
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(145, 80, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(145, 121, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		userField = new JTextField();
		userField.setBounds(237, 77, 109, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					ClientLog c = new ClientLog();
					boolean found = false;
					
					for(Client x : c.getClients()) {
						if(x.getUsername().contentEquals(userField.getText())) {
							if(x.getPassword().contentEquals(passwordField.getText())) {
								dispose();
								ClientMainMenu menu = new ClientMainMenu();
								menu.setVisible(true);
								found = true;
								x.setLoginStatus(true);
							}
						}
					}
					
					if(!found)
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
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
					FirstPage page = new FirstPage();
					
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		btnClientLoginBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnClientLoginBack);
	}
}
