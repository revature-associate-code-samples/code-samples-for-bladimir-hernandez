package com.revature.project0.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.revature.project0.dao.UserImplementation;
import com.revature.project0.model.User;
import com.revature.project0.util.JDBCConnectionUtil;

import  org.apache.log4j.Logger;



public class UserService {
	final static Logger Log = Logger.getLogger(UserImplementation.class);

	private static UserService userService;
	private UserService() {
		
	}
	public static UserService getUserService() {
		if(userService == null) {
			userService = new UserService();
		}
		return userService;
	}
	
	public static User getUserDetails(User user){
		return UserImplementation.getUserDao().getUser(user);
	}
	
	public static List<User> listAllUsers(){
		return UserImplementation.getUserDao().getAllUsers();
	}
	
	public static boolean registerUser(User user) {
		return UserImplementation.getUserDao().insertUser(user);
	}
	public boolean registerUserProcs(User user) {

		return UserImplementation.getUserDao().insertUser(user);
	}
	
	//log in user
	public boolean logInUser(String loginUsername, String loginPass) {
		return UserImplementation.getUserDao().checkUser(loginUsername,  loginPass);
	}
	public boolean somethingWithMoney(String user, String pass) {
		try( Connection conn = JDBCConnectionUtil.getConnection() ){
			String UName = user;
			String PW = pass;
			String sql= "SELECT U_BALANCE  FROM users_table WHERE U_USERNAME = " + "'"+ UName+ "'" ;			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				System.out.println("What would you like to do? ");
				System.out.println("1.) Check Balance");
				System.out.println("2.) Deposit");
				System.out.println("3.) Withdraw");
				System.out.println("4.) Logout");
				
				//declare a new scanner object
				@SuppressWarnings("resource")
				Scanner input = new Scanner(System.in);
				int userInput2 = input.nextInt();
				
				if( userInput2 == 1) {
					System.out.println("Your balance is: $" + rs.getString("U_Balance"));
					continue;
				}
				if( userInput2 == 2) {
					
					System.out.println("How much $ would you like to deposit?");
					//declare a new scanner object
					@SuppressWarnings("resource")
					Scanner input3 = new Scanner(System.in);
					//read in user input and store it
					int amount = input3.nextInt();
					
					int newAmount = rs.getInt("U_BALANCE") + amount;
					String sql2= "UPDATE USERS_TABLE SET U_BALANCE = "+ "'"+ newAmount + "'"+ " WHERE U_USERNAME = " + "'"+ UName+ "'" ;			
					System.out.println("You have deposited $" + amount);
					System.out.println("Your new balance is " + newAmount);

					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(sql2);
					
					continue;

				}
				if(userInput2 ==3) {
					System.out.println("How much $ would you like to withdraw?");
					//declare a new scanner object
					@SuppressWarnings("resource")
					Scanner input3 = new Scanner(System.in);
					//read in user input and store it
					int amount = input3.nextInt();
					
					int newAmount = rs.getInt("U_BALANCE") - amount;
					String sql2= "UPDATE USERS_TABLE SET U_BALANCE = "+ "'"+ newAmount + "'"+ " WHERE U_USERNAME = " + "'"+ UName+ "'" ;			
					//System.out.println(sql2);
					System.out.println("You have withdrawn $" + amount);
					System.out.println("Your new balance is " + newAmount);

					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(sql2);
				}
				if(userInput2 == 4) {
					System.out.println("Thank you for Banking with us!");
					return false;
				}

			}
			somethingWithMoney( user,  pass);
			
		}
		catch(SQLException s) {
			Log.error("catch block in somethingWithMoney - SQLException - occured");
			s.getMessage();
		
		} 
		catch(NullPointerException n) {
			Log.error("catch block in somethingWithMoney - NullPointerException - occured");

			n.getMessage();
		}
		finally {
			//System.out.println("Thank you for Banking with us!");

		}
		return false; 
	}
	public void approveUser(String loginUsername) {
		System.out.println("Would you like to approve an account? (Y/N)");
		
		//declare a new scanner object
		@SuppressWarnings("resource")
		Scanner approve = new Scanner(System.in);
		//read in user input and store it
		String approve1 = approve.nextLine();
		
		if ( approve1.equals("Y") || approve1.equals("y")) {
			System.out.println("Who would you like to approve?");
			System.out.println("Please enter their username: " );
			
			//declare a new scanner object
			@SuppressWarnings("resource")
			Scanner something = new Scanner(System.in);
			//read in user input and store it
			String Uapprove = something.nextLine();

			//connect;
			try( Connection conn = JDBCConnectionUtil.getConnection() ){
				String sql2= "UPDATE USERS_TABLE SET U_ISACTIVE = 1  WHERE U_USERNAME = " + "'"+ Uapprove+ "'" ;			
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sql2);
				System.out.println(Uapprove + " has been approved! This account is now active!");
				System.out.println("----------------------------------");				
				}
				
				catch(SQLException s) {
					Log.error("catch block in approveUser - SQLException - occured");
					s.getMessage();
				
				} 
				catch(NullPointerException n) {
					Log.error("catch block in approveUser - NullPointerException - occured");
					n.getMessage();
				}

		}
		else if (approve1.equals("N") || approve1.equals("n")) {
			System.out.println("No");
		}
		
	}	
}
