package matcher;

import java.util.ArrayList;

import parsing.ParseHardware;
import components.CPU;
import components.Hardware;
import components.Motherboard;

public class FindComponents {
	
	ParseHardware parseHw = new ParseHardware();
	
	public Motherboard getMotherboardFromArrayList(ArrayList components){
		Motherboard mb = new Motherboard();
		for(int i = 0; i<components.size();i++){
			if(components.get(i) instanceof Motherboard)
			mb = (Motherboard) components.get(i);
		}
		return mb;
	}
	public ArrayList mergeComponentsArrayList(ArrayList userSelectedComponents, ArrayList matchedComponents){
		/*
		 * When matching always fallback to Motherboard.
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
			Hardware hw = (Hardware) userSelectedComponents.get(a);
			for(int i = 0; i<matchedComponents.size();i++){
				if(parseHw.isASubClass(hw.getClass(), matchedComponents.get(i))){
					System.out.println(i + a);
				}
			}
		}
		return newList;
	}
}
