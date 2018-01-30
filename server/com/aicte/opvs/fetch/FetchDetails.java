package com.aicte.opvs.fetch;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
//import org.json.JSONException;
import org.json.JSONObject;

import com.aicte.opvs.databasemethods.AICTEQuery;
import com.aicte.opvs.details.IndividualDetails;
import com.aicte.opvs.helpers.HttpPost;
import com.aicte.opvs.helpers.JSONReply;
import com.google.gson.Gson;

/**
 * Servlet implementation class FetchDetails
 */
@WebServlet("/FetchDetails")
public class FetchDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static StringBuffer strBuff;
	static final String COOKIES_HEADER = "Set-Cookie";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	 * 	private static JSONObject merge(JSONObject... jsonObjects) throws JSONException {
	 *
	 * 		JSONObject jsonObject = new JSONObject();
	 *
	 * 		for(JSONObject temp : jsonObjects){
	 *			Iterator<String> keys = temp.keys();
	 *	    	while(keys.hasNext()){
	 *	    		String key = keys.next();
	 *	        	jsonObject.put(key, temp.get(key));
	 *	    	}
	 *
	 * 		}
	 * 		return jsonObject;
	 * 	}
	 */
	
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

		// receive Login ID from user and category 
		strBuff = HttpPost.listenClient(request);
		//String category = request.getHeader("Category");
		String str = new String("(FetchDetails doPost)failed");

		
		System.out.println();
		System.out.println("FetchDetails servlet");
		System.out.println(strBuff);
		// fetch details from respective databases according to category


		JSONObject temp = new JSONObject(strBuff.toString());
		
		String category = temp.getString("role");
		System.out.println("category:" + category);
//		Enumeration<String> headerNames = request.getHeaderNames();
//
//		while (headerNames.hasMoreElements()) {
//
//			String headerName = headerNames.nextElement();
//			System.out.println(headerName);
//			System.out.println("\n");
//
//			Enumeration<String> headers = request.getHeaders(headerName);
//			while (headers.hasMoreElements()) {
//				String headerValue = headers.nextElement();
//				System.out.println("\t" + headerValue);
//				System.out.println("\n");
//			}
//
//		}


		int errorCode = -1;
		String status = "ERROR";
		String message = "Unhandled Exception";
		JSONArray data = new JSONArray();
		
		JSONObject replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
		
//		//Map<String, List<String>> headerFields = request.getHeaders(COOKIES_HEADER);
//		
//        List<String> cookiesHeader = request.getHeaders(COOKIES_HEADER);
//        
//        if (cookiesHeader != null) {
//            for (String cookie : cookiesHeader) {
//                cookieStore.add(null, HttpCookie.parse(cookie).get(0));
//            }               
//        }
		//Cookie[] cookies = request.getH;
        
