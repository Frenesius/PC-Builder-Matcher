package databasemanager;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDbManager {
	String MONGODB_URI = "localhost";
	int MONGODB_PORT = 27017;	
	String MONGODB_DATABASE_NAME = "test";
	
	public DB openMongoDatabase() throws UnknownHostException{
		DB db = (new MongoClient(MONGODB_URI,MONGODB_PORT)).getDB(MONGODB_DATABASE_NAME);
		return db; 
	}
	   
}