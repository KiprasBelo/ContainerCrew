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
import java.awt.event.ActionEvent;

//All menu options for the admin

public class AdminMainMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnLogout;
	private JButton btnFindClients;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
	//			try {
	//				AdminMainMenu frame = new AdminMainMenu();
	//				frame.setVisible(true);
	//			} catch (Exception e) {
	//					e.printStackTrace();
	//			}
	//		}
	//	});
	//}

	/**
	 * Create the frame.
	 */
	public AdminMainMenu() {
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
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminLogin admin = new AdminLogin();
				admin.setVisible(true);
			}
		});
		btnLogout.setBounds(128, 137, 156, 54);
		contentPane.add(btnLogout);
		
		btnFindClients = new JButton("Find Clients");
		btnFindClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ClientFinder find = new ClientFinder();
				find.setVisible(true);
			}
		});
		btnFindClients.setBounds(125, 60, 163, 49);
		contentPane.add(btnFindClients);
	}
	
	public JButton getLogoutButton() {
		return btnLogout;
	}
	
	public JButton getFindClientsButton() {
		return btnFindClients;
	}
}
