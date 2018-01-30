/**
 * 
 */
package com.aicte.opvs.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lenovo
 *
 */
public class HttpPost {
	
	private static StringBuffer strBuff;
	
	public static StringBuffer listenClient(HttpServletRequest request) {

		try {	
			ServletInputStream is = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			strBuff = new StringBuffer(); 

			//Reading from the stream
			while((line = br.readLine()) != null) {

				strBuff.append(line);
			}

			br.close();
		} catch(Exception e) {
			System.out.println(e);
		}

		return strBuff;
	}

	public static void replyClient(HttpServletResponse response, String str){

		try{
			response.setContentType("application/json");
			
			ServletOutputStream sos=response.getOutputStream();
			sos.print(str);
			sos.flush();
			sos.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
