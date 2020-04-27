package containersoftware;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Database for storing and manipulating containers

public class ContainerLog {
	
	private static ArrayList<Container> containers = new ArrayList<Container>();
	private File file;
	private static Container selectedContainer;
	
	public ContainerLog() {}
	
	public void addContainer(Container contain) {
		containers.add(contain);
	}
	
	public ArrayList<Container> getContainers() {
		return containers;
	}
	
	//creation of database
	public void createDatabase() {
		try {
			file = new File("ContainerDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Adds new entries to Textfile database
	public void addToDatabase(Container c) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("ContainerDatabase.txt", true));
			write.newLine();
			write.write(c.toString(true));
			write.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Updates ArrayList based on Textfile
	public void updateDatabase() throws FileNotFoundException {
		file = new File("ContainerDatabase.txt");
		Scanner scan = new Scanner(file);
		containers.clear();
		
		while(scan.hasNextLine()) {
			String temp = scan.nextLine();
			String[] data = temp.split(",");
			Container c = new Container();
			this.addContainer(c);
			
			Order o = new Order();
			c.setCurrentOrder(o);
			
			c.setContainerID(Integer.parseInt(data[0]));
			c.setOwnerID(Integer.parseInt(data[1]));
			c.getCurrentOrder().setStartLocation(data[2]);
			c.getCurrentOrder().setCargo(data[3]);
			c.getCurrentOrder().setEndLocation(data[4]);
			c.setTemperature(Double.parseDouble(data[5]));
			c.setInTransit(Boolean.parseBoolean(data[6]));
			c.setStartDate(data[7]);
			c.setHumidity(Integer.parseInt(data[8]));
			c.setPressure(Integer.parseInt(data[9]));
			
			for(int i = 0; i < 48; i++) {
				
				c.getDataPoints()[i] = Double.parseDouble(data[i+10]);
				
			}
			
		}
		
	}
	
	//updates Textfile based on ArrayList
	public void updateContainerDatabaseInfo(Container c) throws FileNotFoundException {
		Path path = Paths.get("ContainerDatabase.txt");
		try {
			List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
			
			for(int i = 0; i < content.size(); i++) {
				if(content.get(i).charAt(0) == (c.toString(true).charAt(0))){
					content.set(i, c.toString(true));
					break;
				}
			}
			
			Files.write(path, content, StandardCharsets.UTF_8);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Assigns an unassigned or creates a new container for a client
	public void addContainerToClient(String origin, String destination, String cargo, int temp) {
		
		Container c;
		ClientLog log2 = new ClientLog();
		OrderLog log3 = new OrderLog();
		Order o;
		
		ResponceObject responce = log2.getSelectedClient().addShipments(this);
		
		if(responce.getErrorMessage().contentEquals("Could not find available container")) {
			
			c = new Container();
			
			c.setOwnerID(log2.getSelectedClient().getClientID());
			c.setTemperature(temp);
			
			o = new Order(c.getContainerID(), origin, destination, cargo);
			c.addOrders(o);
			o.setCurrentOrder(true);
			c.setCurrentOrder(o);
			
			c.getCurrentOrder().setStartLocation(origin);
			c.getCurrentOrder().setCargo(cargo);
			c.getCurrentOrder().setEndLocation(destination);
			c.setStartDate();
			c.setInTransit(true);
			this.addToDatabase(c);
			log3.addToDatabase(c.getCurrentOrder());
			this.addContainer(c);
			log2.getSelectedClient().addShipments(c);
			
		}
		else {
			if(log2.getSelectedClient().getShipments().size() > 0)
				c = log2.getSelectedClient().getShipment(log2.getSelectedClient().getShipments().size()-1);
			else c = log2.getSelectedClient().getShipment(0);
			
			o = new Order(c.getContainerID(), origin, destination, cargo);
			c.addOrders(o);
			o.setCurrentOrder(true);
			c.setCurrentOrder(o);
			c.setStartDate();
		
		}
		
	}
	
	//sets currently selected containers to work on
	public void setSelectedContainer(Container c) {
		for(Container x : containers) {
			if(x.getContainerID() == c.getContainerID()) {
				selectedContainer = x;
				x.setSelectedContainer(true);
			}
		}
	}
	
	public Container getSelectedContainer() {
		return selectedContainer;
	}
	
	//ends journey for a container
	public void end() throws FileNotFoundException {
		for(Container x : this.getContainers()) {
			
			if(x.getSelectedContainer()) {
				
				x.endJourney();
				this.updateContainerDatabaseInfo(x);
			}
			
		}
	}

}
