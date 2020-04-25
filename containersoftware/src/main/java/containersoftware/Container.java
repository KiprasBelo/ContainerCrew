package containersoftware;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private double[] dataPoints = new double[48];
	private String startDate;
	
	
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
	
	public void setStartDate() {
		Date created = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM:dd:hh");
		startDate = format.format(created);
	}
	
	public void setStartDate(String string) {
		startDate = string;
	}
	
	public void addData(int location, int end, double temp) {
		
		double topThreshhold = temperature + 2;
		double bottomThreshhold = temperature - 2;
		double random;
		
		for(int i = location; i < end; i++) {
			
			random = (Math.random() * 4) + bottomThreshhold;
			dataPoints[i] = random;
			System.out.println("added");
			
		}
		
	}
	
	public double[] getDataPoints() {
		return dataPoints;
	}
	
	public String getStartDate() {
		return startDate;
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
	
	public String toString(boolean bool) {
		return this.getContainerID()+","+this.getOwnerID()+","+this.getCurrentOrder().getStartLocation()+","+
				this.getCurrentOrder().getCargo()+","+this.getCurrentOrder().getEndLocation()+","+this.getTemperature()+","+this.getInTransit()+","+this.getStartDate()+","+dataPoints[0]+","+dataPoints[1]
						+","+dataPoints[2]+","+dataPoints[3]+","+dataPoints[4]+","+dataPoints[5]+","+dataPoints[6]+","+dataPoints[7]+","+dataPoints[8]+","+dataPoints[9]+","+dataPoints[10]
								+","+dataPoints[11]+","+dataPoints[12]+","+dataPoints[13]+","+dataPoints[14]+","+dataPoints[15]+","+dataPoints[16]+","+dataPoints[17]+","+dataPoints[18]
										+","+dataPoints[19]+","+dataPoints[20]+","+dataPoints[21]+","+dataPoints[22]+","+dataPoints[23]+","+dataPoints[24]+","+dataPoints[25]+","+dataPoints[26]
												+","+dataPoints[27]+","+dataPoints[28]+","+dataPoints[29]+","+dataPoints[30]+","+dataPoints[31]+","+dataPoints[32]+","+dataPoints[33]+","+dataPoints[34]
														+","+dataPoints[35]+","+dataPoints[36]+","+dataPoints[37]+","+dataPoints[38]+","+dataPoints[39]+","+dataPoints[40]+","+dataPoints[41]
																+","+dataPoints[42]+","+dataPoints[43]+","+dataPoints[44]+","+dataPoints[45]+","+dataPoints[46]+","+dataPoints[47];
	}
	
	public String toString() {
		return "Shipment of "+this.getCurrentOrder().getCargo()+" from\n "+this.getCurrentOrder().getStartLocation()+" to "+this.getCurrentOrder().getEndLocation();
	}


}
