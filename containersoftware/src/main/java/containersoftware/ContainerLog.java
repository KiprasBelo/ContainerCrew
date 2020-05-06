package containersoftware;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

public class ContainerLog implements Database {
	
	private static ArrayList<Container> containers = new ArrayList<Container>();
	private File file;
	private File tempFile;
	private static Container selectedContainer;
	
	public ContainerLog() {}
	
	public void addContainer(Container contain) {
		containers.add(contain);
	}
	
	public ArrayList<Container> getContainers() {
		return containers;
	}
	
	public void createDatabase() {
		try {
			file = new File("ContainerDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds objects directly to the Text file database
	 * 
	 * @param c the container to be added
	 */
	public void addToDatabase(Container c) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("ContainerDatabase.txt", true));
			
			write.write(c.toString(1));
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
			c.addOrders(o);
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
		scan.close();
	}
	
	/**
	 * Updates a Text file line to match one in the ArrayList
	 * 
	 * @param c the container object to be updated
	 * @throws FileNotFoundException
	 */
	public void updateContainerDatabaseInfo(Container c) throws FileNotFoundException {
		Path path = Paths.get("ContainerDatabase.txt");
		try {
			List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
			
			//Finds the same object id and updates the line
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
	
	/**
	 * Uses the addShipments method to either assign a new journey to an existing container or create one
	 * 
	 * @param origin the port of origin of the order
	 * @param destination the port of destination of the order
	 * @param cargo the cargo the container will carry
	 * @param temp the preferred temperature of the cargo
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean addContainerToClient(String origin, String destination, String cargo, int temp) throws FileNotFoundException {
		
		Container c;
		ClientLog log2 = new ClientLog();
		OrderLog log3 = new OrderLog();
		Order o;
		
		//checks if there are available containers
		boolean add = log2.getSelectedClient().addShipments(this);
		
		//if not, new container is created and teh order is assigned
		if(!add) {
			
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
			return true;
		}
		//Assigns a found container the new order
		else {
			if(log2.getSelectedClient().getShipments().size() > 0)
				c = log2.getSelectedClient().getShipment(log2.getSelectedClient().getShipments().size()-1);
			else c = log2.getSelectedClient().getShipment(0);
			
			o = new Order(c.getContainerID(), origin, destination, cargo);
			c.addOrders(o);
			c.setOwnerID(log2.getSelectedClient().getClientID());
			o.setCurrentOrder(true);
			c.setCurrentOrder(o);
			c.getCurrentOrder().setStartLocation(origin);
			c.getCurrentOrder().setCargo(cargo);
			c.getCurrentOrder().setEndLocation(destination);
			log3.addToDatabase(c.getCurrentOrder());
			try {
				log3.updateDatabase();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			c.setInTransit(true);
			c.setStartDate();
			try {
				this.updateContainerDatabaseInfo(c);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	/**
	 * Selects the container to work on
	 * 
	 * @param c the container to select
	 */
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
	
	/**
	 * Ends the current journey for a container and unassigns from client
	 * 
	 * @throws FileNotFoundException
	 */
	public void end() throws FileNotFoundException {
		ClientLog log = new ClientLog();
		
		//find and remove container from shipments ArrayList
		for(Client c : log.getClients()) {
			
			if(c.getClientID() == this.getSelectedContainer().getOwnerID()) {
				
				c.removeShipments(this.getSelectedContainer().getContainerID());
				
			}
			
		}
		
		//find and set unassign the owner for a container
		for(Container x : this.getContainers()) {
			
			if(x.getSelectedContainer()) {
				
				x.endJourney();
				x.setOwnerID(-1);
				this.updateContainerDatabaseInfo(x);
			}
			
		}
	}
	
	/**
	 * Completely deletes a container from the database
	 * 
	 * @param c the container to delete
	 * @throws IOException
	 */
	public void removeContainer(Container c) throws IOException {
		tempFile = new File("tempFile.txt");
		tempFile.createNewFile();
		Path path = Paths.get("ContainerDatabase.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader("ContainerDatabase.txt"));
		BufferedWriter write = new BufferedWriter(new FileWriter(tempFile));
		
		String remove = c.toString(true);
		String currentLine;
		
		//Finds the same object id and updates the line
		while((currentLine = reader.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if(trimmedLine.contentEquals(remove)) continue;
			write.write(currentLine + System.getProperty("line.separator"));
		}
		reader.close();
		write.close();
		
		Files.delete(path);
		boolean success = tempFile.renameTo(file);
	}

}
