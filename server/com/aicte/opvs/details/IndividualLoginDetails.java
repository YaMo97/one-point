package com.aicte.opvs.details;

import org.json.JSONObject;

public class IndividualLoginDetails extends Credentials {

	protected int individualSno;
	protected String studentId;
	protected String facultyId;
	protected String fullName;
	//protected String emailId;
	protected String aadhaarNumber;

	/*protected String loginId;
    protected String passwordHash;
    protected String aadhaarStatus;
    protected String bankStatus;
    protected String panStatus;*/

	// Constructor
	public IndividualLoginDetails() {
		studentId = null;
		facultyId = null;
		fullName = null;
		emailId = null;
		aadhaarNumber = null;
		this.collegeName = null;
		this.universityName = null;
		/*    loginId = null;
	passwordHash=null;
	aadhaarStatus=null;
	bankStatus=null;
	panStatus=null;*/
	}	

	public int getIndividualSno() {
		return individualSno;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getFacultyId() {
		return facultyId;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmailId() {
		return emailId;
	}


	/*
    public String getLoginId() {
        return loginId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getAadhaarStatus() {
        return aadhaarStatus;
    }

    public String getBankStatus() {
        return bankStatus;
    }

    public String getPanStatus() {
        return panStatus;
    }*/


	public StringBuffer getLoginDetailsBuffer(String category) {

		StringBuffer strBuff = getCollegeDetailsBuffer();

		strBuff.append(category).append("; ");
		strBuff.append(fullName).append("; ");

		if (category.equals("Student"))
			strBuff.append(studentId).append("; ");
		else if (category.equals("Faculty"))
			strBuff.append(facultyId).append("; ");

		strBuff.append(emailId).append("; ");
		//strBuff.append(loginId).append("; ");

		return strBuff;
	}

	public void setIndividualSno(int x) {
		individualSno = x;
	}

	public void setStudentId(String x) {
		studentId = x;
	}

	public void setFacultyId(String x) {
		facultyId = x;
	}

	public void setFullName(String x) {
		fullName = x;
	}

	public void setEmailId(String x) {
		emailId = x;
	}
	/*
    public void setLoginId(String x) {
        loginId = x;
    }

    public void setPasswordHash(String x) {
        passwordHash = x;
    }

    public void setAadhaarStatus(String x) {
        aadhaarStatus = x;
    }

    public void setBankStatus(String x) {
        bankStatus = x;
    }

    public void setPanStatus(String x) {
        panStatus = x;
    }*/



	public void setStudentLoginDetails(int a, String b, String c, String d, String e, String f, String g, String h) {
		individualSno = a;
		studentId = b;
		universityName = c;
		collegeName=d;
		fullName = e;
		emailId = f;
		aadhaarNumber = g;
		password = h;
		/* loginId = g;
		passwordHash=h;
		aadhaarStatus=i;
		bankStatus=j;
		panStatus=k;*/
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public void setStudentLoginDetails(JSONObject obj) {
		System.out.println(obj.toString());
		System.out.println("Hooray!");
		//    	individualSno	= obj.getInt("sno"); 
		studentId		= obj.getString("student_id"); 
		universityName	= obj.getString("university_name"); 
		collegeName		= obj.getString("college_name");
		fullName		= obj.getString("full_name");
		emailId			= obj.getString("email_id"); 
		aadhaarNumber 	= obj.getString("aadhaar_number");
		this.collegeName  = obj.getString("college_name");
		this.universityName = obj.getString("university_name");

		/*loginId			= obj.getString("login_id"); 
    	passwordHash	= obj.getString("password_hash"); 
    	aadhaarStatus	= obj.getString("aadhaar_status"); 
    	bankStatus		= obj.getString("bank_status");
    	panStatus		= obj.getString("pan_status");*/
		System.out.println("Hooray!");
	}

	public void setFacultyLoginDetails(int a, String b, String c, String d, String e, String f,String g, String h) {

		individualSno = a;
		facultyId = b;
		universityName=c;
		collegeName=d;
		fullName = e;
		emailId = f;
		aadhaarNumber 	= g;
		password = h;
		/* loginId = g;
		passwordHash=h;
		aadhaarStatus=i;
		bankStatus=j;
		panStatus=k;*/
	}

	public void setFacultyLoginDetails(JSONObject obj) {

		this.setFacultyLoginDetails(obj.getInt("sno"), 
				obj.getString("faculty_id"), 
				obj.getString("university_name"), 
				obj.getString("college_name"),
				obj.getString("full_name")	, 
				obj.getString("emailId"), 
				obj.getString("aadhaar_number"), 
				obj.getString("password")
				);
	}


	public void setLoginDetailsBuffer(StringBuffer strBuff) {
		setCollegeDetailsBuffer(strBuff);
	}

}
