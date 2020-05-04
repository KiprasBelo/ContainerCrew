package containersoftware;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class FirstPageTest {

	FirstPage page = new FirstPage();
	JButton proceed;
	
	@Test
	public void testProceedButton() {
		
		proceed = page.getProceedButton();
		assertNotNull(proceed);
		proceed.doClick(250);
		
	}

}
