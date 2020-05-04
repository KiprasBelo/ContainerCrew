package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContainerEditorTest {
	
	ContainerLog log = new ContainerLog();
	OrderLog orderLog = new OrderLog();
	Container c;

	ContainerEditor edit = new ContainerEditor();
	JButton save;
	JButton back;
	JButton end;
	
	@Before
	public void setUp() throws FileNotFoundException {
		
		c = new Container();
		Order o = new Order(c.getContainerID(),"nowhere","nowhere","nothing");
		c.addOrders(o);
		c.setCurrentOrder(o);
		c.setOwnerID(-1);
		c.setInTransit(true);
		log.addContainer(c);
		orderLog.addOrders(o);
		orderLog.addToDatabase(o);
		orderLog.updateDatabase();
		log.addToDatabase(c);
		log.updateDatabase();
		log.setSelectedContainer(log.getContainers().get(log.getContainers().size()-1));
	}
	
	@Test
	public void testBackButton() {
		back = edit.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testSaveButton() {
		save = edit.getSaveButton();
		assertNotNull(save);
		
		edit.getHumidityField().setText("80");
		edit.getPressureField().setText("2");
		
		save.doClick(250);
		
		assertEquals(log.getSelectedContainer().getHumidity(), 80);
		assertEquals(log.getSelectedContainer().getPressure(), 2);
	}
	
	@Test
	public void testEndButton() throws IOException {
		end = edit.getEndButton();
		assertNotNull(end);
		end.doClick(250);
		
		assertEquals(log.getContainers().get(log.getContainers().size()-1).getOwnerID(),-1);
		
	}
	
	@After
	public void tearDown() throws IOException {
		log.updateDatabase();
		orderLog.updateDatabase();
		
		log.removeContainer(log.getContainers().get(log.getContainers().size()-1));
		log.getContainers().remove(log.getContainers().size()-1);
		orderLog.removeOrder(orderLog.getOrders().get(orderLog.getOrders().size()-1));
		orderLog.getOrders().remove(orderLog.getOrders().size()-1);
	}

}
