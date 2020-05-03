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

//Database that stores all orders ever created and manipulates orders

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
	
	//Creates Database
	public void createDatabase() {
		try {
			file = new File("OrderDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Adds new entries to database
	public void addToDatabase(Order o) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("OrderDatabase.txt", true));
			write.write(o.toString());
			write.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Updates ArrayList based on Textfile
	public void updateDatabase() throws FileNotFoundException {
		file = new File("OrderDatabase.txt");
		Scanner scan = new Scanner(file);
		//clients.clear();
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
	
	public void updateOrderDatabaseInfo(Order o) throws FileNotFoundException {
		Path path = Paths.get("OrderDatabase.txt");
		try {
			List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
			
			for(int i = 0; i < content.size(); i++) {
				if(content.get(i).charAt(0) == (o.toString(true).charAt(0))){
					content.set(i, o.toString(true));
					break;
				}
			}
			
			Files.write(path, content, StandardCharsets.UTF_8);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
