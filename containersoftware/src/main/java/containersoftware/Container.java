package containersoftware;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Container {
	
	private int containerId = 0;
	private int ownerID;
	private double temperature;
	private double humidity;
	private double pressure;
	private ArrayList<Order> history = new ArrayList<Order>();
	private boolean inTransit = false;
	private Order currentOrder;
	
	
	public Container() {
		Path path = Paths.get("/Users/LTMC4/OneDrive/Desktop/ContainerDatabase.txt");
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
		containerId = counter;
	}
	
	public Container(Order o) {
		Path path = Paths.get("/Users/LTMC4/OneDrive/Desktop/ContainerDatabase.txt");
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
		containerId = counter;
		history.add(o);
	}
	
	public void addOrders(Order o) {
		history.add(o);
	}
	
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	public int getContainerID() {
		return containerId;
	}
	
	public void setOwnerID(int owner) {
		ownerID = owner;
	}
	
	public int getOwnerID() {
		return ownerID;
	}
	
	public boolean getInTransit() {
		return inTransit;
	}
	
	public void setInTransit(boolean transit) {
		inTransit = transit;
	}
	
	public Order getOrder(int pos) {
		return history.get(pos);
	}
	
	public ArrayList<Order> getHistory() {
		return history;
	}
	
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	public void setCurrentOrder(Order o) {
		currentOrder = o;
	}
	
	public void setContainerID(int id) {
		containerId = id;
	}
	
	public String toString() {
		return this.getContainerID()+","+this.getOwnerID()+","+this.getCurrentOrder().getStartLocation()+","+
				this.getCurrentOrder().getCargo()+","+this.getCurrentOrder().getEndLocation()+","+this.getInTransit();
	}


}
