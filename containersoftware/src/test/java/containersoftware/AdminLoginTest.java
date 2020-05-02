package containersoftware;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class AdminLoginTest {

	AdminLogin admin = new AdminLogin();
	JButton back;
	JButton login;
	
	@Test
	public void testBackButton() {
		back = admin.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testLoginButton() {
		admin.getUserField().setText("Admin");
		admin.getPasswordField().setText("Password");
		login = admin.getLoginButton();
		assertNotNull(login);
		login.doClick(250);
	}

}
