package containersoftware;

import java.util.ArrayList;

public class Container {
	
	static int IDcounter = 0;
	private int containerId = 0;
	private int ownerID;
	private double temperature;
	private double humidity;
	private double pressure;
	private ArrayList<Order> history = new ArrayList<Order>();
	private boolean inTransit = false;
	
	public Container() {
		IDcounter++;
		containerId = IDcounter;
	}
	
	public Container(Order o) {
		IDcounter++;
		containerId = IDcounter;
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


}
