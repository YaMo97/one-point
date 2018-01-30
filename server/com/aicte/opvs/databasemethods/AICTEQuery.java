package com.aicte.opvs.databasemethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aicte.opvs.configuration.Configuration;
import com.aicte.opvs.details.*;

	
	
public class AICTEQuery<T> {
	
	final String MySQLDriver = Configuration.DRIVER;
	//final String URL = "jdbc:mysql://";
	final String URL = Configuration.URL;
	//final String HOST = "localhost";
	final String HOST = Configuration.HOST;
	final int PORT = Configuration.AICTE_PORT;
	final String USER = Configuration.USER;
	final String PASS = Configuration.PASS;
	final String DATABASE = Configuration.AICTE_DATABASE;
	final int LOCAL_DATABASE = Configuration.LOCAL_DATABASE;
	
	Connection con;
	
	public AICTEQuery() {
		try {
			
			switch (LOCAL_DATABASE) {
			case 1:
				// Local Database Exists
				
				// Register the Driver
				   Class.forName(MySQLDriver);
				  
				// Establish Connection
				   System.out.println(URL + HOST+":"+PORT+"/"+DATABASE+"?user=" + USER + "&password=" + PASS);
				   con = DriverManager.getConnection(URL + HOST + ":" + PORT + "/" + DATABASE + "?user=" + USER + "&password=" + PASS);
				break;
				
			case 0:
				// Local Database Does Not Exist
				// Using Online Database
				//Class.forName("org.sqlite.JDBC");
			    //con = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");

			    // initialise the tables if necessary
//			    this.createDatabase(conn);
			}
			
			} catch(Exception e) {
				System.out.println("AICTEQuery(): Caught: " + e);
			}		
	}
	
	public JSONArray execute(String opcode, T obj) 
		throws JSONException {
		
		JSONArray json = null;
		
		ResultSet rs = null;
		
		PreparedStatement preStmt = null;
		
		String queryString = new String();
		
		try {			
			switch (LOCAL_DATABASE) {
			case 1:
				// Local Database Exists ?
				
				
				preStmt = con.prepareStatement(SQLQuery.SQL.get(opcode));
				break;
				
			case 0:
				// Local Database Does Not Exist
				// Using Online Database
				queryString = SQLQuery.SQL.get(opcode);
				break;
			}
			
			switch (opcode.charAt(0)) {
			case 'L': 	// First
				// For Login Details
				//switch(opcode.charAt(1)) {
				///case 'E':	// Second
					// Employee
					
					switch (LOCAL_DATABASE) {
					case 1:
						// Local Database Exists
						
						preStmt.setString(1, ((Credentials) obj).getEmailId());
						
						rs=preStmt.executeQuery();
						
						json = ResultSetConverter.convert(rs);
					
						
						break;
						
					case 0:
						// Local Database Does Not Exist
						// Using Online Database
						queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((Credentials) obj).getEmailId() + "\'");
						
						json = WebSQL.execute(queryString);
						break;
					}
					break;
					
				//case 'S':	// Second
					// Student
					
					/*switch (LOCAL_DATABASE) {
					case 1:
						// Local Database Exists
						
						preStmt.setString(1, ((IndividualDetails) obj).getLoginId());
						
						rs=preStmt.executeQuery();
						
						json = ResultSetConverter.convert(rs);
						
						break;
						
					case 0:
						// Local Database Does Not Exist
						// Using Online Database
						queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getLoginId() + "\'");
						
						json = WebSQL.execute(queryString);
						break;
					}
					
					break;
					
				//case 'F':	// Second
					// Faculty
					
					switch (LOCAL_DATABASE) {
					case 1:
						// Local Database Exists
						
						preStmt.setString(1, ((IndividualDetails) obj).getLoginId());
						
						rs=preStmt.executeQuery();
						
						json = ResultSetConverter.convert(rs);
						
						break;
						
					case 0:
						// Local Database Does Not Exist
						// Using Online Database
						queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getLoginId() + "\'");
						
						json = WebSQL.execute(queryString);
						break;
					}
					break;
				
*/				
	
			case 'C':	// First
				// For College
				break;
				
			case 'V':	// First
				// For Verify
				
				switch(opcode.charAt(1)) {
				case 'A':
					// For Aadhaar
					switch (LOCAL_DATABASE) {
					case 1:
						// Local Database Exists
						
						preStmt.setString(1, ((IndividualDetails) obj).getAadhaarNumber());
						
						rs=preStmt.executeQuery();
						
						json = ResultSetConverter.convert(rs);
						
						break;
						
					case 0:
						// Local Database Does Not Exist
						// Using Online Database
						queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getAadhaarNumber() + "\'");
						
						json = WebSQL.execute(queryString);
						break;
					}
					break;
				
				default:
					System.out.println("Unhandled verification aadhaar opcode");
				}
				break;
				
				
			case 'F':	// First
				
				// For Fetching
				switch(opcode.charAt(1)) {
				case 'U':	// Second
					// University List
					
					switch (LOCAL_DATABASE) {
					case 1:
						// Local Database Exists
						
						preStmt.setString(1, ((String) obj));
						
						rs=preStmt.executeQuery();
						
						json = ResultSetConverter.convert(rs);
						
						break;
						
					case 0:
						// Local Database Does Not Exist
						// Using Online Database
						queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((String) obj) + "\'");
						
						json = WebSQL.execute(queryString);
						break;
					}
					break;
					
				case 'C':	// Second
					// College List
				
				/*default:
					System.out.println("Unhandled Login opcode");*/
					
					switch (LOCAL_DATABASE) {
					case 1:
						// Local Database Exists
						
						preStmt.setString(1, ((String) obj));
						
						rs=preStmt.executeQuery();
						
						json = ResultSetConverter.convert(rs);
						
						break;
						
					case 0:
						// Local Database Does Not Exist
						// Using Online Database
						queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((String) obj) + "\'");
						
						json = WebSQL.execute(queryString);
						break;
					}
					break;
					
