package matcher;
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
	
	MatcherMotherboardCompatibility(){
		neo4j = new Neo4jManager();
		db = neo4j.openNeo4jDatabase();
	}
	
	public Memory matchRamBasedOnMobo(String motherboardGeheugenType){
		Memory ram = new Memory();
		String query = "MATCH (n:MEMORY) "
					 + "WHERE n.Geheugentype =~ '.*(?i)"+motherboardGeheugenType+".*' "
					 + "RETURN n "
					 + "LIMIT 20; ";
		
		ArrayList b = this.neo4j.executeQueryNeo4j(this.db, query);
		ram = extractDataFromMemoryNode(this.db, b);
		ram.toString();
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
		GPU gpu = new GPU();
		String query = "MATCH (n:GRAPHICSCARD) "
				     + "WHERE n.`Card Interface (Video)` =~ '.*(?i)"+motherboardCardInterface+".*' "
					 + "RETURN n "
				 	 + "LIMIT 10;";
		
		ArrayList b = this.neo4j.executeQueryNeo4j(this.db, query);
		
		b.hashCode();
		return gpu;
	}
	public GPU extractDataFromGpuNode(GraphDatabaseService db, ArrayList n){
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
	public CPU extractDataFromCpuNode(GraphDatabaseService db, ArrayList n){
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
	public Memory extractDataFromMemoryNode(GraphDatabaseService db, ArrayList n){
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
		}
		finally {    
			tx.close();  
	 	} 
		return ram;
	}
}	
