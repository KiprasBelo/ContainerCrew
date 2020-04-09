package containersoftware;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	
	//Successful login
	Client client = new Client("john123", "password");
	Client client1;
	ResponceObject responce;
	Order o = new Order();
	Order order;
	ClientLog log = new ClientLog();
	ContainerLog containLog = new ContainerLog();
	ResponceObject responce2;
	Container contains;
	
	@Given("a client with a valid account")
	public void a_client_with_a_valid_account() {
		client.setType('C');
	}

	@Given("login status is (true|false)$")
	public void login_status_is_false(boolean isLoggedIn) {
		client.setLoginStatus(isLoggedIn);
	}

	@When("enter correct username and password")
	public void enter_correct_username_and_password() {
		responce = client.logIn("john123", "password");
	}

	@Then("Displays message about successful login")
	public void displays_message_about_successful_login() {
		assertEquals(responce.getErrorMessage(), "Logged in");
	}
	
	//unsuccessful login
	
	@When("enter incorrect username and password")
	public void enter_incorrect_username_and_password() {
		responce = client.logIn("john123", "pass");
	}

	@Then("Displays message about unsuccessful login")
	public void displays_message_about_unsuccessful_login() {
		assertEquals(responce.getErrorMessage(), "Incorrect username or password");
	}
	
	//successful logout
	
	@When("press logout")
	public void press_logout() {
		responce = client.logOut();
	}

	@Then("Displays message about successful logout")
	public void displays_message_about_successful_logout() {
		assertEquals(responce.getErrorMessage(), "Successfully logged out");
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
	public void account_has_valid_shipments() {
		Container c = new Container(o);
		containLog.addContainer(c);
		client.addShipments(containLog);
		client.getShipment(0).getOrder(0).setCargo("Bananas");
	}

	@When("access container info")
	public void access_container_info() {
		responce = client.hasShipments();
	}

	@Then("Displays message about basic container logistic information")
	public void displays_message_about_basic_container_logistic_information() {
		assertEquals(responce.getErrorMessage(), "Has shipments");
		assertEquals(client.getShipment(0).getOrder(0).getCargo(), "Bananas");
	}
	
	//No current shipments
	
	@Given("account has no valid shipments")
	public void account_has_no_valid_shipments() {
		responce = client.hasShipments();
	}

	@Then("Displays message about no valid shipments")
	public void displays_message_about_no_valid_shipments() {
		assertEquals(responce.getErrorMessage(), "Has no shipments");
	}
	
	// Client Management
	//Find Client based on name or email
	
	@Given("A client account with the name {string}")
	public void a_client_account_with_the_name(String string) {
		client1 = new Client("johnjohn", "123");
		client1.setName("John Johnson");
	}
	
	@Given("the same account has the email {string}")
	public void the_same_account_has_the_email(String string) {
		client1.setEmail("jjohnson@gmail.com");
	}
	
	@When("Enter a valid name or email in name lookup")
	public void enter_a_valid_name_or_email_in_name_lookup() {
		log.addClients(client1);
		responce = log.findClientViaName("John Johnson");
		responce2 = log.findClientViaEmail("jjohnson@gmail.com");
	}

	@Then("Display message that the client account is selected for view")
	public void display_message_that_the_client_account_is_selected_for_view() {
		assertEquals(responce.getErrorMessage(), "Found client with name John Johnson");
		assertEquals(responce2.getErrorMessage(), "Found client with email jjohnson@gmail.com");
	}
	
	//No client with name or email
	
	@When("Enter an invalid name or email in name lookup")
	public void enter_an_invalid_name_or_email_in_name_lookup() {
		responce = log.findClientViaName("Johnson John");
		responce2 = log.findClientViaEmail("gmail@jjohnson.com");
	}

	@Then("Display message that the client was not found")
	public void display_message_that_the_client_was_not_found() {
		assertEquals(responce.getErrorMessage(), "Did not find client with name Johnson John");
		assertEquals(responce2.getErrorMessage(), "Did not find client with email gmail@jjohnson.com");
	}
	
	//Add containers to accounts
	
	@Given("A container in ContainerLog with inTransit staus (true|false)$")
	public void a_container_in_ContainerLog_with_inTransit_staus_false(boolean transit) {
		contains = new Container();
		contains.setInTransit(transit);
		containLog.addContainer(contains);
	}

	@Given("A destination of {string}")
	public void a_destination_of(String string) {
		o = new Order();
		o.setEndLocation("New York");
		contains.addOrders(o);
	}

	@When("I assign the container to the client")
	public void i_assign_the_container_to_the_client() {
		responce = client.addShipments(containLog);
	}

	@Then("Display message that the Container has been added")
	public void display_message_that_the_Container_has_been_added() {
		assertEquals(responce.getErrorMessage(), "Successfully added container");
	}
	
	//No container available
	
	@Given("No containers in the containerLog")
	public void no_containers_in_the_containerLog() {
		containLog.getContainers().clear();
	}

	@Given("An order with a destination of {string}")
	public void an_order_with_a_destination_of(String string) {
		o = new Order();
		o.setEndLocation("New York");
	}
	
	@When("I try to assign the container to the client")
	public void i_try_to_assign_the_container_to_the_client() {
		responce = client.addShipments(containLog);
	}

	@Then("Display message that a container is not available")
	public void display_message_that_a_container_is_not_available() {
		assertEquals(responce.getErrorMessage(), "Could not find available container");
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


}
