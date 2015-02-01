package matcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

import components.*;
import org.jsoup.Jsoup;
import org.neo4j.graphdb.GraphDatabaseService;

import parsing.FilterString;
import parsing.ParseHardware;

import databasemanager.Neo4jManager;
import us.codecraft.xsoup.Xsoup;

/**
 * This class handles everything that needs to be done with Motherboard and matching.
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
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
		Memory ram = new Memory();
		String query = "MATCH (n:MEMORY) "
					 + "WHERE n.Geheugentype =~ '.*(?i)"+motherboardGeheugenType+".*' "
					 + "RETURN n;";
		ArrayList matches = this.neo4j.executeQueryNeo4j(this.db, query);
		if(matches.size()>0){
			ram  = (Memory) priceComponent.getPricesByArrayList(parseHw.parseQueryToRAMObject(this.db, matches));
		}else{ram = null;}
		return ram;
	}

	/**
	 * This method is used to match a CPU Object from the Neo4j Database. Needs a Motherboard socket for compatibility.
	 *
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
	 *
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

	/**
	 * Gets the pries of the user selected Components.
	 *
	 * @param userSelectedComponents ArrayList with the user selected components.
	 * @return ArrayList with the components and prices.
	 */
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

	/**
	 * Gets the images for the Components.
	 *
	 * @param componentList ArrayList with the matched and final components which needs image urls.
	 * @return ArrayList with the same components and all components have image urls.
	 */
	public ArrayList getImageForComponent(ArrayList<Hardware> componentList){
		ArrayList newList = new ArrayList();
		for(Hardware h : componentList){
			newList.add(getImageForHardware(h));
		}
		return newList;
	}

	/**
	 * Gets the images for a specific Hardware.
	 *
	 * @param hardware A Hardware that needs an image url.
	 * @return Hardware with the image url.
	 */
	public Hardware getImageForHardware(Hardware hardware){
		String xpath = "//*[@id=\"entity\"]/div/div[2]/header/div[3]/div[1]/a/img/@src";
		try {

			hardware.setImageurl(
					Xsoup.compile(xpath)
					.evaluate(Jsoup.connect(hardware.getUrl()).get())
					.get());
			return hardware;
		}
		catch (MalformedURLException mue) { mue.printStackTrace(); }
		catch (IOException ioe) { ioe.printStackTrace(); }
		return hardware;
	}
}