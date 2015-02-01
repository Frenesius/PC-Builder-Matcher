package databasemanager;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * <h1>MongoDB Database Manager.</h1>
 * Creates an Connection with the MongoDB Database
 * <b>Do not forget to change the MONGODB_URI<b/>
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
public class MongoDbManager {
	private String MONGODB_URI = "localhost";
	private int MONGODB_PORT = 27017;
	private String MONGODB_DATABASE_NAME = "test";

	/**
	 * Opens a connection With the MongoDatabase.
	 * @return Returns the DB object of the MongoDB database.
	 */
	public DB openMongoDatabase() throws UnknownHostException{
		return (new MongoClient(MONGODB_URI,MONGODB_PORT)).getDB(MONGODB_DATABASE_NAME);
	}
}