

import java.util.ArrayList;

public class ClientLog {

	private static ArrayList<Client> clients = new ArrayList<Client>();
	private Client selectedClient;
	
	public ClientLog() {}
	
	public void addClients(Client c) {
		clients.add(c);
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public Client getSelectedClient() {
		
		Client c = null;
		
		for(Client x : clients) {
			if(x.getLoginStatus()) {
				selectedClient = x;
				c = x;
				break;
			}
		}
		return c;
	}
	
	public ResponceObject findClientViaName(String name) {
		ResponceObject responce = new ResponceObject("Found client with name "+name);
		
		for(Client x : clients) {
			if(x.getName().contentEquals(name)) {
				selectedClient = x;
				return responce;
			}
		}
		return responce = new ResponceObject("Did not find client with name "+name);
	}
	
	public ResponceObject findClientViaEmail(String email) {
		ResponceObject responce = new ResponceObject("Found client with email "+email);
		
		for(Client x : clients) {
			if(x.getEmail().contentEquals(email)) {
				selectedClient = x;
				return responce;
			}
		}
		return responce = new ResponceObject("Did not find client with email "+email);
	}
	
	
}


