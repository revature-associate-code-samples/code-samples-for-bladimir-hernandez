package com.revature.project0.dao;


import com.revature.project0.model.User;
import com.revature.project0.service.UserService;
import com.revature.project0.util.JDBCConnectionUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import  org.apache.log4j.Logger;

public class UserImplementation implements UserDao {

	private static UserImplementation userDao; 
	final static Logger Log = Logger.getLogger(UserImplementation.class);
	
	//singleton
	private UserImplementation() {
	}
	
	public static UserImplementation getUserDao(){
		if (userDao == null) {
			userDao = new UserImplementation();
		}
		return userDao;
	}
	
	@Override
	public boolean insertUserProcedure(User user) {
		try( Connection conn = JDBCConnectionUtil.getConnection() ){
			String storeProcs= "call insert_user (?,?,?,?,?,?)";
			
			CallableStatement cs = conn.prepareCall(storeProcs);
			
			cs.setString(2,user.getFirstname());
			cs.setString(3, user.getLastname());
			cs.setString(4, user.getUsername());
			cs.setString(5,  user.getPassword());
			cs.setBoolean(6, user.getIsAdmin());
			cs.setBoolean(7,  user.getIsActive());
			cs.executeUpdate();
			
			if ( cs.executeUpdate()>0) {
				return true;
			}

		}
		catch(SQLException s) {
			Log.error("catch block in insertUserProcedure - SQLException - occured");
			s.getMessage();
		} 
		return false; 
	}
	
	public boolean insertUser(User user) {
		try( Connection conn = JDBCConnectionUtil.getConnection() ){
			
			//Calling the store procedure
			String storedProc = "CALL INSERT_USER(?,?,?,?,?,?,?)";
			
			//Using callable statement in itself is not vulnerable to SQL Injection
			CallableStatement cs = conn.prepareCall(storedProc);
		
			
			cs.setString(1,user.getFirstname());
			cs.setString(2, user.getLastname());
			cs.setString(3, user.getUsername());
			cs.setString(4,  user.getPassword());
			cs.setBoolean(5,  user.getIsAdmin());
			cs.setBoolean(6,  user.getIsActive());
			cs.setInt(7, user.getBalance());
			cs.executeUpdate();
			

		}
		catch(SQLException s) {
			Log.error("catch block in insertUser - SQLException - occured");
			s.getMessage();
		} 
		return false; 
	}
	


	public boolean insertUserProcedure() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkUser(String user, String pass) {
		//connect;
		try( Connection conn = JDBCConnectionUtil.getConnection() ){
			String UName = user;
			String PW = pass;
			String sql= "SELECT U_USERNAME, U_PASSWORD, U_ISADMIN, U_ISACTIVE, U_BALANCE  FROM users_table WHERE U_USERNAME = " + "'"+ UName+ "'" ;			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {

				if(rs.getString("U_USERNAME").equals(UName) && (rs.getString("U_PASSWORD").equals(PW) )) {
					if(rs.getInt("U_ISADMIN") == 1){
						System.out.println("This account is an administrator account");						
						System.out.println("----------------------------------");
						UserService.getUserService().approveUser(UName);
					}
					if(rs.getInt("U_ISACTIVE") == 0){
						System.out.println("However, this account cannot be used until it is approved by an administrator. Therefore, ");
						return false;
					}
					return true;
				}

				return false;
			}
			
		}

		catch(SQLException s) {
			Log.error("catch block in checkUser - SQLException - occured");
			s.getMessage();
		
		} 
		catch(NullPointerException n) {
			n.getMessage();
		}
		return false; 
	}
	

	public List<User> getAllUsers() {
		
		try( Connection conn = JDBCConnectionUtil.getConnection() ){
			
			String sql= "select * from users_table";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			List<User> userList= new ArrayList<>();
			while(rs.next()) {
				userList.add(new User(
						rs.getString("U_FIRSTNAME"),
						rs.getString("U_LASTNAME"),
						rs.getString("U_USERNAME"),
						rs.getString("U_PASSWORD"),
						rs.getBoolean("U_ISADMIN"),
						rs.getBoolean("U_ISACTIVE"),
						rs.getInt("U_BALANCE")
						));
			}
			return userList;
		}
		catch(SQLException s) {
			Log.error("catch block in getAllUsers - SQLException - occured");
			s.getMessage();
		} 
		return new ArrayList<User>(); 
	}
	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean checkUser() {
		// TODO Auto-generated method stub
		return false;
	}



	
}
