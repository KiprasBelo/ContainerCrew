package containersoftware;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

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
	private JButton btnBack;
	private JButton btnLoad;
	private JLabel humidityLbl;
	private JLabel pressureLbl;
	private LineChart chart;
	
	/**
	 * Create the frame.
	 */
	public ExtraContainerInfo() {
		//Adjust the position of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setBounds(screenWidth/2 - 450/2, screenHeight/2 - 300/2, 450, 138);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		humidityLbl = new JLabel(""+0);
		humidityLbl.setBounds(239, 11, 114, 14);
		contentPane.add(humidityLbl);
		
		pressureLbl = new JLabel(""+0);
		pressureLbl.setBounds(239, 36, 114, 14);
		contentPane.add(pressureLbl);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chart != null)
					chart.dispose();
				dispose();
				ContainerTracker track = new ContainerTracker();
				track.setVisible(true);
			}
		});
		btnBack.setBounds(207, 65, 89, 23);
		contentPane.add(btnBack);
		
		//Button to load additional info
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				humidityLbl.setText(""+c.getHumidity());
				pressureLbl.setText(""+c.getPressure());
				
			}
		});
		btnLoad.setBounds(96, 65, 89, 23);
		contentPane.add(btnLoad);
	}
	
	public void getChart(LineChart c) {
		chart = c;
	}
	
	public void setContainer(Container c) {
		this.c = c;
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JButton getLoadButton() {
		return btnLoad;
	}
	
	public JLabel getHunidityLbl() {
		return humidityLbl;
	}
	
	public JLabel getPressureLbl() {
		return pressureLbl;
	}

}
