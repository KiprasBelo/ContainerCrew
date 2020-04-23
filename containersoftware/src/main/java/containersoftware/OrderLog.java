package containersoftware;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderLog {
	
	private static ArrayList<Order> orders = new ArrayList<Order>();
	private File file;
	
	public OrderLog() {}
	
	public void addOrders(Order o) {
		orders.add(o);
	}
	
	public ArrayList<Order> getOrders(){
		return orders;
	}
	
	public void createDatabase() {
		try {
			file = new File("/Users/LTMC4/OneDrive/Desktop/OrderDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addToDatabase(Order o) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("/Users/LTMC4/OneDrive/Desktop/OrderDatabase.txt", true));
			write.newLine();
			write.write(o.toString());
			write.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDatabase() throws FileNotFoundException {
		file = new File("/Users/LTMC4/OneDrive/Desktop/ContainerDatabase.txt");
		Scanner scan = new Scanner(file);
		orders.clear();
		
		while(scan.hasNextLine()) {
			String temp = scan.nextLine();
			String[] data = temp.split(",");
			Order o = new Order(Integer.parseInt(data[1]), data[2], data[3], data[4]);
			o.setCurrentOrder(Boolean.parseBoolean(data[5]));
			o.setOrderID(Integer.parseInt(data[0]));
			this.addOrders(o);
		}
		
	}
}
