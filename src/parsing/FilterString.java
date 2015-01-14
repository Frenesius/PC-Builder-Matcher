package parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterString {
	public String filterStringOnDdrType(String ddrTypeFilter){
		/*
		 * Filters the string on DDR type.
		 * Needed to put the ddr type in a certain way.
		 */
		String pattern = "(ddr\\d)";
		String filteredString = ddrTypeFilter;
		
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(ddrTypeFilter);
		
		if (m.find( ))
			filteredString = m.group(0);
	    
		return filteredString;
	}
	public String[] splitByCommas(String input){
		/*
		 * Splits on commas.
		 * i.e. The EAN number has 3 numbers in them
		 * This will extract those 3 numbers.
		 */
		return input.split(",");
	}
	public String[] filterWhitespaceToCardInterface(String[] arrayCardInterface){
		/*
		 * Adds whitespaces to CardInterface.
		 * This is needed to add whitespaces in certain places.
		 * i.e. 1xpci-e3.0x16 --> 1x pci-e 3.0 x16
		 */
		for(int i = 0;i<arrayCardInterface.length;i++){
			String tempStr = arrayCardInterface[i];
			String filteredStr = "";
			String pattern = "(\\dx)?(pci-e)(\\d..)(x.)(.)?";
			Pattern regexPattern = Pattern.compile(pattern);
			Matcher regexMatcher = regexPattern.matcher(tempStr);
			if (regexMatcher.find()) {
				if(regexMatcher.group(1) != null){filteredStr += regexMatcher.group(1) + " ";}
				filteredStr += regexMatcher.group(2)+" "+regexMatcher.group(3)+ " "+ regexMatcher.group(4);
				if(regexMatcher.group(5) != null){filteredStr += regexMatcher.group(5);}
				arrayCardInterface[i] = filteredStr;
			}
		}
		return arrayCardInterface;
	}
	public String addWhitespaceToEanNumber(String EAN) throws Exception{
		return EAN.replace(",", ", ");
	}
}
