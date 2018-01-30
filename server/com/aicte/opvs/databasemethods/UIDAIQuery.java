package com.aicte.opvs.databasemethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONException;

import com.aicte.opvs.configuration.Configuration;
import com.aicte.opvs.details.*;

public class UIDAIQuery<T> {

	final String MySQLDriver = Configuration.DRIVER;
	final String URL = Configuration.URL;
	final String HOST = Configuration.HOST;
	final int PORT = Configuration.UIDAI_PORT;
	final String USER = Configuration.USER;
	final String PASS = Configuration.PASS;
	final String DATABASE = Configuration.UIDAI_DATABASE;
	final int LOCAL_DATABASE = Configuration.LOCAL_DATABASE;
	
	Connection con;
	
	public UIDAIQuery() {
		try {
			switch (LOCAL_DATABASE) {
			case 1:
			// Register the Driver
			   Class.forName(MySQLDriver);
			  
			// Establish Connection
			   System.out.println(URL + HOST+":"+PORT+"/"+DATABASE+"?user=" + USER + "&password=" + PASS);
			   con = DriverManager.getConnection(URL + HOST + ":" + PORT + "/" + DATABASE + "?user=" + USER + "&password=" + PASS);
			
			   break;
			   
			case 0:
				break;
				
			default:
			}
				
		} catch(Exception e) {
			System.out.println("UIDAIQuery(): Caught: " + e);
		}		
	}
	
	public JSONArray execute(String opcode, T obj) 
		throws JSONException {
		
		JSONArray json = null;
		
		ResultSet rs = null;
		
		PreparedStatement preStmt = null;
		
		String queryString = new String();
		
		try {			
			switch (LOCAL_DATABASE) {
			case 1:
				// Local Database Exists ?
				
				
				preStmt = con.prepareStatement(SQLQuery.SQL.get(opcode));
				break;
				
			case 0:
				// Local Database Does Not Exist
				// Using Online Database
				queryString = SQLQuery.SQL.get(opcode);
				break;
			}
			
			switch (opcode.charAt(0)) {
			
			case 'V':
				// For Verify
				
				switch(opcode.charAt(1)) {
				case 'A':
					// For Aadhaar
					switch (LOCAL_DATABASE) {
					case 1:
						// Local Database Exists
						
						preStmt.setString(1, ((IndividualDetails) obj).getAadhaarNumber());
						
						rs=preStmt.executeQuery();
						
						json = ResultSetConverter.convert(rs);
						break;
						
					case 0:
						// Local Database Does Not Exist
						// Using Online Database
						queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getAadhaarNumber() + "\'");
						
						json = WebSQL.execute(queryString);
						break;
					}
					
				default:
					System.out.println("Unhandled Login opcode");
				}
				break;
			default:
				System.out.println("Unhandled Query");
			}
			
		} catch(Exception e) {
			System.out.println("UIDAIQuery: Caught: " + e);
		}
		
		return json;
	}
}
