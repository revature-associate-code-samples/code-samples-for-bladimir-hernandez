package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCConnectionUtil {
	
	public static Connection getConnection(){
		String url = "jdbc:oracle:thin:@revature.cadefds9ysl1.us-east-2.rds.amazonaws.com:1521:ORCL";
		String user = "bladhrnandz";
		String pass = "CamelsrCool4l!";
		try {
			 return DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
