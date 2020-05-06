package containersoftware;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

//Displays Client Account Info

public class AccountInfoDisplay extends JFrame {

	private JPanel contentPane;
	private JButton accountinfoback;
	private JButton editinfobutton;
	private ClientLog log = new ClientLog();

	/**
	 * Create the frame.
	 */
	public AccountInfoDisplay() {
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(47, 58, 150, 14);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(47, 83, 150, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNumber = new JLabel("Phone Number");
		lblNumber.setBounds(47, 108, 150, 14);
		contentPane.add(lblNumber);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(47, 133, 150, 14);
		contentPane.add(lblAddress);
		
		JLabel lblReferencePerson = new JLabel("Reference Person");
		lblReferencePerson.setBounds(47, 158, 150, 14);
		contentPane.add(lblReferencePerson);
		
		JLabel nameLbl = new JLabel(log.getSelectedClient().getName());
		nameLbl.setBounds(192, 52, 175, 20);
		contentPane.add(nameLbl);
		
		JLabel emailLbl = new JLabel(log.getSelectedClient().getEmail());
		emailLbl.setBounds(192, 77, 175, 20);
		contentPane.add(emailLbl);
		
		JLabel numberLbl = new JLabel(log.getSelectedClient().getPhoneNumber());
		numberLbl.setBounds(192, 102, 175, 20);
		contentPane.add(numberLbl);
		
		JLabel addressLbl = new JLabel(log.getSelectedClient().getAddress());
		addressLbl.setBounds(192, 130, 175, 20);
		contentPane.add(addressLbl);
		
		JLabel referencePersonLbl = new JLabel(log.getSelectedClient().getReferencePerson());
		referencePersonLbl.setBounds(192, 155, 175, 20);
		contentPane.add(referencePersonLbl);
		
		accountinfoback = new JButton("Back");
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
		
		accountinfoback.setBounds(7, 227, 89, 23);
		contentPane.add(accountinfoback);
		
		
		editinfobutton = new JButton("Edit");
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
		
		
		editinfobutton.setBounds(7, 197, 89, 23);
		contentPane.add(editinfobutton);
		
		JLabel lblHeader = new JLabel("Account info");
		lblHeader.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHeader.setBounds(165, 16, 120, 24);
		contentPane.add(lblHeader);
		
	}
	
	public JButton getBackButton() {
		return accountinfoback;
	}
	
	public JButton getEditButton() {
		return editinfobutton;
	}
}
