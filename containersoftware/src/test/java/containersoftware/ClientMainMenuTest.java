package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.Test;

public class ClientMainMenuTest {

	ClientMainMenu menu = new ClientMainMenu();
	ClientLog log = new ClientLog();
	JButton logout;
	JButton addJourney;
	JButton trackJourney;
	JButton account;
	
	@Test
	public void testAccountButton() throws FileNotFoundException {
		log.updateDatabase();
		log.getClients().get(1).setLoginStatus(true);
		account = menu.getAccountButton();
		assertNotNull(account);
		account.doClick(250);
	}
	
	@Test
	public void testAddJourneyButton() {
		addJourney = menu.getAddJourneyButton();
		assertNotNull(addJourney);
		addJourney.doClick(250);
	}
	
	@Test
	public void testTrackJourneyButton() {
		trackJourney = menu.getTrackJourneyButton();
		assertNotNull(trackJourney);
		trackJourney.doClick(250);
	}
	
	@Test
	public void testLogout() {
		logout = menu.getLogoutButton();
		assertNotNull(logout);
		logout.doClick(250);
	}

}
