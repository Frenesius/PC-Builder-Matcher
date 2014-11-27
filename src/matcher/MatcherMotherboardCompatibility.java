package matcher;
import java.sql.SQLException;
import java.util.ArrayList;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.core.NodeProxy;

import components.CPU;
import components.GPU;
import components.Memory;
import databasemanager.Neo4jManager;

public class MatcherMotherboardCompatibility {
	Neo4jManager neo4j;
	GraphDatabaseService db;
	PriceComponent priceComponent;
	
	MatcherMotherboardCompatibility() throws SQLException{
		this.neo4j = new Neo4jManager();
		this.db = neo4j.openNeo4jDatabase();
		this.priceComponent = new PriceComponent();
	}
	
	public Memory matchRamBasedOnMobo(String motherboardGeheugenType){
		Memory ram = new Memory();
		String query = "MATCH (n:MEMORY) "
					 + "WHERE n.Geheugentype =~ '.*(?i)"+motherboardGeheugenType+".*' "
					 + "RETURN n "
					 + "LIMIT 20; ";
		ArrayList b = this.neo4j.executeQueryNeo4j(this.db, query);
		
		priceComponent.getPricesByComponent(b);
		ram = extractDataFromMemoryNode(this.db, b);
		
		return ram;
	}
	public CPU matchCpuBasedOnMobo(String motherboardSocket){
		CPU cpu = new CPU(); //TODO Remove the new
		String query = "MATCH (n:PROCESSOR) "
					 + "WHERE n.Socket =~ '.*"+motherboardSocket+".*' "
					 + "RETURN n "
					 + "LIMIT 20;";
	    ArrayList b = this.neo4j.executeQueryNeo4j(this.db, query);
	    cpu = extractDataFromCpuNode(this.db, b);
		
	    return cpu;
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
		ArrayList b = this.neo4j.executeQueryNeo4j(this.db, query);
		
		b = priceComponent.getPricesByComponent(parseQueryToRamObject(this.db, b));
		gpu = (GPU) priceComponent.comparePricesFromComponentArray(b);
		gpu.hashCode();
		
		return gpu;
	}
	private ArrayList parseQueryToRamObject(GraphDatabaseService db, ArrayList components){
		ArrayList c = new ArrayList();
		for(int i = 0; i<components.size();i++){
			GPU gpu = new GPU();
			Transaction tx =  db.beginTx();
			try{
				NodeProxy ramNode = (NodeProxy) components.get(i);
				gpu.setEan(ramNode.getProperty("EAN").toString());
				gpu.setIsEmpty(false);
				gpu.setIsMatched(true);				
				tx.success();
			}catch(Exception e){
				gpu.setIsEmpty(true);
				gpu.setIsMatched(false);
			}finally {c.add(gpu);
				tx.close();}
		}
		
		return c;
	}
	private GPU extractDataFromGpuNode(GraphDatabaseService db, ArrayList n){
		GPU gpu = new GPU();
		int randomNumber = (int) Math.floor(Math.random()*20);
		Transaction tx =  db.beginTx();
		try{
			NodeProxy ramNode = (NodeProxy) n.get(randomNumber);
			gpu.setEan(ramNode.getProperty("EAN").toString());
			gpu.setIsEmpty(false);
			gpu.setIsMatched(true);
			tx.success();
		}catch(Exception e){
			gpu.setIsEmpty(true);
			gpu.setIsMatched(false);
		}finally {tx.close();} 
		
		return gpu;
	}
	private CPU extractDataFromCpuNode(GraphDatabaseService db, ArrayList n){
		CPU cpu = new CPU();
		int randomNumber = (int) Math.floor(Math.random()*20);
		Transaction tx =  db.beginTx();
		try{
			NodeProxy ramNode = (NodeProxy) n.get(randomNumber);
			cpu.setEan(ramNode.getProperty("EAN").toString());
			cpu.setIsEmpty(false);
			cpu.setIsMatched(true);
			tx.success();
		}catch(Exception e){
			cpu.setIsEmpty(true);
			cpu.setIsMatched(false);
		}finally {tx.close();} 
		
		return cpu;
	}
	private Memory extractDataFromMemoryNode(GraphDatabaseService db, ArrayList n){
		Memory ram = new Memory();
		int randomNumber = (int) Math.floor(Math.random()*20);
		Transaction tx =  db.beginTx();
		try{
			NodeProxy ramNode = (NodeProxy) n.get(randomNumber);
			ram.setEan(ramNode.getProperty("EAN").toString());
			ram.setIsEmpty(false);
			ram.setIsMatched(true);
			tx.success();
		} catch(Exception e){
			ram.setIsEmpty(true);
			ram.setIsMatched(false);
		}finally{tx.close();} 
		
		return ram;
	}
}	
