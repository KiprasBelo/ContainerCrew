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
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(50, 37, 106, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(50, 62, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setBounds(50, 97, 91, 14);
		contentPane.add(lblNewLabel_2);
		
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
		nameEdit.setBounds(166, 34, 86, 20);
		contentPane.add(nameEdit);
		nameEdit.setColumns(10);
		
		emailEdit = new JTextField();
		emailEdit.setBounds(166, 59, 86, 20);
		contentPane.add(emailEdit);
		emailEdit.setColumns(10);
		
		numberEdit = new JTextField();
		numberEdit.setBounds(166, 94, 86, 20);
		contentPane.add(numberEdit);
		numberEdit.setColumns(10);
	}

}
