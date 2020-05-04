package containersoftware;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

//Screen that lists Clients as an admin

public class ClientFinder extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField emailField;
	private Client current;
	private JButton btnLoad;
	private JButton btnLoadEmail;
	private JButton btnBack;
	private JList list;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
	//			try {
	//				ClientFinder frame = new ClientFinder();
	//				frame.setVisible(true);
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	});
	//}

	/**
	 * Create the frame.
	 */
	public ClientFinder() {
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
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminMainMenu menu = new AdminMainMenu();
				menu.setVisible(true);
			}
		});
		btnBack.setBounds(10, 227, 86, 23);
		contentPane.add(btnBack);
		
		list = new JList();
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2 && list.locationToIndex(arg0.getPoint()) == list.getSelectedIndex()) {
					if(!list.isSelectionEmpty()) {
						
						dispose();
						AdminContainerList containers = new AdminContainerList();
						containers.setClient(current);
						containers.setVisible(true);
					}
				}
			}
		});
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				current = (Client)list.getSelectedValue();
				
			}
		});
		list.setBounds(150, 11, 274, 239);
		contentPane.add(list);
		
		//Button to load all clients
		btnLoad = new JButton("Load All Clients");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultListModel DLM = new DefaultListModel();
				ClientLog log = new ClientLog();
				
				try {
					log.updateDatabase();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				list.setModel(DLM);
				DLM.removeAllElements();
				
				for(Client x : log.getClients()) {
						DLM.addElement(x);
				}
			}
		});
		btnLoad.setBounds(10, 11, 130, 23);
		contentPane.add(btnLoad);
		
		JLabel lblName = new JLabel("Find by Name");
		lblName.setBounds(10, 45, 130, 14);
		contentPane.add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(10, 70, 86, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Find by Email");
		lblEmail.setBounds(10, 101, 130, 14);
		contentPane.add(lblEmail);
		
		emailField = new JTextField();
		emailField.setBounds(10, 126, 86, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		//Button to find clients by criteria
		btnLoadEmail = new JButton("Load");
		btnLoadEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultListModel model = new DefaultListModel();
				ClientLog log = new ClientLog();
				
				try {
					log.updateDatabase();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				list.setModel(model);
				model.removeAllElements();
				
				
				if(log.findClients(emailField.getText(), nameField.getText()))
					model.addElement(log.getFoundClient());
				
			}
				
		});
		btnLoadEmail.setBounds(7, 157, 89, 23);
		contentPane.add(btnLoadEmail);
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JButton getLoadAllButton() {
		return btnLoad;
	}

	public JButton getLoadByCriteriaButton() {
		return btnLoadEmail;
	}
	
	public JTextField getNameField() {
		return nameField;
	}
	
	public JTextField getEamilField() {
		return emailField;
	}
	
	public JList getList() {
		return list;
	}
}