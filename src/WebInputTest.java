import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import matcher.FindComponents;
import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;

import org.junit.Test;

import components.Motherboard;
import components.WebInput;


public class WebInputTest {
	private WebInput webinput = new WebInput();
	private MatcherMain matcher = new MatcherMain();
	private FindComponents findComponents = new FindComponents();
	private MatcherMotherboardCompatibility matchMobo = matcher.matchMobo;
	@Test
	public void webInputTest() {
		ArrayList result = webinput.inputWebserverMatchToMobo();
		
		assertEquals(10,result.size());
	}
	@Test
	public void componentsInputTest() {
		ArrayList result = matcher.getHardwareByInput(webinput.inputWebserverMatchToMobo());
		
		assertEquals(10,result.size());		//Reproduces the input from the front end. There need to be 10 objects in the ArrayList.
	}
	@Test
	public void selectedComponentsInputTest() {
		ArrayList componentsInput = matcher.getHardwareByInput(webinput.inputWebserverMatchToMobo());
		ArrayList result = matcher.determineSelectedComponents(componentsInput);

		assertEquals(true,result.size()>1);		//If the result is less than 1, it means that the use has not selected any components from the front end.
	}
	@Test
	public void createQueryTest() {
		ArrayList matchedComponents = matcher.determineSelectedComponents(matcher.getHardwareByInput(webinput.inputWebserverMatchToMobo()));
		String result = matcher.createQuery(matchedComponents);

		assertEquals(true, result.length()>20);		//Length of the Query - "MATCH (n)  RETURN n;"
	}
	@Test
	public void matchMotherboardTest() {
		ArrayList matchedComponents = matcher.determineSelectedComponents(matcher.getHardwareByInput(webinput.inputWebserverMatchToMobo()));
		String query = matcher.createQuery(matchedComponents);
		Motherboard mb = new Motherboard();
		if(query.equals("MOBO")){
	    	mb = findComponents.getMotherboardFromArrayList(matchedComponents);
			assertEquals(false, mb.getIsMatched());		//If MOBO is returned, it means that the motherboard is already in the ArrayList. 
														//The motherboard can be retrieved from the arraylist and so its not matched. 
														//Therefore the isMatched = false.
		}else{
	    	mb = matchMobo.matchMotherboard(query);
			assertEquals(true, mb.getIsMatched());		//If the mobo goes trought the matchMotherboard() method, the isMatched will be true.
		}
		
		assertEquals(false, mb.getIsEmpty());			//The object motherboard can not be empty, so the check will be isEmpty in the form of an boolean.
	}
	@Test
	public void matchHardwareFromMotherboardTest() throws SQLException {
		ArrayList matchedComponents = matcher.determineSelectedComponents(matcher.getHardwareByInput(webinput.inputWebserverMatchToMobo()));
		String query = matcher.createQuery(matchedComponents);
		Motherboard mb = new Motherboard();
		if(query.equals("MOBO"))
	    	mb = findComponents.getMotherboardFromArrayList(matchedComponents);
	    else
	    	mb = matchMobo.matchMotherboard(query);
		ArrayList tempComponents = matcher.matchFromMotherboard(mb);
		
		assertEquals(true, tempComponents != null);			//If the query is executed wrong, the arraylist is empty
	}		
}