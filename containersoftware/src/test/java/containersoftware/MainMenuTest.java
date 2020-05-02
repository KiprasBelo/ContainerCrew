package containersoftware;

import static org.junit.Assert.*;

import java.awt.Component;

import javax.swing.JButton;

import org.junit.Test;

public class MainMenuTest {
	
	MainMenu menu = new MainMenu();
	JButton clientLogin;
	JButton adminLogin;
	JButton clientRegister;

	@Test
	public void testClientLoginButton() {
		
		clientLogin = menu.getClientLoginButton();
		assertNotNull(clientLogin);
		clientLogin.doClick(250);
		
	}

	@Test
	public void testAdminLoginButton() {
	
		adminLogin = menu.getAdminLoginButton();
		assertNotNull(adminLogin);
		adminLogin.doClick(250);
	
	}
	
	@Test
	public void testClientRegisterButton() {
	
		clientRegister = menu.getClientRegisterButton();
		assertNotNull(clientRegister);
		clientRegister.doClick(250);
	
	}
}