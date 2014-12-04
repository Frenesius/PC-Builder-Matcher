package matcher;

import java.util.ArrayList;

import components.CASE;
import components.CPU;
import components.GPU;
import components.HDD;
import components.Hardware;
import components.Memory;
import components.Motherboard;
import components.OpticalDrive;
import components.PSU;
import components.SSD;
import components.Soundcard;

public class FindComponents {
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
		
		for(int i = 0; i<matchedComponents.size();i++){
			Hardware hw = new Hardware();
			try{
				hw = (Hardware) matchedComponents.get(i);
			}catch(Exception e){}
			
			
		}
		return newList;
		
	}
}
