show databases;
use aicte2;
select * from credentials;
delete * from credentials
where objectId=null;
select * from CREDENTIALS where email_id like "%" ;