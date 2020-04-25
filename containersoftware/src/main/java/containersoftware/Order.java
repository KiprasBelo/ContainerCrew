package containersoftware;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private String startLocation;
	private String endLocation;
	private String currentLocation;
	private String cargo;
	private int orderID = 0;
	private int assignedContainerId;
	private boolean currentOrder;
	
	public Order() {
		Path path = Paths.get("OrderDatabase.txt");
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
		orderID = counter;
	}
	
	public Order(int owner, String start, String end, String cargo) {
		assignedContainerId = owner;
		startLocation = start;
		endLocation = end;
		this.cargo = cargo;
		Path path = Paths.get("OrderDatabase.txt");
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
		orderID = counter;
	}
	
	public void setOrderID(int id) {
		orderID = id;
	}
	
	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public int getOrderID() {
		return orderID;
	}

	public int getAssignedContainerId() {
		return assignedContainerId;
	}

	public void setAssignedContainerId(int assignedContainerId) {
		this.assignedContainerId = assignedContainerId;
	}

	public boolean isCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(boolean currentOrder) {
		this.currentOrder = currentOrder;
	}
	
	public String toString() {
		return this.getOrderID()+","+this.getAssignedContainerId()+","+this.getStartLocation()+","+this.getEndLocation()+","+this.getCargo()+","+this.isCurrentOrder();
	}
	
}
