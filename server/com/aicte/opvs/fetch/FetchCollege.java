package com.aicte.opvs.fetch;

import java.io.IOException;
//import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aicte.opvs.databasemethods.AICTEQuery;
import com.aicte.opvs.helpers.HttpGet;
import com.aicte.opvs.helpers.JSONReply;
//import com.google.gson.Gson;

/**
 * Servlet implementation class FetchCollege
 */
@WebServlet("/FetchCollege")
public class FetchCollege extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static JSONObject replyJSON;

//	private ArrayList<String> universityList, collegeList;   

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchCollege() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println();
		System.out.println("FetchCollege servlet");
		
//		String str = new String();
//		universityList = null;
//		collegeList = null;
		
		int errorCode = -1;
		String status = "ERROR";
		String message = "Unhandled Exception";
		JSONArray data = new JSONArray();
		
		replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
		
		try {
			String state = request.getParameter("state");
			System.out.println("State = " + state);
			
			if (state != null) {
				String university = request.getParameter("university");
				System.out.println("University = " + university);
				
				if (university != null) {
					// fetch colleges from college table where state and university given
					// fetch Universities from college table where state given		
					
					AICTEQuery<String> query = new AICTEQuery<>();
					
					JSONArray jsonArray = query.execute("FC", university);
					System.out.println(jsonArray.toString());			
//					
//					int i = 0;
//					collegeList = new ArrayList<String>();
//					while (!(jsonArray.isNull(i)))
//					{
//						collegeList.add(jsonArray.getJSONObject(i).getString("college_name"));
//						i++;
//					}
//					
//					str = new Gson().toJson(collegeList);
//					System.out.println(str + "\n" + i);
					
					// insert JSON Objects in data jsonArray 
					// dataArray[0] = { "name":"College1"}
					// dataArray[0] = { "name":"College2"}
					
					errorCode = 0;
					status = "SUCCESS";
					message = "Colleges Fetched";
					data = jsonArray;
					
					
					replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
					
					//replyClient(response, new StringBuffer("" + i), new StringBuffer(str));
				} 
				else 
				{
//					// fetch Universities from college table where state given		
					
					AICTEQuery<String> query = new AICTEQuery<>();
					
					JSONArray jsonArray= query.execute("FU", state);
					System.out.println(jsonArray.toString());	
					
//					int i = jsonArray.length();
//					universityList = new ArrayList<String>();
//					while (!(jsonArray.isNull(i)))
//					{
//						// System.out.println(i);
//						universityList.add(jsonArray.getJSONObject(i).getString("university_name"));
//						i++;
//					}
//					
//					str = new Gson().toJson(universityList);
//					System.out.println(str + "\n" + i);
//					
					errorCode = 0;
					status = "SUCCESS";
					message = "Universities Fetched";

					data = jsonArray;
					
					replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
					
					
					//replyClient(response, new StringBuffer(1), new StringBuffer(str));
				}
				
				System.out.println("state "+ state + " university " + university);
			}
			else 
			{
				// return error code;
				
				errorCode = 1;
				status = "ERROR";
				message = "No State Found!";
				data = new JSONArray();
				
				
				replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
				
				//replyClient(response, new StringBuffer("-1"), new StringBuffer("No state found!"));
			}
			
			HttpGet.replyClient(response, replyJSON.toString());
			
		} catch(Exception e) {
			System.out.println("FetchCollege: Caught: " + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
