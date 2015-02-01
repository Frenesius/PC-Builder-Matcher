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

/**
 * This is the Main class to call. The method fullCheck is the main method.
 * This will handle everything.
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
public class PcBuilder {

	private MatcherMain matcher = new MatcherMain();
	private ParseHardware parseHw = new ParseHardware();
	FindComponents findComponents = new FindComponents();
	MatcherMotherboardCompatibility matchMobo = matcher.matchMobo;

	/**
	   * This method is for Debug purposes
	   *
	   */
	public void start() throws SQLException {
		WebInput webinput = new WebInput();
		ArrayList a = this.fullCheck(webinput.inputWebserverCPU());
	}

	/**
	   * This method is used to do a fully matching.
	   * @param componentsInput ArrayList with JSON Strings.
	   * @return Returns a ArrayList with the Matched components.
	   */
	public ArrayList fullCheck(ArrayList componentsInput) {
		Motherboard mb = new Motherboard();
		ArrayList selectedComponents = new ArrayList();
	    ArrayList finishedComponents = new ArrayList();

		ArrayList matchedComponents = matcher.determineSelectedComponents(matcher.getHardwareByInput(componentsInput));	
		selectedComponents = matchedComponents;		//Copies the components the user has selected
	    String result = matcher.createQuery(matchedComponents);
	    
	    //If there is a MOBO in the list match everything from motherboard
	    if(result.equals("MOBO"))
	    	mb = findComponents.getMotherboardFromArrayList(matchedComponents);
	    else
	    	mb = matchMobo.matchMotherboard(result);
	    selectedComponents = MatcherMain.matchMobo.getPricesSelectedComponents(selectedComponents);
	    try {
			matchedComponents = matcher.matchFromMotherboard(mb);
		}catch(Exception e){
			System.out.println("ERROR");
		}
		finishedComponents = findComponents.mergeComponentsArrayList(selectedComponents,
				matchedComponents); //Gets prices and merges the cheapest hardware.
	    return finishedComponents;
	}
}
