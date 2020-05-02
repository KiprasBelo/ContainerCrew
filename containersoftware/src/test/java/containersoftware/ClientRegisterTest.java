package containersoftware;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class ClientRegisterTest {

	ClientRegister register = new ClientRegister();
	JButton back;
	JButton btnRegister;
	
	@Test
	public void testBackButton() {
		back = register.getBackButton();
		assertNotNull(back);
		back.doClick(250);
	}
	
	@Test
	public void testRegisterButton() {
		btnRegister = register.getRegisterButton();
		assertNotNull(btnRegister);
		register.getNameField().setText("jUnitTestName");
		register.getUserNameField().setText("jUnitTestUser");
		register.getEmailField().setText("jUnitTest@gmail.com");
		register.getNumberField().setText("1231231234");
		register.getAddressField().setText("123 jUnitTest way");
		register.getReferenceField().setText("jUnitTestReference");
		register.getPasswordField().setText("testpassword");
		register.getConfirmPasswordField().setText("testpassword");
		btnRegister.doClick(250);
	}

}
