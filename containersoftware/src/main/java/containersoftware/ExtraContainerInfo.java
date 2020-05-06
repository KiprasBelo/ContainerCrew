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
import java.awt.Font;

//Displayed along side temp graph to provide all needed container info

public class ExtraContainerInfo extends JFrame {

	private JPanel contentPane;
	private Container c;
	private JButton btnBack;
	private JButton btnLoad;
	private JLabel humidityLbl;
	private JLabel pressureLbl;
	private LineChart chart;
	private JLabel lblHumidityUnit;
	private JLabel lblPressureUnit;
	private JLabel lblHeader;
	
	/**
	 * Create the frame.
	 */
	public ExtraContainerInfo() {
		//Adjust the position of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setBounds(screenWidth/2 - 450/2, 470, 450, 138);
		setResizable( false );
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHumidity = new JLabel("Humidity");
		lblHumidity.setBounds(101, 35, 137, 14);
		contentPane.add(lblHumidity);
		
		JLabel lblPressure = new JLabel("Atmospheric Pressure");
		lblPressure.setBounds(101, 60, 137, 14);
		contentPane.add(lblPressure);
		
		humidityLbl = new JLabel(""+0);
		humidityLbl.setBounds(293, 35, 23, 14);
		contentPane.add(humidityLbl);
		
		pressureLbl = new JLabel(""+0);
		pressureLbl.setBounds(293, 60, 23, 14);
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
		btnBack.setBounds(112, 80, 89, 23);
		contentPane.add(btnBack);
		
		//Button to load additional info
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				humidityLbl.setText(""+c.getHumidity());
				pressureLbl.setText(""+c.getPressure());
				
			}
		});
		btnLoad.setBounds(247, 80, 89, 23);
		contentPane.add(btnLoad);
		
		lblHumidityUnit = new JLabel("%");
		lblHumidityUnit.setBounds(321, 34, 26, 16);
		contentPane.add(lblHumidityUnit);
		
		lblPressureUnit = new JLabel("Atm");
		lblPressureUnit.setBounds(321, 59, 26, 16);
		contentPane.add(lblPressureUnit);
		
		lblHeader = new JLabel("Last measured values");
		lblHeader.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHeader.setBounds(122, 6, 206, 24);
		contentPane.add(lblHeader);
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
