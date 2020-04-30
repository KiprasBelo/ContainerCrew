package containersoftware;

import java.awt.*;
import javax.swing.*;

public class TestUtils {

	static int counter;
	
	public static Component getChildNamed(Component parent, String name) {
		if(name.contentEquals(parent.getName()) ) {return parent;}
		
		if(parent instanceof java.awt.Container) {
			Component[] children = (parent instanceof JMenu) ?
					((JMenu)parent).getMenuComponents() :
					((java.awt.Container)parent).getComponents();
					
			for(int i = 0; i < children.length; i++) {
				
				Component child = getChildNamed(children[i], name);
				if(child != null) {return child;}
				
			}
		}
		return null;
	}
	
}
