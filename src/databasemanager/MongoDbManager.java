package databasemanager;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDbManager {
	private String MONGODB_URI = "localhost";
	private int MONGODB_PORT = 27017;
	private String MONGODB_DATABASE_NAME = "test";
	
	public DB openMongoDatabase() throws UnknownHostException{
		return (new MongoClient(MONGODB_URI,MONGODB_PORT)).getDB(MONGODB_DATABASE_NAME);
	}
}