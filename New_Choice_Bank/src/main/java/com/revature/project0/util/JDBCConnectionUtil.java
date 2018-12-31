package com.revature.project0.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCConnectionUtil {
	//JDBC - Java Database Connectivity
	//DB - database
	
	public static Connection getConnection(){
		String url = "jdbc:oracle:thin:@revature.cadefds9ysl1.us-east-2.rds.amazonaws.com:1521:ORCL";
		String user = "bladhrnandz";
		String pass = "CamelsrCool4l!";
		try {
			 return DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
