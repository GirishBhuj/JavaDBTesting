package GBSoft.DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DbManager
{
	private static Connection MySqlCon = null;

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		//DbManager.setMysqlDbConnection();
		//System.out.println(DbManager.getQuery("select Emp_Fname,Salary from Employee where Salary > 50000"));

		Hashtable<String, String> mTestDataHashTable = DbManager.GetExcelData();
		System.out.println("Full Name: " + mTestDataHashTable.get("fullName"));
		System.out.println(mTestDataHashTable.get("birthdate"));
	}

	public static void setMysqlDbConnection() throws SQLException, ClassNotFoundException
	{
		try
		{   
			//Class.forName (TestConfig.mysqldriver);
			Class.forName("com.mysql.jdbc.Driver");
			MySqlCon = DriverManager.getConnection ("jdbc:mysql://localhost:3306/company", "root", "grbmyDB#123");
			if(!MySqlCon.isClosed())
				System.out.println("Successfully connected to MySQL server");	
		}
		catch (Exception e)
		{
			System.err.println ("Cannot connect to database server" + e.getMessage());
			// monitoringMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject+" - (Script failed with Error, Datamart database used for reports, connection not established)", TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
		}
	}

	public static List<String> getQuery(String query) throws SQLException
	{
		Statement St = MySqlCon.createStatement();
		ResultSet rs = St.executeQuery(query);
		List<String> values = new ArrayList<String>();
		while(rs.next())
		{
			values.add(rs.getString(1));			
		}
		return values;
	}

	public static Hashtable<String,String> GetExcelData()
	{
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\TestData.xlsx");
		//return ExcelFunction.ReadExcel("CreateAccountRediffmailTest", xls);
		return ExcelFunction.ReadExcel("SendMailRediffmailTest", xls);
	}
}