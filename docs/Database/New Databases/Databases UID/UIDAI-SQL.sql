use uidai;

create table UID (
	sno 				int auto_increment 	NOT NULL 	UNIQUE,
    aadhaar_number 		int(16) 		UNIQUE,
    full_name 			varchar(100) 	NOT NULL,
    date_of_birth 		date 			NOT NULL,
    gender 				varchar(12) 	NOT NULL,
    perm_address 		varchar(300) 	NOT NULL,
    father_name 		varchar(100)	NOT NULL,
    PRIMARY KEY(aadhaar_number)
);