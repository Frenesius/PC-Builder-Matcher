package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.tooling.GlobalGraphOperations;

import databasemanager.Neo4jManager;

public class Main {

	public static void main(String[] args){
	    Neo4jManager a = new Neo4jManager();
	    GraphDatabaseService b;
	    List list;
	    ArrayList arr = new ArrayList();
	    try{
	    	b = a.openNeo4jDatabase();
	    	arr = a.getAllNodes1(b);
	    	for(int i = 0; i<arr.size(); i++){
	    		Node m = (Node) arr.get(i);
	    		
	    	}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	
	    }	
	}
}	