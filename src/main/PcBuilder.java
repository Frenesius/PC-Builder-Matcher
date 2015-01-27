package main;

import java.sql.SQLException;
import java.util.ArrayList;

import components.Hardware;
import matcher.FindComponents;
import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;
import matcher.PriceComponent;
import parsing.ParseHardware;

import components.Motherboard;
import components.WebInput;

public class PcBuilder {

	private MatcherMain matcher = new MatcherMain();
	private ParseHardware parseHw = new ParseHardware();
	FindComponents findComponents = new FindComponents();
	MatcherMotherboardCompatibility matchMobo = matcher.matchMobo;

	//Starts to run the program
	public void start() throws SQLException {
		//Connections and objects
		WebInput webinput = new WebInput();
		//Haalt alle onderdelen op gebasseert op de mobo.
		ArrayList a = this.fullCheck(webinput.inputWebserverCPU());
		String b = "";
	}
	/**
	   * This method is used to do a fully matching.
	   * @param componentsInput ArrayList with JSON Strings.
	   * @return ArrayList Returns the result of the checking.
	   */
	public ArrayList fullCheck(ArrayList componentsInput) throws SQLException{
		Motherboard mb = new Motherboard();
		ArrayList selectedComponents = new ArrayList();
	    ArrayList finishedComponents = new ArrayList();

		ArrayList matchedComponents = matcher.determineSelectedComponents(matcher.getHardwareByInput(componentsInput));	
		selectedComponents = matchedComponents;								//Copies the components the user has selected
	    String result = matcher.createQuery(matchedComponents);
	    
	    //If there is a MOBO in the list match everything from motherboard
	    if(result.equals("MOBO"))
	    	mb = findComponents.getMotherboardFromArrayList(matchedComponents);
	    else
	    	mb = matchMobo.matchMotherboard(result);
	    selectedComponents = MatcherMain.matchMobo.getPricesSelectedComponents(selectedComponents);
	    matchedComponents = matcher.matchFromMotherboard(mb);//MOGELIJKE BUG
		finishedComponents = findComponents.mergeComponentsArrayList(selectedComponents,
				matchedComponents); //Gets prices and merges the cheapest hardware.

	    return finishedComponents;
	}


}
