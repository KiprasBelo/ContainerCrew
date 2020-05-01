package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListModel;

import org.junit.Test;

public class ClientFinderTest {
	
	ClientFinder find = new ClientFinder();
	ClientLog log = new ClientLog();
	JButton back;
	JButton loadAll;
	JButton loadCriteria;
	JList list;
	ListModel<Class<?>> model;

	@Test
	public void testBackButton() {
		back = find.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testLoadAllButton() throws FileNotFoundException {
		loadAll = find.getLoadAllButton();
		assertNotNull(loadAll);
		loadAll.doClick(250);
		
		list = find.getList();
		model = list.getModel();
		
		log.updateDatabase();
		
		//assertEquals(log.getClients().get(0),model.getElementAt(0));
	}
	
	@Test
	public void testLoadByCriteriaButton() {
		loadCriteria = find.getLoadByCriteriaButton();
		assertNotNull(loadCriteria);
		
		find.getNameField().setText("kip");
		find.getNameField().setText("kip@gmail.com");
		
		loadCriteria.doClick(250);
		
		list = find.getList();
		model = list.getModel();
		//assertEquals(log.getClients().get(1),model.getElementAt(0));
	}

}
