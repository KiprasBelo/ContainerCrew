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

public class ClientLog {

	private static ArrayList<Client> clients = new ArrayList<Client>();
	private static Client selectedClient;
	private File file;
	private static String tempDate;
	
	public ClientLog() {}
	
	public void addClients(Client c) {
		clients.add(c);
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public void createDatabase() throws IOException {
		try {
			file = new File("ClientDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
			//this.setTempDate(data[8]);
			c.setLastLoggedIn(data[9]);
			
		}
		
	}
	
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
			e.printStackTrace();
		}
		
	}
	
	public void setTempDate(String string) {
		tempDate = string;
	}
	
	public String getTempDate() {
		return tempDate;
	}
	
	public void checkDates(Container x) {
		
		//for(Client c : clients) {
		//	
		//	if(x.getOwnerID() == c.getClientID()) {
		//		tempDate = c.getTemp();
		//	}
			
		//}
		
		this.getSelectedClient();
		System.out.println("Does this run");
		
		//for(Container x : selectedClient.getShipments()) {
			
			System.out.println("Does this run2");
		
			selectedClient.compareDates(x.getStartDate());
			System.out.println(selectedClient.getTimeDifference());
			
			if(selectedClient.getTimeDifference() < 48) {
				for(int k = 0; k < selectedClient.getTimeDifference(); k++) {
					x.addData(0,selectedClient.getTimeDifference(),x.getTemperature());
					System.out.println("option1");
				}
			}
			else {
				System.out.println(selectedClient.getLastLoggedIn()+", "+selectedClient.getTemp());
				System.out.println(selectedClient.getLastLoggedIn()+", "+tempDate);
				selectedClient.compareDates(tempDate);
				
				if(selectedClient.getTimeDifference() < 48) {
					
					for(int i = selectedClient.getTimeDifference(); i > 0; i--) {
						
						for(int j = 0; j < 48-1; j++) {
							x.getDataPoints()[j] = x.getDataPoints()[j+1];
							System.out.println("option2");
						}
						
					}
					
					x.addData(48-selectedClient.getTimeDifference(),48, x.getTemperature());
					
				}
				else {
					
					x.addData(0,48, x.getTemperature());
					System.out.println("option3");
					
				}
			}
		//}
	}
	
	public void addToDatabase(Client c) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("ClientDatabase.txt", true));
			write.write(c.toString());
			write.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
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
	
	public ResponceObject findClientViaName(String name) {
		ResponceObject responce = new ResponceObject("Found client with name "+name);
		
		for(Client x : clients) {
			if(x.getName().contentEquals(name)) {
				selectedClient = x;
				return responce;
			}
		}
		return responce = new ResponceObject("Did not find client with name "+name);
	}
	
	public ResponceObject findClientViaEmail(String email) {
		ResponceObject responce = new ResponceObject("Found client with email "+email);
		
		for(Client x : clients) {
			if(x.getEmail().contentEquals(email)) {
				selectedClient = x;
				return responce;
			}
		}
		return responce = new ResponceObject("Did not find client with email "+email);
	}
	
	
}


