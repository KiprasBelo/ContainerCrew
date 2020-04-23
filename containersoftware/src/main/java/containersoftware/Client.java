package containersoftware;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Client extends Account {
	
	private ArrayList<Container> shipments = new ArrayList<Container>();
	private String referencePerson;
	//static int IDcounter = 0;
	private int clientID;
	
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
	
	public String toString() {
		return this.getClientID()+","+this.getUsername()+","+this.getPassword()+","+this.getName()+","+this.getEmail()+","+this.getPhoneNumber()+","+this.getAddress()+","+this.getLoginStatus()+"\n";
	}
	
	public String toString(boolean x) {
		return this.getClientID()+","+this.getUsername()+","+this.getPassword()+","+this.getName()+","+this.getEmail()+","+this.getPhoneNumber()+","+this.getAddress()+","+this.getLoginStatus();
	}

}
