package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class Databaseconnection {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String epn="test";
		
		
		Driver drivverref=new Driver();
		DriverManager.registerDriver(drivverref);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root","root");
		 Statement stmt = conn.createStatement();
		 
		 ResultSet set = stmt.executeQuery("select * from project;");
		
		
		while(set.next())
		 {
			 System.out.println(set.getString(1));
			 String apn = set.getString(4);
			 if(apn.equals(epn))
			 {
				 System.out.println("Match found");
				
			 }
			 else
			 {
				 System.out.println("match not found");
			 }
		}
		 
	
		int status = stmt.executeUpdate("insert into project values('TY_PROJ_007','karthikraj12','12/01/2022','test','created',100);");
		
		
	if(status==1)
		 {
			 System.out.println("Data updated sucessfully");
			 
		 }
		 else
		 {
			 System.out.println("data updation failed");
		 }
		 
		 
		 	
		 
		 conn.close();
	}
}

