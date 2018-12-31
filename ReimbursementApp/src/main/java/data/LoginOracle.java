package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.LoginDao;

import beans.Login;
import util.ConnectionUtil;
//import util.ConnectionUtil;
import util.JDBCConnectionUtil;


public class LoginOracle implements LoginDao {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	@Override
	public Login login(String firstname, String lastname, String email, String username, String password, String userType) {
		Login login = null;

		String sql = "Select * from USER_TABLE "
				+ "where U_USERNAME = ? and U_PASSWORD = ?";
		
		
		try (Connection conn = cu.getConnection() ){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				login = new Login(
						rs.getInt("U_ID"),
						rs.getString("U_FNAME"),
						rs.getString("U_LNAME"),
						rs.getString("U_EMAIL"),
						rs.getString("U_USERNAME"),
						rs.getString("U_PASSWORD"),
						rs.getString("USER_TYPE"));
			}
			return login;
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return login;
	}

	@Override
	public Login login(String username, String password) {
		return null;
	}


}
