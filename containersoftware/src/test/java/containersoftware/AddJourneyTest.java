package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddJourneyTest {
	
	ClientLog log = new ClientLog();
	ContainerLog log2 = new ContainerLog();
	OrderLog orderLog = new OrderLog();
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
	public void testRegisterButton() throws IOException {
		journey.getOriginField().setText("jUnitOriginTest");
		journey.getDestinationField().setText("jUnitDestinationTest");
		journey.getCargoField().setText("jUnitCargoTest");
		journey.getTempField().setText("1");
		
		register = journey.getRegisterButton();
		assertNotNull(register);
		register.doClick(250);
		
		assertEquals("jUnitCargoTest",c.getShipment(0).getCurrentOrder().getCargo());
		
		log2.updateDatabase();
		orderLog.updateDatabase();
		log2.removeContainer(log2.getContainers().get(log2.getContainers().size()-1));
		log2.getContainers().remove(log2.getContainers().size()-1);
		orderLog.removeOrder(orderLog.getOrders().get(orderLog.getOrders().size()-1));
		orderLog.getOrders().remove(orderLog.getOrders().size()-1);
	}
	
	@After
	public void tearDown() throws IOException {
		log.Logout();
	}

}
