package containersoftware;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Font;

//Screen to edit Client account info

public class AccountInfoEdit extends JFrame {

	private JPanel contentPane;
	private JTextField nameEdit;
	private JTextField emailEdit;
	private JTextField numberEdit;
	private JTextField referenceEdit;
	private JTextField addressEdit;
	private JButton saveButton;
	private JButton editback;
	private ClientLog client = new ClientLog();
	private JLabel lblHeader;

	/**
	 * Create the frame.
	 */
	public AccountInfoEdit() {
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
		
		JLabel lblReference = new JLabel("Reference Person");
		lblReference.setBounds(47, 158, 150, 14);
		contentPane.add(lblReference);
		
		saveButton = new JButton("Save");
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				if(nameEdit.getText().length() > 0) {
					client.getSelectedClient().setName(nameEdit.getText());
				}
				if(emailEdit.getText().length() > 0) {
					client.getSelectedClient().setEmail(emailEdit.getText());
				}
				if(numberEdit.getText().length() > 0) {
					client.getSelectedClient().setPhoneNumber(numberEdit.getText());
				}
				if(addressEdit.getText().length() > 0) {
					client.getSelectedClient().setAddress(addressEdit.getText());
				}
				if(referenceEdit.getText().length() > 0) {
					client.getSelectedClient().setReferencePerson(referenceEdit.getText());
				}
					
				try {
					client.updateClientDatabaseInfo(client.getSelectedClient());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
					
				dispose();
				AccountInfoDisplay display = new AccountInfoDisplay();
				display.setVisible(true);
				
				
			}
		});
		
		
		saveButton.setBounds(7, 197, 89, 23);
		contentPane.add(saveButton);
		
		editback = new JButton("Back");
		editback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AccountInfoDisplay display = new AccountInfoDisplay();
				display.setVisible(true);
				
			}
		});
		
		
		editback.setBounds(7, 227, 89, 23);
		contentPane.add(editback);
		
		nameEdit = new JTextField();
		nameEdit.setBounds(192, 52, 175, 20);
		contentPane.add(nameEdit);
		nameEdit.setColumns(10);
		
		emailEdit = new JTextField();
		emailEdit.setBounds(192, 77, 175, 20);
		contentPane.add(emailEdit);
		emailEdit.setColumns(10);
		
		numberEdit = new JTextField();
		numberEdit.setBounds(192, 102, 175, 20);
		contentPane.add(numberEdit);
		numberEdit.setColumns(10);
		
		referenceEdit = new JTextField();
		referenceEdit.setBounds(192, 152, 175, 20);
		contentPane.add(referenceEdit);
		referenceEdit.setColumns(10);
		
		addressEdit = new JTextField();
		addressEdit.setBounds(192, 127, 175, 20);
		contentPane.add(addressEdit);
		addressEdit.setColumns(10);
		
		lblHeader = new JLabel("Edit account information");
		lblHeader.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHeader.setBounds(107, 16, 235, 24);
		contentPane.add(lblHeader);
	}
	
	public JButton getBackButton() {
		return editback;
	}
	
	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JTextField getNameField() {
		return nameEdit;
	}
	
	public JTextField getNumberField() {
		return numberEdit;
	}
	
	public JTextField getEmailField() {
		return emailEdit;
	}
	
	public JTextField getAddressField() {
		return addressEdit;
	}
	
	public JTextField getReferenceField() {
		return referenceEdit;
	}
	
}
