package containersoftware;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class ClientMainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMainMenu frame = new ClientMainMenu();
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
	public ClientMainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Journey");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddJourney journey = new AddJourney();
				journey.setVisible(true);
			}
		});
		
		
		btnNewButton.setBounds(124, 67, 175, 47);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Track Jounies");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				ContainerTracker contain = new ContainerTracker();
				contain.setVisible(true);
				
				
			}
		});
		
		
		btnNewButton_1.setBounds(124, 125, 175, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ClientLog log = new ClientLog();
				Client c = log.getSelectedClient();
				log.getSelectedClient().setLoginStatus(false);
				try {
					log.updateClientDatabaseInfo(c);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				dispose();
				ClientLogin login = new ClientLogin();
				login.setVisible(true);
				
			}
			
		});
		
		
		
		btnLogout.setBounds(124, 186, 175, 47);
		contentPane.add(btnLogout);
		
		JButton btnNewButton_3 = new JButton("Account");
		
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AccountInfoDisplay info = new AccountInfoDisplay();
				info.setVisible(true);
				
			}
		});
		
		
		btnNewButton_3.setBounds(124, 11, 175, 45);
		contentPane.add(btnNewButton_3);
	}
}
