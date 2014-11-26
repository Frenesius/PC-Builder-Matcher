package parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterString {
	public String filterStringOnDdrType(String ddrTypeFilter){

		String pattern = "(ddr\\d)";
		String filteredString = ddrTypeFilter;
		
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(ddrTypeFilter);
		
		if (m.find( )) {
			filteredString = m.group(0);
	    }
		
		return filteredString;
	}
	public String[] splitByCommas(String input){
		String[] output = input.split(",");
		return output;
	}
}
