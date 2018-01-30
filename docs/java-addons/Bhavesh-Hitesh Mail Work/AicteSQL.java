// import required packages

class AicteSQL {

	final String MySQLDriver = "com.mysql.jdbc.Driver";
	
	final String URL = "jdbc:mysql://";
	final String HOST = "localhost";
	final String PORT = "3307"
	final String USER = "user";
	final String PASS = "user";

	PreparedStatement preStmt;
	ResultSet rs;
	String SQL;
	Connection con

	public AicteSQL() {
		
		// Register the Driver
		   Class.forName(MySQLDriver);
		
		// Establish Connection
	           con=DeviceManager.getConnection(URL + HOST+":"+PORT+"/test?user=" + USER + "&password=" + PASS);
		
		// Create Statement

	}

	public IndividualDetails execute(String SQL) ed{

	}	

	public String selectAll()
	{SQL="select * from AicteSQL";}

	public String insertInto()
	{SQL="insert into Aicte values("+")";}

	public String insertInto()
	{SQL="insert into Aicte values("+")";}



