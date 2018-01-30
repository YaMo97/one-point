package com.aicte.opvs.details;

import org.json.JSONObject;

public class EmployeeDetails extends Credentials{

	protected int employeeSno;
	protected String employeeId;
	protected String employeeName;
	//protected String passwordHash;

	// Constructor
	public EmployeeDetails() {
		super();

		this.employeeId = null;
		this.employeeName = null;
	}

	public EmployeeDetails(int employeeSno, String employeeId, String employeeName) {
		super();
		this.employeeSno = employeeSno;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}

	public int getEmployeeSno() {
		return employeeSno;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmployeeSno(int x) {
		employeeSno = x;
	}

	public void setEmployeeId(String x) {
		employeeId = x;
	}

	public void setEmployeeName(String x) {
		employeeName = x;
	}

	public void setEmailId(String x) {  
		this.emailId = x;
	}

	public void setEmployeeDetails(int a, String b, String c, String d, String e, String f) {

		employeeSno = a;
		employeeId = b;
		employeeName = c;
		emailId = d;
		this.password = f;
	}

	public void setEmployeeDetails(JSONObject obj) {
		employeeSno		= obj.getInt("sno");
		employeeId 		= obj.getString("employee_id"); 
		employeeName 	= obj.getString("employee_name");
		/*emailId 		= obj.getString("email_id"); 
		password     	= obj.getString("password");*/	
	}

}

