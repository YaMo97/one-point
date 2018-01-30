package com.aicte.opvs.network;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;


public class HttpPost {

	public static HttpURLConnection con = null;
	public static StringBuffer urlName =  new StringBuffer("http://localhost:7080/OnePointVerificationSystem/");
	public static JSONObject json = null;
	
	public static void sendRequest(String targetServlet, String requestQuery) {
		try {
			CookieHandler.setDefault(AllCookies.cookieManager);
			
//			urlName.append(targetServlet);
	        
	        URL url = new URL(urlName.toString() + targetServlet.toString());
	        con = (HttpURLConnection) (url.openConnection());
	        con.setConnectTimeout(40000);
	        con.setReadTimeout(40000);
	        con.setRequestMethod("POST");
	        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
	        HttpURLConnection.setFollowRedirects(true);
	        con.setDoOutput(true);
	        con.setDoInput(true);
	        con.setUseCaches(false);
	        con.setAllowUserInteraction(false);
//	        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        con.setRequestProperty("Content-Type", "application/json");
	        con.setRequestProperty("Content-Language", "en-US");
	        
	        // Send Post Request
	        OutputStream os = con.getOutputStream();
	        BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(os, "UTF-8"));
	        writer.write(requestQuery);

	        writer.flush();
	        writer.close();
	        os.close();
	        
	        con.connect(); 
		} catch (Exception e) {
	    	System.out.println("Caught: " + e);
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
	        
	        //Retrieve all stored HttpCookies from CookieStore
            List<HttpCookie> cookies = AllCookies.cookieStore.getCookies();

            int cookieIdx = 0;

            //Iterate HttpCookie object
            for (HttpCookie ck : cookies) {

                System.out.println("------------------ Cookie." + ++cookieIdx  + " ------------------");

                //Get the cookie name
                System.out.println("Cookie name: " + ck.getName());

                //Get the domain set for the cookie
                System.out.println("Domain: " + ck.getDomain());

                //Get the max age of the cookie
                System.out.println("Max age: " + ck.getMaxAge());

                //Get the path of the server
                System.out.println("Server path: " + ck.getPath());

                //Get boolean if the cookie is being restricted to a secure protocol
                System.out.println("Is secured: " + ck.getSecure());

                //Gets the value of the cookie
                System.out.println("Cookie value: " + ck.getValue());

                //Gets the version of the protocol with which the given cookie is related.
                System.out.println("Cookie protocol version: " + ck.getVersion());
            }

		} catch (Exception e) {
	    	System.out.println("Caught: " + e);
//	        e.printStackTrace();
	    } finally {
	        con.disconnect();
	        con = null;
	    }
		
		return json;
	}
}
