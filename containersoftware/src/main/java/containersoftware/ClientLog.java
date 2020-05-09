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

/**
 * This class creates and manages the Client account database
 *
 */
public class ClientLog implements Database{

	private static ArrayList<Client> clients = new ArrayList<Client>();
	private static Client selectedClient;
	private Client foundClient;
	private File file;
	private static String tempDate;
	private File tempFile;
	
	public ClientLog() {}
	
	/**
	 * Adds a client to the Database ArrayList
	 * 
	 * @param c the client to add
	 */
	public void addClients(Client c) {
		clients.add(c);
	}
	
	/**
	 * Gets the whole client Database
	 * 
	 * @return an ArrayList of the Database
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public void createDatabase() throws IOException {
		try {
			file = new File("ClientDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	public void updateDatabase() throws FileNotFoundException {
		file = new File("ClientDatabase.txt");
		Scanner scan = new Scanner(file);
		clients.clear();
		
		while(scan.hasNextLine()) {
			String temp = scan.nextLine();
			String[] data = temp.split(",");
			
			//Creates client object with same info to be added to the ArrayList
			Client c = new Client(data[1], data[2]);
			this.addClients(c);
			c.setClientID(Integer.parseInt(data[0]));
			c.setName(data[3]);
			c.setEmail(data[4]);
			c.setPhoneNumber(data[5]);
			c.setAddress(data[6]);
			c.setReferencePerson(data[7]);
			
			//Used to track clients
			if(Boolean.parseBoolean(data[8])) {
				selectedClient = c;
				c.setLoginStatus(true);
			}
			c.setLastLoggedIn(data[9]);
			
		}
		scan.close();
	}
	
	/**
	 * Updates a Text file line to match one in the ArrayList
	 * 
	 * @param c the client object to be updated
	 * @throws FileNotFoundException
	 */
	public void updateClientDatabaseInfo(Client c) throws FileNotFoundException {
		Path path = Paths.get("ClientDatabase.txt");
		try {
			List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
			
			//Finds the same object id and updates the line
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
	
	/**
	 * Adds objects directly to the Text file database
	 * 
	 * @param c the client to be added
	 */
	public void addToDatabase(Client c) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("ClientDatabase.txt", true));
			write.write(c.toString(1));
			write.close();
			
		} catch(Exception e) {
			//e.printStackTrace();
		}
		
	}
	
	/**
	 * Uses compareDates from client class to know how much graph data to update
	 * 
	 * @param x the container for which the dates are checked
	 */
	public void checkDates(Container x) {
		
		this.getSelectedClient();
		
		//checks if the start date for the journey is less than 48 hours
		selectedClient.compareDates(x.getStartDate());
			
		if(selectedClient.getTimeDifference() < 48) {
			for(int k = 0; k < selectedClient.getTimeDifference(); k++) {
				x.addData(0,selectedClient.getTimeDifference(),x.getTemperature());
			}
		}
		else {
			
			//checks if the last login date is over or under 48 hours
			selectedClient.compareDates(this.getTempDate());
				
			if(selectedClient.getTimeDifference() < 48) {
					
				for(int i = selectedClient.getTimeDifference(); i > 0; i--) {
					
					//shifts unaltered data left to add more temperature data to the end
					for(int j = 0; j < 48-1; j++) {
						x.getDataPoints()[j] = x.getDataPoints()[j+1];
					}
						
				}
					
				x.addData(48-selectedClient.getTimeDifference(),48, x.getTemperature());
					
			}
			else {
					
				x.addData(0,48, x.getTemperature());
					
			}
		}
		
	}
	
	/**
	 * Sets and Gets the logged in client as the selected client
	 * 
	 * @return the logged in client
	 */
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
	
	/**
	 * Used by UI to register a client given all the info
	 * 
	 * @param user the username of the new account
	 * @param pass the password of the new account
	 * @param checkPass a field to make sure password is entered as desired
	 * @param name the name of the new client
	 * @param email the email of the new client
	 * @param number the phone number of the new client
	 * @param address the address of the new client
	 * @param reference a reference person of the new client
	 * @return a boolean expressing the success of the registration or an error pane
	 * @throws FileNotFoundException
	 */
	public boolean Register(String user, String pass, String checkPass, String name, String email, String number, String address, String reference) throws FileNotFoundException {
		
		boolean found = false;
		
		//checks that there are no duplicate usernames
		this.updateDatabase();
		for(Client x : clients) {
			if(x.getUsername().contentEquals(user)) {
				found = true;
				break;
			}
		}
		
		//checks to see if both password fields match then creates the client
		if(pass.contentEquals(checkPass)) {
			if(!found) {
				Client c = new Client(user, pass);
				this.addClients(c);
				c.setName(name);
				c.setEmail(email);
				c.setPhoneNumber(number);
				c.setAddress(address);
				c.setReferencePerson(reference);
				this.addToDatabase(c);
				
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Username already taken!");
				return false;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Passwords do not match!");
			return false;
		}
		
	}
	
	/**
	 * Used by Admin UI to find clients based on criteria
	 * 
	 * @param email the email of the client to find
	 * @param name the name of the client to find
	 * @return a boolean confirmin whether or not the client was found
	 */
	public boolean findClients(String email, String name) {
		
		boolean found = false;
		
		//if client is found they are set as the selected client
		for(Client x : clients) {
			if(x.getEmail().contentEquals(email) || x.getName().contentEquals(name)) {
				foundClient = x;
				found = true;
				return found;
			}
		}
		return found;
	}
	
	/**
	 * Logs a client in
	 * 
	 * @param user the username of the client logging in
	 * @param password the password of the client loggin in
	 * @return a boolean confirming the login
	 * @throws FileNotFoundException
	 */
	public boolean Login(String user, String password) throws FileNotFoundException {
		
		this.updateDatabase();
		boolean found = false;
		
		//checks if the given username and password match a client in the database
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
	
	/**
	 * Logs the current client out
	 * 
	 * @return a boolean confirming whether or not the client was logged out
	 * @throws FileNotFoundException
	 */
	public boolean Logout() throws FileNotFoundException {
		Client c = this.getSelectedClient();
		
		//logs client out and updates database
		if(c != null) {
			c.setLoginStatus(false);
			this.updateClientDatabaseInfo(c);
			return true;
		}
		return false;
	}
	
	/**
	 * Completely removes a client from the database by creating and renaming a tempfile
	 * 
	 * @param c the client to be removed from the system
	 * @throws IOException if any file accessing or writing goes wrong
	 */
	public void removeClient(Client c) throws IOException {
		tempFile = new File("tempFile.txt");
		tempFile.createNewFile();
		Path path = Paths.get("ClientDatabase.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter write = new BufferedWriter(new FileWriter(tempFile));
		
		String remove = c.toString(true);
		String currentLine;
		
		//reads through the database and writes all but the selected client to the new database
		while((currentLine = reader.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if(trimmedLine.contentEquals(remove)) continue;
			write.write(currentLine + System.getProperty("line.separator"));
		}
		reader.close();
		write.close();
		
		Files.delete(path);
		boolean success = tempFile.renameTo(file);
	}
	
	/**
	 * Gets the client found by criteria
	 * 
	 * @return the client matching criteria
	 */
	public Client getFoundClient() {
		return foundClient;
	}

	/**
	 * Manually sets the found client
	 * 
	 * @param foundClient the new client to set
	 */
	public void setFoundClient(Client foundClient) {
		this.foundClient = foundClient;
	}
	
	/**
	 * Manually sets the date the checkDates method uses
	 * 
	 * @param string the date in proper format "mm:dd:hh"to check
	 */
	public void setTempDate(String string) {
		tempDate = string;
	}
	
	/**
	 * Gets the date checkDates method uses
	 * 
	 * @return a string of the date in the given format
	 */
	public String getTempDate() {
		return tempDate;
	}

	public void resetLoginStatuses() throws FileNotFoundException {
		this.updateDatabase();
		for(Client c : clients) {
			
			c.setLoginStatus(false);
			this.updateClientDatabaseInfo(c);
		}
	}
	
	
}


