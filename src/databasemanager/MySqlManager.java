package databasemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import components.Hardware;


public class MySqlManager {
	/*
	 * MySQL manager.
	 * Used to get data from MySQL.
	 */
	public boolean isConnected = false;
	
	//TODO Put this in a config.
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/hardwareprice";
	private String MYSQL_USER = "user1";
	private String MYSQL_PASSWORD = "user1";

	public Connection connectDB() throws SQLException {
		/*
		 * MySql connection.
		 * Gives Connection object back.
		 */
		Connection myConn = null;
		myConn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
		if (myConn != null) 
			isConnected = true;
		else
			myConn.close();
		return myConn;
	}
	
	public ResultSet executeQueryMySql(Connection mysqlConnection, String query){
		/*
		 * Executes Queries and gives a ResultSet back.
		 */
		ResultSet c = null;
		try {
			Statement myStmt = mysqlConnection.createStatement();
			myStmt.execute(query);
			c = myStmt.getResultSet();
		}catch (SQLException e){e.printStackTrace();}
		return c;
	}
	int i = 0;
	public ResultSet getPricesByComponentEan(Connection mysqlConn, String EAN){
		String query = "SELECT ean, shopname, delivery, priceex, priceinc, linkshop, max(timestamp) "
				 + "FROM hardwareprice.hardwareprice "
				 + "WHERE ean = '"+EAN+"' "
				 + "GROUP BY shopname;";
		//2830 queries
		System.out.println(query);
		i++;
		return this.executeQueryMySql(mysqlConn, query);
	}
	public ResultSet getEverything(Connection mysqlConn){
		String query = "SELECT ean, shopname, delivery, priceex, priceinc, linkshop, max(timestamp) "
				+ "FROM hardwareprice.hardwareprice GROUP BY hardwareprice.ean; ";
		//2830 queries
		//System.out.println(query);
		i++;
		return this.executeQueryMySql(mysqlConn, query);
	}
	public String buildQuery(ArrayList component){
		String query = "SELECT ean, shopname, delivery, priceex, min(priceinc), linkshop, max(timestamp) " +
				"FROM hardwareprice.hardwareprice ";
		for(int i = 0; i < component.size();i++){
			Hardware hardware = (Hardware) component.get(i);
			if(i == 0)
				query +=" WHERE ean LIKE \'"+hardware.getEan()+"\'";
			else
				query +=" OR ean LIKE \'"+hardware.getEan()+"\'";

		}
		query += "GROUP BY shopname;";
		return query;
	}

}
