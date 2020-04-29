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
import javax.swing.JLabel;
import java.awt.Font;

//Current main and the first menu to display

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
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
				
			}
		});
		btnProceed.setBounds(114, 168, 218, 56);
		frame.getContentPane().add(btnProceed);
		
		JLabel lblAgile = new JLabel("Agile Object Oriented Software Development 02160");
		lblAgile.setBounds(79, 11, 283, 14);
		frame.getContentPane().add(lblAgile);
		
		JLabel lblGroup = new JLabel("Group K");
		lblGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroup.setBounds(186, 36, 69, 25);
		frame.getContentPane().add(lblGroup);
	}
}
