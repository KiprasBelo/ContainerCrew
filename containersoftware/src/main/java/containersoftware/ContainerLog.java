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
			file = new File("ContainerDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	public void updateDatabase() throws FileNotFoundException {
		file = new File("ContainerDatabase.txt");
		Scanner scan = new Scanner(file);
		//clients.clear();
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

}
