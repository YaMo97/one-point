package com.aicte.opvs.databasemethods;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class SQLQuery {
	
	public static final Map <String, String> SQL;
	static {
		final Map <String, String> sql = new HashMap<>();
		
		// LE = Login Employee
		sql.put("L", "select * from CREDENTIALS where email_id = ?");
		// LE = Login Employee
		//sql.put("LS", "select password_hash from STUDENT_LOGIN where login_id = ?");
		// LE = Login Employee
		//sql.put("LF", "select password_hash from FACULTY_LOGIN where login_id = ?");
		
		// LE = Verify AADHAR
		sql.put("VA", "select * from UID where aadhaar_number = ?");
		
		// LE = Login Employee
		sql.put("FU", "SELECT DISTINCT university_name from COLLEGE where state_name = ? ");
		// LE = Login Employee
		sql.put("FC", "SELECT DISTINCT college_name from COLLEGE where university_name = ? ");
		// sql.put("FS", "select * from student A, student_login B , college C where A.university_name = ? and A.college_name = ? and A.student_id = (select student_id from student_login where login_id = ?) and A.student_id = B.student_id and A.university_name = C.university_name and A.college_name = C.college_name");
		
		// FSR = FETCH STUDENT REGISTER
		sql.put("FSL", "select * from STUDENT_REGISTER where email_id = ?");
		// FSC = FETCH STATE FROM COLLEGE
		sql.put("FSC", "select state_name, district_name from COLLEGE where university_name = ? and college_name = ?");
		// FSA = FETCH STUDENT FROM STUDENT REGITER
		sql.put("FSA", "select * from STUDENT_TEMP T, STUDENT_REGISTER R, STUDENT_STATUS S, COLLEGE C WHERE T.university_name like ? and T.college_name like ? and T.student_id like ? AND R.university_name=T.university_name and R.college_name=T.college_name and R.student_id = T.student_id and S.university_name=T.university_name and S.college_name=T.college_name and R.student_id = T.student_id and T.university_name=C.university_name and T.college_name=C.college_name; ");
		
		// FFL = FETCH FACULTY REGISTER
		sql.put("FFL", "select * from FACULTY_REGISTER where email_id = ?");
		// FFC = FETCH FACULTY college
		sql.put("FFC", "select state_name, district_name from COLLEGE where university_name = ? and college_name = ?");
		// FFA = FETCH FACULTY ALL
		sql.put("FFA", "select * from STUDENT_TEMP T, STUDENT_REGISTER R, STUDENT_STATUS S, COLLEGE C WHERE T.university_name like ? and T.college_name like ? and T.faculty_id like ? AND R.university_name=T.university_name and R.college_name=T.college_name and R.faculty_id = T.faculty_id and S.university_name=T.university_name and S.college_name=T.college_name and R.faculty_id = T.faculty_id and T.university_name=C.university_name and T.college_name=C.college_name; ");
		//FBE = Fetch Bulk Employee
		sql.put("FBE", "select * from EMPLOYEE_REGISTER where email_id = ?");
		// FBS = Fetch Bulk Students
		sql.put("FBS", "select * from STUDENT_TEMP T, STUDENT_REGISTER R, STUDENT_STATUS S, COLLEGE C WHERE C.state_name like ? and C.university_name like ? and C.college_name like ? AND R.university_name=C.university_name and R.college_name=C.college_name and S.university_name=C.university_name and S.college_name=C.college_name and T.university_name=C.university_name and T.college_name=C.college_name LIMIT ?, ?");
		// FBF = Fetch Bulk Faculty
		sql.put("FBF", "select * from FACULTY_TEMP T, FACULTY_REGISTER R, FACULTY_STATUS S, COLLEGE C WHERE C.state_name like ? and C.university_name like ? and C.college_name like ? AND R.university_name=C.university_name and R.college_name=C.college_name and S.university_name=C.university_name and S.college_name=C.college_name and T.university_name=C.university_name and T.college_name=C.college_name LIMIT ?, ?");
		// RIC = Register Insert Credentials
		sql.put("RIC", "Insert into CREDENTIALS (`email_id`,`password`,`role`) values(?,?,?) ");
		// RCE = Register Check Employee
		sql.put("RCE", "select * from Employee_Register where email_id = ?");
		// RIE = Register Insert Employee_register 
		sql.put("RIE", "INSERT INTO EMPLOYEE_REGISTER ( `full_name`, `mobile_number`, `email_id`, `date_of_birth`, `gender`, `marital_status`, `corr_address`, `perm_address`, `aadhaar_number`, `privilege`, `pan_number`) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		// RCSR = Register Check Student Register
		sql.put("RCSR", "select * from STUDENT_REGISTER where email_id = ?");
		// RCSM = Register Check Student Master
		sql.put("RCSM", "select * from Student_Master where student_id = ?");
		// RISS = Register Insert Student Status
		sql.put("RISS", "INSERT INTO STUDENT_STATUS ( `aadhaar_status`, `pan_status`, `bank_status`, `college_status`, `university_status`, `student_id`, `college_name`, `university_name`) VALUES(?,?,?,?,?,?,?,?)");
		// RCFR = Register Check Faculty Register
		sql.put("RCFR", "select * from FACULTY_REGISTER where email_id = ?");
		// RCFM = Register Check Faculty Master
		sql.put("RCFM", "select * from FACULTY_MASTER where faculty_id = ?");
		// RISR = Register INSERT STUDENT REGISTER
		sql.put("RISR", "INSERT INTO STUDENT_REGISTER(`STUDENT_ID`,`UNIVERSITY_NAME`,`COLLEGE_NAME`,`AADHAAR_NUMBER`,`FULL_NAME`,`EMAIL_ID`) VALUES(?,?,?,?,?,?)");
		//RIFR = Register INSERT FACULTY REGISTER
		sql.put("RIFR", "INSERT INTO FACULTY_REGISTER(`FACULTY_ID`,`UNIVERSITY_NAME`,`COLLEGE_NAME`,`AADHAAR_NUMBER`,`FULL_NAME`,`EMAIL_ID`) VALUES(?,?,?,?,?,?)");
		// RIFS = Register Insert Faculty Status
		sql.put("RISS", "INSERT INTO FACULTY_STATUS ( `aadhaar_status`, `pan_status`, `bank_status`, `college_status`, `university_status`, `faculty_id`, `college_name`, `university_name`) VALUES(?,?,?,?,?,?,?,?)");
		
		//GET EMPLOYEE DETAILS
		sql.put("GED","SELECT * FROM EMPLOYEE_REGISTER WHERE email_id = ?");
		
		//GET STUDENT DETAILS
		sql.put("GSD", "select * from Student_Master where email_id = ?");
		//GET FACULTY DETAILS
		sql.put("GFD", "select * from Faculty_Master where email_id = ?");
		//GET 
		
		
		SQL = Collections.unmodifiableMap(sql);
		
	}
}
