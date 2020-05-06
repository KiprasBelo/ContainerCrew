package containersoftware;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

//Current main and the first menu to display

public class FirstPage {

	private JFrame frame;
	private ClientLog log = new ClientLog();
	private ContainerLog log2 = new ContainerLog();
	private OrderLog log3 = new OrderLog();
	public int screenHeight;
	public int screenWidth;
	private JButton btnProceed;
	

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
	public FirstPage(){
		try {
			log.createDatabase();
			log2.createDatabase();
			log3.createDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Finding screen size to center JFrame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		frame = new JFrame();
		frame.setResizable( false );
		frame.setBounds(screenWidth/2 - 450/2, screenHeight/2 - 300/2, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnProceed = new JButton("Proceed to application");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
				
			}
		});
		btnProceed.setBounds(125, 189, 200, 50);
		frame.getContentPane().add(btnProceed);
		
		JLabel lblAgile = new JLabel("Agile Object Oriented Software Development 02160");
		lblAgile.setBounds(63, 24, 324, 14);
		frame.getContentPane().add(lblAgile);
		
		JLabel lblGroup = new JLabel("By group K");
		lblGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroup.setBounds(366, 247, 78, 25);
		frame.getContentPane().add(lblGroup);
		
		JLabel lblForImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/containerTrackerNew.png")).getImage();
		lblForImage.setIcon(new ImageIcon(img));
		lblForImage.setBounds(26, 50, 398, 110);
		frame.getContentPane().add(lblForImage);
	}
	
	public JButton getProceedButton() {
		return btnProceed;
	}
}
