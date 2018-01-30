package com.aicte.opvs.control;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.JSONException;

import com.aicte.opvs.databasemethods.AICTEQuery;
import com.aicte.opvs.details.Credentials;
//import com.aicte.opvs.details.EmployeeDetails;
//import com.aicte.opvs.details.IndividualDetails;
import com.aicte.opvs.helpers.HttpPost;
import com.aicte.opvs.helpers.JSONReply;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


//	private static StringBuffer strBuff, replyBuff;
	private static StringBuffer receivedStringBuffer; // replyStringBuffer;
	JSONObject receivedJSON, replyJSON;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("\nLogin servlet: \n");

//		strBuff = HttpPost.listenClient(request);
//		System.out.println(strBuff);
		
		receivedStringBuffer = HttpPost.listenClient(request);
		
		receivedJSON = new JSONObject(receivedStringBuffer.toString());

		System.out.println(receivedJSON.toString());
		
		int errorCode = 1;
		String status = "ERROR";
		String message = "";
		String role = "";
		int objectId = -1;
		JSONArray data = new JSONArray();
		

		/*String role = receivedJSON.getString("role");
		System.out.println("User Role: " + role);*/
		
		Credentials receivedDetails = new Gson().fromJson(receivedJSON.toString(), Credentials.class);
		
		try {
			AICTEQuery<Credentials> query = new AICTEQuery<>();
			
			JSONArray jsonArray= query.execute("L", receivedDetails);
			System.out.println("jsonArray: " + jsonArray.toString());
			
			if (!(jsonArray.isNull(0))) {
				
			
				JSONObject dataJSON = jsonArray.getJSONObject(0);
				
				System.out.println(dataJSON.toString());
			
				switch(dataJSON.get("role").toString())
				{
				    case "EMPLOYEE" :
				    	System.out.println(dataJSON.get("password"));
				    	if (dataJSON.get("password").equals(receivedDetails.getPassword())) {
							// return true
							errorCode = 0;
							status = "SUCCESS";
							message = "Employee Login Successful!";
//							finalResponse=JSONReply.generateReplyJson(errorCode ,replyBuff,new JSONObject(),"logged in successfully");

							// TODO: Add information to replyJSON.data
							data = new JSONArray();
							data.put(receivedDetails);
							
							replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
							
							HttpSession session = request.getSession();
							session.setAttribute("username", receivedDetails.getEmailId());
							session.setAttribute("id", session.getId());
							
							Cookie cookie = new Cookie("username", receivedDetails.getEmailId());
							Cookie cookie2 = new Cookie("id", session.getId());
							
							response.addCookie(cookie);
							response.addCookie(cookie2);
						}
						else {
							// else return error code 1 (login/id password incorrect)
							errorCode = 1;
							status = "ERROR";
							message = "Login ID or Password is Incorrect!";
							data = new JSONArray();
							
							replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
						}
				    	break;
				    	
				    case "EMPLOYEE_ADMIN" :
				    	if (dataJSON.get("password").equals(receivedDetails.getPassword())) {
							// return true
							errorCode = 0;
							status = "SUCCESS";
							message = "Employee_ADMIN Login Successful!";
//							finalResponse=JSONReply.generateReplyJson(errorCode ,replyBuff,new JSONObject(),"logged in successfully");

							
							// TODO: Add information to replyJSON.data
							data = new JSONArray();
							data.put(receivedDetails);
							
							replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
							
							HttpSession session = request.getSession();
							session.setAttribute("username", receivedDetails.getEmailId());
							
						}
						else {
							// else return error code 1 (login/id password incorrect)
							errorCode = 1;
							status = "ERROR";
							message = "Login ID or Password is Incorrect!";
							data = new JSONArray();
							
							replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
						}
				    	break;
				    	
				    case "STUDENT" :
				    	System.out.println(dataJSON.get("password"));
				    	if (dataJSON.get("password").equals(receivedDetails.getPassword())) {
							// return true
				    		System.out.print("in student ");
							errorCode = 0;
							status = "SUCCESS";
							message = "STUDENT Login Successful!";
							role = "STUDENT";
							System.out.println( dataJSON.getInt("object_id"));
							objectId = dataJSON.getInt("object_id");
//							finalResponse=JSONReply.generateReplyJson(errorCode ,replyBuff,new JSONObject(),"logged in successfully");

							// TODO: Add information to replyJSON.data
							data = new JSONArray();
							
							replyJSON = JSONReply.generateReplyJSON(objectId, status, data, role);
							
							HttpSession session = request.getSession();
							session.setAttribute("username", receivedDetails.getEmailId());
							session.setAttribute("id", session.getId());
							
						}
						else {
							// else return error code 1 (login/id password incorrect)
							errorCode = 1;
							status = "ERROR";
							message = "Login ID or Password is Incorrect!";
							data = new JSONArray();
							
							replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
						}
				    	break;
				    	
				    case "FACULTY" :
				    	System.out.println(dataJSON.get("password"));
				    	if (dataJSON.get("password").equals(receivedDetails.getPassword())) {
							// return true
							errorCode = 0;
							status = "SUCCESS";
							message = "FACULTY Login Successful!";
							role = "FACULTY";
							System.out.print(dataJSON.getInt("object_id"));
							objectId = dataJSON.getInt("object_id");
							System.out.print("why");
//							finalResponse=JSONReply.generateReplyJson(errorCode ,replyBuff,new JSONObject(),"logged in successfully");

							// TODO: Add information to replyJSON.data
							data = new JSONArray();
							
							replyJSON = JSONReply.generateReplyJSON(objectId, status, data, role);
							
							HttpSession session = request.getSession();
							session.setAttribute("username", receivedDetails.getEmailId());
							session.setAttribute("id", session.getId());
						}
						else {
							// else return error code 1 (login/id password incorrect)
							errorCode = 1;
							status = "ERROR";
							message = "Login ID or Password is Incorrect!";
							data = new JSONArray();
							
							replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
						}
				    	break;
				default : System.out.println("UNKNOWN ROLE");
				}
			} else {
				errorCode = 1;
				status = "ERROR";
				message = "Login ID or Password is Incorrect!";
				data = new JSONArray();
				
				replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
			}
		}catch(Exception exception){
			System.out.println("Login Servlet :"+exception.toString());
		}
			
		/*switch (role){
		
		// Login Employee
		case "EMPLOYEE":	
			//Credentials receivedEmployeeDetails = new Gson().fromJson(receivedJSON.toString(), Credentials.class);
			
			// Fetch Login Details from database Employee Login
			try{
				AICTEQuery<Credentials> query = new AICTEQuery<>();
				
				JSONArray jsonArray= query.execute("L", receivedEmployeeDetails);
				//System.out.println(jsonArray.toString());
				
				if (!(jsonArray.isNull(0))) {
					// Compare receivedPasswordHash with passwordHash(fetched from database)
					
					JSONObject employeeJSON = jsonArray.getJSONObject(0); 
					System.out.println(employeeJSON.get("password_hash"));
					
					if (employeeJSON.get("password_hash").equals(receivedEmployeeDetails.getPasswordHash())) {
						// return true
						errorCode = 0;
						status = "SUCCESS";
						message = "Login Successful!";
//						finalResponse=JSONReply.generateReplyJson(errorCode ,replyBuff,new JSONObject(),"logged in successfully");

						replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
						
						HttpSession session = request.getSession();
						session.setAttribute("username", receivedEmployeeDetails.getEmailId());
						
					}
					else {
						// else return error code 1 (login/id password incorrect)
						errorCode = 1;
						status = "ERROR";
						message = "Login ID or Password is Incorrect!";
						
						replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
					}
				}
				else {
					// Employee Doesn't Exist
					errorCode = 2;
					status = "ERROR";
					message = "Employee Doesn't Exist!";
					
					
					replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
				}
				
			} catch(Exception e) {
				System.out.println(e);
			}
			break;

			// Login Student
		case "Student": {

			IndividualDetails individualDetails = new Gson().fromJson(strBuff.toString(), IndividualDetails.class);
			//****************************************************************** Fetch from database**************************************			
			// Fetch Login Details from database Student Login
			
			try {
					AICTEQuery<IndividualDetails> query = new AICTEQuery<>();
					
					JSONArray jsonArray= query.execute("LS", individualDetails);
					System.out.println(jsonArray.toString());
					
					if (!(jsonArray.isNull(0))) {
						// Compare receivedPasswordHash with passwordHash(fetched from database)
						
						JSONObject individualJSON = jsonArray.getJSONObject(0); 
						System.out.println(individualJSON.get("password_hash"));
						
						if (individualJSON.get("password_hash").equals(individualDetails.getPasswordHash())) {
							// return true
							errorCode = 0;
							replyBuff = new StringBuffer("success");
//							finalResponse=JSONReply.generateReplyJson(errorCode,replyBuff,new JSONObject(),"logged in successfully");

							Cookie loginCookie = new Cookie("username", individualDetails.getLoginId());
							//setting cookie to expiry in 30 mins
							loginCookie.setMaxAge(30*60);
							response.addCookie(loginCookie);
						}
					else {
						// else return error code 1 (login/id password incorrect)
						errorCode = 1;
					}
				}
				else{
					errorCode = 2;
					System.out.println("errorCode: "+errorCode);
					
					replyBuff = new StringBuffer("Student Doesn't Exist!");
					System.out.println("replyBuff: " + replyBuff);
				}

			}catch(Exception e){System.out.println(e);}
			break;
		}

		// Login Faculty
		case "Faculty": {

			IndividualDetails individualDetails = new Gson().fromJson(strBuff.toString(), IndividualDetails.class);
			//****************************************************************** Fetch from database**************************************			
			// Fetch Login Details from database Faculty Login
			try {
				AICTEQuery<IndividualDetails> query = new AICTEQuery<>();
				
				JSONArray jsonArray= query.execute("LF", individualDetails);
				System.out.println(jsonArray.toString());
				
				if (!(jsonArray.isNull(0))) {
					// Compare receivedPasswordHash with passwordHash(fetched from database)
					
					JSONObject individualJSON = jsonArray.getJSONObject(0); 
					System.out.println(individualJSON.get("password_hash"));
					
					if (individualJSON.get("password_hash").equals(individualDetails.getPasswordHash())) {
						// return true
						errorCode = 0;
						replyBuff = new StringBuffer("success");
						
//						finalResponse=JSONReply.generateReplyJson(errorCode,replyBuff,new JSONObject(),"logged in successfully");
					    //individualJSON = new JSONObject("{\"errorCode\":"+errorCode + ",\"replyBuff\":"+replyBuff+",message:\"ok\"}");
						Cookie loginCookie = new Cookie("username", individualDetails.getLoginId());
						//setting cookie to expiry in 30 mins
						loginCookie.setMaxAge(30*60);
						response.addCookie(loginCookie);
					}
					else {
						// else return error code 1 (login/id password incorrect)
						errorCode = 1;
					}
				}
				else{
					errorCode = 2;
					System.out.println("errorCode: "+errorCode);
					
					replyBuff = new StringBuffer("Faculty Doesn't Exist!");
					System.out.println("replyBuff: " + replyBuff);
				}

			}catch(Exception e){System.out.println(e);}

			break;
		}

		default:
			errorCode = 1;

		}*/
		
		
		//****************************************************************** Send the response**************************************					
		//reply the client

		HttpPost.replyClient(response, replyJSON.toString());
	}

}