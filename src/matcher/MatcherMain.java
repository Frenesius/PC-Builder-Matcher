package matcher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import parsing.FilterString;
import threads.CpuThread;
import threads.GpuThread;
import threads.RamThread;

import com.google.gson.Gson;

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

public class MatcherMain{
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
	private FilterString filter = new FilterString();
	public static MatcherMotherboardCompatibility matchMobo = new MatcherMotherboardCompatibility();
	/**
	   * This method is used to match components when we have a ArrayList with a Motherboard.
	   * @param componentsList ArrayList with the components (Motherboard needs to be in it).
	   * @return ArrayList Returns a matched components ArrayList.
	   */
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
		
		String EAN = this.filter.splitByCommas(motherboard.getEan())[0];														//Ean to select the thing
		String motherboardSocket = motherboard.getSocket();																//Staat goed
		String motherboardGeheugenType = filter.filterStringOnDdrType(motherboard.getGeheugentype());					//Gefilterd
		String[] temparr = this.filter.filterWhitespaceToCardInterface(this.filter.splitByCommas(motherboard.getCardinterface()));
		String motherboardCardInterface = temparr[temparr.length-1];
		
		//Multithread 
		//Thread t = new Thread(new Runnable() {public void run(){}});
		//t.start();

//======//===============================
		ram = matchMobo.matchRamBasedOnMobo(motherboardGeheugenType);
		cpu = matchMobo.matchCpuBasedOnMobo(motherboardSocket);
		gpu = matchMobo.matchGpuBasedOnMobo(motherboardCardInterface);

//======//===============================
		
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
	/**
	   * This method is used to match components when we have a Motherboard.
	   * @param motherboardSocket Socket of the Motherboard. Needed to match a CPU.
	   * @param motherboardCardInterface Card interface of the Motherboard. Needed to match a GPU.
	   * @param motherboardGeheugenType Memory type (DDR type). Needed to match a RAM.
	   * @return ArrayList Returns a matched components ArrayList.
	   */
	private ArrayList getHardware(String motherboardSocket, String motherboardCardInterface, String motherboardGeheugenType){
		ArrayList hardware = new ArrayList();
		CPU cpu = new CPU();
		GPU gpu = new GPU();
		Memory ram = new Memory();

		CpuThread cpuThread = new CpuThread(motherboardSocket);
		RamThread ramThread = new RamThread(motherboardGeheugenType);
		GpuThread gpuThread = new GpuThread(motherboardCardInterface);

		Thread t1 = new Thread(cpuThread);
		Thread t2 = new Thread(ramThread);
		Thread t3 = new Thread(gpuThread);

		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t3.join();
			t2.join();
		}catch(Exception e){

		}
		hardware.add(0, (Memory) ramThread.getHardware());		//1
		hardware.add(1, (CPU) cpuThread.getHardware());			//2
		hardware.add(2, (GPU) gpuThread.getHardware());			//3

		return hardware;
	}
	public ArrayList matchFromMotherboard(Motherboard motherboard) throws SQLException{
		/*
		 * Matches CPU,GPU and RAM when you have Motherboard as input.
		 * !!Motherboard needs to be on row 7.
		 */
		ArrayList componentsList = new ArrayList();
		CPU cpu = new CPU();
		GPU gpu = new GPU();
		Memory ram = new Memory();
		HDD hdd = new HDD();
		SSD ssd = new SSD();
		PSU psu = new PSU();
		CASE computerCase = new CASE();
		OpticalDrive opt = new OpticalDrive();
		Soundcard soundcard = new Soundcard();
		
		String EAN = this.filter.splitByCommas(motherboard.getEan())[0];								//Ean to select the thing
		String motherboardSocket = motherboard.getSocket();												//Staat goed
		String motherboardGeheugenType = filter.filterStringOnDdrType(motherboard.getGeheugentype());					//Gefilterd
		String[] temparr = this.filter.filterWhitespaceToCardInterface(this.filter.splitByCommas(motherboard.getCardinterface()));
		String motherboardCardInterface = temparr[temparr.length-1];
		
		//Multithread
		ArrayList result = this.getHardware(motherboardSocket, motherboardCardInterface, motherboardGeheugenType);
		ram = (Memory) result.get(0);
		cpu = (CPU) result.get(1);
		gpu = (GPU) result.get(2);

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
	
//============================================MATCHER TO MOBO	
	/**
	   * Determines if a component is selected or not.
	   * @param components ArrayList with components.
	   * @return ArrayList Returns a ArrayList with components that are not empty.
	   */
	public ArrayList determineSelectedComponents(ArrayList components){
		ArrayList selectedComponents = new ArrayList();
		for(int i = 0; i<components.size();i++){
			Hardware h = (Hardware) components.get(i);
			if(!h.getIsEmpty())
				selectedComponents.add(h);
		}
		return selectedComponents;
	}
	/**
	   * Creates a query to get the Motherboard matching the compatible Hardware.
	   * @param matchedComponents ArrayList with the matched components.
	   * @return String Query to get the Motherboard from Neo4jDatabase.
	   */
	public String createQuery(ArrayList matchedComponents){
		String matchMoboQuery = "";
		String matchCypher = "MATCH (n:MOTHERBOARD) ";
		matchMoboQuery += matchCypher;
		
		for(int i = 0; i<matchedComponents.size(); i++){
			String tempQuery = "";
			Hardware componentHardware = (Hardware) matchedComponents.get(i);
			if(i == 0) tempQuery+= "WHERE ";
			else tempQuery+= "AND ";
			
			if(componentHardware instanceof Motherboard)
				return "MOBO";
			if(componentHardware instanceof CPU){
				String cpuSocket = ((CPU) componentHardware).getSocket();
				tempQuery += "n.Socket =~ '.*"+cpuSocket+".*' ";
			}
			if(componentHardware instanceof GPU){
				String cardInterface = ((GPU) componentHardware).getCardinterfacevideo();
				String[] tempArr = filter.filterWhitespaceToCardInterface(filter.splitByCommas(cardInterface));
				cardInterface = tempArr[tempArr.length-1];
				tempQuery += "n.`Card Interface (moederbord)` =~ '.*(?i)"+cardInterface+".*' ";
			}
			if(componentHardware instanceof Memory){ //Parsing
				String ddrType = filter.filterStringOnDdrType(((Memory) componentHardware).getGeheugentype());
				tempQuery += "n.`Geheugentype (moederbord)` =~ '.*(?i)"+ddrType+".*'";
			}
			matchMoboQuery+= tempQuery;
		}
		String returnCypher = "RETURN n;";
		matchMoboQuery += returnCypher;
		return matchMoboQuery;	
	}	
	
//===========================================================
	/**
	   * This method parses JSON strings to Objects.
	   * @param componentsList ArrayList with the components.
	   * @return ArrayList Returns a Hardware Object ArrayList.
	   */
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
					componentsJSON = componentsJSON.replaceAll("\\(","").toLowerCase();
					componentsJSON = componentsJSON.replaceAll("\\)","").toLowerCase();
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

