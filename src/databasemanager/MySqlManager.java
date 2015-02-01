package databasemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import components.Hardware;

/**
 * <h1>MySQL manager.<h1/>
 * Used to get data from MySQL.
 * <b>Do not forget to change the MYSQL_URL<b/>
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
public class MySqlManager {
	public boolean isConnected = false;
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/hardwareprice";
	private String MYSQL_USER = "user1";
	private String MYSQL_PASSWORD = "user1";

	/**
     * Opens an MySQL connection with the database
     * @return Gives Connection object back. This is the JDBC connection object.
     */
	public Connection connectDB() throws SQLException {
		Connection myConn = null;
		myConn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
		if (myConn != null) 
			isConnected = true;
		else
			myConn.close();
		return myConn;
	}

	/**
	 * Executes Queries on the MySQL Database.
	 *
	 * @param mysqlConnection The Connection to the database. You can get this connection by calling connectDB().
	 * @param query String with the query that you want to execute on the MySQL Database.
 	 */
	public ResultSet executeQueryMySql(Connection mysqlConnection, String query){

		ResultSet c = null;
		try {
			Statement myStmt = mysqlConnection.createStatement();
			myStmt.execute(query);
			c = myStmt.getResultSet();
		}catch (SQLException e){e.printStackTrace();}
		return c;
	}

	/**
	 * Gets the prices for a specific EAN number.
	 *
	 * @param mysqlConnection The Connection to the database. You can get this connection by calling connectDB().
	 * @param EAN The EAN number that you want to get all the prices off.
	 * @return ResultSet which contains all the prices of a specific EAN number.
	 */
	public ResultSet getPricesByComponentEan(Connection mysqlConnection, String EAN){
		String query = "SELECT ean, shopname, delivery, priceex, priceinc, linkshop, max(timestamp) "
				 + "FROM hardwareprice.hardwareprice "
				 + "WHERE ean = '"+EAN+"' "
				 + "GROUP BY shopname;";

		return this.executeQueryMySql(mysqlConnection, query);
	}

	/**
	 * This will get all the lines in the MySQL database.
	 * Debug Purposes.
	 *
	 * @param mysqlConnection The Connection to the database. You can get this connection by calling connectDB().
	 * @return ResultSet which contains all the lines of the MySQL Database.
	 */
	public ResultSet getEverything(Connection mysqlConnection){
		String query = "SELECT ean, shopname, delivery, priceex, priceinc, linkshop, max(timestamp) "
				+ "FROM hardwareprice.hardwareprice GROUP BY hardwareprice.ean; ";
		return this.executeQueryMySql(mysqlConnection, query);
	}

	/**
	 * Builds a Query based on the components that you have given as input.
	 * The Query will match the cheapest Component from the ArrayList.
	 *
	 * @param component ArrayList with all the Components. This will be used to find the cheapest Hardware in that list.
	 * @return String with the query.
	 */
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
