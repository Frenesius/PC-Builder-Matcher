package main;

import java.sql.Connection;
import java.sql.SQLException;

import databasemanager.MySqlManager;

public class Main {

	public static void main(String[] args){
	    MySqlManager a = new MySqlManager();
	    try{
	    	Connection b = a.connectDB();
	    	a.executeQueryMySql(b);
	    }catch(Exception e){}
	    
		
		
		
		
	}	
}
	