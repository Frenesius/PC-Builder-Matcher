package main;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.util.StringLogger;

import databasemanager.Neo4jManager;

public class Main {

	public static void main(String[] args) {
	    Neo4jManager a = new Neo4jManager();
		GraphDatabaseService db = a.openNeo4jDatabase();
		
		String query = "MATCH(n) RETURN n";
		Transaction tx =  db.beginTx();		
		 try { 
			 	//Node n = db.createNode();
			
			    ExecutionEngine engine = new ExecutionEngine(db);
			    ExecutionResult result = engine.execute(query);			    
			    System.out.println(result);
			    String stringDump = result.dumpToString();
		        System.out.println(stringDump);
			    tx.success();
		       } 
		 finally {    
		     tx.close();  
		 } 
	}	
}
	