package databasemanager;
import java.util.ArrayList;

import org.glassfish.jersey.client.ClientResponse;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.tooling.GlobalGraphOperations;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Neo4jManager {
	/*
	 * Neo4j Database manager.
	 * Used to execute queries, get nodes.
	 */
	private  String DB_PATH = "/home/j/Build/graph.db";  //TODO CHANGE LOCATION
	private  String SERVER_ROOT_URI = "http://localhost:7474/";
	
	public GraphDatabaseService openNeo4jDatabase(){
		/*
		 * Neo4j connection. Gives GraphDatabaseService back.
		 */
		
		GraphDatabaseService start = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH); 
		start.shutdown();
		return new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	}
	public void closeNeo4jDatabase(GraphDatabaseService graphDb){
		/*
		 * Shuts the database connection off.
		 * Needed to use the connection on multiple places in the code.
		 */
		graphDb.shutdown();
		System.out.println("Database Closed Successfully...");
	}
	public ArrayList executeQueryNeo4j(GraphDatabaseService db, String cypherQuery){
		/*
		 * Executes a Cypher query on the Neo4j Database.
		 * Gives back an ArrayList with the NodeProxy of all nodes.
		 */
		Transaction tx =  db.beginTx();		
		ArrayList nodeArr = new ArrayList();
		try { 
			ExecutionEngine engine = new ExecutionEngine(db);
			ExecutionResult result = engine.execute(cypherQuery);
			ResourceIterator<Node> n_column = result.columnAs("n");
			
			for(int i = 0; n_column.hasNext();i++){
				Node ga  = n_column.next();
				nodeArr.add(ga);
				}
			tx.success();
			} 
		finally {tx.close();} 
		return nodeArr;
	}
	public void openConnectionRest(){
		/*
		 * Testing to get a connection in REST.
		 */
		WebResource resource = Client.create()
		        .resource(SERVER_ROOT_URI);
		ClientResponse response = resource.get(ClientResponse.class);

		System.out.println( String.format( "GET on [%s], status code [%d]",
		        SERVER_ROOT_URI, response.getStatus() ) );
		response.close();
	}
    public ArrayList<Node> getAllNodes(GraphDatabaseService graphDb ){
    	/*
    	 * Gets all Nodes in the Neo4j Database and gives it back in an ArrayList.
    	 */
        ArrayList<Node> nodes = new ArrayList<Node>();
        try {
        	Transaction tx = graphDb.beginTx();
            for (Node node : GlobalGraphOperations.at( graphDb ).getAllNodes()){
                nodes.add(node);
            }
            tx.success();
        }catch(Exception e){}
        return nodes;	//Returns an ArrayList with all the Nodes.
    }
    public void getNodeByProperty(ArrayList nodeList, String property){
    	/*
    	 * Gets the property of an Node Proxy.
    	 */
		for(int i = 0; i<nodeList.size();i++){
			Node n = (Node) nodeList.get(i);
			try{n.getProperty(property);}
			catch(Exception e){}
		}
	}	
}

