package matcher;

import java.util.ArrayList;

import components.*;
import parsing.ParseHardware;

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
	public ArrayList mergeComponentsArrayList(ArrayList userSelectedComponents, ArrayList matchedComponents) {
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
		for(int i = 0; i<userSelectedComponents.size();i++) {
			Hardware h = (Hardware) userSelectedComponents.get(i);
			int position = checkInstance(h);
			if(position != -1)
				newList.add(position, h);
		}
		for (int i = 0; i < matchedComponents.size(); i++) {
			Hardware h = (Hardware) matchedComponents.get(i);
			if (h != null) {
				try {
					newList.get(i);
				} catch (Exception e) {
					newList.add(i, h);
				}
			}
		}
		return newList;
	}
	public int checkInstance(Hardware h) {
		if (h instanceof CPU)
			return 0;
		else if (h instanceof GPU)
			return 1;
		else if (h instanceof Memory)
			return 2;
		else if (h instanceof HDD)
			return 3;
		else if (h instanceof SSD)
			return 4;
		else if (h instanceof PSU)
			return 5;
		else if (h instanceof CASE)
			return 6;
		else if (h instanceof Motherboard)
			return 7;
		else if (h instanceof OpticalDrive)
			return 8;
		else if (h instanceof Soundcard)
			return 9;
		return -1;
	}
}
