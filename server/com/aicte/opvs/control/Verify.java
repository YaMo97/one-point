package com.aicte.opvs.control;


import java.io.IOException;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

//import com.aicte.onepointverificationserver.databasemethods.AICTEQuery;
//import com.aicte.onepointverificationserver.databasemethods.DatabaseConnections;
import com.aicte.opvs.databasemethods.UIDAIQuery;
import com.aicte.opvs.details.IndividualDetails;
import com.aicte.opvs.helpers.HttpPost;
import com.aicte.opvs.helpers.JSONReply;
import com.google.gson.Gson;

/**
 * Servlet implementation class Verify
 */
@WebServlet("/Verify")
public class Verify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static StringBuffer strBuff;
//	private DatabaseConnections databaseConnections1;
//	//private DatabaseConnections databaseConnections,databaseConnections1;
//	//private PreparedStatement preparedStatement,preparedStatement1,preparedStatement2;
//	private PreparedStatement preparedStatement;
//	//private Statement statement;
//	// private Connection con, con1;
//	private Connection con1;
//	//private ResultSet rs,rs1,rs2;
//	private ResultSet rs;
//	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verify() {
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
		// databaseConnections=new DatabaseConnections(3307);
		// con=databaseConnections.getConnection();
//		databaseConnections1=new DatabaseConnections(3308);
//		con1=databaseConnections1.getConnection();
		// receive form submission
		strBuff = HttpPost.listenClient(request);
		
		JSONObject replyJSON = new JSONObject();
		int errorCode = 4;
		String status = "ERROR";
		String message = "Failed!";
		JSONArray data = new JSONArray();
		
		IndividualDetails individualDetails = new Gson().fromJson(strBuff.toString(), IndividualDetails.class);
		System.out.println("V: replyJSON: " + replyJSON.toString());
		
		// extract variables
		/*
		// Fetch all details from Database
		try{
			preparedStatement=con.prepareStatement("select * from STUDENT_LOGIN where login_id = ?");
			preparedStatement.setString(1, individualDetails.getLoginId());
			//statement=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=preparedStatement.executeQuery();
			if(rs.next())
			{
				individualDetails.setStudentLoginDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5)	, rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11));
				System.out.println(individualDetails.getUniversityName());
				
				preparedStatement1=con.prepareStatement("select * from COLLEGE where university_name = ? and college_name = ?");
				preparedStatement1.setString(1, individualDetails.getUniversityName());
				preparedStatement1.setString(2, individualDetails.getCollegeName());
				rs1=preparedStatement1.executeQuery();
				if (rs1.next()){
					individualDetails.setStateName(rs1.getString(1));
					individualDetails.setDistrictName(rs1.getString(2));
					preparedStatement2=con.prepareStatement("select * from STUDENT where university_name = ? and college_name = ? and student_id=?");
					preparedStatement2.setString(1, individualDetails.getUniversityName());
					preparedStatement2.setString(2, individualDetails.getCollegeName());
					preparedStatement2.setString(3, individualDetails.getStudentId());
					rs2=preparedStatement.executeQuery();
					if (rs2.next()){
						String json=new Gson().toJson(individualDetails);
						System.out.println("json"+json);
						String str=new Gson().toJson(individualDetails);
							
						errCode = new StringBuffer("0");
						errCode.append(";");
						errCode.append(str);
					}
					else{
						errCode = new StringBuffer("1");
						System.out.println("studenterror not found in student"+errCode);
					}
					//replyClient(response, new StringBuffer(str);
				}
				else {
				
					errCode = new StringBuffer("1");
					System.out.println("studenterror not found in college"+errCode);
				}
			}
			else{
				errCode = new StringBuffer("1");
				System.out.println("studenterror"+errCode);
			}
		
		}catch(Exception e){System.out.println(e);}
		*/
		try {
			UIDAIQuery<IndividualDetails> query = new UIDAIQuery<>();
			
			JSONArray jsonArray= query.execute("VA", individualDetails);
			System.out.println(jsonArray.toString());
			
			if (!(jsonArray.isNull(0))) {
				// Compare receivedPasswordHash with passwordHash(fetched from database)
				
				JSONObject individualJSON = jsonArray.getJSONObject(0); 
				System.out.println(individualJSON.get("full_name"));
						
				if (individualJSON.get("full_name").equals(individualDetails.getFullName())
					
					&& individualJSON.get("gender").equals(individualDetails.getGender())) {
					// return true
					
					errorCode = 0;
					status = "SUCCESS";
					message = "Verification Successful";
					data = new JSONArray();
					
					
					// update aadhaar status Category_login
					
					//preparedStatement2=con.prepareStatement("");
//					if (category.equals("Student")){
//						preparedStatement2.setString(1, "student");
//						preparedStatement2.setString(2, "student");
//					}	
//					if (category.equals("Faculty")) {
//						preparedStatement2.setString(1, "faculty");
//						preparedStatement2.setString(2, "faculty");
//					}
					
					// set aadhaar status to ACCEPTED
				}
				
				else {
					// else return error code 1 (full name not match incorrect)
					
					errorCode = 1;
					status = "ERROR";
					message = "Verification Failed!";
					data = new JSONArray();
				}
			}
			else{

				errorCode = 1;
				status = "ERROR";
				message = "Verification Failed";
				data = new JSONArray();
			}
			
		}catch(Exception e){System.out.println("V: Caught: " + e);}
		
		// Verify the details (Matching Strings) (Best approach is to use inbuilt data structures and to compare them using their inbuilt functions
		
		// for each detail/variable 4 error codes:
		// 0 : if matching
		// 1 : if not matchings
		// 2 : missing in database
		// 3 : not provided in form(only for non mandatory)
		
		
		replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
		
		HttpPost.replyClient(response, replyJSON.toString());
	}

}
