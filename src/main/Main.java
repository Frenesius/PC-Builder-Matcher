package main;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException{
	    PcBuilder builder = new PcBuilder();
	    long startTime = System.currentTimeMillis();
		builder.start();
	    long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total time: " +totalTime);
	}
}	