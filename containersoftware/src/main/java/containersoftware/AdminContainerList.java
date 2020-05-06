package containersoftware;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

//Screen When Admin is presented with Containers belonging to a selected Client

public class AdminContainerList extends JFrame {

	private JPanel contentPane;
	private Client c;
	private Container current;
	private JButton btnLoad;
	private JButton btnBack;

	/**
	 * Create the frame.
	 */
	public AdminContainerList() {
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
		
		JList list = new JList();
		list.setBounds(139, 27, 262, 223);
		contentPane.add(list);
		
		JLabel lblContainer = new JLabel("All Client Containers");
		lblContainer.setBounds(6, 12, 130, 14);
		contentPane.add(lblContainer);
		
		
		//Button to Load all containers
		btnLoad = new JButton("Load Containers");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel DLM = new DefaultListModel();
				ContainerLog log = new ContainerLog();
				ClientLog log2 = new ClientLog();
				
				try {
					log.updateDatabase();
					log2.updateDatabase();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				list.setModel(DLM);
				DLM.removeAllElements();
				
				for(Container x : log.getContainers()) {
					if(c.getClientID() == x.getOwnerID() && x.getInTransit()) {
						DLM.addElement(x);
					}
				}
			}
		});
		btnLoad.setBounds(6, 38, 126, 23);
		contentPane.add(btnLoad);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				current = (Container)list.getSelectedValue();
				
			}
		});
		list.setBounds(145, 11, 262, 239);
		contentPane.add(list);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ClientFinder find = new ClientFinder();
				find.setVisible(true);
			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnBack);
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2 && list.locationToIndex(arg0.getPoint()) == list.getSelectedIndex()) {
					if(!list.isSelectionEmpty()) {
						
						ContainerLog log = new ContainerLog();
						
						dispose();
						ContainerEditor edit = new ContainerEditor();
						
						log.setSelectedContainer(current);
						
						edit.setVisible(true);
					}
				}
			}
		});
	}
	
	public void setClient(Client client) {
		c = client;
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JButton getLoadButton() {
		return btnLoad;
	}
}
