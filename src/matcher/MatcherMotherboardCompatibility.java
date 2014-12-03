package matcher;
import java.sql.SQLException;
import java.util.ArrayList;

import org.neo4j.graphdb.GraphDatabaseService;

import parsing.ParseHardware;

import components.CPU;
import components.GPU;
import components.Memory;
import components.Motherboard;

import databasemanager.Neo4jManager;

public class MatcherMotherboardCompatibility {
	Neo4jManager neo4j;
	GraphDatabaseService db;
	PriceComponent priceComponent;
	ParseHardware parseHw;
	
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
			matches = priceComponent.getPricesByComponent(parseHw.parseQueryToGPUObject(this.db, matches));
			gpu = (GPU) priceComponent.comparePricesFromComponentArray(matches);
		}else{gpu = null;}
		
		return gpu;
	}
	
	public Memory matchRamBasedOnMobo(String motherboardGeheugenType){
		Memory ram = new Memory();
		String query = "MATCH (n:MEMORY) "
					 + "WHERE n.Geheugentype =~ '.*(?i)"+motherboardGeheugenType+".*' "
					 + "RETURN n;";
		ArrayList matches = this.neo4j.executeQueryNeo4j(this.db, query);
		if(matches.size()>0){
			matches = priceComponent.getPricesByComponent(parseHw.parseQueryToRAMObject(this.db, matches));
			ram = (Memory) priceComponent.comparePricesFromComponentArray(matches);
		}else{ram = null;}
		
		return ram;
	}
	public CPU matchCpuBasedOnMobo(String motherboardSocket){
		CPU cpu = new CPU(); //TODO Remove the new
		String query = "MATCH (n:PROCESSOR) "
					 + "WHERE n.Socket =~ '.*"+motherboardSocket+".*' "
					 + "RETURN n;";
		ArrayList matches = this.neo4j.executeQueryNeo4j(this.db, query);		
		if(matches.size()>0){
			matches = priceComponent.getPricesByComponent(parseHw.parseQueryToCPUObject(this.db, matches));
			cpu = (CPU) priceComponent.comparePricesFromComponentArray(matches);
		}else{cpu = null;}		
		
		return cpu;
	}
	public Motherboard matchMotherboard(String matchMoboQuery){
		Motherboard mb = new Motherboard();
		ArrayList matches = this.neo4j.executeQueryNeo4j(this.db, matchMoboQuery);		
		if(matches.size()>0){
			matches = priceComponent.getPricesByComponent(parseHw.parseQueryToMotherboardObject(this.db, matches));
			mb = (Motherboard) priceComponent.comparePricesFromComponentArray(matches);
		}return mb;
	}	
	

}