package matcher;

import java.util.ArrayList;

import com.google.gson.Gson;
import components.CASE;
import components.CPU;
import components.GPU;
import components.HDD;
import components.Memory;
import components.Motherboard;
import components.OpticalDrive;
import components.PSU;
import components.SSD;
import components.Soundcard;

public class MatcherMain {
	public void matchMotherboard(ArrayList components){
	
	}

	public ArrayList getNodesByInput(ArrayList components){
		Gson gson = new Gson();
		ArrayList componentsObjects = new ArrayList();
		for(int i = 0; i<components.size(); i++){
			String part = (String) components.get(i);
			if(part != null)
				part = part.replaceAll("\\s","").toLowerCase();			
			switch(i){
			case 0:	//CPU
				CPU cpu = gson.fromJson(part, CPU.class);
				cpu.setHardwaresort("cpu");
				componentsObjects.add(cpu);
				break;
			case 1:	//GPU
				GPU gpu = gson.fromJson(part, GPU.class);
				gpu.setHardwaresort("gpu");
				componentsObjects.add(gpu);
				break;
			case 2:	//MEMORY
				Memory ram = gson.fromJson(part, Memory.class);
				ram.setHardwaresort("memory");
				componentsObjects.add(ram);
				break;
			case 3:	//HDD
				HDD hdd = gson.fromJson(part, HDD.class);
				hdd.setHardwaresort("hdd");
				componentsObjects.add(hdd);
				break;
			case 4:	//SSD
				SSD ssd = gson.fromJson(part, SSD.class);
				ssd.setHardwaresort("ssd");
				componentsObjects.add(ssd);
				break;
			case 5:	//PSU
				PSU psu = gson.fromJson(part, PSU.class);
				psu.setHardwaresort("psu");
				componentsObjects.add(psu);
				break;
			case 6:	//CASE
				CASE casepc = gson.fromJson(part, CASE.class);
				casepc.setHardwaresort("case");
				componentsObjects.add(casepc);
				break;
			case 7:	//Motherboard
				Motherboard mobo = gson.fromJson(part, Motherboard.class);
				mobo.setHardwaresort("motherboard");
				componentsObjects.add(mobo);
				break;
			case 8:	//OpticalDrive
				OpticalDrive opt = gson.fromJson(part, OpticalDrive.class);
				opt.setHardwaresort("opticaldrive");
				componentsObjects.add(opt);
				break;
			case 9:	//Soundcard
				Soundcard soundcard = gson.fromJson(part, Soundcard.class);
				soundcard.setHardwaresort("soundcard");
				componentsObjects.add(soundcard);
				break;
			}

		}
		return componentsObjects;

	}


}

