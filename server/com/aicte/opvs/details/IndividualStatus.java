package com.aicte.opvs.details;

import org.json.JSONException;
import org.json.JSONObject;

public class IndividualStatus extends IndividualLoginDetails {

	protected String aadhaarStatus;
	protected String bankStatus;
	protected String panStatus;
	protected String collegeStatus;
	protected String universityStatus;

	public IndividualStatus () {
		aadhaarStatus = null;
		bankStatus = null;
		panStatus = null;
		collegeStatus = null;
		universityStatus = null;
	}

	public String getAadhaarStatus() {
		return aadhaarStatus;
	}

	public void setAadhaarStatus(String aadhaarStatus) {
		this.aadhaarStatus = aadhaarStatus;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String getPanStatus() {
		return panStatus;
	}

	public void setPanStatus(String panStatus) {
		this.panStatus = panStatus;
	}

	public String getCollegeStatus() {
		return collegeStatus;
	}

	public void setCollegeStatus(String collegeStatus) {
		this.collegeStatus = collegeStatus;
	}

	public String getUniversityStatus() {
		return universityStatus;
	}

	public void setUniversityStatus(String universityStatus) {
		this.universityStatus = universityStatus;
	}

	public void setStudentStatusDetils(String aadhaar_status,
			String bank_status,
			String pan_status,
			String college_status,
			String university_status,
			String college_name,
			String university_name,
			String studentId)
	{
		this.aadhaarStatus = aadhaar_status;
		this.bankStatus = bank_status;
		this.panStatus = pan_status;
		this.collegeStatus = college_status;
		this.universityStatus = university_status;
		this.collegeName = college_name;
		this.universityName = university_name;
		this.studentId = studentId;
	}

	public void setStudentStatusDetails(JSONObject obj) {

		try {
			this.aadhaarStatus = obj.getString("aadhaar_status");
			this.bankStatus = obj.getString("bank_status");
			this.panStatus = obj.getString("pan_status");
			this.collegeStatus = obj.getString("college_status");
			this.universityStatus = obj.getString("university_status");
			this.collegeName = obj.getString("college_name");
			this.universityName = obj.getString("university_name");
			this.studentId = obj.getString("studentId");
		} catch (JSONException e) {
			System.out.println("setStudentStatusDetails: Caught: " + e);
		}
	}

}
