package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import org.junit.Test;

public class ClientLoginTest {
	
	ClientLogin login = new ClientLogin();
	JButton back;
	JButton clientLogin;
	ClientLog log = new ClientLog();
	Client c;
	
	@Test
	public void testBackButton() {
		back = login.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testClientLogin() throws FileNotFoundException {
		login.getUserField().setText("kip");
		login.getPasswordField().setText("pass");
		clientLogin = login.getLoginButton();
		assertNotNull(clientLogin);
		clientLogin.doClick(250);
		c = log.getSelectedClient();
		c.setLoginStatus(false);
		log.updateClientDatabaseInfo(c);
	}

}
