package containersoftware;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FirstPage {

	private JFrame frame;
	ClientLog log = new ClientLog();
	ContainerLog log2 = new ContainerLog();
	OrderLog log3 = new OrderLog();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstPage window = new FirstPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public FirstPage() throws IOException {
		log.createDatabase();
		log2.createDatabase();
		log3.createDatabase();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Admin Login");
		btnNewButton.setBounds(120, 167, 218, 56);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Client Register");
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				
				try {
					
					frame.dispose();
					ClientRegister register = new ClientRegister();
					register.setVisible(true);
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		
		
		btnNewButton_1.setBounds(120, 82, 218, 56);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton goToClientLogin = new JButton("Client Login");
		
		goToClientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try {
					
					frame.dispose();
					ClientLogin login = new ClientLogin();
					login.setVisible(true);
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		goToClientLogin.setBounds(120, 30, 218, 47);
		frame.getContentPane().add(goToClientLogin);
	}
}
