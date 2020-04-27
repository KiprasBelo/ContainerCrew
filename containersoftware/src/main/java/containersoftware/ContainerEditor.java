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
import java.awt.event.ActionEvent;

public class ContainerEditor extends JFrame {

	private JPanel contentPane;
	private JTextField humidityField;
	private JTextField pressureField;
	private Container container;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContainerEditor frame = new ContainerEditor();
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
	public ContainerEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ClientFinder find = new ClientFinder();
				find.setVisible(true);
			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				try {
					
					ContainerLog log = new ContainerLog();
					
					for(Container x : log.getContainers()) {
						
						if(x.getSelectedContainer()) {
							
							if(humidityField.getText().length() > 0) {
								x.setHumidity(Integer.parseInt(humidityField.getText()));
							}
							if(pressureField.getText().length() > 0) {
								x.setPressure(Integer.parseInt(pressureField.getText()));
							}
							x.setSelectedContainer(false);
							log.updateContainerDatabaseInfo(x);
						}
						
					}
					dispose();
					ClientFinder display = new ClientFinder();
					display.setVisible(true);
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnSave.setBounds(10, 193, 89, 23);
		contentPane.add(btnSave);
		
		JLabel lblHumidity = new JLabel("Humidity");
		lblHumidity.setBounds(112, 93, 46, 14);
		contentPane.add(lblHumidity);
		
		JLabel lblPressure = new JLabel("Atmpsphereic Pressure");
		lblPressure.setBounds(42, 118, 116, 14);
		contentPane.add(lblPressure);
		
		humidityField = new JTextField();
		humidityField.setBounds(168, 90, 86, 20);
		contentPane.add(humidityField);
		humidityField.setColumns(10);
		
		pressureField = new JTextField();
		pressureField.setBounds(168, 115, 86, 20);
		contentPane.add(pressureField);
		pressureField.setColumns(10);
		
		JButton btnEnd = new JButton("End Journey");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try {
					
					ContainerLog log = new ContainerLog();
					
					for(Container x : log.getContainers()) {
						
						if(x.getSelectedContainer()) {
							
							x.setInTransit(false);
							x.getCurrentOrder().setCurrentOrder(false);
							x.setSelectedContainer(false);
							log.updateContainerDatabaseInfo(x);
						}
						
					}
					dispose();
					ClientFinder display = new ClientFinder();
					display.setVisible(true);
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnEnd.setBounds(152, 193, 116, 23);
		contentPane.add(btnEnd);
	}

}
