package matcher;
import java.sql.SQLException;
import java.util.ArrayList;

import components.*;
import org.neo4j.graphdb.GraphDatabaseService;

import parsing.FilterString;
import parsing.ParseHardware;

import databasemanager.Neo4jManager;

public class MatcherMotherboardCompatibility {
	private Neo4jManager neo4j;
	private GraphDatabaseService db;
	private PriceComponent priceComponent;
	private ParseHardware parseHw;
	private Thread t;
	private String threadName;
	FilterString filterString = new FilterString();
	
	public MatcherMotherboardCompatibility(){
		this.neo4j = new Neo4jManager();
		this.db = neo4j.openNeo4jDatabase();
		try {
			this.priceComponent = new PriceComponent();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.parseHw = new ParseHardware();
	}
	/**
	   * This method is used to match a GPU Object from the Neo4j Database.
	   * @param motherboardCardInterface String of the Motherboard Card Interface to match a GPU.
	   * @return GPU Returns a GPU Object.
	   */
	public GPU matchGpuBasedOnMobo(String motherboardCardInterface){
		/*USE THIS FOR THE OTHER HARDWARE
		 * [x] Selects the GPU that is compatible with the mobo from Neo4j
		 * [x] Makes objects from those selected nodes
		 * [x] Gets the prices for all the nodes and writes them in the object
		 * [x] Selects the cheapest one and returns that gpu
		 */
		GPU gpu = new GPU();
		String query = "MATCH (n:GRAPHICSCARD) "
				     + "WHERE n.`Card Interface (Video)` =~ '.*(?i)"+motherboardCardInterface+".*' "
					 + "RETURN n;";
		ArrayList matches = this.neo4j.executeQueryNeo4j(this.db, query);
		if(matches.size()>0){
			gpu = (GPU) priceComponent.getPricesByArrayList(parseHw.parseQueryToGPUObject(this.db, matches));
		}else{gpu = null;}
		
		return gpu;
	}
	/**
	   * This method is used to match a Memory Object from the Neo4j Database.
	   * @param motherboardGeheugenType String of the Motherboard Memory Type to match a Memory.
	   * @return Memory Returns a Memory Object.
	   */
	
	public Memory matchRamBasedOnMobo(String motherboardGeheugenType){
		//================================
		Memory ram = new Memory();
		String query = "MATCH (n:MEMORY) "
					 + "WHERE n.Geheugentype =~ '.*(?i)"+motherboardGeheugenType+".*' "
					 + "RETURN n;";
		ArrayList matches = this.neo4j.executeQueryNeo4j(this.db, query);
		if(matches.size()>0){
			//TODO Return een ram met goedkoopste prijs
			//================================
			ram  = (Memory) priceComponent.getPricesByArrayList(parseHw.parseQueryToRAMObject(this.db, matches));
		}else{ram = null;}
		
		return ram;
	}
	/**
	   * This method is used to match a CPU Object from the Neo4j Database. Needs a Motherboard socket for compatibility.
	   * @param motherboardSocket String of the Motherboard Socket to match a CPU.
	   * @return CPU Returns a CPU Object.
	   */
	public CPU matchCpuBasedOnMobo(String motherboardSocket){
		CPU cpu = new CPU(); //TODO Remove the new
		String query = "MATCH (n:PROCESSOR) "
					 + "WHERE n.Socket =~ '.*"+motherboardSocket+".*' "
					 + "RETURN n;";
		ArrayList matches = this.neo4j.executeQueryNeo4j(this.db, query);		
		if(matches.size()>0){
			cpu = (CPU) priceComponent.getPricesByArrayList(parseHw.parseQueryToCPUObject(this.db, matches));

		}else{cpu = null;}		
		
		return cpu;
	}
	/**
	   * This method is used to match a Motherboard Object from the Neo4j Database.
	   * @param matchMoboQuery String of the Query to match a Motherboard.
	   * @return Motherboard Returns a Motherboard Object.
	   */
	public Motherboard matchMotherboard(String matchMoboQuery){
		Motherboard mb = new Motherboard();
		ArrayList matches = this.neo4j.executeQueryNeo4j(this.db, matchMoboQuery);		
		if(matches.size()>0){
			mb = (Motherboard) priceComponent.getPricesByArrayList(parseHw.parseQueryToMotherboardObject(this.db, matches));
		}return mb;
	}
	public ArrayList getPricesSelectedComponents(ArrayList userSelectedComponents){
		ArrayList priceComponentList = new ArrayList();
		for(int i = 0; i<userSelectedComponents.size();i++) {
			ArrayList a = new ArrayList();
			Hardware h = (Hardware) userSelectedComponents.get(i);
			if(h instanceof CPU){
				try {
					h.setEan(filterString.addWhitespaceToEanNumber(h.getEan()));
				}catch(Exception e){}
			}
			a.add(h);
			priceComponentList.add(priceComponent.getPricesByArrayList(a));

		}
		return priceComponentList;
	}


	
}