package matcher;
import java.util.ArrayList;

import org.neo4j.graphdb.GraphDatabaseService;

import databasemanager.Neo4jManager;

public class MatcherMotherboardCompatibility {
	Neo4jManager neo4j;
	GraphDatabaseService db;
	
	MatcherMotherboardCompatibility(){
		neo4j = new Neo4jManager();
		db = neo4j.openNeo4jDatabase();
	}
	
	public void matchRamBasedOnMobo(String motherboardGeheugenType, String EAN){
		String query = "MATCH (mobo) --> (moboBaseNode) --> (Hardware), (ram) --> (ramBaseNode) --> (Hardware) "
				+ "WHERE mobo.EAN = '"+EAN+"' "
				+ "AND ram.Geheugentype =~ '.*"+motherboardGeheugenType+".*' "
				+ "RETURN ram "
				+ "LIMIT 10; ";
		
		ArrayList b = this.neo4j.executeQueryNeo4j(this.db, query);
		b.hashCode();
		String a = "a";
	}
}	
