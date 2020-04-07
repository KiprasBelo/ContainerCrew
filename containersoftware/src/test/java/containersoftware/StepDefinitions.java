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
	ClientLog log = new ClientLog();
	ResponceObject responce2;
	
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
		client.addShipments(new Container(o));
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

}
