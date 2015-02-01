package parsing;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains everything that has to do with filtering Strings.
 * This is needed to be able to match the Property names in the Neo4j Database.
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
public class FilterString {

	/**
	 * This filters the String on DDR type.
	 *
	 * @param ddrTypeFilter String with the DDR type in it.
	 * @return String with the fitered/parsed DDR.
	 */
	public String filterStringOnDdrType(String ddrTypeFilter){
		String pattern = "(ddr\\d)";
		String filteredString = ddrTypeFilter;
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(ddrTypeFilter);

		if (m.find( ))
			filteredString = m.group(0);
		return filteredString;
	}

	/**
	 * This will split on commas.
	 * i.e. The EAN number has 3 numbers in them.
	 * This will extract those 3 numbers.
	 *
	 * @param input String with the input that needs to be split on commas.
	 * @return Array with Strings that are all splitted.
	 */
	public String[] splitByCommas(String input){
		return input.split(",");
	}

	/**
	 * Adds whitespaces to CardInterface.
	 * This is needed to add whitespaces in certain places.
	 * i.e. 1xpci-e3.0x16 --> 1x pci-e 3.0 x16.
	 *
	 * @param arrayCardInterface Array with the CardInterface.
	 * @return ArrayList with the whitespaces added to the CardInterface.
	 */
	public ArrayList filterWhitespaceToCardInterface(String[] arrayCardInterface){

		ArrayList filteredArr = new ArrayList();
		for(int i = 0;i<arrayCardInterface.length;i++){

			String tempStr = arrayCardInterface[i].replace(" ", "");
			String filteredStr = "";
			String pattern = "(\\dx)?(pci-e)(\\d..)(x.)(.)?";
			Pattern regexPattern = Pattern.compile(pattern);
			Matcher regexMatcher = regexPattern.matcher(tempStr);
			if (regexMatcher.find()) {
				if(regexMatcher.group(1) != null){filteredStr += regexMatcher.group(1) ;}
				filteredStr += regexMatcher.group(2)+" "+regexMatcher.group(3)+ " "+ regexMatcher.group(4);
				if(regexMatcher.group(5) != null){filteredStr += regexMatcher.group(5);}
				filteredArr.add(filteredStr);
			}
		}
		return filteredArr;
	}

	/**
	 * Adds whitespaces to the EAN number.
	 *
	 * @param EAN the EAN number that needs whitespaces added.
	 * @return String with the whitespaces added.
	 */
	public String addWhitespaceToEanNumber(String EAN){
		return EAN.replace(",", ", ");
	}
}
