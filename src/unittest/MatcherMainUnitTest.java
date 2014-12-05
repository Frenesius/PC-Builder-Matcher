package unittest;

import java.util.ArrayList;

import org.junit.Test;

import components.WebInput;

import matcher.MatcherMain;



public class MatcherMainUnitTest  {
	@Test
	public void multiplicationOfZeroIntegersShouldReturnZero() {

	   // MyClass is tested
		WebInput webinput = new WebInput();
	   MatcherMain matcherMain = new MatcherMain();
	   ArrayList componentsInput = matcherMain.getHardwareByInput(webinput.inputWebserverMatchToMobo());		  
	   ArrayList matchedComponents = matcherMain.determineSelectedComponents(componentsInput);	
	   // Tests
	   
	 } 
}
