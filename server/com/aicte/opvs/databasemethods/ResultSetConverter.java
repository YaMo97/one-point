package com.aicte.opvs.databasemethods;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ResultSetConverter {
  
	public static JSONArray convert( ResultSet rs ) 
			throws SQLException, JSONException
	{
		JSONArray json = new JSONArray();
	    ResultSetMetaData rsmd = rs.getMetaData();
	    
	    while(rs.next()) {	
	    	int numColumns = rsmd.getColumnCount();
	    	JSONObject obj = new JSONObject();
		
		    for (int i=1; i < numColumns + 1; i++) {
		    	String column_name = rsmd.getColumnName(i);
		    	int columnType = rsmd.getColumnType(i);
		    	
		        if(columnType == java.sql.Types.ARRAY){
		        	obj.put(column_name, rs.getArray(i));
		        }
		        else if(columnType == java.sql.Types.BIGINT){
		        	obj.put(column_name, rs.getInt(i));
		        }
		        else if(columnType == java.sql.Types.BOOLEAN){
		        	obj.put(column_name, rs.getBoolean(i));
		        }
		        else if(columnType == java.sql.Types.BLOB){
		        	obj.put(column_name, rs.getBlob(i));
		        }
		        else if(columnType == java.sql.Types.DOUBLE){
		        	obj.put(column_name, rs.getDouble(i)); 
		        }
		        else if(columnType == java.sql.Types.FLOAT){
		        	obj.put(column_name, rs.getFloat(i));
		        }
		        else if(columnType == java.sql.Types.INTEGER){
		        	obj.put(column_name, rs.getInt(i));
		        }
		        else if(columnType == java.sql.Types.NVARCHAR){
		        	obj.put(column_name, rs.getNString(i));
		        }
		        else if(columnType == java.sql.Types.VARCHAR){
		        	obj.put(column_name, rs.getString(i));
		        }
		        else if(columnType == java.sql.Types.TINYINT){
		        	obj.put(column_name, rs.getInt(i));
		        }
		        else if(columnType == java.sql.Types.SMALLINT){
		        	obj.put(column_name, rs.getInt(i));
		        }
		        else if(columnType == java.sql.Types.DATE){
//		        	String str = rs.getString(i);
		        	try {
		        		obj.put(column_name, rs.getDate(i)); // Throwing exception
		        	} catch (SQLException e) {
		        		System.out.println("RSC - Caught: " + e);
		        		obj.put(column_name, "1970-01-01");
		        	}
//		        	obj.put(column_name, "date");
		        }
		        else if(columnType == java.sql.Types.TIMESTAMP){
		        	obj.put(column_name, rs.getTimestamp(i));   
		        }
		        else{
		        	obj.put(column_name, rs.getObject(i));
		        }
		    }
		
		    json.put(obj);
	    }
	
	    return json;
	}
}