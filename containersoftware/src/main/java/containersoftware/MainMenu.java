package containersoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		//frame = new JFrame();
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AdminLogin admin = new AdminLogin();
				admin.setVisible(true);
				
			}
		});
		btnAdminLogin.setBounds(120, 167, 218, 56);
		getContentPane().add(btnAdminLogin);
		
		JButton btnClientRegister = new JButton("Client Register");
		
		
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
		
		
		btnClientRegister.setBounds(120, 82, 218, 56);
		getContentPane().add(btnClientRegister);
		
		JButton goToClientLogin = new JButton("Client Login");
		
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
		
		goToClientLogin.setBounds(120, 30, 218, 47);
		getContentPane().add(goToClientLogin);
	}

}
