package com.aicte.opvs.details;

import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home-PC
 */
public class CollegeDetails {

	protected int collegeSno;
	protected String universityName;
	protected String collegeName;
	protected String collegeType;
	protected String stateName;
	protected String districtName;

	// Constructor
	public CollegeDetails() {
		collegeSno = -1;
		universityName = null;
		collegeName = null;
		collegeType = null;
		stateName = null;
		districtName = null;
	}

	public int getCollegeSno() {
		return collegeSno;
	}

	public String getUniversityName() {
		return universityName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public String getCollegeType() {
		return collegeType;
	}

	public String getStateName() {
		return stateName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public StringBuffer getCollegeDetailsBuffer() {

		StringBuffer strBuff = new StringBuffer();

		strBuff.append(universityName).append("; ");

		strBuff.append(collegeName).append("; ");
		strBuff.append(collegeType).append("; ");
		strBuff.append(stateName).append("; ");
		strBuff.append(districtName).append("; ");        

		return strBuff;
	}

	public void setCollegeSno(int x) {
		collegeSno = x;
	}

	public void setUniversityName(String x) {
		universityName = x;
	}

	public void setCollegeName(String x) {
		collegeName = x;
	}

	public void setCollegeType(String x) {
		collegeType = x;
	}

	public void setStateName(String x) {
		stateName = x;
	}

	public void setDistrictName(String x) {
		districtName = x;
	}

	public void setCollegeDetails(int a, String b, String c, String d, String e, String f) {
		collegeSno = a;
		universityName = b;
		collegeName = c;
		collegeType = d;
		stateName = e;
		districtName = f;
	}

	public void setCollegeDetails(JSONObject obj) {
		collegeSno 		= obj.getInt("sno");
		universityName 	= obj.getString("university_name"); 
		collegeName 	= obj.getString("college_name");
		collegeType 	= obj.getString("college_type"); 
		stateName 		= obj.getString("state_name"); 
		districtName 	= obj.getString("district_name");	
	}

	public void setCollegeDetailsBuffer(StringBuffer strBuff){

	}
}



