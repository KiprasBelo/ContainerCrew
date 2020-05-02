package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddJourneyTest {
	
	ClientLog log = new ClientLog();
	Client c;
	
	AddJourney journey;
	JButton back;
	JButton register;

	@Before
	public void setUp() throws FileNotFoundException {
		log.updateDatabase();
		log.getClients().get(2).setLoginStatus(true);
		c = log.getClients().get(2);
		c.getShipments().clear();
		log.updateClientDatabaseInfo(c);
		journey = new AddJourney();
	}
	
	@Test
	public void testBackButton() {
		back = journey.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testRegisterButton() {
		journey.getOriginField().setText("jUnitOriginTest");
		journey.getDestinationField().setText("jUnitDestinationTest");
		journey.getCargoField().setText("jUnitCargoTest");
		journey.getTempField().setText("1");
		
		register = journey.getRegisterButton();
		assertNotNull(register);
		register.doClick(250);
		
		assertEquals("jUnitCargoTest",c.getShipment(0).getCurrentOrder().getCargo());
	}
	
	@After
	public void tearDown() throws FileNotFoundException {
		
		log.Logout();
	}

}
