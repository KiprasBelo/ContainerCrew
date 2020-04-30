package containersoftware;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class MainMenuTest {

	@Test
	public void testClientLoginButton() {
		MainMenu menu;
		JButton clientLogin;
		
		menu = new MainMenu();
		menu.setVisible(true);
		
		clientLogin = (JButton)TestUtils.getChildNamed(menu, "Client Login");
		
		assertNotNull(clientLogin);
		
		clientLogin.doClick(250);
		
		
	}
}
