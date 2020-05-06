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

//Database that stores all orders ever created and manipulates orders

public class OrderLog implements Database{
	
	private static ArrayList<Order> orders = new ArrayList<Order>();
	private File file;
	private File tempFile;
	
	public OrderLog() {}
	
	public void addOrders(Order o) {
		orders.add(o);
	}
	
	public ArrayList<Order> getOrders(){
		return orders;
	}
	
	public void createDatabase() {
		try {
			file = new File("OrderDatabase.txt");
			file.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds objects directly to the Text file database
	 * 
	 * @param o the order to be added
	 */
	public void addToDatabase(Order o) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("OrderDatabase.txt", true));
			write.write(o.toString());
			write.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
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
		scan.close();
	}
	
	/**
	 * Updates a Text file line to match one in the ArrayList
	 * 
	 * @param o the order object to be updated
	 * @throws FileNotFoundException
	 */
	public void updateDatabaseInfo(Order o) throws FileNotFoundException {
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
	
	/**
	 * Completely deletes an order from the database
	 * 
	 * @param o the order to delete
	 * @throws IOException
	 */
	public void removeOrder(Order o) throws IOException {
		tempFile = new File("tempFile.txt");
		tempFile.createNewFile();
		Path path = Paths.get("OrderDatabase.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter write = new BufferedWriter(new FileWriter(tempFile));
		
		String remove = o.toString(true);
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
