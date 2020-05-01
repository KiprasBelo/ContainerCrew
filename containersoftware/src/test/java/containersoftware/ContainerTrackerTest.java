package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContainerTrackerTest {
	
	ContainerTracker track = new ContainerTracker();
	ContainerLog log = new ContainerLog();
	ClientLog log2 = new ClientLog();
	JButton back;
	JButton loadAll;
	JButton loadByCriteria;
	
	@Before
	public void setUp() throws FileNotFoundException {
		log.updateDatabase();
		log2.updateDatabase();
		
		log2.getClients().get(1).setLoginStatus(true);
		log2.updateClientDatabaseInfo(log2.getClients().get(1));
	}

	@Test
	public void testBackButton() {
		back = track.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testLoadAllButton() {
		loadAll = track.getLoadAllButton();
		assertNotNull(loadAll);
		loadAll.doClick(250);
	}
	
	@Test
	public void testLoadByCriteriaButton() {
		loadByCriteria = track.getFindByCriteriaButton();
		assertNotNull(loadByCriteria);
		
		track.getOriginField().setText("Chicago");
		track.getDestinationField().setText("Dover");
		track.getCargoField().setText("Cars");
		
		loadByCriteria.doClick(250);
	}
	
	@After
	public void tearDown() throws FileNotFoundException {
		log2.Logout();
	}

}
