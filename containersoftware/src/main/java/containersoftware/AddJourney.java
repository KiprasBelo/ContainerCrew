package containersoftware;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

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
		
		JLabel lblNewLabel = new JLabel("Port of Origin");
		lblNewLabel.setBounds(63, 33, 103, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Destination");
		lblNewLabel_1.setBounds(63, 71, 103, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cargo");
		lblNewLabel_2.setBounds(63, 107, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		
		JButton btnRegister = new JButton("Add Journey");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				Container c;
				
				ContainerLog log = new ContainerLog();
				ClientLog log2 = new ClientLog();
				OrderLog log3 = new OrderLog();
				Order o;
				
				//try {
				//	log.updateDatabase();
				//	//log2.updateDatabase();
				//	log3.updateDatabase();
				//} catch(Exception e) {
				//	JOptionPane.showMessageDialog(null, e);
				//}
				
				ResponceObject responce = log2.getSelectedClient().addShipments(log);
				
				if(responce.getErrorMessage().contentEquals("Could not find available container")) {
					
					c = new Container();
					
					c.setOwnerID(log2.getSelectedClient().getClientID());
					c.setTemperature(Integer.parseInt(tempField.getText()));
					
					o = new Order(c.getContainerID(), originField.getText(), destinationField.getText(), cargoField.getText());
					c.addOrders(o);
					o.setCurrentOrder(true);
					c.setCurrentOrder(o);
					
					c.getCurrentOrder().setStartLocation(originField.getText());
					c.getCurrentOrder().setCargo(cargoField.getText());
					c.getCurrentOrder().setEndLocation(destinationField.getText());
					c.setInTransit(true);
					log.addToDatabase(c);
					log3.addToDatabase(c.getCurrentOrder());
					log.addContainer(c);
					log2.getSelectedClient().addShipments(c);
					
				}
				else {
					if(log2.getSelectedClient().getShipments().size() > 0)
						c = log2.getSelectedClient().getShipment(log2.getSelectedClient().getShipments().size()-1);
					else c = log2.getSelectedClient().getShipment(0);
					
					o = new Order(c.getContainerID(), originField.getText(), destinationField.getText(), cargoField.getText());
					c.addOrders(o);
					o.setCurrentOrder(true);
					c.setCurrentOrder(o);
					System.out.println("Succesful");
				}
				
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
		
		JLabel lblNewLabel_3 = new JLabel("Prefered Temp Celcius");
		lblNewLabel_3.setBounds(63, 132, 118, 14);
		contentPane.add(lblNewLabel_3);
		
		tempField = new JTextField();
		tempField.setBounds(214, 129, 151, 20);
		contentPane.add(tempField);
		tempField.setColumns(10);
	}
}
