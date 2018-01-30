package com.aicte.opvs.databasemethods;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnections {
	final String MySQLDriver = "com.mysql.jdbc.Driver";
	
	final String URL = "jdbc:mysql://";
	final String HOST = "localhost";
	final int PORT = 3307;
	final String USER = "user";
	final String PASS = "user";
	
	private String database;
	
	Connection con;
	
	
	public DatabaseConnections(int PORT){
		
			try{
			// Register the Driver
			   Class.forName(MySQLDriver);
			  
			switch (PORT){
				case 3307:
					database="aicte";
					break;
					
				case 3308:
					database="uidai";
					break;
					
				case 3309:
					database="npci";
					break;
			}
			// Establish Connection
		           con=DriverManager.getConnection(URL + HOST+":"+PORT+"/"+database+"?user=" + USER + "&password=" + PASS);
			
			}catch(Exception e){System.out.println(e);}
		           
		
	}
	
	public Connection getConnection()
	{
		return con;
	}
}
