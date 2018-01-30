use aicte;
show tables;

#drop table STUDENT;
#drop table STUDENT_LOGIN;
#drop table FACULTY;
#drop table FACULTY_LOGIN;
#drop table COLLEGE;
#drop table EMPLOYEE_LOGIN;

create table COLLEGE (
	sno 				int auto_increment 	NOT NULL 	UNIQUE,
    university_name 	varchar(200) 	NOT NULL,
    college_name 		varchar(200) 	NOT NULL,
    college_type 		varchar(100) 	NOT NULL,
    state_name 			varchar(100) 	NOT NULL,
    district_name 		varchar(100) 	NOT NULL,
    UNIQUE(university_name, college_name),
    PRIMARY KEY(university_name, college_name)
);

create table STUDENT_LOGIN (
	sno					int	auto_increment	NOT NULL	UNIQUE,	
	student_id			varchar(20)		NOT NULL,
    university_name		varchar(200)	NOT NULL,
    college_name		varchar(200)	NOT NULL,
    full_name			varchar(100)	NOT NULL,
    email_id			varchar(100)	UNIQUE,
    login_id			varchar(64)		UNIQUE,
    password_hash		varchar(64),
    aadhaar_status 		varchar(15) 	NOT NULL 	DEFAULT 'PENDING',
    bank_status			varchar(15) 	NOT NULL 	DEFAULT 'PENDING',
    pan_status			varchar(15) 	NOT NULL 	DEFAULT 'PENDING',
    UNIQUE(university_name, college_name, student_id),
	PRIMARY KEY(university_name, college_name, student_id),
    FOREIGN KEY(university_name, college_name) references COLLEGE(university_name, college_name)
);

create table STUDENT (
	sno 				int auto_increment 	NOT NULL 	UNIQUE,	
	student_id 			varchar(20) 	NOT NULL,
    university_name 	varchar(200) 	NOT NULL,
    college_name 		varchar(200) 	NOT NULL,
    full_name 			varchar(100) 	NOT NULL,
    aadhaar_number 		int(16) 		UNIQUE,
    father_name 		varchar(100),
    mother_name 		varchar(100),
    date_of_birth 		date 			NOT NULL,
    gender 				varchar(12) 	NOT NULL,
    marital_status 	varchar(10) 	NOT NULL,
    nationality 		varchar(55) 	NOT NULL,
    corr_address 		varchar(300) 	NOT NULL,
    perm_address 		varchar(300) 	NOT NULL,
    mobile_number 		int(10),
    telephone_number 	int(12),
    course 				varchar(100) 	NOT NULL,
    branch 				varchar(100) 	NOT NULL,
    start_date 			date 			NOT NULL,
    end_date 			date 			NOT NULL,
    current_year 		int 			NOT NULL,
    PAN 				varchar(10) 	UNIQUE,
    bank_name 			varchar(100),
    bank_branch 		varchar(100),
    account_number 		int(18),
    IFSC 				varchar(12),
	MICR 				varchar(9),
    UNIQUE(university_name, college_name, student_id),
	PRIMARY KEY(university_name, college_name, student_id),
    FOREIGN KEY(university_name, college_name, student_id) references STUDENT_LOGIN(university_name, college_name, student_id),
    FOREIGN KEY(university_name, college_name) references COLLEGE(university_name, college_name)
);

create table FACULTY_LOGIN (
	sno					int	auto_increment	NOT NULL	UNIQUE,	
	faculty_id			varchar(20)	NOT NULL	UNIQUE,
    university_name		varchar(200)	NOT NULL,
    college_name		varchar(200)	NOT NULL,
    full_name			varchar(100)	NOT NULL,
    email_id			varchar(100)	UNIQUE,
    login_id			varchar(64)	UNIQUE,
    password_hash		varchar(64),
    aadhaar_status 		varchar(15) 	NOT NULL 	DEFAULT 'PENDING',
    bank_status			varchar(15) 	NOT NULL 	DEFAULT 'PENDING',
    pan_status			varchar(15) 	NOT NULL 	DEFAULT 'PENDING',
	PRIMARY KEY(faculty_id),
    FOREIGN KEY(university_name, college_name) references COLLEGE(university_name, college_name)
);

create table FACULTY (
	sno					int	auto_increment	NOT NULL	UNIQUE,	
	faculty_id			varchar(20)	NOT NULL		UNIQUE,
    university_name		varchar(200)	NOT NULL,
    college_name		varchar(200)	NOT NULL,
    full_name			varchar(100)	NOT NULL,
    aadhaar_number		int(16)			UNIQUE,
    father_name			varchar(100),
    mother_name			varchar(100),
    date_of_birth		date			NOT NULL,
    gender				varchar(12)	NOT NULL,
    marital_status		varchar(10)	NOT NULL,
    nationality			varchar(55)	NOT NULL,
    corr_address		varchar(300)	NOT NULL,
    perm_address		varchar(300)	NOT NULL,
    mobile_number		int(10),
    telephone_number	int(12),
    department			varchar(100)	NOT NULL,
    designation			varchar(100)	NOT NULL,
    join_date			date			NOT NULL,
    special_area		varchar(100),
    appointment_type	varchar(20) 	NOT NULL,
    PAN					varchar(10)	UNIQUE,
    bank_name			varchar(100),
    bank_branch			varchar(100),
    account_number		int(18),
    IFSC				varchar(12),
	MICR				varchar(9),
	PRIMARY KEY(faculty_id),
    FOREIGN KEY(university_name, college_name, faculty_id) references FACULTY_LOGIN(university_name, college_name, faculty_id),
    FOREIGN KEY(university_name, college_name) references COLLEGE(university_name, college_name)
);

create table EMPLOYEE_LOGIN (
	sno					int	auto_increment	NOT NULL	UNIQUE,
    employee_id			varchar(20)	NOT NULL	UNIQUE,
    employee_name		varchar(100)	NOT NULL,
    email_id			varchar(100)	UNIQUE,
    login_id			varchar(64)	UNIQUE,
    password_hash		varchar(64),
    PRIMARY KEY(employee_id)
);