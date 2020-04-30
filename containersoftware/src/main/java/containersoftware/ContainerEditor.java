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

//Screen allowing Admin to edit some aspects of a Container

public class ContainerEditor extends JFrame {

	private JPanel contentPane;
	private JTextField humidityField;
	private JTextField pressureField;
	ContainerLog log = new ContainerLog();
	ClientLog log2 = new ClientLog();

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
				
				
				
				if(humidityField.getText().length() > 0) {
					log.getSelectedContainer().setHumidity(Integer.parseInt(humidityField.getText()));
				}
				if(pressureField.getText().length() > 0) {
					log.getSelectedContainer().setPressure(Integer.parseInt(pressureField.getText()));
				}
				log.getSelectedContainer().setSelectedContainer(false);
				try {
					log.updateContainerDatabaseInfo(log.getSelectedContainer());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
						
					
					dispose();
					ClientFinder display = new ClientFinder();
					display.setVisible(true);
					
				
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
					
				try{
					log.end();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
					
				dispose();
				ClientFinder display = new ClientFinder();
				display.setVisible(true);
				
			}
		});
		btnEnd.setBounds(152, 193, 116, 23);
		contentPane.add(btnEnd);
	}

}
