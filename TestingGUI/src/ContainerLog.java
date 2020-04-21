

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContainerLog {
	
	ArrayList<Container> containers = new ArrayList<Container>();
	File file;
	
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
		//clients.clear();
		
		while(scan.hasNextLine()) {
			String temp = scan.nextLine();
			String[] data = temp.split(",");
			Container c = new Container();
			this.addContainer(c);
			c.setContainerID(Integer.parseInt(data[0]));
			c.setOwnerID(Integer.parseInt(data[1]));
			
		}
		
	}

}
