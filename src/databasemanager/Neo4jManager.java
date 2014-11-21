package databasemanager;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jManager {
	public static String DB_PATH = "/home/j/Build/graph.db";  //TODO CHANGE LOCATION
	
	public GraphDatabaseService openNeo4jDatabase(){
		GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH); 
		return graphDb;
	}
	
	public void closeNeo4jDatabase(GraphDatabaseService graphDb){
		graphDb.shutdown();
		System.out.println("Database Closed Successfully...");
	}
	
	public void executeQueryNeo4j(GraphDatabaseService db, String query){
		Transaction tx =  db.beginTx();		
		try { 
			ExecutionEngine engine = new ExecutionEngine(db);
			ExecutionResult result = engine.execute(query);			    
			tx.success();
		       } 
		 finally {    
		     tx.close();  
		 } 		
	}
	
	
	
	
	
}
