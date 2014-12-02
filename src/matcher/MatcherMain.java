package matcher;

import java.sql.SQLException;
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
	FilterString filter = new FilterString();
	
	public ArrayList matchFromMotherboard(ArrayList componentsList) throws SQLException{
		/*
		 * Matches CPU,GPU and RAM when you have Motherboard as input.
		 * !!Motherboard needs to be on row 7.
		 */
		CPU cpu = new CPU();
		GPU gpu = new GPU();
		Memory ram = new Memory();
		HDD hdd = new HDD();
		SSD ssd = new SSD();
		PSU psu = new PSU();
		CASE computerCase = new CASE();
		Motherboard motherboard = new Motherboard();
		OpticalDrive opt = new OpticalDrive();
		Soundcard soundcard = new Soundcard();
		
		
		motherboard = (Motherboard) componentsList.get(7);
		
		MatcherMotherboardCompatibility matchMobo = new MatcherMotherboardCompatibility();
		
		String EAN = this.filter.splitByCommas(motherboard.getEan())[0];														//Ean to select the thing
		String motherboardSocket = motherboard.getSocket();																//Staat goed
		String motherboardGeheugenType = filter.filterStringOnDdrType(motherboard.getGeheugentype());					//Gefilterd
		String[] temparr = this.filter.filterWhitespaceToCardInterface(this.filter.splitByCommas(motherboard.getCardinterface()));
		String motherboardCardInterface = temparr[temparr.length-1];
		
		ram = matchMobo.matchRamBasedOnMobo(motherboardGeheugenType);
		cpu = matchMobo.matchCpuBasedOnMobo(motherboardSocket);
		gpu = matchMobo.matchGpuBasedOnMobo(motherboardCardInterface);
		
		//Fills the List up
		componentsList.clear();
		componentsList.add(0, cpu);
		componentsList.add(1, gpu);
		componentsList.add(2, ram);
		componentsList.add(3, hdd);
		componentsList.add(4, ssd);
		componentsList.add(5, psu);
		componentsList.add(6, computerCase);
		componentsList.add(7, motherboard);
		componentsList.add(8, opt);
		componentsList.add(9, soundcard);
		
		return componentsList;
	}
//===========================================================
	public void matchMoboFromCPU(ArrayList componentsList){
		CPU cpu = (CPU) componentsList.get(0);
		
		String cpuSocket = cpu.getSocket();
	}
	public void matchMoboFromGPU(ArrayList componentsList){
		GPU gpu = (GPU) componentsList.get(1);
		Memory ram = (Memory) componentsList.get(2);
		
		String[] temparr = this.filter.filterWhitespaceToCardInterface(this.filter.splitByCommas(gpu.getCardinterfacevideo()));
		
		String gpuSlot = temparr[temparr.length-1];
		String ramType = filter.filterStringOnDdrType(ram.getGeheugentype());
	}
	public void matchMoboFromRAM(ArrayList componentsList){
		Memory ram = (Memory) componentsList.get(2);
	
		String ramType = filter.filterStringOnDdrType(ram.getGeheugentype());
	}

	public void matchMoboFromCPUandGPU(ArrayList componentsList){
		CPU cpu = (CPU) componentsList.get(0);
		GPU gpu = (GPU) componentsList.get(1);
		
		String[] temparr = this.filter.filterWhitespaceToCardInterface(this.filter.splitByCommas(gpu.getCardinterfacevideo()));
		
		String gpuSlot = temparr[temparr.length-1];
		String cpuSocket = cpu.getSocket();
		
	}
	public void matchMoboFromCPUandRAM(ArrayList componentsList){
		CPU cpu = (CPU) componentsList.get(0);
		Memory ram = (Memory) componentsList.get(2);
		
		String cpuSocket = cpu.getSocket();
		String ramType = filter.filterStringOnDdrType(ram.getGeheugentype());
	}
	public void matchMoboFromCPUandGPUandRAM(ArrayList componentsList){
		CPU cpu = (CPU) componentsList.get(0);
		GPU gpu = (GPU) componentsList.get(1);
		Memory ram = (Memory) componentsList.get(2);
	
		String[] temparr = this.filter.filterWhitespaceToCardInterface(this.filter.splitByCommas(gpu.getCardinterfacevideo()));
		
		String cpuSocket = cpu.getSocket();
		String gpuSlot = temparr[temparr.length-1];
		String ramType = filter.filterStringOnDdrType(ram.getGeheugentype());
	}

	public void matchMoboFromGPUandRAM(ArrayList componentsList){
		GPU gpu = (GPU) componentsList.get(1);
		Memory ram = (Memory) componentsList.get(2);
		
		String[] temparr = this.filter.filterWhitespaceToCardInterface(this.filter.splitByCommas(gpu.getCardinterfacevideo()));
		
		String gpuSlot = temparr[temparr.length-1];
		String ramType = filter.filterStringOnDdrType(ram.getGeheugentype());
	}
