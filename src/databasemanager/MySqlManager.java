package databasemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySqlManager {
	public boolean isConnected = false;
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/hardwareprice";
	private String MYSQL_USER = "user1";
	private String MYSQL_PASSWORD = "user1";


	public Connection connectDB() throws SQLException {
		Connection myConn = null;
		myConn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
		if (myConn != null) 
			isConnected = true;
		else
			myConn.close();
		return myConn;
	}
	
	public ResultSet executeQueryMySql(Connection mysqlConnection, String query){
		ResultSet c = null;
		try {
			Statement myStmt = mysqlConnection.createStatement();
			myStmt.execute(query);
			c = myStmt.getResultSet();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	
}
