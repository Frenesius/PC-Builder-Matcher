package databasemanager;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.glassfish.jersey.client.ClientResponse;
import org.neo4j.cypher.internal.spi.v2_1.TransactionBoundQueryContext.NodeOperations;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.tooling.GlobalGraphOperations;

import scala.collection.Iterator;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Neo4jManager {
	public static String DB_PATH = "/home/j/Build/graph.db";  //TODO CHANGE LOCATION
	public static String SERVER_ROOT_URI = "http://localhost:7474/";
	
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
			System.out.println("Start");
				
			ExecutionEngine engine = new ExecutionEngine(db);
			ExecutionResult result = engine.execute(query);
			//Do something
			tx.success();
			} 
		finally {    
		     tx.close();  
		 		} 		
	}
	
	public void openConnectionRest(){
		WebResource resource = Client.create()
		        .resource(SERVER_ROOT_URI);
		ClientResponse response = resource.get(ClientResponse.class);

		System.out.println( String.format( "GET on [%s], status code [%d]",
		        SERVER_ROOT_URI, response.getStatus() ) );
		response.close();
	}
	
    public ArrayList<Node> getAllNodes(GraphDatabaseService graphDb ){
        ArrayList<Node> nodes = new ArrayList<>();
        try (Transaction tx = graphDb.beginTx()){
            for (Node node : GlobalGraphOperations.at( graphDb ).getAllNodes()){
                nodes.add(node);
            }
            tx.success();
        }
        return nodes;	//Returns an ArrayList with all the Nodes.
    }
	
    public void getNodeByProperty(ArrayList arr, String property){
		for(int i = 0; i<arr.size();i++){
			Node n = (Node) arr.get(i);
			try{
				n.getProperty(property);
			}
			catch(Exception e){}
		}
	}
    
	
	
	
	
}

