package containersoftware;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Database {

	/**
	 * Creates database text file if it does not exist
	 * 
	 * @throws IOException
	 */
	void createDatabase() throws IOException;
	
	/**
	 * Updates the ArrayList of a log to match the text file
	 * 
	 * @throws FileNotFoundException
	 */
	void updateDatabase() throws FileNotFoundException;
}
