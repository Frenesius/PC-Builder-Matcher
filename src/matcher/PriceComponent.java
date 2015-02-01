package matcher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.neo4j.graphdb.GraphDatabaseService;

import components.Hardware;

import databasemanager.MySqlManager;
import databasemanager.Neo4jManager;

/**
 * This class will handle everything that has to do with prices.
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
public class PriceComponent {
	private MySqlManager mysqlManager;
	private Connection mysqlConn;
	private Neo4jManager neo4jManager;
	
	PriceComponent() throws SQLException{
		this.mysqlManager = new MySqlManager();
		this.mysqlConn = mysqlManager.connectDB();
		this.neo4jManager = new Neo4jManager();
	}

	/**
	 * This will get the cheapest Motherboard from Neo4j.
	 *
	 * @param graphDb The Neo4j Graph Database Service.
	 * @param cypherQuery Query to match the Motherboard.
	 * @return
	 */
	public Hardware getCheapestMotherboard(GraphDatabaseService graphDb, String cypherQuery){
		ArrayList tempArray = this.neo4jManager.executeQueryNeo4j(graphDb, cypherQuery);
		return this.getPricesByArrayList(tempArray);
	}

	/**
	 * This will get all the prices for components in the ArrayList.
	 *
	 * @param component ArrayList with the components that needs prices.
	 * @return ArrayList with the components and their prices.
	 */
	public Hardware getPricesByArrayList(ArrayList component){ //TODO
		ArrayList newComponentList = new ArrayList();
		Hardware hardware = null;
		String query = mysqlManager.buildQuery(component);
		//Testing
		ResultSet rs  = mysqlManager.executeQueryMySql(mysqlConn, query);
		try {
			if(rs.next()){
				String ean = rs.getString(rs.findColumn("ean"));
				hardware = findHardwareByEan(component, ean);
				hardware.setEan(rs.getString(rs.findColumn("ean")));
				hardware.setShopname(rs.getString(rs.findColumn("shopname")));
				hardware.setDelivery(rs.getString(rs.findColumn("delivery")));
				hardware.setPriceex(rs.getString(rs.findColumn("priceex")));
				hardware.setPriceinc(rs.getString(rs.findColumn("min(priceinc)")));
				hardware.setLinkshop(rs.getString(rs.findColumn("linkshop")));
				hardware.setTimestamp((rs.getString(rs.findColumn("max(timestamp)"))));
			}
		}catch(SQLException e){e.printStackTrace();}
		return hardware;
	}

	/**
	 * This will get the prices per component.
	 * @param component ArrayList with the components that needs prices.
	 * @return Hardware with price.
	 */
	public Hardware getPricesByComponent(ArrayList component){ //TODO
		ArrayList newComponentList = new ArrayList();
		Hardware hardware = null;
		String query = mysqlManager.buildQuery(component);
		//Testing
		ResultSet rs  = mysqlManager.executeQueryMySql(mysqlConn, query);
		try {
			if(rs.next()){
				String ean = rs.getString(rs.findColumn("ean"));
				hardware = findHardwareByEan(component, ean);
				hardware.setEan(rs.getString(rs.findColumn("ean")));
				hardware.setShopname(rs.getString(rs.findColumn("shopname")));
				hardware.setDelivery(rs.getString(rs.findColumn("delivery")));
				hardware.setPriceex(rs.getString(rs.findColumn("priceex")));
				hardware.setPriceinc(rs.getString(rs.findColumn("min(priceinc)")));
				hardware.setLinkshop(rs.getString(rs.findColumn("linkshop")));
				hardware.setTimestamp((rs.getString(rs.findColumn("max(timestamp)"))));
			}
		}catch(SQLException e){e.printStackTrace();}
		return hardware;
	}

	/**
	 * This will compare the prices from a ArrayList and return the cheapest.
	 *
	 * @param component ArrayList with components.
	 * @return Hardware that is the cheapest.
	 */
	public Hardware comparePricesFromComponentArray(ArrayList component){
		float lowstPrice = 100;
		int cheapestIndex = 0;
		for(int i = 0; i<component.size();i++){
			Hardware h = (Hardware) component.get(i);
			float price = 0;
			try{
				price = Float.parseFloat(h.getPriceex());
				if(price<lowstPrice){
					lowstPrice = price;
					cheapestIndex = i;
				}
			}catch(Exception e){continue;}
		}
		return (Hardware) component.get(cheapestIndex);
	}

	/**
	 * This will find the Hardware by EAN number.
	 *
	 * @param component ArrayList with the components.
	 * @param ean EAN number that you want from the ArrayList.
	 * @return
	 */
	public Hardware findHardwareByEan(ArrayList component, String ean){
		Hardware hardware = null;
		for(int i =0; i<component.size();i++){
			hardware = (Hardware) component.get(i);
			String eanComp = hardware.getEan();
			if (eanComp.equals(ean)){
				return hardware;
			}
		}
		return hardware;
	}
} 

