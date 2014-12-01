package parsing;

import java.util.ArrayList;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.core.NodeProxy;

import components.CPU;
import components.GPU;
import components.Memory;

public class ParseHardware {
	
	public ArrayList parseQueryToGPUObject(GraphDatabaseService db, ArrayList components){
		ArrayList componentsArray = new ArrayList();
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
			}finally {
				componentsArray.add(gpu);
				tx.close();}
		}
		return componentsArray;
	}

	public ArrayList parseQueryToCPUObject(GraphDatabaseService db, ArrayList components){
		ArrayList componentsArray = new ArrayList();
		for(int i = 0; i<components.size();i++){
			CPU cpu = new CPU();
			Transaction tx =  db.beginTx();
			try{
				NodeProxy ramNode = (NodeProxy) components.get(i);
				cpu.setEan(ramNode.getProperty("EAN").toString());
				cpu.setIsEmpty(false);
				cpu.setIsMatched(true);				
				tx.success();
			}catch(Exception e){
				cpu.setIsEmpty(true);
				cpu.setIsMatched(false);
			}finally {
				componentsArray.add(cpu);
				tx.close();}
		}
		return componentsArray;
	}
	public ArrayList parseQueryToRAMObject(GraphDatabaseService db, ArrayList components){
		ArrayList componentsArray = new ArrayList();
		for(int i = 0; i<components.size();i++){
			Memory ram = new Memory();
			Transaction tx =  db.beginTx();
			try{
				NodeProxy ramNode = (NodeProxy) components.get(i);
				ram.setEan(ramNode.getProperty("EAN").toString());
				ram.setIsEmpty(false);
				ram.setIsMatched(true);				
				tx.success();
			}catch(Exception e){
				ram.setIsEmpty(true);
				ram.setIsMatched(false);
			}finally {
				componentsArray.add(ram);
				tx.close();}
		}
		return componentsArray;
	}
}
