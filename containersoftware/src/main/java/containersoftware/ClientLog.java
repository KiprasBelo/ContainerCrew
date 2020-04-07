package containersoftware;

import java.util.ArrayList;

public class ClientLog {

	private ArrayList<Client> clients = new ArrayList<Client>();
	private Client selectedClient;
	
	public ClientLog() {}
	
	public void addClients(Client c) {
		clients.add(c);
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
	
	public Client getSelectedClient() {
		return selectedClient;
	}
	
	
}


