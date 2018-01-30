package com.aicte.opvs.network;

import java.io.InputStream;
import java.net.CookieHandler;
//import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

import org.json.JSONObject;


public class HttpGet {

	public static HttpURLConnection con = null;
	public static StringBuffer urlName =  new StringBuffer("http://localhost:7080/OnePointVerificationSystem");
	public static JSONObject json = null;
	
	public static void sendRequest(String targetServlet, String query) {
		try {
			
			CookieHandler.setDefault(AllCookies.cookieManager);
			
//			urlName.append(targetServlet);
	        
			String data = new String("1" + query);
			System.out.println("data: " + data);
			URI uri = new URI("http", "null", "localhost", 7080, "/OnePointVerificationSystem" + targetServlet, "data=" + data, null);
			
			URL url = uri.toURL();
//	        URI uri = new URI(urlName.toString() + targetServlet.toString() + query);
	        
	        System.out.println(url.toString());
	        con = (HttpURLConnection) (url.openConnection());
	        con.setConnectTimeout(40000);
	        con.setReadTimeout(40000);
	        con.setRequestMethod("GET");
	        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
	        HttpURLConnection.setFollowRedirects(true);
	        con.setDoOutput(true);
	        con.setDoInput(true);
	        con.setUseCaches(false);
	        con.setAllowUserInteraction(false);
//	        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        con.setRequestProperty("Content-Type", "application/json");
	        con.setRequestProperty("Content-Language", "en-US");
	        
	        con.connect(); 
		} catch (Exception e) {
	    	System.out.println("HttpGet send: Caught: " + e);
//	        e.printStackTrace();
	    }
	}
	
	public static JSONObject receiveResponse() {
		try {
			
			CookieHandler.setDefault(AllCookies.cookieManager);
			
			// Read Response
	        int x;
	        StringBuffer response = new StringBuffer();
	        InputStream in = con.getInputStream();
	        while ((x = in.read()) != -1) {
	        	System.out.print((char) x);
	        	response.append((char) x);
	        }
	        System.out.print(response.toString());
	        
	        json = new JSONObject(response.toString());
	        
		} catch (Exception e) {
	    	System.out.println("HttpGet recres: Caught: " + e);
	    } finally {
	        con.disconnect();
	        con = null;
	    }
		
		return json;
	}
}
