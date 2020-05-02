package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountInfoDisplayTest {
	
	ClientLog log = new ClientLog();
	Client c;

	AccountInfoDisplay display;
	JButton back;
	JButton edit;
	
	@Before
	public void setUp() throws FileNotFoundException {
		log.updateDatabase();
		log.getClients().get(1).setLoginStatus(true);
		c = log.getClients().get(1);
		log.updateClientDatabaseInfo(c);
		display = new AccountInfoDisplay();
	}

	@Test
	public void testBackButton() {
		back = display.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testEditButton() {
		edit = display.getEditButton();
		assertNotNull(edit);
		edit.doClick(250);
	}
	
	@After
	public void tearDown() throws FileNotFoundException {
		log.Logout();
	}

}
