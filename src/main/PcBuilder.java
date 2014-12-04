package main;

import java.sql.SQLException;
import java.util.ArrayList;

import matcher.FindComponents;
import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;
import parsing.ParseHardware;

import components.Motherboard;
import components.WebInput;

public class PcBuilder {
	
	MatcherMain matcher = new MatcherMain();
	ParseHardware parseHw = new ParseHardware();
	//Starts to run the program
	public void start() throws SQLException{
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
		ArrayList selectedComponents = new ArrayList();
		Motherboard mb = new Motherboard();
		FindComponents findComponents = new FindComponents();
	    MatcherMotherboardCompatibility matchMobo = matcher.matchMobo;
	    
	    ArrayList finishedComponents = new ArrayList();
	    ArrayList tempComponents = new ArrayList();
	    
		ArrayList matchedComponents = matcher.determineSelectedComponents(componentsInput);	
		selectedComponents = matchedComponents;												//Copies the components the user has selected
	    String result = matcher.createQuery(matchedComponents);
	    
	    //If there is a MOBO in the list match everything from motherboard
	    if(result == "MOBO")
	    	mb = findComponents.getMotherboardFromArrayList(matchedComponents);
	    else
	    	mb = matchMobo.matchMotherboard(result);
	    
	    tempComponents = matcher.matchFromMotherboard(mb);
    	//Remove the stuff you got as an input
    	//i.e. This matches and give back CPU,and if CPU already selected. Replace the selected CPU
	    finishedComponents = findComponents.mergeComponentsArrayList(selectedComponents, tempComponents); //Not complete
	    String debuga = "a";
	    debuga.toLowerCase();
	    return finishedComponents;	
	}


}
