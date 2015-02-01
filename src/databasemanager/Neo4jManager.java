package databasemanager;
import java.util.ArrayList;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.tooling.GlobalGraphOperations;

/**
 * <h1>Neo4j Manager</h1>
 * Used to Execute queries to the Neo4j Database
 * <b>Do not forget to change the DB_PATH<b/>
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
public class Neo4jManager {
	private  String DB_PATH = "/home/j/Build/graph.db";
	private  String SERVER_ROOT_URI = "http://localhost:7474/";

	/**
	 * Neo4j connection. Gives GraphDatabaseService back.
	 * @return Returns GraphDatabaseService
	 */
	public GraphDatabaseService openNeo4jDatabase(){
		GraphDatabaseService start = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH); 
		start.shutdown();
		return new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	}

	/**
	 * Shuts the database connection off.
	 * Needed to use the connection on multiple places in the code.
	 * @param graphDb The GraphDatabaseService. Which you can get by calling openNeo4jDatabase()
	 */
	public void closeNeo4jDatabase(GraphDatabaseService graphDb){

		graphDb.shutdown();
		System.out.println("Database Closed Successfully...");
	}

	/**
	 * Executes a Cypher query on the Neo4j Database.
	 * Gives back an ArrayList with the NodeProxy of all nodes.
	 * @param graphDb The GraphDatabaseService. Which you can get by calling openNeo4jDatabase().
	 * @param cypherQuery The Query you want to execute on the Neo4j Database.
	 * @return Returns a ArrayList with all the nodes that have been matched with the query.
     */
	public ArrayList executeQueryNeo4j(GraphDatabaseService graphDb, String cypherQuery){
		Transaction tx =  graphDb.beginTx();
		ArrayList nodeArr = new ArrayList();
		try { 
			ExecutionEngine engine = new ExecutionEngine(graphDb);
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

	/**
     * Gets all Nodes in the Neo4j Database and gives it back in an ArrayList.
	 * @param graphDb The GraphDatabaseService. Which you can get by calling openNeo4jDatabase().
	 * @return ArrayList with all the Nodes.
     */
	public ArrayList<Node> getAllNodes(GraphDatabaseService graphDb ){

        ArrayList<Node> nodes = new ArrayList<Node>();
        try {
        	Transaction tx = graphDb.beginTx();
            for (Node node : GlobalGraphOperations.at( graphDb ).getAllNodes()){
                nodes.add(node);
            }
            tx.success();
        }catch(Exception e){}
        return nodes;
    }
}

