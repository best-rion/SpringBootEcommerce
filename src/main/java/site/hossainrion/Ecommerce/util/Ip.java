package site.hossainrion.Ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ip
{
	public static String getIp()
	{
		
		try {

			String url = "";
			Connection myConn = DriverManager.getConnection(DBInfo.url, DBInfo.user, DBInfo.password);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT url_address FROM url_table");
			
			while (myRs.next()) {
				url = myRs.getString("url_address");
			}
			myConn.close();
			return url;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}