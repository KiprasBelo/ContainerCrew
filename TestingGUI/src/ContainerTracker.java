import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class ContainerTracker extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContainerTracker frame = new ContainerTracker();
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
	public ContainerTracker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBackground(Color.WHITE);
		list.setBounds(151, 187, 103, -143);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("Load Containers");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel DLM = new DefaultListModel();
				ContainerLog log = new ContainerLog();
				ClientLog log2 = new ClientLog();
				
				try {
					log.updateDatabase();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				for(Container x : log.getContainers()) {
					if(log2.getSelectedClient().getClientID() == x.getOwnerID()) {
						DLM.addElement(x);
					}
				}
				list.setModel(DLM);
			}
		});
		
		
		btnNewButton.setBounds(10, 103, 111, 23);
		contentPane.add(btnNewButton);
	}
}
