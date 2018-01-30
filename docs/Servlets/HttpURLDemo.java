// Demonstrate HttpURLConnection
import java.net.*;
import java.io.*;
import java.util.*;

public class HttpURLDemo {
	
	public static void display(String str) {
		
		System.out.println(str);
		
	}
	
	public static void main(String args[]) throws Exception {
		URL hp = new URL("http://localhost:7080/Testing/SessionTrack");
				
		HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
		
		// Display request method.
		System.out.println("Request method is " + hpCon.getRequestMethod());
		
		// Display response code.
		System.out.println("Response code is " + hpCon.getResponseCode());
		
		// Display response message.
		System.out.println("Response message is " + hpCon.getResponseMessage());
		
		// Get a list of the header fields and a set of the header keys
		Map<String, List<String>> hdrMap = hpCon.getHeaderFields();
		Set<String> hdrField = hdrMap.keySet();
		
		System.out.println("\nHere is the header:");
		
		// Display all header keys and values
		for (String k : hdrField) {
			System.out.println("Key: " + k + " Value: " + hdrMap.get(k));
		}
		
		InputStream in = hpCon.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str = br.readLine();
		
		// int a;
		// while ((a = in.read()) != -1)
			//System.out.print((char) a );
		
		display(str);
		
		
	}
}