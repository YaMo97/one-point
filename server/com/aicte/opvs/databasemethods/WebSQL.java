package com.aicte.opvs.databasemethods;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;

import org.json.JSONArray;

import com.aicte.opvs.helpers.HttpClient;

//import com.aicte.onepointverificationserver.configuration.Configuration;

public class WebSQL {

	static JSONArray json = new JSONArray();
	static HttpURLConnection httpURLConnection = null;
	
//	static String WEBSITE = Configuration.WEBSITE;
//	static String SQLHANDLER = Configuration.SQLHANDLER;
//	
	
	static String WEBSITE = "http://onepoint.000webhostapp.com";
	static String SQLHANDLER = "sample1.php";
	
	public static JSONArray execute(String query) {
		
		try {
			query = URLEncoder.encode(query, "utf-8")
					.replaceAll("\\*", "%2A");
			
			URI uri = new URI(new String(WEBSITE + "/" + SQLHANDLER + "?query=" + query));
			
			json = new JSONArray(HttpClient.sendGet(uri.toString()));
			System.out.println(json); 
			
		} catch(Exception e) {
			System.out.println("WebSQL: Caught: " + e);
		}
		
		return json;
	}
	
//	public static void main(String s[]) {
//		JSONArray js = execute("SELECT * FROM employee_logi;");
//		System.out.println(js.toString());
//	}
}
