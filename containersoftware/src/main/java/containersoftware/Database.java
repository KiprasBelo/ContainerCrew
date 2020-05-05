package containersoftware;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Database {

	//Database creation
	void createDatabase() throws IOException;
	
	//Adding textfile info into ArrayList
	void updateDatabase() throws FileNotFoundException;
}
