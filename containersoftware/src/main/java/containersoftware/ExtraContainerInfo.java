package containersoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Displayed along side temp graph to provide all needed container info

public class ExtraContainerInfo extends JFrame {

	private JPanel contentPane;
	private Container c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExtraContainerInfo frame = new ExtraContainerInfo();
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
	public ExtraContainerInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 138);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHumidity = new JLabel("Humidity Percentage");
		lblHumidity.setBounds(86, 11, 130, 14);
		contentPane.add(lblHumidity);
		
		JLabel lblPressure = new JLabel("Atmospheric Pressure");
		lblPressure.setBounds(86, 36, 130, 14);
		contentPane.add(lblPressure);
		
		JLabel humidityLbl = new JLabel(""+0);
		humidityLbl.setBounds(239, 11, 114, 14);
		contentPane.add(humidityLbl);
		
		JLabel pressureLbl = new JLabel(""+0);
		pressureLbl.setBounds(239, 36, 114, 14);
		contentPane.add(pressureLbl);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ContainerTracker track = new ContainerTracker();
				track.setVisible(true);
			}
		});
		btnBack.setBounds(207, 65, 89, 23);
		contentPane.add(btnBack);
		
		//Button to load additional info
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				humidityLbl.setText(""+c.getHumidity());
				pressureLbl.setText(""+c.getPressure());
				
			}
		});
		btnLoad.setBounds(96, 65, 89, 23);
		contentPane.add(btnLoad);
	}
	
	public void setContainer(Container c) {
		this.c = c;
	}

}