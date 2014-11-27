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
	public String[] filterWhitespaceToCardInterface(String[] arrayCardInterface){
		for(int i = 0;i<arrayCardInterface.length;i++){
			String tempStr = arrayCardInterface[i];
			String filteredStr = "";
			String pattern = "(\\dx)?(pci-e)(\\d..)(x.)(.)?";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(tempStr);
			if (m.find( )) {
				if(m.group(1) != null){filteredStr += m.group(1) + " ";}
				filteredStr += m.group(2)+" "+m.group(3)+ " "+ m.group(4);
				if(m.group(5) != null){filteredStr += m.group(5);}
				arrayCardInterface[i] = filteredStr;
			}
		}
		return arrayCardInterface;
	}
}
