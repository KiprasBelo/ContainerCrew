package containersoftware;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

//Database and database functionalities for Clients

public class ClientLog {

	private static ArrayList<Client> clients = new ArrayList<Client>();
	private static Client selectedClient;
	private Client foundClient;
	private File file;
	private static String tempDate;
	
	public ClientLog() {}
	
	public void addClients(Client c) {
		clients.add(c);
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	
	//Following 4 methods are for database manipulation
	public void createDatabase() throws IOException {
		try {
			file = new File("ClientDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	//Updates the ArrayList to fit textfile
	public void updateDatabase() throws FileNotFoundException {
		file = new File("ClientDatabase.txt");
		Scanner scan = new Scanner(file);
		clients.clear();
		
		while(scan.hasNextLine()) {
			String temp = scan.nextLine();
			String[] data = temp.split(",");
			
			Client c = new Client(data[1], data[2]);
			this.addClients(c);
			c.setClientID(Integer.parseInt(data[0]));
			c.setName(data[3]);
			c.setEmail(data[4]);
			c.setPhoneNumber(data[5]);
			c.setAddress(data[6]);
			c.setReferencePerson(data[7]);
			if(Boolean.parseBoolean(data[8])) {
				selectedClient = c;
				c.setLoginStatus(true);
			}
			c.setLastLoggedIn(data[9]);
			
		}
		
	}
	
	//Updates textfile to fit ArrayList
	public void updateClientDatabaseInfo(Client c) throws FileNotFoundException {
		Path path = Paths.get("ClientDatabase.txt");
		try {
			List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
			
			for(int i = 0; i < content.size(); i++) {
				if(content.get(i).charAt(0) == (c.toString(true).charAt(0))){
					content.set(i, c.toString(true));
					break;
				}
			}
			
			Files.write(path, content, StandardCharsets.UTF_8);
			
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	//Add new entries to database
	public void addToDatabase(Client c) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("ClientDatabase.txt", true));
			write.write(c.toString());
			write.close();
			
		} catch(Exception e) {
			//e.printStackTrace();
		}
		
	}
	
	//Creation of datapoints for temperature graph based on time since creation or last login
	public void checkDates(Container x) {
		
		this.getSelectedClient();
		selectedClient.compareDates(x.getStartDate());
			
		if(selectedClient.getTimeDifference() < 48) {
			for(int k = 0; k < selectedClient.getTimeDifference(); k++) {
				x.addData(0,selectedClient.getTimeDifference(),x.getTemperature());
			}
		}
		else {
			selectedClient.compareDates(this.getTempDate());
				
			if(selectedClient.getTimeDifference() < 48) {
					
				for(int i = selectedClient.getTimeDifference(); i > 0; i--) {
						
					for(int j = 0; j < 48-1; j++) {
						x.getDataPoints()[j] = x.getDataPoints()[j+1];
					}
						
				}
					
				x.addData(48-selectedClient.getTimeDifference(),48, x.getTemperature());
					
			}
			else {
					
				x.addData(0,48, x.getTemperature());
				System.out.println("option3");
					
			}
		}
		
	}
	
	//Selects current client that is being used
	public Client getSelectedClient() {
		
		Client c = null;
		
		for(Client x : clients) {
			if(x.getLoginStatus()) {
				selectedClient = x;
				c = x;
				break;
			}
		}
		return c;
	}
	
	//Registers a new client object and adds to database
	public ResponceObject Register(String user, String pass, String checkPass, String name, String email, String number, String address, String reference) {
		
		ResponceObject responce;
		
		if(pass.contentEquals(checkPass)) {
			Client c = new Client(user, pass);
			this.addClients(c);
			c.setName(name);
			c.setEmail(email);
			c.setPhoneNumber(number);
			c.setAddress(address);
			c.setReferencePerson(reference);
			this.addToDatabase(c);
			
			return responce = new ResponceObject("Client Registered");
		}
		else {
			JOptionPane.showMessageDialog(null, "Passwords do not match!");
			return responce = new ResponceObject("Client not Registered");
		}
		
	}
	
	//Finds clients based on criteria
	public boolean findClients(String email, String name) {
		
		boolean found = false;
		
		for(Client x : clients) {
			if(x.getEmail().contentEquals(email) || x.getName().contentEquals(name)) {
				foundClient = x;
				found = true;
				return found;
			}
		}
		return found;
	}
	
	//Checks database for valid login
	public boolean Login(String user, String password) throws FileNotFoundException {
		
		this.updateDatabase();
		boolean found = false;
		
		for(Client x : clients) {
			
			if(x.getUsername().contentEquals(user) && x.getPassword().contentEquals(password)) {
				found = true;
				x.setLoginStatus(true);
				this.setTempDate(x.getLastLoggedIn());
				x.setLastDate();
				this.getSelectedClient();
				this.updateClientDatabaseInfo(x);
				break;
			}
			
		}
		
		return found;
	}
	
	//Logs account out
	public boolean Logout() throws FileNotFoundException {
		Client c = this.getSelectedClient();
		if(c != null) {
			c.setLoginStatus(false);
			this.updateClientDatabaseInfo(c);
			return true;
		}
		return false;
	}
	
	//Getters and Setters
	public Client getFoundClient() {
		return foundClient;
	}

	public void setFoundClient(Client foundClient) {
		this.foundClient = foundClient;
	}
	
	public void setTempDate(String string) {
		tempDate = string;
	}
	
	public String getTempDate() {
		return tempDate;
	}
	
	
}


