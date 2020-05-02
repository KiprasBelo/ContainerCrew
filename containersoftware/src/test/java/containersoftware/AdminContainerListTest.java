package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

public class AdminContainerListTest {
	
	AdminContainerList list = new AdminContainerList();
	ClientLog log = new ClientLog();
	Client c;
	JButton back;
	JButton load;
	
	@Before
	public void setUp() throws FileNotFoundException {
		log.updateDatabase();
		c = log.getClients().get(1);
		list.setClient(c);
	}

	@Test
	public void testBackButton() {
		back = list.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testLoadButton() {
		load = list.getLoadButton();
		assertNotNull(load);
		load.doClick(250);
	}

}
