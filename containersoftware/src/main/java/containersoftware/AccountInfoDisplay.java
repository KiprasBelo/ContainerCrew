package containersoftware;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountInfoDisplay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountInfoDisplay frame = new AccountInfoDisplay();
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
	public AccountInfoDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(50, 37, 106, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(50, 62, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setBounds(50, 97, 91, 14);
		contentPane.add(lblNewLabel_2);
		
		ClientLog log = new ClientLog();
		
		JLabel nameLbl = new JLabel(log.getSelectedClient().getName());
		nameLbl.setBounds(191, 37, 139, 14);
		contentPane.add(nameLbl);
		
		JLabel emailLbl = new JLabel(log.getSelectedClient().getEmail());
		emailLbl.setBounds(191, 62, 139, 14);
		contentPane.add(emailLbl);
		
		JLabel numberLbl = new JLabel(log.getSelectedClient().getPhoneNumber());
		numberLbl.setBounds(191, 97, 139, 14);
		contentPane.add(numberLbl);
		
		JButton accountinfoback = new JButton("Back");
		accountinfoback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				try {
					
					dispose();
					ClientMainMenu menu = new ClientMainMenu();
					menu.setVisible(true);
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		accountinfoback.setBounds(10, 227, 89, 23);
		contentPane.add(accountinfoback);
		
		
		JButton editinfobutton = new JButton("Edit");
		editinfobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				try {
					
					dispose();
					AccountInfoEdit edit = new AccountInfoEdit();
					edit.setVisible(true);
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		
		
		editinfobutton.setBounds(10, 195, 89, 23);
		contentPane.add(editinfobutton);
	}

}
