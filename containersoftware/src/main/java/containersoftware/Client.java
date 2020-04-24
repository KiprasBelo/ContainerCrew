package containersoftware;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Client extends Account {
	
	private ArrayList<Container> shipments = new ArrayList<Container>();
	private String referencePerson;
	//static int IDcounter = 0;
	private int clientID;
	//private Date lastLogin;
	private String lastLoggedIn;
	private int timeDifference;
	
	public Client(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		
		Path path = Paths.get("/Users/LTMC4/OneDrive/Desktop/ClientDatabase.txt");
		int counter = 0;
		
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
	
	public void compareDates(String last) {
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM:dd:hh");
		String current = format.format(now);
		int monthDifference;
		int dateDifference;
		int hourDifference;
		int totalDifference;
		
		String[] curr = current.split(":");
		String[] past = last.split(":");
		
		monthDifference = Math.abs(Integer.parseInt(curr[0])-Integer.parseInt(past[0]));
		dateDifference = Math.abs(Integer.parseInt(curr[1])-Integer.parseInt(past[1]));
		hourDifference = Integer.parseInt(curr[2])-Integer.parseInt(past[2]);
		
		//totalDifference = hourDifference+(dateDifference*24)+
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
	
	public ResponceObject addShipments(ContainerLog log) {
		ResponceObject responce;
		
		for(Container x : log.getContainers()) {
			if(!x.getInTransit()) {
				x.setInTransit(true);
				shipments.add(x);
				return responce = new ResponceObject("Successfully added container");
			}
		}
		return responce = new ResponceObject("Could not find available container");
	}
	
	public void addShipments(Container c) {
		
		shipments.add(c);
		
	}
	
	public void removeShipments(int location) {
		shipments.get(location).setInTransit(false);
		shipments.remove(location);
	}
	
	public ResponceObject hasShipments(){
		ResponceObject responce = new ResponceObject("Has shipments");
		if(shipments.isEmpty()) {
			return responce = new ResponceObject("Has no shipments");
		}
		return responce;
	}

	public Container getShipment(int location) {
		return shipments.get(location);
	}
	
	public ArrayList<Container> getShipments(){
		return shipments;
	}
	
	public int getClientID() {
		return clientID;
	}
	
	public void setClientID(int id) {
		clientID = id;
	}
	
	public String getLastLoggedIn() {
		return lastLoggedIn;
	}
	
	public int getTimeDifference() {
		return timeDifference;
	}
	
	public void setLastDate() {
		Date lastLogin = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM:dd:hh");
		lastLoggedIn = format.format(lastLogin);
	}
	
	public void setLastLoggedIn(String date) {
		lastLoggedIn = date;
	}
	
	public String toString() {
		return this.getClientID()+","+this.getUsername()+","+this.getPassword()+","+this.getName()+","+this.getEmail()+","+this.getPhoneNumber()+","+this.getAddress()+","+this.getLoginStatus()+","+this.getLastLoggedIn()+"\n";
	}
	
	public String toString(boolean x) {
		return this.getClientID()+","+this.getUsername()+","+this.getPassword()+","+this.getName()+","+this.getEmail()+","+this.getPhoneNumber()+","+this.getAddress()+","+this.getLoginStatus()+","+this.getLastLoggedIn();
	}

}
