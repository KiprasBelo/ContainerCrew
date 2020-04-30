package containersoftware;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

//Screen to add a new Journey for a Client

public class AddJourney extends JFrame {

	private JPanel contentPane;
	private JTextField cargoField;
	private JTextField destinationField;
	private JTextField originField;
	private JTextField tempField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddJourney frame = new AddJourney();
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
	public AddJourney() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(0, 227, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblOrigin = new JLabel("Port of Origin");
		lblOrigin.setBounds(63, 33, 103, 14);
		contentPane.add(lblOrigin);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(63, 71, 103, 14);
		contentPane.add(lblDestination);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(63, 107, 46, 14);
		contentPane.add(lblCargo);
		
		JLabel lblTemp = new JLabel("Prefered Temp Celcius");
		lblTemp.setBounds(63, 132, 118, 14);
		contentPane.add(lblTemp);
		
		//Button to Register Journey
		JButton btnRegister = new JButton("Add Journey");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				ContainerLog log = new ContainerLog();
				log.addContainerToClient(originField.getText(), destinationField.getText(), cargoField.getText(), Integer.parseInt(tempField.getText()));
				
				dispose();
				ClientMainMenu menu = new ClientMainMenu();
				menu.setVisible(true);
				
			}
		});
		
		
		btnRegister.setBounds(150, 177, 118, 23);
		contentPane.add(btnRegister);
		
		cargoField = new JTextField();
		cargoField.setBounds(214, 104, 151, 20);
		contentPane.add(cargoField);
		cargoField.setColumns(10);
		
		destinationField = new JTextField();
		destinationField.setBounds(214, 68, 151, 20);
		contentPane.add(destinationField);
		destinationField.setColumns(10);
		
		originField = new JTextField();
		originField.setBounds(214, 30, 151, 20);
		contentPane.add(originField);
		originField.setColumns(10);
		
		tempField = new JTextField();
		tempField.setBounds(214, 129, 151, 20);
		contentPane.add(tempField);
		tempField.setColumns(10);
	}
}
