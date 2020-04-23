package containersoftware;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContainerLog {
	
	private static ArrayList<Container> containers = new ArrayList<Container>();
	private File file;
	
	public ContainerLog() {}
	
	public void addContainer(Container contain) {
		containers.add(contain);
	}
	
	public ArrayList<Container> getContainers() {
		return containers;
	}
	
	public void createDatabase() {
		try {
			file = new File("/Users/LTMC4/OneDrive/Desktop/ContainerDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addToDatabase(Container c) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("/Users/LTMC4/OneDrive/Desktop/ContainerDatabase.txt", true));
			write.newLine();
			write.write(c.toString());
			write.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDatabase() throws FileNotFoundException {
		file = new File("/Users/LTMC4/OneDrive/Desktop/ContainerDatabase.txt");
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
			c.setInTransit(Boolean.parseBoolean(data[5]));
			
		}
		
	}

}
