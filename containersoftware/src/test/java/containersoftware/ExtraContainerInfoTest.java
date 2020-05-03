package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExtraContainerInfoTest {
	
	ContainerLog log = new ContainerLog();
	Container c;

	ExtraContainerInfo info;
	JButton back;
	JButton load;
	
	@Before
	public void setUp() throws FileNotFoundException {
		log.updateDatabase();
		c = log.getContainers().get(0);
		info = new ExtraContainerInfo();
	}
	
	@Test
	public void testBackButton() {
		back = info.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testLoadButton() {
		load = info.getLoadButton();
		assertNotNull(load);
		info.setContainer(c);
		load.doClick(250);
		
		assertEquals(c.getHumidity(),Integer.parseInt(info.getHunidityLbl().getText()));
		assertEquals(c.getPressure(),Integer.parseInt(info.getPressureLbl().getText()));
	}

}