//		Cookie detailCookie = null;
//    	Cookie[] cookies = request.getCookies();
//    	System.out.println("Cookie: Hooray");
//    	if(cookies != null){
//    		System.out.println("Hooray");
//	    	for(Cookie cookie : cookies){
//	    		System.out.println("Hooray");
//	    		if(cookie.getName().equals("username")){
//	    			System.out.println("Hooray");
//	    			detailCookie = cookie;
//	    			break;
//	    		}
//	    	}
//    	}

		StringBuffer errCode=new StringBuffer("1");
		
		switch (category)
		{
		case "STUDENT":
		{
			IndividualDetails individualDetails = new Gson().fromJson(strBuff.toString(), IndividualDetails.class);
			//****************************************************************** Fetch from database**************************************			
			// Fetch Login Details from database Student Login
			try{
				AICTEQuery<IndividualDetails> query1 = new AICTEQuery<>();
				
//				JSONArray jsonArray1 = query1.execute("FSA", individualDetails);
//				System.out.println("jsonArray1: " + jsonArray1.toString());
//				
//				if (!(jsonArray1.isNull(0))) {
//					
//					System.out.println("Hooray!");
//					
//					errorCode = 0;
//					status = "SUCCESS";
//					message = "Success";
//					data = new JSONArray();
//					data.put(jsonArray1.get(0));
//				} else {
//					errorCode = 1;
//					status = "ERROR";
//					message = "studenterror not found in student";
//					data = new JSONArray();
//				}
//				
//			
				JSONArray jsonArray1 = query1.execute("FSL", individualDetails);
				System.out.println("jsonArray1: " + jsonArray1.toString());
				
				if (!(jsonArray1.isNull(0))) {
//					str = new Gson().toJson(individualDetails);
//					JSONObject object1 = new JSONObject(str);
//					object1 = merge(object1, jsonArray1.getJSONObject(0));
//										
//					individualDetails.setStudentLoginDetails(object1);
					
//					individualDetails.setStudentLoginDetails(object1.getInt("sno"), object1.getString("student_id"), object1.getString("university_name"), object1.getString("college_name"),
//							object1.getString("full_name")	, object1.getString("email_id"), object1.getString("login_id"), object1.getString("password_hash"), object1.getString("aadhaar_status"), object1.getString("bank_status"), object1.getString("pan_status"));
//					
//					individualDetails = new Gson().fromJson(str, IndividualDetails.class);
					
					individualDetails.setStudentLoginDetails(jsonArray1.getJSONObject(0));
					
					AICTEQuery<IndividualDetails> query2 = new AICTEQuery<>();
					JSONArray jsonArray2 = query2.execute("FSC", individualDetails);
					System.out.println("jsonArray2: " + jsonArray2.toString());
					
					if (!(jsonArray2.isNull(0))) {
						
						JSONObject obj = jsonArray2.getJSONObject(0);
						
//						individualDetails.setCollegeDetails(obj);
						
						individualDetails.setStateName(obj.getString("state_name"));
						individualDetails.setDistrictName(obj.getString("district_name"));
						
						System.out.println(new Gson().toJson(individualDetails));

						AICTEQuery<IndividualDetails> query3 = new AICTEQuery<>();
						
						JSONArray jsonArray3 = query3.execute("FSA", individualDetails);
						System.out.println("jsonArray3: " + jsonArray3.toString());
	
						if (!(jsonArray3.isNull(0))) {
							
//							str = new Gson().toJson(individualDetails);
//							
//							JSONObject object2 = new JSONObject(str);
//							object2 = merge(object2, jsonArray3.getJSONObject(0));
//					
//							System.out.println("object2: "+ object2.toString());
							
//							individualDetails.setIndividualDetails(object2);
							
							System.out.println("Hooray!");
													
//							individualDetails.setIndividualPersonalDetails(jsonArray3.getJSONObject(0));
//							individualDetails.setIndividualStudentProfessionalDetails(jsonArray3.getJSONObject(0));
//							individualDetails.setIndividualBankDetails(jsonArray3.getJSONObject(0));
												
							errCode = new StringBuffer("0");
							errorCode = 0;
							status = "SUCCESS";
							message = "Success";
							data = new JSONArray();
							data.put(jsonArray3.get(0));
						}
						else {
							errCode = new StringBuffer("1");
							System.out.println("studenterror not found in student"+errCode);
							errorCode = 1;
							status = "ERROR";
							message = "studenterror not found in student";
							data = new JSONArray();
						}
					}
					else {

						errCode = new StringBuffer("1");
						System.out.println("studenterror not found in college"+errCode);
						errorCode = 1;
						status = "ERROR";
						message = "studenterror not found in student";
						data = new JSONArray();
					}
				}
				else{
					errCode = new StringBuffer("1");
					System.out.println("studenterror"+errCode);
					errorCode = 1;
					status = "ERROR";
					message = "studenterror not found in student";
					data = new JSONArray();
				}		
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		break;
	}
			
			
		case "FACULTY":
		{
//			IndividualDetails individualDetails = new Gson().fromJson(strBuff.toString(), IndividualDetails.class);
//			//****************************************************************** Fetch from database**************************************			
//			// Fetch Login Details from database Student Login
//			try{
//				AICTEQuery<IndividualDetails> query1 = new AICTEQuery<>();
//				
//				JSONArray jsonArray1 = query1.execute("FFA", individualDetails);
//				System.out.println("jsonArray1: " + jsonArray1.toString());
//				
//				if (!(jsonArray1.isNull(0))) {
//					
//					System.out.println("Hooray!");
//					
//					errorCode = 0;
//					status = "SUCCESS";
//					message = "Success";
//					data = new JSONArray();
//					data.put(jsonArray1.get(0));
//				} else {
//					errorCode = 1;
//					status = "ERROR";
//					message = "facultyerror not found in faculty";
//					data = new JSONArray();
//				}
//				
//			} catch(Exception e) {
//				System.out.println(e);
//			}
//			
//			break;
//		}
			IndividualDetails individualDetails = new Gson().fromJson(strBuff.toString(), IndividualDetails.class);
			//****************************************************************** Fetch from database**************************************			
			// Fetch Login Details from database Student Login
			try {
				AICTEQuery<IndividualDetails> query1 = new AICTEQuery<>();
				
				JSONArray jsonArray1 = query1.execute("FFL", individualDetails);
				System.out.println("jsonArray1: " + jsonArray1.toString());
				
				if (!(jsonArray1.isNull(0))) {
					
					individualDetails.setFacultyLoginDetails(jsonArray1.getJSONObject(0));
					
					AICTEQuery<IndividualDetails> query2 = new AICTEQuery<>();
					JSONArray jsonArray2 = query2.execute("FFC", individualDetails);
					System.out.println("jsonArray2: " + jsonArray2.toString());
					
					if (!(jsonArray2.isNull(0))) {
						
						JSONObject obj = jsonArray2.getJSONObject(0);
						
						individualDetails.setStateName(obj.getString("state_name"));
						individualDetails.setDistrictName(obj.getString("district_name"));
						
						System.out.println(new Gson().toJson(individualDetails));

						AICTEQuery<IndividualDetails> query3 = new AICTEQuery<>();
						
						JSONArray jsonArray3 = query3.execute("FFA", individualDetails);
						System.out.println("jsonArray3: " + jsonArray3.toString());
	
						if (!(jsonArray3.isNull(0))) {

							System.out.println("Hooray!");
													
//							individualDetails.setIndividualPersonalDetails(jsonArray3.getJSONObject(0));
//							individualDetails.setIndividualFacultyProfessionalDetails(jsonArray3.getJSONObject(0));
//							individualDetails.setIndividualBankDetails(jsonArray3.getJSONObject(0));
												
							errCode = new StringBuffer("0");
							errorCode = 0;
							status = "SUCCESS";
							message = "Success";
							data = new JSONArray();
							data.put(jsonArray3.get(0));
							
						}
						else{
							errCode = new StringBuffer("1");
							System.out.println("facultyerror not found in faculty"+errCode);
							errorCode = 1;
							status = "ERROR";
							message = "facultyerror not found in faculty";
							data = new JSONArray();
						}
					}
					else {

						errCode = new StringBuffer("1");
						System.out.println("facultyerror not found in college"+errCode);
						errorCode = 1;
						status = "ERROR";
						message = "facultyerror not found in college";
						data = new JSONArray();
					}
				}
				else{
					errCode = new StringBuffer("1");
					System.out.println("facultyerror"+errCode);
					errorCode = 1;
					status = "ERROR";
					message = "facultyerror";
					data = new JSONArray();
				}

				str = new Gson().toJson(individualDetails);
				System.out.println("Hooray!" + str);
				
			}catch(Exception e){System.out.println(e);}
			
			break;
		}
		
		default:
			
		}
		
		// response.addCookie(detailCookie);
		// convert result to StringBuffer
//		HttpPost.replyClient(response, errCode, new StringBuffer(str));
		
		replyJSON = JSONReply.generateReplyJSON(errorCode, status, data, message);
		System.out.println("r" + replyJSON.toString());
		HttpPost.replyClient(response, replyJSON.toString());
	}
}
