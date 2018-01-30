use npci;

create table NPCI (
	sno 				int auto_increment 	NOT NULL 	UNIQUE,
    first_holder_name	varchar(100)	NOT NULL,
    second_holder_name	varchar(100),
    third_holder_name	varchar(100),
    account_number		int(18) 		NOT NULL 	UNIQUE,
    account_type		varchar(10)		NOT NULL,
	bank_name			varchar(100) 	NOT NULL,
    bank_branch			varchar(100) 	NOT NULL,
    IFSC				varchar(12) 	NOT NULL,
	MICR				varchar(9) 		NOT NULL,
    aadhaar_first 		int(16) 		UNIQUE,
    aadhaar_second 		int(16) 		UNIQUE,
    aadhaar_third 		int(16) 		UNIQUE,
    pan_first 			int(10) 		UNIQUE,
    pan_second 			int(10) 		UNIQUE,
    pan_third 			int(10) 		UNIQUE,
    PRIMARY KEY(account_number)
);