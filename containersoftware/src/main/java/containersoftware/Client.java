package containersoftware;


import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class creates and manages Client objects
 *
 */
public class Client extends Account {
	
	private ArrayList<Container> shipments = new ArrayList<Container>();
	private String referencePerson;
	private int clientID;
	private String lastLoggedIn;
	private int timeDifference;

	/**
	 * Client constructor auto increments the id
	 * 
	 * @param username the new client username
	 * @param password the new client password
	 */
	public Client(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		
		Path path = Paths.get("ClientDatabase.txt");
		int counter = 0;
		
		//auto increments id
		try {
			List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
			
			for(int i = 0; i < content.size(); i++) {
				if(content.get(i).charAt(0) > counter){
					counter++;
				}
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		
		clientID = counter;
	}
	
	/**
	 * Compares the time difference in hours of a given date and today's date
	 * 
	 * @param last the time represented in the given date format to compare
	 */
	public void compareDates(String last) {
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM:dd:hh");
		String current = format.format(now);
		int monthDifference;
		int dateDifference;
		int hourDifference;
		
		String[] curr = current.split(":");
		String[] past = last.split(":");
		
		//Calculates difference between the Months, Days, and Hours
		monthDifference = Math.abs(Integer.parseInt(curr[0])-Integer.parseInt(past[0]));
		dateDifference = Math.abs(Integer.parseInt(curr[1])-Integer.parseInt(past[1]));
		hourDifference = Integer.parseInt(curr[2])-Integer.parseInt(past[2]);
		
		//Hours are calculated based on difference of Months and Days
		if(monthDifference == 0) {
			timeDifference = (dateDifference*24) + hourDifference;
		}
		else if(monthDifference >= 2){
			timeDifference = 49;
		}
		else {
			timeDifference = hourDifference + (Integer.parseInt(curr[1]) * 24) + ((30 - Integer.parseInt(past[1])) * 24);
		}
		
	}
	
	/**
	 * Adds a container to a client if it is not owned and not in transit
	 * 
	 * @param log the container database object
	 * @return a boolean representing whether or not an available container was found
	 * @throws FileNotFoundException
	 */
	public boolean addShipments(ContainerLog log) throws FileNotFoundException {
		log.updateDatabase();
		for(Container x : log.getContainers()) {
			if(!x.getInTransit() && x.getOwnerID() == -1) {
				x.setInTransit(true);
				shipments.add(x);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a container to the shipments ArrayList
	 * 
	 * @param c the container to add
	 */
	public void addShipments(Container c) {
		
		shipments.add(c);
		
	}
	
	/**
	 * Removes a container from the shipments ArrayList
	 * 
	 * @param containerID the id of the container to remove
	 * @throws FileNotFoundException
	 */
	public void removeShipments(int containerID) throws FileNotFoundException {
		int count = 0;
		
		for(Container x : shipments) {
			
			if(x.getContainerID() == containerID) {
				Order o;
				OrderLog log = new OrderLog();
				o = x.getCurrentOrder();
				o.setCurrentOrder(false);
				log.updateDatabaseInfo(o);
				x.setInTransit(false);
				shipments.remove(count);
			}
			count++;
			
		}
	}
	
	/**
	 * Tells whether or not the client has any current orders
	 * 
	 * @return a boolean saying whether or not the ArrayList is empty
	 */
	public boolean hasShipments(){
		if(shipments.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Automatically sets the last login of a user
	 */
	public void setLastDate() {
		Date lastLogin = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM:dd:hh");
				
		lastLoggedIn = format.format(lastLogin);
	}

	/**
	 * Gets a container from the shipments list
	 * 
	 * @param location the location of the container to return
	 * @return a container in the given position
	 */
	public Container getShipment(int location) {
		return shipments.get(location);
	}
	
	/**
	 * Gets the entire shipments list
	 * 
	 * @return an ArrayList with all client orders
	 */
	public ArrayList<Container> getShipments(){
		return shipments;
	}
	
	/**
	 * Gets the client id
	 * 
	 * @return an int with the id
	 */
	public int getClientID() {
		return clientID;
	}
	
	/**
	 * Manually sets the client id
	 * 
	 * @param id the new id
	 */
	public void setClientID(int id) {
		clientID = id;
	}
	
	/**
	 * Gets the last date of login for a client
	 * 
	 * @return a string with the last login in the form "mm:dd:hh"
	 */
	public String getLastLoggedIn() {
		return lastLoggedIn;
	}
	
	/**
	 * Gets the calculated time difference between dates in hours
	 * 
	 * @return an int of hours of difference
	 */
	public int getTimeDifference() {
		return timeDifference;
	}
	
	/**
	 * Manually sets the client's reference person
	 * 
	 * @param the new reference person name
	 */
	public void setReferencePerson(String person) {
		referencePerson = person;
	}
	
	/**
	 * Gets the client's reference person
	 * 
	 * @return person a string with the reference name
	 */
	public String getReferencePerson() {
		return referencePerson;
	}
	
	/**
	 * Manually sets the last logged in date
	 * 
	 * @param date the new date in the format "mm:dd:hh"
	 */
	public void setLastLoggedIn(String date) {
		lastLoggedIn = date;
	}
	
	public String toString() {
		return this.getName()+" Email: "+this.getEmail()+" Living at: "+this.getAddress();
	}
	
	public String toString(int x) {
		return this.getClientID()+","+this.getUsername()+","+this.getPassword()+","+this.getName()+","+this.getEmail()+","+this.getPhoneNumber()+","+this.getAddress()+","+this.getReferencePerson()+","+this.getLoginStatus()+","+this.getLastLoggedIn()+"\n";
	}
	
	public String toString(boolean x) {
		return this.getClientID()+","+this.getUsername()+","+this.getPassword()+","+this.getName()+","+this.getEmail()+","+this.getPhoneNumber()+","+this.getAddress()+","+this.getReferencePerson()+","+this.getLoginStatus()+","+this.getLastLoggedIn();
	}

}
