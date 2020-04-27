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
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(50, 11, 106, 14);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(50, 36, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNumber = new JLabel("Phone Number");
		lblNumber.setBounds(50, 61, 91, 14);
		contentPane.add(lblNumber);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(50, 86, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblReferencePerson = new JLabel("Reference Person");
		lblReferencePerson.setBounds(50, 111, 91, 14);
		contentPane.add(lblReferencePerson);
		
		ClientLog log = new ClientLog();
		
		JLabel nameLbl = new JLabel(log.getSelectedClient().getName());
		nameLbl.setBounds(191, 11, 139, 14);
		contentPane.add(nameLbl);
		
		JLabel emailLbl = new JLabel(log.getSelectedClient().getEmail());
		emailLbl.setBounds(191, 36, 139, 14);
		contentPane.add(emailLbl);
		
		JLabel numberLbl = new JLabel(log.getSelectedClient().getPhoneNumber());
		numberLbl.setBounds(191, 61, 139, 14);
		contentPane.add(numberLbl);
		
		JLabel addressLbl = new JLabel(log.getSelectedClient().getAddress());
		addressLbl.setBounds(191, 86, 139, 14);
		contentPane.add(addressLbl);
		
		JLabel referencePersonLbl = new JLabel(log.getSelectedClient().getReferencePerson());
		referencePersonLbl.setBounds(191, 111, 139, 14);
		contentPane.add(referencePersonLbl);
		
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
