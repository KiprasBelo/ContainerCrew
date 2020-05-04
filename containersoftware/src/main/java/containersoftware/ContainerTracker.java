package containersoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Screen with a list of all containers for a certain client

public class ContainerTracker extends JFrame {

	private JPanel contentPane;
	private JTextField originField;
	private JTextField destinationField;
	private JTextField cargoField;
	private Container current;
	private ContainerLog log = new ContainerLog();
	private ClientLog log2 = new ClientLog();
	private JButton btnLoadAll;
	private JButton btnBack;
	private JButton btnFindbyLoad;

	/**
	 * Create the frame.
	 */
	public ContainerTracker() {
		//Adjust the position of the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setBounds(screenWidth/2 - 540/2, screenHeight/2 - 300/2, 540, 300);
		setResizable( false );
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		
		//Selects Container via mouseclick and displays container info
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2 && list.locationToIndex(arg0.getPoint()) == list.getSelectedIndex()) {
					if(!list.isSelectionEmpty()) {
						
						log2.checkDates(current);
						try {
							log.updateContainerDatabaseInfo(current);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						dispose();
						LineChart chart = new LineChart("Container Temperature", "Container Temperature for the last 48 hours", current);
						ExtraContainerInfo info = new ExtraContainerInfo();
						info.getChart(chart);
						info.setContainer(current);
						chart.pack();
						chart.setVisible(true);
						info.setVisible(true);
					}
				}
			}
		});
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				current = (Container) list.getSelectedValue();
			}
		});
		
		
		
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(185, 44, 324, 206);
		contentPane.add(list);
		
		//Loads all containers for a client
		btnLoadAll = new JButton("Load All Containers");
		btnLoadAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel DLM = new DefaultListModel();
				
				try {
					log.updateDatabase();
					log2.updateDatabase();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				list.setModel(DLM);
				DLM.removeAllElements();
				
				for(Container x : log.getContainers()) {
					if(log2.getSelectedClient().getClientID() == x.getOwnerID() && x.getInTransit()) {
						DLM.addElement(x);
					}
				}
	
			}
		});
		
		
		btnLoadAll.setBounds(10, 11, 149, 23);
		contentPane.add(btnLoadAll);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				ClientMainMenu menu = new ClientMainMenu();
				menu.setVisible(true);
				
			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblFind = new JLabel("Find by:");
		lblFind.setBounds(10, 45, 46, 14);
		contentPane.add(lblFind);
		
		originField = new JTextField();
		originField.setBounds(10, 70, 86, 20);
		contentPane.add(originField);
		originField.setColumns(10);
		
		destinationField = new JTextField();
		destinationField.setBounds(10, 101, 86, 20);
		contentPane.add(destinationField);
		destinationField.setColumns(10);
		
		cargoField = new JTextField();
		cargoField.setBounds(10, 132, 86, 20);
		contentPane.add(cargoField);
		cargoField.setColumns(10);
		
		JLabel lblOrigin = new JLabel("Origin");
		lblOrigin.setBounds(106, 73, 46, 14);
		contentPane.add(lblOrigin);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(106, 104, 69, 14);
		contentPane.add(lblDestination);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(106, 135, 46, 14);
		contentPane.add(lblCargo);
		
		//loads containers via certain criteria
		btnFindbyLoad = new JButton("Load");
		btnFindbyLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultListModel DLM = new DefaultListModel();
				
				try {
					log.updateDatabase();
					log2.updateDatabase();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				list.setModel(DLM);
				DLM.removeAllElements();
				
				for(Container x : log.getContainers()) {
					if((log2.getSelectedClient().getClientID() == x.getOwnerID() && x.getInTransit()) && (x.getCurrentOrder().getStartLocation().contentEquals(originField.getText()) ||
							x.getCurrentOrder().getEndLocation().contentEquals(destinationField.getText()) || x.getCurrentOrder().getCargo().contentEquals(cargoField.getText()))) {
						DLM.addElement(x);
					}
				}
				
			}
		});
		btnFindbyLoad.setBounds(10, 163, 89, 23);
		contentPane.add(btnFindbyLoad);
		
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JButton getLoadAllButton() {
		return btnLoadAll;
	}
	
	public JButton getFindByCriteriaButton() {
		return btnFindbyLoad;
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
}
