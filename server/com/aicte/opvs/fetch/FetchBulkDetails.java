package com.aicte.opvs.fetch;

import java.io.IOException;
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

/**
 * Servlet implementation class FetchBulkDetails
 */
@WebServlet("/FetchBulkDetails")
public class FetchBulkDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static JSONObject replyJSON;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBulkDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("\nFetchBulkDetails servlet: \n");
		
		// Authorize Cookie
		
		// Get Parameters
		JSONObject parameters = new JSONObject();
		
		parameters.put("role", 			request.getParameter("role"));
		parameters.put("state", 		request.getParameter("state"));
		parameters.put("university", 	request.getParameter("university"));
		parameters.put("college", 		request.getParameter("college"));
		parameters.put("course",		request.getParameter("course"));
		parameters.put("year", 			request.getParameter("year"));
		parameters.put("offset", 		request.getParameter("offset"));
		parameters.put("rowCount", 		request.getParameter("rowCount"));
		
		System.out.println("Parameters: " + parameters.toString());
		
		int errorCode = -1;
		String status = "ERROR";
		String message = "Unhandled Exception";
		JSONArray data = new JSONArray();
		
		replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
		
		
		JSONArray jsonArray = new JSONArray();
			
		// switch role
		
		if (parameters.has("role")) {
			
			if (parameters.has("offset")) {
				
				if (parameters.has("rowCount")) {
					
					if (parameters.has("state")) {
						
					
						switch (parameters.getString("role")) {
						case "EMPLOYEE":
							AICTEQuery<JSONObject> employeeQuery = new AICTEQuery<>();
							
							jsonArray = employeeQuery.execute("FBE", parameters);
							
							if (jsonArray.isNull(0)) {
								message = "No Records Found!";
							} else {
								message = "Employee Details Fetched!";
							}

							replyJSON = JSONReply.generateReplyJSON(0, "SUCCESS", jsonArray, message);
							break;
							
						case "STUDENT":
							AICTEQuery<JSONObject> studentQuery = new AICTEQuery<>();
							
							jsonArray = studentQuery.execute("FBS", parameters);
							
							if (jsonArray.isNull(0)) {
								message = "No Records Found!";
							} else {
								message = "Student Details Fetched!";
							}
							
							replyJSON = JSONReply.generateReplyJSON(0, "SUCCESS", jsonArray, message);
							break;
							
						case "FACULTY":
							AICTEQuery<JSONObject> facultyQuery = new AICTEQuery<>();
							
							jsonArray = facultyQuery.execute("FBF", parameters);

							if (jsonArray.isNull(0)) {
								message = "No Records Found!";
							} else {
								message = "Faculty Details Fetched!";
							}
							
							replyJSON = JSONReply.generateReplyJSON(0, "SUCCESS", jsonArray, message);
							break;
						
						default: 
							replyJSON = JSONReply.generateReplyJSON(5, "ERROR", new JSONArray(), "Unknown Role!");
						}
					} else {
						replyJSON = JSONReply.generateReplyJSON(4, "ERROR", new JSONArray(), "Input State!");
					}
				} else {
					replyJSON = JSONReply.generateReplyJSON(3, "ERROR", new JSONArray(), "Input Row Count!");
				}	
			} else {
				replyJSON = JSONReply.generateReplyJSON(2, "ERROR", new JSONArray(), "Input Offset!");
			}
			
		} else {
			replyJSON = JSONReply.generateReplyJSON(1, "ERROR", new JSONArray(), "Input Role!");
		}
		
		HttpGet.replyClient(response, replyJSON.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
