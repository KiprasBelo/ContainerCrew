package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountInfoEditTest {
	
	ClientLog log = new ClientLog();
	Client c;
	
	AccountInfoEdit edit;
	JButton back;
	JButton save;
	
	@Before
	public void setUp() throws FileNotFoundException {
		log.updateDatabase();
		log.getClients().get(2).setLoginStatus(true);
		c = log.getClients().get(2);
		log.updateClientDatabaseInfo(c);
		edit = new AccountInfoEdit();
	}

	@Test
	public void testBackButton() {
		back = edit.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testSaveButton() {
		back = edit.getBackButton();
		assertNotNull(back);
		
		edit.getNameField().setText("JunitEditName");
		edit.getNumberField().setText("1231231234");
		edit.getAddressField().setText("123 Junit lane");
		edit.getReferenceField().setText("JunitEditReference");
		edit.getEmailField().setText("JunitEditEmail");
		
		back.doClick(250);
	}
	
	@After
	public void tearDown() throws FileNotFoundException {
		log.Logout();
	}

}
