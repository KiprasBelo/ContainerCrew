package containersoftware;

import java.util.ArrayList;

public class Client extends Account {
	
	private ArrayList<Container> shipments = new ArrayList<Container>();
	private String referencePerson;
	static int IDcounter = 0;
	private int clientID;
	
	public Client(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		IDcounter++;
		clientID = IDcounter;
	}
	
	public ResponceObject addShipments(ContainerLog log) {
		ResponceObject responce;
		
		for(Container x : log.getContainers()) {
			if(!x.getInTransit()) {
				shipments.add(x);
				x.setInTransit(false);
				return responce = new ResponceObject("Successfully added container");
			}
		}
		return responce = new ResponceObject("Could not find available container");
	}
	
	public void removeShipments(int location) {
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
	
	public int getClientID() {
		return clientID;
	}
	

}
