package containersoftware;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class AdminMainMenuTest {
	
	AdminMainMenu menu = new AdminMainMenu();
	JButton logout;
	JButton findClients;

	@Test
	public void testFindClients() {
		findClients = menu.getFindClientsButton();
		assertNotNull(findClients);
		findClients.doClick(250);
	}
	
	@Test
	public void testLogout() {
		logout = menu.getLogoutButton();
		assertNotNull(logout);
		logout.doClick(250);
	}

}
