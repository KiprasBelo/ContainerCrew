package containersoftware;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	
	Client client = new Client("john123", "password");
	Client client1;
	Order o = new Order();
	Order order;
	ClientLog log = new ClientLog();
	ContainerLog containLog = new ContainerLog();
	OrderLog orderLog = new OrderLog();
	Container contains;
	String username;
	String password;
	String destination;
	ArrayList<Container> cont = new ArrayList<Container>();
	boolean temp = false;
	int id;
	int idToRemove;
	
	//Register Client
	
	@Given("An entered username {string}")
	public void an_entered_username(String string) {
		try {
			log.createDatabase();
			containLog.createDatabase();
			orderLog.createDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		username = string;
	}

	@Given("An entered password {string}")
	public void an_entered_password(String string) {
		password = string;
	}

	@When("Hit register button")
	public void hit_register_button() throws FileNotFoundException {
		temp = log.Register(username, password, password, null, null, null, null, null);
	}

	@Then("Display message about successful register")
	public void display_message_about_successful_register() throws IOException {
		log.updateDatabase();
		assertEquals(temp, true);
		log.removeClient(log.getClients().get(log.getClients().size()-1));
		log.getClients().remove(log.getClients().size()-1);
	}
	
	//Login
	
	@Given("a client with a valid account")
	public void a_client_with_a_valid_account() {
		assertNotNull(client);
	}

	@Given("login status is (true|false)$")
	public void login_status_is_false(boolean isLoggedIn) {
		client.setLoginStatus(isLoggedIn);
	}

	@When("enter correct username and password")
	public void enter_correct_username_and_password() {
		try {
			temp = log.Login("John123", "pass");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		log.setFoundClient(client);
	}

	@Then("Displays message about successful login")
	public void displays_message_about_successful_login() {
		assertEquals(temp, log.getSelectedClient().getLoginStatus());
	}
	
	//successful logout
	
	@When("press logout")
	public void press_logout() {
		try {
			temp = log.Logout();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}

	@Then("Displays message about successful logout")
	public void displays_message_about_successful_logout() {
		assertEquals(temp, true);
	}
	
	//unsuccessful login
	
	@When("enter incorrect username and password")
	public void enter_incorrect_username_and_password() {
		try {
			temp = log.Login("123john", "password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Then("Displays message about unsuccessful login")
	public void displays_message_about_unsuccessful_login() {
		assertEquals(temp, client.getLoginStatus());
	}
	
	//Updating account info
	
	@When("Entered new account info")
	public void entered_new_account_info() {
		client.setName("John Smith");
		client.setEmail("johnsmith@gmail.com");
	}

	@Then("Displays message about updated account info")
	public void displays_message_about_updated_account_info() {
		assertEquals(client.getName(), "John Smith");
		assertEquals(client.getEmail(), "johnsmith@gmail.com");
	}
	
	//Accessing container info
	
	@Given("account has valid shipments")
	public void account_has_valid_shipments() throws FileNotFoundException {
		Container c = new Container(o);
		client.addShipments(c);
		client.getShipment(0).getOrder(0).setCargo("Bananas");
	}

	@When("access container info")
	public void access_container_info() {
		temp = client.hasShipments();
	}

	@Then("Displays message about basic container logistic information")
	public void displays_message_about_basic_container_logistic_information() {
		assertEquals(temp, true);
		assertEquals(client.getShipment(0).getOrder(0).getCargo(), "Bananas");
	}
	
	//Access Containers as Client
	
	@Given("a valid client account")
	public void a_valid_client_account() {
		assertNotNull(client);
	}
	
	@Given("a <list> of containers with matching client id {int}")
	public void a_list_of_containers_with_matching_client_id(Integer int1) throws FileNotFoundException {
		containLog.updateDatabase();
		for(Container x: containLog.getContainers()) {
			if(x.getOwnerID() == int1) {
				client.addShipments(x);
			}
		}
	}

	@When("I search for a container using port of origin {string} and destinaltion {string}")
	public void i_search_for_a_container_using_port_of_origin_and_destinaltion(String string, String string2) {
		
		boolean isfound = false;
		
		for(Container x : client.getShipments()) {
			if(x.getCurrentOrder().getStartLocation().contentEquals(string) && x.getCurrentOrder().getEndLocation().contentEquals(string2)) {
				isfound = true;
			}
		}
		assertEquals(true, isfound);
	}
	

	@Then("the containers matching the search will be displayed")
	public void the_containers_matching_the_search_will_be_displayed() {
		contains = client.getShipment(0);
		assertEquals(contains.toString(true), "0,6,New York,Bananas,Oslo,1.0,true,04:24:06,70,1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0");
	}
	
	//No current shipments
	
	@Given("account has no valid shipments")
	public void account_has_no_valid_shipments() {
		temp = client.hasShipments();
	}

	@Then("Displays message about no valid shipments")
	public void displays_message_about_no_valid_shipments() {
		assertEquals(temp, false);
	}
	
	// Client Management
	//Find Client based on name or email
	
	@Given("A client account with the name {string}")
	public void a_client_account_with_the_name(String string) {
		assertNotNull(log.getClients().get(3));
	}
	
	@Given("the same account has the email {string}")
	public void the_same_account_has_the_email(String string) {
		log.getClients().get(3).setEmail("jjohnson@gmail.com");
	}
	
	@When("Enter a valid name or email in name lookup")
	public void enter_a_valid_name_or_email_in_name_lookup() {
		temp = log.findClients("jjohnson@gmail.com","test");
	}

	@Then("Display message that the client account is selected for view")
	public void display_message_that_the_client_account_is_selected_for_view() {
		assertEquals(temp, true);
	}
	
	//No client with name or email
	
	@When("Enter an invalid name or email in name lookup")
	public void enter_an_invalid_name_or_email_in_name_lookup() {
		temp = log.findClients("gmail@jjohnson.com", "Johnson John");
	}

	@Then("Display message that the client was not found")
	public void display_message_that_the_client_was_not_found() {
		assertEquals(temp, false);
	}
	
	//No container available
	
	@Given("No available containers in the containerLog")
	public void no_available_containers_in_the_containerLog() throws FileNotFoundException {
		contains = new Container();
		o = new Order(contains.getContainerID(),"nowhere","nowhere","nothing");
		contains.addOrders(o);
		contains.setCurrentOrder(o);
		contains.setOwnerID(-1);
		contains.setInTransit(false);
		containLog.addContainer(contains);
		containLog.addToDatabase(contains);
	}

	@Given("An order with a destination of {string}")
	public void an_order_with_a_destination_of(String string) {
		destination = "New York";
	}
		
	@When("I try to assign the container to the client")
	public void i_try_to_assign_the_container_to_the_client() {
		log.getClients().get(6).setLoginStatus(true);
		try {
			temp = containLog.addContainerToClient(null, destination, null, 10);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Then("Display message that a container is not available")
	public void display_message_that_a_container_is_not_available() {
		assertEquals(temp, true);
		log.getClients().get(6).setLoginStatus(false);
	}
		
	//Add containers to accounts

	@Given("A destination of {string}")
	public void a_destination_of(String string) {
		destination = string;
	}

	@When("I assign the container to the client")
	public void i_assign_the_container_to_the_client() {
		try {
			log.updateDatabase();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		log.getClients().get(6).setLoginStatus(true);
		try {
			temp = containLog.addContainerToClient(null, destination, null, 5);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Then("Display message that the Container has been added")
	public void display_message_that_the_Container_has_been_added() {
		assertEquals(temp, true);
		log.getClients().get(6).setLoginStatus(false);
	}
	
	//Build Container temperature Data
	
	@Given("Todays date along with Container start date")
	public void todays_date_along_with_Container_start_date() {
		log.getClients().get(6).setLoginStatus(true);
		log.getSelectedClient().getShipment(0).setStartDate("4:20:01");
		assertEquals(true,log.getSelectedClient().getShipment(0).getInTransit());
	}

	@When("Check dates of container access")
	public void check_dates_of_container_access() {
		log.setTempDate("4:27:12");
		log.checkDates(log.getSelectedClient().getShipment(0));
	}

	@Then("A number of hours of temperature are generated")
	public void a_number_of_hours_of_temperature_are_generated() {
		log.getClients().get(6).setLoginStatus(false);
	}
	
	//Different Time for Temperature data
	@Given("Todays date along with Container start or last login date")
	public void todays_date_along_with_Container_start_or_last_login_date() {
		log.getClients().get(6).setLoginStatus(true);
		log.getSelectedClient().getShipment(0).setStartDate("4:20:01");
		assertEquals(true,log.getSelectedClient().getShipment(0).getInTransit());
	}
	
	@When("Check dates of login")
	public void check_dates_of_login() {
		log.setTempDate("5:4:12");
		log.checkDates(log.getSelectedClient().getShipment(0));
	}

	@Then("A number of hours of new temperature are generated")
	public void a_number_of_hours_of_new_temperature_are_generated() {
		log.getClients().get(6).setLoginStatus(false);
	}
	
	//Different Time for Temperature data
	@Given("Todays date along with Container start as todays date")
	public void todays_date_along_with_Container_start_as_todays_date() {
		log.getClients().get(6).setLoginStatus(true);
		log.getSelectedClient().getShipment(0).setStartDate("5:4:12");
		assertEquals(true,log.getSelectedClient().getShipment(0).getInTransit());
	}

	@When("Check dates of Container start")
	public void check_dates_of_Container_start() {
		log.setTempDate(log.getSelectedClient().getShipment(0).getStartDate());
		log.checkDates(log.getSelectedClient().getShipment(0));
	}

	@Then("A number of hours of new temperature are created")
	public void a_number_of_hours_of_new_temperature_are_created() {
		log.getClients().get(6).setLoginStatus(false);
	}
	
	//Edit container Info as Admin
	
	@When("Edit humidity and atmospheric pressure")
	public void edit_humidity_and_atmospheric_pressure() {
		log.getClients().get(6).getShipment(0).setHumidity(80);
		log.getClients().get(6).getShipment(0).setPressure(2);
	}

	@Then("The values are changed in the database")
	public void the_values_are_changed_in_the_database() {
		assertEquals(log.getClients().get(6).getShipment(0).getHumidity(), 80);
		assertEquals(log.getClients().get(6).getShipment(0).getPressure(), 2);
	}
	
	//Access Container History
	
	@Given("A container in the containerLog")
	public void a_container_in_the_containerLog() {
		contains = new Container();
		containLog.addContainer(contains);
	}

	@Given("at least one order behind current order in the container histroy")
	public void at_least_one_order_in_the_container_histroy() {
		o = new Order();
		o.setCargo("Apples");
		contains.addOrders(o);
		order = new Order();
		o.setCargo("Oranges");
		contains.addOrders(order);
	}

	@When("I call the history for a container")
	public void i_call_the_history_for_a_container() {
		contains.getHistory();
	}

	@Then("Display order History")
	public void display_order_History() {
		assertEquals(contains.getHistory().get(0), contains.getOrder(0));
	}
	
	//Remove container / end journey
	
	@Given("A container with transit status of (true|false)$")
	public void a_container_with_transit_status_of_true(boolean logged) {
		log.getClients().get(6).setLoginStatus(true);
		temp = log.getSelectedClient().getShipment(0).getInTransit();
		assertEquals(temp, logged);
	}

	@When("Choose to end the journey")
	public void choose_to_end_the_journey() {
		
		id = log.getSelectedClient().getShipment(0).getContainerID();
		containLog.setSelectedContainer(log.getSelectedClient().getShipment(0));
		
		try {
			containLog.end();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		log.getClients().get(6).setLoginStatus(false);
	}

	@Then("Login status is false and Container is removed from client")
	public void login_status_is_false_and_Container_is_removed_from_client() throws IOException {
		for(Container x : containLog.getContainers()) {
			if(x.getContainerID() == id) {
				assertEquals(x.getInTransit(), false);
			}
		}
		containLog.updateDatabase();
		orderLog.updateDatabase();
		containLog.removeContainer(containLog.getContainers().get(containLog.getContainers().size()-1));
		containLog.getContainers().remove(containLog.getContainers().size()-1);
		containLog.removeContainer(containLog.getContainers().get(containLog.getContainers().size()-1));
		containLog.getContainers().remove(containLog.getContainers().size()-1);
		orderLog.removeOrder(orderLog.getOrders().get(orderLog.getOrders().size()-1));
		orderLog.getOrders().remove(orderLog.getOrders().size()-1);
		orderLog.removeOrder(orderLog.getOrders().get(orderLog.getOrders().size()-1));
		orderLog.getOrders().remove(orderLog.getOrders().size()-1);

	}


}
