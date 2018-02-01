package com.aicte.opvs.control;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aicte.opvs.databasemethods.AICTEQuery;
import com.aicte.opvs.details.EmployeeDetails;
import com.aicte.opvs.details.IndividualDetails;
//import com.aicte.opvs.details.IndividualLoginDetails;
//import com.aicte.opvs.details.IndividualStatus;
import com.aicte.opvs.helpers.HttpPost;
import com.aicte.opvs.helpers.JSONReply;
import com.aicte.opvs.helpers.Password;
import com.google.gson.Gson;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static StringBuffer receivedStringBuffer;
	public static StringBuffer errCode;
	JSONObject receivedJSON, replyJSON;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("\nRegister servlet: \n");
		
		receivedStringBuffer = HttpPost.listenClient(request);
		
		receivedJSON = new JSONObject(receivedStringBuffer.toString());

		System.out.println(receivedJSON.toString());
		
		int errorCode = -1;
		String status = "ERROR";
		String message = "Unhandled Error";
		JSONArray data = new JSONArray();
		
		String role = receivedJSON.getString("role").toUpperCase();
		System.out.println("User Role: " + role);
		
		
		switch (role){
		
		// Register Employee
		case "EMPLOYEE":
			
			AICTEQuery<EmployeeDetails> employeeQuery = new AICTEQuery<>();
			
			EmployeeDetails receivedEmployeeDetails = new Gson().fromJson(receivedJSON.toString(), EmployeeDetails.class);
			
			try {
				
				// Check if employee already registered
				// RCE = Register Check Employee
				JSONArray jsonArray = employeeQuery.execute("RCE", receivedEmployeeDetails);
				System.out.println(jsonArray.toString());
				
				if ((jsonArray.isNull(0))) {
					// if not 
					// generate random pass
					/*receivedEmployeeDetails.setPassword(Password.generatePassword());
					receivedEmployeeDetails.setRole(role);*/
					
					// insert into employee register table
					// RIER = Register Insert Employee Register details
					@SuppressWarnings("unused")
					JSONArray jsonArray1;
					jsonArray1 = employeeQuery.execute("RIER", receivedEmployeeDetails);
					
					// insert into login credentials
					// RIER = Register Insert Employee Login details
					jsonArray1 = employeeQuery.execute("RIEL", receivedEmployeeDetails);
					
					// return 0 success data-emailid "Registration Successful!";
					errorCode = 0;
					status = "SUCCESS";
					data = new JSONArray();
					data.put(0, new JSONObject().put("emailId", receivedEmployeeDetails.getEmailId()));// emailID
					message = "Registration Successful!";
					
					
				} else {
					// return 1 error data-null "Already Registered!"
					
					errorCode = 1;
					status = "ERROR";
					data = new JSONArray();
					message = "Already Registered!";
				}
			} catch(Exception e) {
				System.out.println("Register: EMPLOYEE: Caught: " + e);
			}
			break;
			
		case "STUDENT":
			
			AICTEQuery<IndividualDetails> studentQuery = new AICTEQuery<>();
			
			IndividualDetails receivedStudentDetails = new Gson().fromJson(receivedJSON.toString(), IndividualDetails.class);

			try {
				
				// Check if student already registered
				// RCSR = Register Check Student Register
				/*JSONArray jsonArray = studentQuery.execute("RCSR", receivedStudentDetails);
				System.out.println(jsonArray.toString());
				
				if ((jsonArray.isNull(0))) {
					// if not 
					// check if student exists in master table
					// RCSM = Register Check Student Master
					JSONArray jsonArray1 = studentQuery.execute("RCSM", receivedStudentDetails);
					System.out.println(jsonArray.toString());
					
					if (!(jsonArray1.isNull(0))) {
						// if exists 
						// generate random pass
						receivedStudentDetails.setPassword(Password.generatePassword());*/
						
						// insert into student register table
						// RISR = Register Insert Student Register
						@SuppressWarnings("unused")
						JSONArray jsonArray2 = studentQuery.execute("RISR", receivedStudentDetails);
						System.out.println("AFTER risr");
						
						// insert into login credentials
						// RISL = Register Insert Student Login
						//jsonArray2 = studentQuery.execute("RISL", receivedStudentDetails);
						
						// TODO: copy student details from student_master to student_temp
						
						
						// insert into student_status
						// RISS = Register Insert Student Status
						jsonArray2 = studentQuery.execute("RISS", receivedStudentDetails);
						System.out.println("AFTER riSS");
						// return 0 success data-emailid "Registration Successful!";
						errorCode = 0;
						status = "SUCCESS";
						data = new JSONArray();
						data.put(0, new JSONObject().put("emailId", receivedStudentDetails.getEmailId()));// emailID
						
						message = "Registration Successful!";
					/*} else {
						// if doesn't exist
						// return 2 error data-null "Student not Enrolled! Please check your StudentID"
						errorCode = 2;
						status = "ERROR";
						data = new JSONArray();

						message = "Student not Enrolled! Please check your StudentID";
					}
				} else {
					// return 1 error data-null "Already Registered!"
					errorCode = 1;
					status = "ERROR";
					data = new JSONArray();
					message = "Already Registered!";
				}*/
			} catch(Exception e) {
				System.out.println("Register: STUDENT: Caught: " + e);
			}
			break;
		
		case "FACULTY":
			AICTEQuery<IndividualDetails> facultyQuery = new AICTEQuery<>();
			
			IndividualDetails receivedFacultyDetails = new Gson().fromJson(receivedJSON.toString(), IndividualDetails.class);

			try {
				
				// Check if faculty already registered
				// RCFR = Register Check Faculty Register
				JSONArray jsonArray = facultyQuery.execute("RCFR", receivedFacultyDetails);
				System.out.println(jsonArray.toString());
				
				if ((jsonArray.isNull(0))) {
					// if not 
					// check if faculty exists in master table
					// RCFM = Register Check Faculty Master
					//stop checking already existing details in register and master table
					/*JSONArray jsonArray1 = facultyQuery.execute("RCFM", receivedFacultyDetails);
					
					if (!(jsonArray1.isNull(0))) {
						// if exists 
						// generate random pass*/
						receivedFacultyDetails.setPassword(Password.generatePassword());
						
						// insert into faculty register table
						// RIFR = Register Insert Faculty Register
						@SuppressWarnings("unused")
						JSONArray jsonArray2 = facultyQuery.execute("RIFR", receivedFacultyDetails);
						
						// insert into login credentials
						// RIFL = Register Insert Faculty Login
						//jsonArray2 = facultyQuery.execute("RIFL", receivedStudentDetails);
						
						// TODO: copy student details from faculty_master to faculty_temp
						
						
						// insert into faculty_status
						// RIFS = Register Insert Faculty Status
						jsonArray = facultyQuery.execute("RIFS", receivedFacultyDetails);
						
						// return 0 success data-emailid "Registration Successful!";
						errorCode = 0;
						status = "SUCCESS";
						data = new JSONArray();
						data.put(0, new JSONObject().put("emailId", receivedFacultyDetails.getEmailId()));// emailID
						
						message = "Registration Successful!";
					} /*else {
						// if doesn't exist
						// return 2 error data-null "Faculty not Enrolled! Please check your FacultyID"
						errorCode = 2;
						status = "ERROR";
						data = new JSONArray();
						data.put(0, new JSONObject().put("emailId", receivedFacultyDetails.getEmailId()));// emailID
						message = "Faculty not Enrolled! Please check your FacultyID";
					}
				} else {
					// return 1 error data-null "Already Registered!"
					errorCode = 1;
					status = "ERROR";
					
					data = new JSONArray();
					message = "Already Registered!";
				}*/
			} catch(Exception e) {
				System.out.println("Register: FACULTY: Caught: " + e);
			}
			break;
			
		case "COLLEGE":
			break;
		
		case "UNIVERSITY":
			break;
		
		default:
			break;
		}
		
		replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
		
		HttpPost.replyClient(response, replyJSON.toString());
	}

}
