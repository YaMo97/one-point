package com.aicte.opvs.details;

import org.json.JSONObject;

public class Credentials extends CollegeDetails {

	protected String emailId;
	protected String password;
	protected String role;
	protected int objectId;

	public Credentials() {
		super();

		this.emailId = null;
		this.password = null;
		this.role = null;
	}

	public Credentials(String emailId, String password, String role, int objectId) {
		super();

		this.emailId = emailId;
		this.password = password;
		this.role = role;
		this.objectId = objectId;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public void setCredentials(JSONObject obj) {
		this.emailId = obj.getString("email_id");
		this.password = obj.getString("password");
		this.role = obj.getString("role");
		this.objectId = obj.getInt("object_id");
	}
}
