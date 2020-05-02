package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

public class ContainerEditorTest {
	
	ContainerLog log = new ContainerLog();
	Container c;

	ContainerEditor edit = new ContainerEditor();
	JButton save;
	JButton back;
	JButton end;
	
	@Before
	public void setUp() throws FileNotFoundException {
		log.updateDatabase();
		c = log.getContainers().get(2);
		log.setSelectedContainer(log.getContainers().get(2));
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
	public void testEndButton() {
		end = edit.getEndButton();
		assertNotNull(end);
		end.doClick(250);
		
		assertEquals(log.getContainers().get(2).getOwnerID(),-1);
		
	}

}
