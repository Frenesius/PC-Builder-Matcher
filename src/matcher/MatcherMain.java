package matcher;

import java.util.ArrayList;

import parsing.FilterString;

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
	/*
	 * When matching always fallback to Motherboard.
	 * 
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
	public void matchFromMotherboard(ArrayList components){
		CPU cpu = new CPU();
		Motherboard mb = new Motherboard();
		GPU gpu = new GPU();
		Memory ram = new Memory();
		mb = (Motherboard) components.get(7);
		
		FilterString filter = new FilterString();
		MatcherMotherboardCompatibility matchMobo = new MatcherMotherboardCompatibility();
		
		String EAN = filter.splitByCommas(mb.getEan())[0];									//Ean to select the thing
		String motherboardSocket = mb.getSocket();					//Staat goed
		String motherboardGeheugenType = filter.filterStringOnDdrType(mb.getGeheugentype());		//Gefilterd
		String[] motherboardCardInterface = filter.splitByCommas(mb.getCardinterface()); //Array with card interfaces
		
		
		matchMobo.matchRamBasedOnMobo(motherboardGeheugenType, EAN);
	}

	public ArrayList getNodesByInput(ArrayList components){
		
		Gson gson = new Gson();
		ArrayList componentsObjects = new ArrayList();
		for(int i = 0; i<components.size(); i++){
			String part = (String) components.get(i);
			if(part != null)
				part = part.toLowerCase();
				try{
					part = part.replaceAll("\\s","").toLowerCase();			
					part = part.replaceAll("\\(moederbord\\)","").toLowerCase();
				}catch(Exception e ){}
				
			switch(i){
			case 0:	//CPU
				CPU cpu = new CPU();
				if(part != null){
					cpu = gson.fromJson(part, CPU.class);					
					cpu.setIsEmpty(false);
				}else{cpu.setIsEmpty(true);}
				cpu.setHardwaresort("cpu");
				componentsObjects.add(cpu);
				break;
			case 1:	//GPU
				GPU gpu = new GPU();
				if(part != null){
					gpu = gson.fromJson(part, GPU.class);					
					gpu.setIsEmpty(false);
				}else{gpu.setIsEmpty(true);}
				gpu.setHardwaresort("gpu");
				componentsObjects.add(gpu);
				break;
			case 2:	//MEMORY
				Memory ram = new Memory();
				if(part != null){
					ram = gson.fromJson(part, Memory.class);					
					ram.setIsEmpty(false);
				}else{ram.setIsEmpty(true);}
				ram.setHardwaresort("memory");
				componentsObjects.add(ram);
				break;
			case 3:	//HDD
				HDD hdd = new HDD();
				if(part != null){
					hdd = gson.fromJson(part, HDD.class);					
					hdd.setIsEmpty(false);
				}else{hdd.setIsEmpty(true);}
				hdd.setHardwaresort("hdd");
				componentsObjects.add(hdd);
				break;
			case 4:	//SSD
				SSD ssd = new SSD();
				if(part != null){
					ssd = gson.fromJson(part, SSD.class);					
					ssd.setIsEmpty(false);
				}else{ssd.setIsEmpty(true);}
				ssd.setHardwaresort("ssd");
				componentsObjects.add(ssd);
				break;
			case 5:	//PSU
				PSU psu = new PSU();
				if(part != null){
					psu = gson.fromJson(part, PSU.class);
					psu.setIsEmpty(false);
				}else{psu.setIsEmpty(true);}				
				psu.setHardwaresort("psu");
				componentsObjects.add(psu);
				break;
			case 6:	//CASE
				CASE casepc = new CASE();
				if(part != null){
					casepc = gson.fromJson(part, CASE.class);
					casepc.setIsEmpty(false);
				}else{casepc.setIsEmpty(true);}
				casepc.setHardwaresort("case");
				componentsObjects.add(casepc);
				break;
			case 7:	//Motherboard
				Motherboard mobo = new Motherboard();
				if(part != null){
					mobo = gson.fromJson(part, Motherboard.class);
					mobo.setIsEmpty(false);
				}else{mobo.setIsEmpty(true);}
				mobo.setHardwaresort("motherboard");
				componentsObjects.add(mobo);
				break;
			case 8:	//OpticalDrive
				OpticalDrive opt = new OpticalDrive();
				if(part != null){
					opt = gson.fromJson(part, OpticalDrive.class);
					opt.setIsEmpty(false);
				}else{opt.setIsEmpty(true);}
				opt.setHardwaresort("opticaldrive");
				componentsObjects.add(opt);
				break;
			case 9:	//Soundcard
				Soundcard soundcard = new Soundcard();
				if(part != null){
					soundcard = gson.fromJson(part, Soundcard.class);
					soundcard.setIsEmpty(false);
				}else{soundcard.setIsEmpty(true);}
				soundcard.setHardwaresort("soundcard");
				componentsObjects.add(soundcard);
				break;
			}
		}
		return componentsObjects;
	}
	
	public void niks(){}


}

