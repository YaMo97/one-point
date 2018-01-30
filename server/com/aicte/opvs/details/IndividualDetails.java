package com.aicte.opvs.details;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class IndividualDetails extends IndividualStatus {

	//protected String aadhaarNumber;
	protected String fatherName;
	protected String motherName;
	protected Date dateOfBirth;
	protected String gender;
	protected String maritalStatus;
	protected String nationality;
	protected String correspondenceAddress;
	protected String permanentAddress;
	protected String mobileNo;
	protected String telephoneNo;
	protected String course;
	protected String department;
	protected String branch;
	protected String designation;
	protected String startDate;
	protected String joinDate;
	protected String endDate;
	protected String specialArea;
	protected int currentYear;
	protected String appointmentType;
	protected String panNo;
	protected String bankName;
	protected String bankBranch;
	protected String accountNumber;
	protected String ifsc;
	protected String micr;

	// Constructor
	public IndividualDetails() {
		aadhaarNumber = null;
		fatherName = null;
		motherName = null;
		dateOfBirth = null;
		gender = null;
		maritalStatus = null;
		nationality = null;
		correspondenceAddress = null;
		permanentAddress = null;
		mobileNo = null;
		telephoneNo = null;
		course = null;
		department = null;
		branch = null;
		designation = null;
		startDate = null;
		joinDate = null;
		endDate = null;
		specialArea = null;
		appointmentType = null;
		panNo = null;
		bankName = null;
		bankBranch = null;
		accountNumber = null;
		ifsc = null;
		micr = null;
	}

	public StringBuffer getIndividualDetailsBuffer(String category) {

		StringBuffer strBuff = getLoginDetailsBuffer(category);

		strBuff.append(aadhaarNumber).append("; ");
		strBuff.append(fatherName).append("; ");     
		strBuff.append(motherName).append("; ");
		strBuff.append(dateOfBirth).append("; ");

		strBuff.append(gender).append("; ");
		strBuff.append(maritalStatus).append("; ");     
		strBuff.append(nationality).append("; ");
		strBuff.append(correspondenceAddress).append("; ");

		strBuff.append(permanentAddress).append("; ");
		strBuff.append(mobileNo).append("; ");     
		strBuff.append(telephoneNo).append("; ");


		strBuff.append(course).append("; ");
		strBuff.append(department).append("; ");
		strBuff.append(branch).append("; ");     
		strBuff.append(designation).append("; ");

		strBuff.append(startDate).append("; ");
		strBuff.append(joinDate).append("; ");
		strBuff.append(endDate).append("; ");
		strBuff.append(specialArea).append("; ");

		strBuff.append(currentYear).append("; ");
		strBuff.append(appointmentType).append("; ");


		strBuff.append(panNo).append("; ");
		strBuff.append(bankName).append("; ");
		strBuff.append(bankBranch).append("; ");
		strBuff.append(accountNumber).append("; ");

		strBuff.append(ifsc).append("; ");
		strBuff.append(micr).append("; ");


		return strBuff;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public String getDateOfBirth() {
		return null; //dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public String getCorrespondenceAddress() {
		return correspondenceAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public String getCourse() {
		return course;
	}

	public String getDepartment() {
		return department;
	}

	public String getBranch() {
		return branch;
	}

	public String getDesignation() {
		return designation;
	}

	public String getStartDate() {

		return null;//startDate;
	}

	public String getJoinDate() {
		return null; //joinDate;
	}

	public String getEndDate() {
		return null; //endDate;
	}

	public String getSpecialArea() {
		return specialArea;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public String getPanNo() {
		return panNo;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public String getMicr() {
		return micr;
	}

	public void setIndividualDetailsBuffer(StringBuffer strBuff){
		setLoginDetailsBuffer(strBuff);
	}

	public void setAadhaarNumber(String x) {
		aadhaarNumber = x;
	}

	public void setFatherName(String x) {
		fatherName = x;
	}

	public void setMotherName(String x) {
		motherName = x;
	}

	public void setDateOfBirth(Date x) {
		dateOfBirth = x;
	}

	public void setGender(String x) {
		gender = x;
	}

	public void setMaritalStatus(String x) {
		maritalStatus = x;
	}

	public void setNationality(String x) {
		nationality = x;
	}

	public void setCorrespondenceAddress(String x) {
		correspondenceAddress = x;
	}

	public void setPermanentAddress(String x) {
		permanentAddress = x;
	}

	public void setMobileNo(String x) {
		mobileNo = x;
	}

	public void setTelephoneNo(String x) {
		telephoneNo = x;
	}

	public void setCourse(String x) {
		course = x;
	}

	public void setDepartment(String x) {
		department = x;
	}

	public void setBranch(String x) {
		branch = x;
	}

	public void setDesignation(String x) {
		designation = x;
	}

	public void setStartDate(String x) {
		startDate = x;
	}

	public void setJoinDate(String x) {
		joinDate = x;
	}

	public void setEndDate(String x) {
		endDate = x;
	}

	public void setSpecialArea(String x) {
		specialArea = x;
	}

	public void setCurrentYear(int x) {
		currentYear = x;
	}

	public void setAppointmentType(String x) {
		appointmentType = x;
	}

	public void setPanNo(String x) {
		panNo = x;
	}

	public void setBankName(String x) {
		bankName = x;
	}

	public void setBankBranch(String x) {
		bankBranch = x;
	}

	public void setAccountNumber(String x) {
		accountNumber = x;
	}

	public void setIfsc(String x) {
		ifsc = x;
	}

	public void setMicr(String x) {
		micr = x;
	}
	public void setIndividualPersonalDetails(String a, String b, String c, Object d, String e, String f,String g, String h, String i, String j,String k) {
		aadhaarNumber = a;
		fatherName = b;
		motherName = c;
		//dateOfBirth = d;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(d.toString());
			dateOfBirth = sdf.parse(d.toString());
		} catch (Exception ex) { System.out.println("Personal Detils dateofbirth: Caught: " + ex); }
		gender = e;
		maritalStatus = f;
		nationality = g;
		correspondenceAddress = h;
		permanentAddress = i;
		mobileNo = j;
		telephoneNo = k;
	}

	public void setIndividualPersonalDetails(JSONObject obj) {
		this.setIndividualPersonalDetails(new Long(obj.getLong("aadhaar_number")).toString(), 
				obj.getString("father_name"), 
				obj.getString("mother_name"), 
				obj.get("date_of_birth"),
				obj.getString("gender")	, 
				obj.getString("marital_status"), 
				obj.getString("nationality"), 
				obj.getString("corr_address"), 
				obj.getString("perm_address"), 
				new Long(obj.getLong("mobile_number")).toString(), 
				new Long(obj.getLong("telephone_number")).toString()
				);
	}

	public void setIndividualStudentProfessionalDetails(String a, String b, Object c, Object d, int e) {
		course = a;
		branch = b;
		//startDate = c;
		//endDate = d;
		currentYear = e;
	}

	public void setIndividualStudentProfessionalDetails(JSONObject obj) {
		this.setIndividualStudentProfessionalDetails(obj.getString("course"), 
				obj.getString("branch"), 
				obj.get("start_date"), 
				obj.get("end_date"), 
				obj.getInt("current_year")
				);
	}

	public void setIndividualFacultyProfessionalDetails(String a, String b, String c, String d, String e) {
		department = a;
		designation = b;
		//joinDate = c;
		specialArea = d;
		appointmentType = e;
	}

	public void setIndividualFacultyProfessionalDetails(JSONObject obj) {
		this.setIndividualFacultyProfessionalDetails(obj.getString("department"), 
				obj.getString("designation"), 

				obj.getString("join_date"), 
				obj.getString("special_area"), 
				obj.getString("appointment_type")
				);
	}

	public void setIndividualBankDetails(String a, String b, String c, String d, String e, String f) {
		panNo = a;
		bankName = b;
		bankBranch = c;
		accountNumber = d;
		ifsc = e;
		micr = f;
	}

	public void setIndividualBankDetails(JSONObject obj) {
		this.setIndividualBankDetails(obj.getString("PAN"), 
				obj.getString("bank_name"), 
				obj.getString("bank_branch"), 
				new Long(obj.getLong("account_number")).toString(), 
				obj.getString("IFSC"),
				obj.getString("MICR")
				);
	}

	public void setIndividualDetails(JSONObject obj) {
		this.setStudentLoginDetails(obj);

		this.setIndividualPersonalDetails(obj);

		if (obj.has("student_id"))
			this.setIndividualStudentProfessionalDetails(obj);

		else if (obj.has("faculty_id"))
			this.setIndividualFacultyProfessionalDetails(obj);

		else 
			System.out.println("setIndividualDetails(): Student/faculty ID not found");

		this.setIndividualBankDetails(obj);

		this.setCollegeDetails(obj);
	}

}
