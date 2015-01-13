package matcher;

import java.util.ArrayList;

import parsing.ParseHardware;
import components.Hardware;
import components.Motherboard;

public class FindComponents {
	
	ParseHardware parseHw = new ParseHardware();
	   /**
	   * This method is used to get a Motherboard from an ArrayList.
	   * @param components ArrayList with the components.
	   * @return Motherboard Returns an motherboard from the ArrayList.
	   */
	public Motherboard getMotherboardFromArrayList(ArrayList components){
		Motherboard mb = new Motherboard();
		for(int i = 0; i<components.size();i++){
			if(components.get(i) instanceof Motherboard)
			mb = (Motherboard) components.get(i);
		}
		return mb;
	}
	/**
	   * This method is used to merge the two ArrayLists. One with the selected components and one with matched.
	   * @param userSelectedComponents ArrayList with the user selected components.
	   * @param matchedComponents ArrayList with the matched components.
	   * @return ArrayList Returns a merged ArrayList.
	   */
	public ArrayList mergeComponentsArrayList(ArrayList userSelectedComponents, ArrayList matchedComponents){ 
		/*
		 * userSelectedComponents goes before matchedComponents
		 * Array with components
		 * arr[0] = CPU	
		 * arr[1] = GPU
		 * arr[2] = RAM
		 * arr[3] = HDD
		 * arr[4] = SSD
		 * arr[5] = PSU
		 * arr[6] = CASE
		 * arr[7] = Motherboard
		 * arr[8] = Opticaldrive
		 * arr[9] = Soundcard	
		 */
		ArrayList newList = new ArrayList();
		for(int a = 0; a<userSelectedComponents.size(); a++){
			Hardware userSelectedHw = (Hardware) userSelectedComponents.get(a);
			for(int i = 0; i<matchedComponents.size();i++){
				Hardware matchedHw;
				matchedHw = (Hardware) matchedComponents.get(i);
				try{
				if(ParseHardware.isASubClass(userSelectedHw.getClass(), matchedHw))
					System.out.print(i);
				}catch(Exception e){System.out.println("null found");}
			}
		}
		return newList;
	}
}
