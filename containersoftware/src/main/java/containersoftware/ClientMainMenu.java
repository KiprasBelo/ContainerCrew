package containersoftware;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

//Screen with Client functionality options

public class ClientMainMenu extends JFrame {

	private JPanel contentPane;
	private ClientLog log = new ClientLog();
	private JButton btnAddJourney;
	private JButton btnTrackJourney;
	private JButton btnLogout;
	private JButton btnAccount;
	private JLabel lblHeader;

	/**
	 * Create the frame.
	 */
	public ClientMainMenu() {
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
		
		btnAddJourney = new JButton("Add Journey");
		btnAddJourney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddJourney journey = new AddJourney();
				journey.setVisible(true);
			}
		});
		
		
		btnAddJourney.setBounds(135, 92, 180, 50);
		contentPane.add(btnAddJourney);
		
		btnTrackJourney = new JButton("Track Jounies");
		btnTrackJourney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				ContainerTracker contain = new ContainerTracker();
				contain.setVisible(true);
				
				
			}
		});
		
		
		btnTrackJourney.setBounds(135, 142, 180, 50);
		contentPane.add(btnTrackJourney);
		
		btnLogout = new JButton("Logout");
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
		
		
		
		btnLogout.setBounds(135, 208, 180, 50);
		contentPane.add(btnLogout);
		
		btnAccount = new JButton("Account");
		
		
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AccountInfoDisplay info = new AccountInfoDisplay();
				info.setVisible(true);
				
			}
		});
		
		
		btnAccount.setBounds(135, 42, 180, 50);
		contentPane.add(btnAccount);
		
		lblHeader = new JLabel("Client menu");
		lblHeader.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHeader.setBounds(167, 6, 116, 24);
		contentPane.add(lblHeader);
	}
	
	public JButton getLogoutButton() {
		return btnLogout;
	}
	
	public JButton getAccountButton() {
		return btnAccount;
	}
	
	public JButton getAddJourneyButton() {
		return btnAddJourney;
	}
	
	public JButton getTrackJourneyButton() {
		return btnTrackJourney;
	}
}