				case 'S':	// Second
					// Student Details
					
				case 'F':	// Second
					// Faculty Details
					
					switch(opcode.charAt(2)) {
					case 'L':	// Third
						// Student Login Details
						switch (LOCAL_DATABASE) {
						case 1:
							// Local Database Exists
							preStmt.setString(1, ((IndividualDetails) obj).getEmailId());
							
							rs=preStmt.executeQuery();
							
							json = ResultSetConverter.convert(rs);
							
							break;
							
						case 0:
							// Local Database Does Not Exist
							// Using Online Database
							queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getEmailId() + "\'");
							
							json = WebSQL.execute(queryString);
							break;
						}
						
						break;
						
					case 'C':	// Third
						// College Details
						
						
						switch (LOCAL_DATABASE) {
						case 1:
							// Local Database Exists
							preStmt.setString(1, ((IndividualDetails) obj).getUniversityName());
							preStmt.setString(2, ((IndividualDetails) obj).getCollegeName());
							
							rs=preStmt.executeQuery();
							
							json = ResultSetConverter.convert(rs);
							
							break;
							
						case 0:
							// Local Database Does Not Exist
							// Using Online Database
							queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getUniversityName() + "\'");
							
							queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getCollegeName() + "\'");
							
							json = WebSQL.execute(queryString);
							break;
						}
						break;
						
					case 'A':	// Third
						// All Details
											
						switch (LOCAL_DATABASE) {
						case 1:
							// Local Database Exists
							preStmt.setString(1, ((IndividualDetails) obj).getUniversityName());
							preStmt.setString(2, ((IndividualDetails) obj).getCollegeName());
							
							if (opcode.charAt(1) == 'S')
								preStmt.setString(3, ((IndividualDetails) obj).getStudentId());
							else if (opcode.charAt(1) == 'F')
								preStmt.setString(3, ((IndividualDetails) obj).getFacultyId());
							
							rs=preStmt.executeQuery();
							
							System.out.println("Query: Hooray!");
							
							json = ResultSetConverter.convert(rs);
							break;
							
						case 0:
							// Local Database Does Not Exist
							// Using Online Database
							queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getUniversityName() + "\'");
							
							queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getCollegeName() + "\'");
							
							if (opcode.charAt(1) == 'S')
								queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getStudentId() + "\'");
							else if (opcode.charAt(1) == 'F')
								queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getFacultyId() + "\'");
							
							System.out.println("Query: " + queryString);
							json = WebSQL.execute(queryString);
							break;
						}
						break;
						
					default:	// Third
						System.out.println("Unhandled Third opcode");
						break;
					}
					break;
					
				// Bulk Details
				case 'B': //Second
					
					switch(opcode.charAt(2)) {
					case 'S':	// Third
						// Bulk Student Details
						
					case 'F':	// Third
						// Bulk Faculty Details
						switch (LOCAL_DATABASE) {
						case 1:
							// Local Database Exists
							if (!((JSONObject) obj).has("state") || ((JSONObject) obj).getString("state").equals("null"))
								preStmt.setString(1, "%");
							else 
								preStmt.setString(1, ((JSONObject) obj).getString("state"));
							
							if (!((JSONObject) obj).has("university") || ((JSONObject) obj).getString("university").equals("null"))
								preStmt.setString(2, "%");
							else 
								preStmt.setString(2, ((JSONObject) obj).getString("university"));
							
							if (!((JSONObject) obj).has("college") || ((JSONObject) obj).getString("college").equals("null"))
								preStmt.setString(3, "%");
							else 
								preStmt.setString(3, ((JSONObject) obj).getString("college"));
							
							preStmt.setInt(4, ((JSONObject) obj).getInt("offset"));
							preStmt.setInt(5, ((JSONObject) obj).getInt("rowCount"));
							
							rs=preStmt.executeQuery();
							
							json = ResultSetConverter.convert(rs);
							
							break;
							
						case 0:
							// Local Database Does Not Exist
							// Using Online Database
							if (!((JSONObject) obj).has("state") || ((JSONObject) obj).getString("state").equals("null")) 
								queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'%\'");
							else
								queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((JSONObject) obj).getString("state") + "\'");
							
							if (!((JSONObject) obj).has("university") || ((JSONObject) obj).getString("university").equals("null")) 
								queryString = queryString.replaceFirst("\\?", "\'%\'");
							else
								queryString = queryString.replaceFirst("\\?", "\'" + ((JSONObject) obj).getString("university") + "\'");
							
							if (!((JSONObject) obj).has("college") || ((JSONObject) obj).getString("state").equals("college")) 
								queryString = queryString.replaceFirst("\\?", "\'%\'");
							else
								queryString = queryString.replaceFirst("\\?", "\'" + ((JSONObject) obj).getString("college") + "\'");
							
							queryString = queryString.replaceFirst("\\?", ((JSONObject) obj).getInt("offset") + "");
							queryString = queryString.replaceFirst("\\?", ((JSONObject) obj).getInt("rowCount") + "");
							
							json = WebSQL.execute(queryString);
							break;
						}	
						break;
						
					case 'E':	// Third
						// Bulk Employee Details
						switch (LOCAL_DATABASE) {
						case 1:
							// Local Database Exists
							preStmt.setInt(1, ((JSONObject) obj).getInt("offset"));
							preStmt.setInt(2, ((JSONObject) obj).getInt("rowCount"));
							
							rs=preStmt.executeQuery();
							
							json = ResultSetConverter.convert(rs);
							
							break;
							
						case 0:
							// Local Database Does Not Exist
							// Using Online Database
							queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", ((JSONObject) obj).getInt("offset") + "");
							
							queryString = queryString.replaceFirst("\\?", ((JSONObject) obj).getInt("rowCount") + "");
							
							json = WebSQL.execute(queryString);
							break;
						}	
						break;
						
					default:	// Third
						System.out.println("Unhandled Third opcode");
						break;
					}
					break;
					
				default:	// Second
					System.out.println("Unhandled Second opcode");
					break;
				}
				break;
			//Register Details
			case 'R' :	switch(opcode.charAt(1)) {
								
							//Register Insert Details
							case 'I' :	switch(opcode.charAt(2)) {
										
										//Register Insert Employee Details
										case 'E' : 	switch(LOCAL_DATABASE) {
													case 1 :
														// Local Database Exists
														preStmt.setString(1, ((EmployeeDetails) obj).getEmployeeName());
														/*preStmt.setString(1, ((EmployeeDetails) obj).ge);
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());*/
														
														rs=preStmt.executeQuery();
														
														json = ResultSetConverter.convert(rs);
														break;
													case 0 :
														// Local Database Does Not Exist
														// Using Online Database
														
														break;
												}
											break;
											
										//Register Insert student Details
										case 'S' :		switch(opcode.charAt(3)) {
										
													//Register Insert into student status Details
													case 'S' :		switch(LOCAL_DATABASE) {
													
												
															case 1 :
																// Local Database Exists
																preStmt.setString(1, ((IndividualDetails) obj).getAadhaarStatus());
																preStmt.setString(2, ((IndividualDetails) obj).getPanStatus());
																preStmt.setString(3, ((IndividualDetails) obj).getBankStatus());
																preStmt.setString(4, ((IndividualDetails) obj).getCollegeStatus());
																preStmt.setString(5, ((IndividualDetails) obj).getUniversityStatus());
																preStmt.setString(6, ((IndividualDetails) obj).getStudentId());
																preStmt.setString(7, ((IndividualDetails) obj).getCollegeName());
																preStmt.setString(8, ((IndividualDetails) obj).getUniversityName());
																
																rs=preStmt.executeQuery();
																
																json = ResultSetConverter.convert(rs);
																
																break;
															case 0 :
																// Local Database Does Not Exist
																// Using Online Database
																queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getAadhaarStatus() + "\'");
																queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getPanStatus() + "\'");
																queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getBankStatus() + "\'");
																queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getCollegeStatus() + "\'");
																queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getUniversityStatus() + "\'");
																queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getStudentId() + "\'");
																queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getCollegeName() + "\'");
																queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getUniversityName() + "\'");
																
																json = WebSQL.execute(queryString);
															
																break;
															}
														break;
												
													//Register Insert into student register Details
													case 'R' :		switch(LOCAL_DATABASE) {
													
													
																case 1 :
																	// Local Database Exists
																	preStmt.setString(1, ((IndividualDetails) obj).getStudentId());
																	preStmt.setString(2, ((IndividualDetails) obj).getUniversityName());
																	preStmt.setString(3, ((IndividualDetails) obj).getCollegeName());
																	preStmt.setString(4, ((IndividualDetails) obj).getAadhaarNumber());
																	preStmt.setString(5, ((IndividualDetails) obj).getFullName());
																	preStmt.setString(6, ((IndividualDetails) obj).getEmailId());

																	rs=preStmt.executeQuery();
																	
																	json = ResultSetConverter.convert(rs);
																	
																	break;
																case 0 :
																	// Local Database Does Not Exist
																	// Using Online Database
																	queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getStudentId() + "\'");
																	queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getUniversityName() + "\'");
																	queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getCollegeName() + "\'");
																	queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getAadhaarNumber() + "\'");
																	queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getFullName() + "\'");
																	queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getEmailId() + "\'");
																	
																	
																	json = WebSQL.execute(queryString);
																
																	
																	break;
															}
														break;
														
														default : 	System.out.println("unjhandled opcodde fourth");
															break;
										
												}
											break;
											
										//Register Insert Faculty Details	
										case 'F' :		switch(opcode.charAt(3)) {
										
													//Register Insert into faculty status Details
													case 'S' :		switch(LOCAL_DATABASE) {
																case 1 :
																	// Local Database Exists
																	preStmt.setString(1, ((IndividualDetails) obj).getAadhaarStatus());
																	preStmt.setString(2, ((IndividualDetails) obj).getPanStatus());
																	preStmt.setString(3, ((IndividualDetails) obj).getBankStatus());
																	preStmt.setString(4, ((IndividualDetails) obj).getCollegeStatus());
																	preStmt.setString(5, ((IndividualDetails) obj).getUniversityStatus());
																	preStmt.setString(6, ((IndividualDetails) obj).getFacultyId());
																	preStmt.setString(7, ((IndividualDetails) obj).getCollegeName());
																	preStmt.setString(8, ((IndividualDetails) obj).getUniversityName());
																	
																	rs=preStmt.executeQuery();
																	
																	json = ResultSetConverter.convert(rs);
																	
																	
																	break;
																case 0 :
																	// Local Database Does Not Exist
																	// Using Online Database
																	
																	
																	break;
															}
														break;
												
													//Register Insert into faculty register Details
													case 'R' :		switch(LOCAL_DATABASE) {
															case 1 :
																// Local Database Exists
																preStmt.setString(1, ((IndividualDetails) obj).getFacultyId());
																preStmt.setString(2, ((IndividualDetails) obj).getUniversityName());
																preStmt.setString(3, ((IndividualDetails) obj).getCollegeName());
																preStmt.setString(5, ((IndividualDetails) obj).getFullName());
																preStmt.setString(4, ((IndividualDetails) obj).getEmailId());

																rs=preStmt.executeQuery();
																
																json = ResultSetConverter.convert(rs);
																
																
																break;
															case 0 :
																// Local Database Does Not Exist
																// Using Online Database
																
																break;
															}
														break;
														
													default : 	System.out.println("unhandled opcodde fourth");
													break;
												}
											break;
											
										//Register Insert INTO CREDENTIALS Details	
										case 'C' :	switch(LOCAL_DATABASE) {
													case 1 :
														// Local Database Exists
														preStmt.setString(1, ((IndividualDetails) obj).getEmailId());
														preStmt.setString(3, ((IndividualDetails) obj).getRole());
//														preStmt.setString(4, ((IndividualDetails) obj).getEmailId());
														
														break;
													case 0 :
														// Local Database Does Not Exist
														// Using Online Database
														
														break;
													}
											break;
											
										default  :	System.out.print("Unhandled Third opcode");
											
							}
								break;
								
							//Register check Details
							case 'C' :	switch(opcode.charAt(2)) {
							
										// check details in employee register while registering
										case 'E' : 		switch(LOCAL_DATABASE) {
													case 1 :
														// Local Database Exists
														
														preStmt.setString(1, ((EmployeeDetails) obj).getEmailId());
														
														rs=preStmt.executeQuery();
														
														json = ResultSetConverter.convert(rs);
														
														break;
													case 0 :
														// Local Database Does Not Exist
														// Using Online Database
														
														break;
													}
											break;
											
										//check details in student while registering
										case 'S' :		switch(opcode.charAt(3)) {
											
													//check details in student register while registering
													case 'R' :		switch(LOCAL_DATABASE) {
															case 1 :
																// Local Database Exists
																
																preStmt.setString(1, ((IndividualDetails) obj).getEmailId());
																
																
																rs=preStmt.executeQuery();
																
																json = ResultSetConverter.convert(rs);
																break;
															case 0 :
																// Local Database Does Not Exist
																// Using Online Database
																System.out.println("after RCSR");
																
																queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getEmailId() + "\'");
																
																//queryString = queryString.replaceFirst("\\?", "\'" + ((IndividualLoginDetails) obj).getCollegeName() + "\'");
																
																json = WebSQL.execute(queryString);
																break;
															}
														break;
														
													///check details in student master while registering
													case 'M' :		switch(LOCAL_DATABASE) {
															case 1 :
																// Local Database Exists
																preStmt.setString(1, ((IndividualDetails) obj).getStudentId());
																
																rs=preStmt.executeQuery();
																
																json = ResultSetConverter.convert(rs);
																break;
															case 0 :
																// Local Database Does Not Exist
																// Using Online Database
																queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getStudentId() + "\'");
																
																json = WebSQL.execute(queryString);
																break;
															}
														break;
													default : 	System.out.println("unhandled opcodde fourth");
													break;
													}
											break;
											
										//check details in faculty while registering
										case 'F' :		switch(opcode.charAt(3)) {
										
													//check details in faculty register while registering
													case 'R' :		switch(LOCAL_DATABASE) {
															case 1 :
																// Local Database Exists
																preStmt.setString(1, ((IndividualDetails) obj).getEmailId());
																
																rs=preStmt.executeQuery();
																
																json = ResultSetConverter.convert(rs);
																
																break;
															case 0 :
																// Local Database Does Not Exist
																// Using Online Database
																
																queryString = SQLQuery.SQL.get(opcode).replaceFirst("\\?", "\'" + ((IndividualDetails) obj).getEmailId() + "\'");
																
																json = WebSQL.execute(queryString);
																
																break;
															}
														break;
														
													///check details in faculty master while registering
													case 'M' :		switch(LOCAL_DATABASE) {
															case 1 :
																// Local Database Exists
																preStmt.setString(1, ((IndividualDetails) obj).getFacultyId());
																
																rs=preStmt.executeQuery();
																
																json = ResultSetConverter.convert(rs);
																break;
															case 0 :
																// Local Database Does Not Exist
																// Using Online Database
																
																break;
															}
														break;
													default : 	System.out.println("unhandled opcodde fourth");
													break;
													}
											break;
											
										default  :	System.out.print("Unhandled Third opcode");
										break;
							}
								break;
							default : System.out.print("Unhandled Second opcode");
			break;
				}
			default:	// First
				System.out.println("Unhandled First opcode");
				break;
			}
			
		} catch(Exception e) {
			System.out.println("AICTEQuery: Caught: " + e);
		}
		
		return json;
	}
}