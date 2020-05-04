package containersoftware;

import java.io.FileNotFoundException;

public interface Database {

	//Database creation
	void createDatabase();
	
	//Adding to textfile database
	void addToDatabase();
	
	//Adding textfile info into ArrayList
	void updateDatabase() throws FileNotFoundException;
	
	//Edits a single object in the database
	void updateDatabaseInfo() throws FileNotFoundException;
}
