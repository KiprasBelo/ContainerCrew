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

//Screen with Client functionality options

public class ClientMainMenu extends JFrame {

	private JPanel contentPane;
	ClientLog log = new ClientLog();

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
		
		JButton btnAddJourney = new JButton("Add Journey");
		btnAddJourney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddJourney journey = new AddJourney();
				journey.setVisible(true);
			}
		});
		
		
		btnAddJourney.setBounds(124, 67, 175, 47);
		contentPane.add(btnAddJourney);
		
		JButton btnTrackJourney = new JButton("Track Jounies");
		btnTrackJourney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				ContainerTracker contain = new ContainerTracker();
				contain.setVisible(true);
				
				
			}
		});
		
		
		btnTrackJourney.setBounds(124, 125, 175, 50);
		contentPane.add(btnTrackJourney);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					log.Logout();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
				ClientLogin login = new ClientLogin();
				login.setVisible(true);
				
			}
			
		});
		
		
		
		btnLogout.setBounds(124, 186, 175, 47);
		contentPane.add(btnLogout);
		
		JButton btnAccount = new JButton("Account");
		
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AccountInfoDisplay info = new AccountInfoDisplay();
				info.setVisible(true);
				
			}
		});
		
		
		btnAccount.setBounds(124, 11, 175, 45);
		contentPane.add(btnAccount);
	}
}
