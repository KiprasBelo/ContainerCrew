package containersoftware;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Font;

//Screen to add a new Journey for a Client

public class AddJourney extends JFrame {

	private JPanel contentPane;
	private JTextField cargoField;
	private JTextField destinationField;
	private JTextField originField;
	private JTextField tempField;
	private JButton btnRegister;
	private JButton btnBack;

	/**
	 * Create the frame.
	 */
	public AddJourney() {
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
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ClientMainMenu menu = new ClientMainMenu();
				menu.setVisible(true);
			}
		});
		btnBack.setBounds(10, 237, 118, 23);
		contentPane.add(btnBack);
		
		JLabel lblOrigin = new JLabel("Port of Origin");
		lblOrigin.setBounds(64, 61, 103, 14);
		contentPane.add(lblOrigin);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(64, 87, 165, 14);
		contentPane.add(lblDestination);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(64, 135, 165, 14);
		contentPane.add(lblCargo);
		
		JLabel lblTemp = new JLabel("Prefered Temp Celsius");
		lblTemp.setBounds(64, 160, 151, 14);
		contentPane.add(lblTemp);
		
		//Button to Register Journey
		btnRegister = new JButton("Add Journey");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				ContainerLog log = new ContainerLog();
				try {
					log.addContainerToClient(originField.getText(), destinationField.getText(), cargoField.getText(), Integer.parseInt(tempField.getText()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				dispose();
				ClientMainMenu menu = new ClientMainMenu();
				menu.setVisible(true);
				
			}
		});
		
		
		btnRegister.setBounds(10, 207, 118, 23);
		contentPane.add(btnRegister);
		
		cargoField = new JTextField();
		cargoField.setBounds(227, 135, 165, 20);
		contentPane.add(cargoField);
		cargoField.setColumns(10);
		
		destinationField = new JTextField();
		destinationField.setBounds(227, 87, 165, 20);
		contentPane.add(destinationField);
		destinationField.setColumns(10);
		
		originField = new JTextField();
		originField.setBounds(227, 61, 165, 20);
		contentPane.add(originField);
		originField.setColumns(10);
		
		tempField = new JTextField();
		tempField.setBounds(227, 160, 165, 20);
		contentPane.add(tempField);
		tempField.setColumns(10);
		
		JLabel lblHeader = new JLabel("Add new journey");
		lblHeader.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHeader.setBounds(142, 15, 165, 24);
		contentPane.add(lblHeader);
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JButton getRegisterButton() {
		return btnRegister;
	}
	
	public JTextField getOriginField() {
		return originField;
	}
	
	public JTextField getDestinationField() {
		return destinationField;
	}
	
	public JTextField getCargoField() {
		return cargoField;
	}
	
	public JTextField getTempField() {
		return tempField;
	}
}
