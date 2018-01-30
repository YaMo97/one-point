package com.aicte.opvs.configuration;

//import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	
	public static String URL = null;
	public static String HOST = null;
	public static int AICTE_PORT;
	public static int UIDAI_PORT;
	public static int NPCI_PORT;
	public static String USER = null;
	public static String PASS = null;
	public static String AICTE_DATABASE = null;
	public static String UIDAI_DATABASE = null;
	public static String NPCI_DATABASE = null;
	public static String DRIVER = null;
	public static String WEBSITE = null;
	public static String SQLHANDLER = null;
	public static int LOCAL_DATABASE;
	
	static {
		try {
			InputStream reader = Configuration.class.getResourceAsStream("/serverconfig.properties");  
			if (reader == null){
				System.out.println("Shit");
			}
			
			
			Properties p=new Properties();  
			p.load(reader);  
			System.out.println(p.getProperty("user"));  
			//System.out.println(p.getProperty("password"));  
			URL = p.getProperty("url");
			//final String HOST = "localhost";
			HOST = p.getProperty("host");
			System.out.println("Hooray");
			AICTE_PORT = Integer.parseInt(p.getProperty("aicte_port"));
			UIDAI_PORT = Integer.parseInt(p.getProperty("uidai_port"));
			NPCI_PORT = Integer.parseInt(p.getProperty("npci_port"));
			USER = p.getProperty("user");
			PASS = p.getProperty("pass");
			AICTE_DATABASE = p.getProperty("aicte_database");
			UIDAI_DATABASE = p.getProperty("uidai_database");
			NPCI_DATABASE = p.getProperty("npci_database");
			DRIVER = p.getProperty("driver");
			WEBSITE = p.getProperty("website");
			SQLHANDLER = p.getProperty("sqlhandler");
			LOCAL_DATABASE = Integer.parseInt(p.getProperty("local_database"));
			
		} catch(Exception e) { 
			System.out.println("Configurations.java: Caught: " + e);
		}
			
	}
}