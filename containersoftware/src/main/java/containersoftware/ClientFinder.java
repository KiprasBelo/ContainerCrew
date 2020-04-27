package containersoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class ClientFinder extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField emailField;
	private Client current;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFinder frame = new ClientFinder();
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
	public ClientFinder() {
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
				AdminMainMenu menu = new AdminMainMenu();
				menu.setVisible(true);
			}
		});
		btnBack.setBounds(10, 227, 86, 23);
		contentPane.add(btnBack);
		
		JList list = new JList();
		
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
		list.setBounds(150, 11, 218, 239);
		contentPane.add(list);
		
		JButton btnLoad = new JButton("Load All Clients");
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
		
		JButton btnLoadEmail = new JButton("Load");
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
				
				for(Client x : log.getClients()) {
					if(x.getEmail().contentEquals(emailField.getText()) || x.getName().contentEquals(nameField.getText()))
						model.addElement(x);
				}
			}
				
		});
		btnLoadEmail.setBounds(7, 157, 89, 23);
		contentPane.add(btnLoadEmail);
	}
}
