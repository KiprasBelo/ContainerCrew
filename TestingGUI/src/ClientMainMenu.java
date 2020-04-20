import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		btnNewButton.setBounds(124, 67, 175, 47);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Track Jounies");
		btnNewButton_1.setBounds(124, 125, 175, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.setBounds(124, 186, 175, 47);
		contentPane.add(btnNewButton_2);
		
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
