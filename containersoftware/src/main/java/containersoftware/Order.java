package containersoftware;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates and manages Order(Journey) objects
 *
 */
public class Order {

	private String startLocation;
	private String endLocation;
	private String cargo;
	private int orderID = 0;
	private int assignedContainerId;
	private boolean currentOrder;
	
	/**
	 * Non Default Constructor auto increments the id
	 */
	public Order() {
		Path path = Paths.get("OrderDatabase.txt");
		int counter = 0;
		
		//auto increments the id
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
	
	/**
	 * Order Constructor auto increments id
	 * 
	 * @param owner the id of the assigned container
	 * @param start the port of origin of an order
	 * @param end the port of destination of an order
	 * @param cargo the cargo the container will carry
	 */
	public Order(int owner, String start, String end, String cargo) {
		this.setAssignedContainerId(owner);
		startLocation = start;
		endLocation = end;
		this.cargo = cargo;
		Path path = Paths.get("OrderDatabase.txt");
		int counter = 0;
		
		//auto increments the id
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
	
	/**
	 * Manually sets the order id
	 * 
	 * @param id the id to set
	 */
	public void setOrderID(int id) {
		orderID = id;
	}
	
	/**
	 * Gets the port of origin
	 * 
	 * @return a string of the origin
	 */
	public String getStartLocation() {
		return startLocation;
	}

	/**
	 * Manually sets the port of origin
	 * 
	 * @param startLocation the new start location
	 */
	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	/**
	 * Gets the port of destination
	 * 
	 * @return a string of the destination
	 */
	public String getEndLocation() {
		return endLocation;
	}

	/**
	 * Manually sets port of destination
	 * 
	 * @param endLocation the new end location
	 */
	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	/**
	 * Gets the assigned cargo
	 * 
	 * @return a string of the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Manually sets cargo
	 * 
	 * @param cargo the new cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	/**
	 * Gets the order id
	 * 
	 * @return an int of the id
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * Gets the id of the assigned container
	 * 
	 * @return an int of the owner id
	 */
	public int getAssignedContainerId() {
		return assignedContainerId;
	}

	/**
	 * Manually set the owner of an order
	 * 
	 * @param assignedContainerId the new owning container id
	 */
	public void setAssignedContainerId(int assignedContainerId) {
		this.assignedContainerId = assignedContainerId;
	}

	/**
	 * Checks if this order is the one the container is in transit with
	 * 
	 * @return a boolean saying whether or not the order is current
	 */
	public boolean isCurrentOrder() {
		return currentOrder;
	}

	/**
	 * Manually sets the current order status
	 * 
	 * @param currentOrder a boolean with the order status
	 */
	public void setCurrentOrder(boolean currentOrder) {
		this.currentOrder = currentOrder;
	}
	
	public String toString() {
		return this.getOrderID()+","+this.getAssignedContainerId()+","+this.getStartLocation()+","+this.getEndLocation()+","+this.getCargo()+","+this.isCurrentOrder()+"\n";
	}
	
	public String toString(boolean bool) {
		return this.getOrderID()+","+this.getAssignedContainerId()+","+this.getStartLocation()+","+this.getEndLocation()+","+this.getCargo()+","+this.isCurrentOrder();
	}
	
}
