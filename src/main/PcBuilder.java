package main;

import java.sql.SQLException;
import java.util.ArrayList;

import matcher.MatcherMain;

import org.neo4j.graphdb.GraphDatabaseService;

import components.Hardware;
import components.WebInput;

import databasemanager.Neo4jManager;

public class PcBuilder {
	
	
	//Starts to run the program
	public void run() throws SQLException{
		//Connections and objects
		Main main = new Main();
		Neo4jManager a = new Neo4jManager();
	    GraphDatabaseService b;
	    MatcherMain matcher = new MatcherMain();
	    b = a.openNeo4jDatabase();
	    WebInput webinput = new WebInput();
	    
	    //Lists
	    
	    ArrayList arr = new ArrayList();
//	    arr = a.getAllNodes(b);
	    a.closeNeo4jDatabase(b);
	    
		/*Case:
	    *	1 Krijg een arraylist van mo
	    *	2 Parse arraylist in onderdelen
	    *	3 Check compatibility
	    *	4 Maak nieuwe list met onderdelen
	    *
	    *
	    */
	    
	    /*
	     * 1 Is de input van mo
	     * Kijgt het in form van:
	     * null voor not selected.
	     * JSON voor geselecteerde onderdelen.
	     */
	    ArrayList webInput = new ArrayList();	 
	    webInput = webinput.inputWebserverMotherboard();
	    /*	2 Haalt op wat meegegeven is en parsed het in objecten. 
	    *	null betekend dat onderdeel niet meegegeven is
	    */
//	    ArrayList matchedComponents = matcher.getHardwareByInput(webInput);
	    
	    /*
	     * 3 Matcher
	     * 	Moet onderdelen matchen
	     */
//	    ArrayList matchedHardware = new ArrayList();
//	    matchedHardware = matcher.matchFromMotherboard(matchedComponents);

	  
	    
	    //Haalt alle onderdelen op gebasseert op de mobo.
	    ArrayList matchedHardware = matcher.matchFromMotherboard(     matcher.getHardwareByInput(webinput.inputWebserverMotherboard()));

	    
	    	}	
	public void fullCheck(){
		//Connections and objects
		Main main = new Main();
		Neo4jManager a = new Neo4jManager();
	    GraphDatabaseService b;
	    MatcherMain matcher = new MatcherMain();
	    b = a.openNeo4jDatabase();
	    WebInput webinput = new WebInput();
	    
		ArrayList matchedComponents = matcher.determineSelectedComponents(matcher.getHardwareByInput(webinput.inputWebserverMatchToMobo()));
	    String result = matcher.createQuery(matchedComponents);
	    if(result == "MOBO"){
	    	//Mobo matchen
	    	//Get mobo, check met instance of
	    	//matchFromMotherboard();
	    }
	}
	
	//Ignore below this line

}