//===========================================================
	
	public ArrayList getHardwareByInput(ArrayList componentsList){
		/* 
		 * Gets the JSON file from the web server input.
		 * Parses the JSON to objects.
		 */
		Gson gson = new Gson();
		ArrayList componentsObjects = new ArrayList();
		for(int i = 0; i<componentsList.size(); i++){
			String componentsJSON = (String) componentsList.get(i);
			if(componentsJSON != null)
				componentsJSON = componentsJSON.toLowerCase();
				try{
					componentsJSON = componentsJSON.replaceAll("\\s","").toLowerCase();			
					componentsJSON = componentsJSON.replaceAll("\\(moederbord\\)","").toLowerCase();
				}catch(Exception e ){}
				
			switch(i){
			case 0:	//CPU
				CPU cpu = new CPU();
				if(componentsJSON != null){
					cpu = gson.fromJson(componentsJSON, CPU.class);					
					cpu.setIsEmpty(false);
				}else{cpu.setIsEmpty(true);}
				cpu.setHardwaresort("cpu");
				componentsObjects.add(cpu);
				break;
			case 1:	//GPU
				GPU gpu = new GPU();
				if(componentsJSON != null){
					gpu = gson.fromJson(componentsJSON, GPU.class);					
					gpu.setIsEmpty(false);
				}else{gpu.setIsEmpty(true);}
				gpu.setHardwaresort("gpu");
				componentsObjects.add(gpu);
				break;
			case 2:	//MEMORY
				Memory ram = new Memory();
				if(componentsJSON != null){
					ram = gson.fromJson(componentsJSON, Memory.class);					
					ram.setIsEmpty(false);
				}else{ram.setIsEmpty(true);}
				ram.setHardwaresort("memory");
				componentsObjects.add(ram);
				break;
			case 3:	//HDD
				HDD hdd = new HDD();
				if(componentsJSON != null){
					hdd = gson.fromJson(componentsJSON, HDD.class);					
					hdd.setIsEmpty(false);
				}else{hdd.setIsEmpty(true);}
				hdd.setHardwaresort("hdd");
				componentsObjects.add(hdd);
				break;
			case 4:	//SSD
				SSD ssd = new SSD();
				if(componentsJSON != null){
					ssd = gson.fromJson(componentsJSON, SSD.class);					
					ssd.setIsEmpty(false);
				}else{ssd.setIsEmpty(true);}
				ssd.setHardwaresort("ssd");
				componentsObjects.add(ssd);
				break;
			case 5:	//PSU
				PSU psu = new PSU();
				if(componentsJSON != null){
					psu = gson.fromJson(componentsJSON, PSU.class);
					psu.setIsEmpty(false);
				}else{psu.setIsEmpty(true);}				
				psu.setHardwaresort("psu");
				componentsObjects.add(psu);
				break;
			case 6:	//CASE
				CASE casepc = new CASE();
				if(componentsJSON != null){
					casepc = gson.fromJson(componentsJSON, CASE.class);
					casepc.setIsEmpty(false);
				}else{casepc.setIsEmpty(true);}
				casepc.setHardwaresort("case");
				componentsObjects.add(casepc);
				break;
			case 7:	//Motherboard
				Motherboard motherboard = new Motherboard();
				if(componentsJSON != null){
					motherboard = gson.fromJson(componentsJSON, Motherboard.class);
					motherboard.setIsEmpty(false);
				}else{motherboard.setIsEmpty(true);}
				motherboard.setHardwaresort("motherboard");
				componentsObjects.add(motherboard);
				break;
			case 8:	//OpticalDrive
				OpticalDrive opt = new OpticalDrive();
				if(componentsJSON != null){
					opt = gson.fromJson(componentsJSON, OpticalDrive.class);
					opt.setIsEmpty(false);
				}else{opt.setIsEmpty(true);}
				opt.setHardwaresort("opticaldrive");
				componentsObjects.add(opt);
				break;
			case 9:	//Soundcard
				Soundcard soundcard = new Soundcard();
				if(componentsJSON != null){
					soundcard = gson.fromJson(componentsJSON, Soundcard.class);
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

