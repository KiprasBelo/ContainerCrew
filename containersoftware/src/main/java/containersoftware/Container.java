package containersoftware;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class creates and manages container objects
 *
 */
public class Container {
	
	private int containerId = 0;
	private int ownerID;
	private double temperature;
	private int humidity;
	private int pressure;
	private ArrayList<Order> history = new ArrayList<Order>();
	private boolean inTransit = false;
	private Order currentOrder;
	private double[] dataPoints = new double[48];
	private String startDate;
	private boolean selectedContainer = false;
	
	/**
	 *Default constructor auto increments id
	 */
	public Container() {
		Path path = Paths.get("ContainerDatabase.txt");
		int counter = 0;
		
		//auto increments id
		try {
			List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
			
			for(int i = 0; i < content.size(); i++) {
				if(content.get(i).charAt(0) >= counter){
					counter++;
					counter++;
				}
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		containerId = counter;
	}
	
	/**
	 * Container constructor auto increments id
	 * 
	 * @param o the order to assign to the container
	 */
	public Container(Order o) {
		Path path = Paths.get("ContainerDatabase.txt");
		int counter = 0;
		
		//auto increments id
		try {
			List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
			
			for(int i = 0; i < content.size(); i++) {
				if(content.get(i).charAt(0) >= counter){
					counter++;
					counter++;
				}
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		containerId = counter;
		history.add(o);
	}
	
	/**
	 * Automatically sets the start date of a journey to today
	 */
	public void setStartDate() {
		Date created = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM:dd:hh");
		startDate = format.format(created);
	}
	
	/**
	 * sets start date to arbitrary value
	 * 
	 * @param string the date in the correct format to set
	 */
	public void setStartDate(String string) {
		startDate = string;
	}
	
	/**
	 * Adds random temperature data based on how many hours have passed and a threshold
	 * 
	 * @param location the location in the dataPoints Array of where to start adding data
	 * @param end the location to stop adding data points in the Array
	 * @param temp the preferred temperature used as a threshold for the generation
	 */
	public void addData(int location, int end, double temp) {
		
		double bottomThreshhold = temperature - 2;
		double random;
		
		for(int i = location; i < end; i++) {
			
			random = (Math.random() * 4) + bottomThreshhold;
			dataPoints[i] = random;
			
		}
		
	}
	
	/**
	 * Ends a journey for a container
	 */
	public void endJourney() {
		this.setInTransit(false);
		this.getCurrentOrder().setCurrentOrder(false);
		this.setSelectedContainer(false);
	}
	
	/**
	 * Gets all the data points of temperature data
	 * 
	 * @return an array of data points
	 */
	public double[] getDataPoints() {
		return dataPoints;
	}
	
	/**
	 * Gets the start date of a container order
	 * 
	 * @return a string in the format "mm:dd:hh"
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * Adds an order to the container history(can be current order)
	 * 
	 * @param o the order to add
	 */
	public void addOrders(Order o) {
		history.add(o);
	}
	
	/**
	 * Gets the preferred temperature of a container
	 * 
	 * @return double of the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * Manually sets the preferred temperature
	 * 
	 * @param temperature the new temperature
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * Gets the container humidity
	 * 
	 * @return an int of humidity percentage
	 */
	public int getHumidity() {
		return humidity;
	}

	/**
	 * Manually sets the current container humidity
	 * 
	 * @param humidity the humidity percentage to set
	 */
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	/**
	 * Gets the current container atmospheric pressure
	 * 
	 * @return and int of the current atmospheric pressure
	 */
	public int getPressure() {
		return pressure;
	}

	/**
	 * Manually sets the atmospheric pressure
	 * 
	 * @param pressure the new pressure to set
	 */
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	
	/**
	 * Gets the container's id
	 * 
	 * @return an int with the id
	 */
	public int getContainerID() {
		return containerId;
	}
	
	/**
	 * Manually sets the client owner of a container
	 * 
	 * @param owner the id of the owning client
	 */
	public void setOwnerID(int owner) {
		ownerID = owner;
	}
	
	/**
	 * Gets the id of the owning client
	 * 
	 * @return an int of the owner's id
	 */
	public int getOwnerID() {
		return ownerID;
	}
	
	/**
	 * Gets whether or not a container is on a journey
	 * 
	 * @return a boolean saying whether or not a container is moving
	 */
	public boolean getInTransit() {
		return inTransit;
	}
	
	/**
	 * Manually sets whether or not a container is on a journey
	 * 
	 * @param transit the boolean to set whether or not the container is moving
	 */
	public void setInTransit(boolean transit) {
		inTransit = transit;
	}
	
	/**
	 * Gets an order from the container history
	 * 
	 * @param pos the position of the order in the ArrayList
	 * @return an Order from the histroy ArrayList
	 */
	public Order getOrder(int pos) {
		return history.get(pos);
	}
	
	/**
	 * Gets all the orders a container ever had
	 * 
	 * @return and ArrayList of all orders for a container
	 */
	public ArrayList<Order> getHistory() {
		return history;
	}
	
	/**
	 * Gets the current order the container is transporting
	 * 
	 * @return an order that is in transit
	 */
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	/**
	 * Manually sets the current order to transport
	 * 
	 * @param o the new order to set
	 */
	public void setCurrentOrder(Order o) {
		currentOrder = o;
	}
	
	/**
	 * Manually sets container id
	 * 
	 * @param id the new id to set
	 */
	public void setContainerID(int id) {
		containerId = id;
	}

	/**
	 * Manually sets this container to the selected container
	 * 
	 * @param selectedContainer the boolean to set
	 */
	public void setSelectedContainer(boolean selectedContainer) {
		this.selectedContainer = selectedContainer;
	}
	
	/**
	 * Gets whether or not the container is selected for admin purposes
	 * 
	 * @return a boolean stating whether or not the container is selected
	 */
	public boolean getSelectedContainer() {
		return selectedContainer;
	}
	
	public String toString(boolean bool) {
		return this.getContainerID()+","+this.getOwnerID()+","+this.getCurrentOrder().getStartLocation()+","+
				this.getCurrentOrder().getCargo()+","+this.getCurrentOrder().getEndLocation()+","+this.getTemperature()+","+this.getInTransit()+","+this.getStartDate()+","+this.getHumidity()+","+this.getPressure()
				+","+dataPoints[0]+","+dataPoints[1]+","+dataPoints[2]+","+dataPoints[3]+","+dataPoints[4]+","+dataPoints[5]+","+dataPoints[6]+","+dataPoints[7]+","+dataPoints[8]+","+dataPoints[9]+","+dataPoints[10]
								+","+dataPoints[11]+","+dataPoints[12]+","+dataPoints[13]+","+dataPoints[14]+","+dataPoints[15]+","+dataPoints[16]+","+dataPoints[17]+","+dataPoints[18]
										+","+dataPoints[19]+","+dataPoints[20]+","+dataPoints[21]+","+dataPoints[22]+","+dataPoints[23]+","+dataPoints[24]+","+dataPoints[25]+","+dataPoints[26]
												+","+dataPoints[27]+","+dataPoints[28]+","+dataPoints[29]+","+dataPoints[30]+","+dataPoints[31]+","+dataPoints[32]+","+dataPoints[33]+","+dataPoints[34]
														+","+dataPoints[35]+","+dataPoints[36]+","+dataPoints[37]+","+dataPoints[38]+","+dataPoints[39]+","+dataPoints[40]+","+dataPoints[41]
																+","+dataPoints[42]+","+dataPoints[43]+","+dataPoints[44]+","+dataPoints[45]+","+dataPoints[46]+","+dataPoints[47];
	}
	
	public String toString(int num) {
		return this.getContainerID()+","+this.getOwnerID()+","+this.getCurrentOrder().getStartLocation()+","+
				this.getCurrentOrder().getCargo()+","+this.getCurrentOrder().getEndLocation()+","+this.getTemperature()+","+this.getInTransit()+","+this.getStartDate()+","+this.getHumidity()+","+this.getPressure()
				+","+dataPoints[0]+","+dataPoints[1]+","+dataPoints[2]+","+dataPoints[3]+","+dataPoints[4]+","+dataPoints[5]+","+dataPoints[6]+","+dataPoints[7]+","+dataPoints[8]+","+dataPoints[9]+","+dataPoints[10]
								+","+dataPoints[11]+","+dataPoints[12]+","+dataPoints[13]+","+dataPoints[14]+","+dataPoints[15]+","+dataPoints[16]+","+dataPoints[17]+","+dataPoints[18]
										+","+dataPoints[19]+","+dataPoints[20]+","+dataPoints[21]+","+dataPoints[22]+","+dataPoints[23]+","+dataPoints[24]+","+dataPoints[25]+","+dataPoints[26]
												+","+dataPoints[27]+","+dataPoints[28]+","+dataPoints[29]+","+dataPoints[30]+","+dataPoints[31]+","+dataPoints[32]+","+dataPoints[33]+","+dataPoints[34]
														+","+dataPoints[35]+","+dataPoints[36]+","+dataPoints[37]+","+dataPoints[38]+","+dataPoints[39]+","+dataPoints[40]+","+dataPoints[41]
																+","+dataPoints[42]+","+dataPoints[43]+","+dataPoints[44]+","+dataPoints[45]+","+dataPoints[46]+","+dataPoints[47]+"\n";
	}
	
	public String toString() {
		return "Shipment of "+this.getCurrentOrder().getCargo()+" from\n "+this.getCurrentOrder().getStartLocation()+" to "+this.getCurrentOrder().getEndLocation();
	}


}
