package main;

import java.sql.SQLException;
import java.util.ArrayList;

import matcher.FindComponents;
import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;

import org.neo4j.graphdb.GraphDatabaseService;

import components.Motherboard;
import components.WebInput;

import databasemanager.Neo4jManager;

public class PcBuilder {
	
	MatcherMain matcher = new MatcherMain();
	//Starts to run the program
	public void run() throws SQLException{
		//Connections and objects
	    WebInput webinput = new WebInput();
	    //Haalt alle onderdelen op gebasseert op de mobo.
	    this.fullCheck(matcher.getHardwareByInput(webinput.inputWebserverMatchToMobo()));
	    
	}	
	public ArrayList fullCheck(ArrayList componentsInput) throws SQLException{
		/*
		 * Does the full check and gives back an compatible and cheapest Hardware in the form of an ArrayList.
		 * 
		 */
		FindComponents findComponents = new FindComponents();
	    MatcherMotherboardCompatibility matchMobo = matcher.matchMobo;
	    
	    ArrayList finishedComponents = new ArrayList();
	    ArrayList tempComponents = new ArrayList();
	    
		ArrayList matchedComponents = matcher.determineSelectedComponents(componentsInput);
	    String result = matcher.createQuery(matchedComponents);
	    Motherboard mb = new Motherboard();
	    //If there is a MOBO in the list match everything from motherboard
	    if(result == "MOBO")
	    	mb = findComponents.getMotherboardFromArrayList(matchedComponents);
	    else
	    	mb = matchMobo.matchMotherboard(result);
	    
	    tempComponents = matcher.matchFromMotherboard(mb);
    	//Remove the stuff you got as an input
    	//i.e. This matches and give back CPU,and if CPU already selected. Replace the selected CPU
	    
	    
	    
	    
	    return finishedComponents;	
	}	
}
