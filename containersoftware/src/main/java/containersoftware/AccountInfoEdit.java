package containersoftware;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountInfoEdit extends JFrame {

	private JPanel contentPane;
	private JTextField nameEdit;
	private JTextField emailEdit;
	private JTextField numberEdit;
	private JTextField referenceEdit;
	private JTextField addressEdit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountInfoEdit frame = new AccountInfoEdit();
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
	public AccountInfoEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(50, 37, 106, 14);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(50, 62, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNumber = new JLabel("Phone Number");
		lblNumber.setBounds(50, 87, 91, 14);
		contentPane.add(lblNumber);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(50, 112, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblReference = new JLabel("Reference Person");
		lblReference.setBounds(50, 137, 91, 14);
		contentPane.add(lblReference);
		
		JButton saveButton = new JButton("Save");
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				try {
					
					ClientLog client = new ClientLog();
					
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
						client.getSelectedClient().setPhoneNumber(addressEdit.getText());
					}
					if(referenceEdit.getText().length() > 0) {
						client.getSelectedClient().setReferencePerson(referenceEdit.getText());
					}
					
					client.updateClientDatabaseInfo(client.getSelectedClient());
					
					dispose();
					AccountInfoDisplay display = new AccountInfoDisplay();
					display.setVisible(true);
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		
		
		saveButton.setBounds(7, 196, 89, 23);
		contentPane.add(saveButton);
		
		JButton editback = new JButton("Back");
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
		nameEdit.setBounds(166, 34, 106, 20);
		contentPane.add(nameEdit);
		nameEdit.setColumns(10);
		
		emailEdit = new JTextField();
		emailEdit.setBounds(166, 59, 106, 20);
		contentPane.add(emailEdit);
		emailEdit.setColumns(10);
		
		numberEdit = new JTextField();
		numberEdit.setBounds(166, 84, 106, 20);
		contentPane.add(numberEdit);
		numberEdit.setColumns(10);
		
		referenceEdit = new JTextField();
		referenceEdit.setBounds(166, 134, 106, 20);
		contentPane.add(referenceEdit);
		referenceEdit.setColumns(10);
		
		addressEdit = new JTextField();
		addressEdit.setBounds(166, 109, 106, 20);
		contentPane.add(addressEdit);
		addressEdit.setColumns(10);
	}
}
