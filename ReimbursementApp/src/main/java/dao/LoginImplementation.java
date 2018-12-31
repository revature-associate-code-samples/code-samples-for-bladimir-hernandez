package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import beans.Login;
import beans.Reimbursement;
import beans.User;
import util.ConnectionUtil;
import util.JDBCConnectionUtil;

//SQL stuff goes in here

public class LoginImplementation implements LoginDao {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();

	private static LoginImplementation LoginDao; 
	
	final static Logger Log = Logger.getLogger(LoginImplementation.class);
	//singleton
	private LoginImplementation() {
	}
	
	public static LoginImplementation getLoginDao(){
		if (LoginDao == null) {
			LoginDao = new LoginImplementation();
		}
		return LoginDao;
	}
	
	public ArrayList<Reimbursement> getPendingReimbursements(Login login) {
		ArrayList<Reimbursement> reimbursementList = new ArrayList<>();
		try( Connection conn = cu.getConnection() ){
			String sql= "select * from REIMBURSEMENT_TABLE WHERE STATUS = 'pending' AND R_EMPLOYEEID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, login.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				reimbursementList.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getString("DETAILS"),
						rs.getInt("AMOUNT"),
						rs.getInt("R_EMPLOYEEID"),
						rs.getInt("R_MANAGERID"),
						rs.getString("STATUS")
						));
			}
			return reimbursementList;
		}
		catch(SQLException s) {
			s.getMessage();
		} 
		return new ArrayList<Reimbursement>(); 
	}
	
	public ArrayList<Reimbursement> getApprovedReimbursements(Login login) {
		ArrayList<Reimbursement> appreimbursementList = new ArrayList<>();
		
		try( Connection conn = cu.getConnection() ){
			String sql = "SELECT * from REIMBURSEMENT_TABLE WHERE STATUS = 'Approved' AND R_EMPLOYEEID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, login.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				appreimbursementList.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getString("DETAILS"),
						rs.getInt("AMOUNT"),
						rs.getInt("R_EMPLOYEEID"),
						rs.getInt("R_MANAGERID"),
						rs.getString("STATUS")
						));
			}
			return appreimbursementList;
		}
		catch(SQLException s) {
			System.out.println(appreimbursementList);
			s.getMessage();
		} 
		return new ArrayList<Reimbursement>(); 
	}

	public boolean submitReimbursement(Login login, String description, int amount) {

		try( Connection conn = cu.getConnection() ){		
			String sql = "call INSERT_REIMBURSEMENT(?,?,?) " ;		
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1,description);
			pstmt.setInt(2, amount);
			pstmt.setInt(3,login.getId());	
			ResultSet rs = pstmt.executeQuery();
			return true;
		}
		catch(SQLException s) {
			s.getMessage();
		} 
		return false;
	}
	@Override
	public User getUser(User user) {
		return null;
	}

	@Override
	public boolean updateLogin(Login login, String FirstName, String LastName, String Email, String password) {
		System.out.println("update User from the loginimplementation!");
		
		String FName = login.getFirstname();
		System.out.println("this is the OG first name of the user " + FName);
		
		try( Connection conn = cu.getConnection() ){		

			
			String sql = "CALL UPDATE_USER(?,?,?,?,?) ";
					
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			pstmt.setString(1,FirstName);
			pstmt.setString(2, LastName);
			pstmt.setString(3, Email);	
			pstmt.setString(4, password);
			pstmt.setString(5, login.getFirstname());
			ResultSet rs = pstmt.executeQuery();
	
			
			return true;
		}
		catch(SQLException s) {
			s.getMessage();
		} 
		return true;
	}

	@Override
	public Reimbursement approveReimbursement(int someid) {
		
		Reimbursement approvedReimbursement = null;
		
		try( Connection conn = cu.getConnection() ){
			String sql = "CALL APPROVE(?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,someid);


			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				new Reimbursement(
						rs.getInt("R_ID"),
						rs.getString("DETAILS"),
						rs.getInt("AMOUNT"),
						rs.getInt("R_EMPLOYEEID"),
						rs.getInt("R_MANAGERID"),
						rs.getString("STATUS")
						);
			}
			return approvedReimbursement;
		}
		catch(SQLException s) {
			s.getMessage();
		} 
		return approvedReimbursement; 
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursements(Login login) {
		ArrayList<Reimbursement> allPending = new ArrayList<>();
		
		try( Connection conn = cu.getConnection() ){
			String sql = "SELECT * FROM REIMBURSEMENT_TABLE WHERE STATUS = 'pending' ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs.next());
			while(rs.next()) {
				allPending.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getString("DETAILS"),
						rs.getInt("AMOUNT"),
						rs.getInt("R_EMPLOYEEID"),
						rs.getInt("R_MANAGERID"),
						rs.getString("STATUS")
						));
			}
			return allPending;
		}
		catch(SQLException s) {
			System.out.println(allPending);
			s.getMessage();
		} 
		return new ArrayList<Reimbursement>(); 
	}


	@Override
	public List<Reimbursement> getAllResolvedReimbursements(Login login) {
		ArrayList<Reimbursement> allApproved = new ArrayList<>();
		
		try( Connection conn = cu.getConnection() ){
			String sql = "SELECT * FROM REIMBURSEMENT_TABLE WHERE STATUS = 'Approved' ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs.next());
			while(rs.next()) {
				allApproved.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getString("DETAILS"),
						rs.getInt("AMOUNT"),
						rs.getInt("R_EMPLOYEEID"),
						rs.getInt("R_MANAGERID"),
						rs.getString("STATUS")
						));
			}
			return allApproved;
		}
		catch(SQLException s) {
			System.out.println(allApproved);
			s.getMessage();
		} 
		return new ArrayList<Reimbursement>(); 
	}

	@Override
	public List<Login> getAllEmployees(Login login) {
		ArrayList<Login> allEmployees = new ArrayList<>();
		
		try( Connection conn = cu.getConnection() ){
			String sql = "SELECT * FROM USER_TABLE WHERE USER_TYPE = 'employee'  ";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				allEmployees.add(new Login(
						rs.getInt("U_ID"),
						rs.getString("U_FNAME"),
						rs.getString("U_LNAME"),
						rs.getString("U_EMAIL"),
						rs.getString("U_USERNAME"),
						rs.getString("U_PASSWORD"),
						rs.getString("USER_TYPE")
						));
			}
			return allEmployees;
		}
		catch(SQLException s) {
			s.getMessage();
		} 
		return new ArrayList<Login>(); 
	}

	public List<Reimbursement> getAllRequestsFromSingleUser(int ID) {
		ArrayList<Reimbursement> allReimbursementFromOne = new ArrayList<>();
		
		try( Connection conn = cu.getConnection() ){
			String sql = "SELECT * FROM REIMBURSEMENT_TABLE WHERE R_EMPLOYEEID = ?  ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,ID);


			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				allReimbursementFromOne.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getString("DETAILS"),
						rs.getInt("AMOUNT"),
						rs.getInt("R_EMPLOYEEID"),
						rs.getInt("R_MANAGERID"),
						rs.getString("STATUS")
						));
			}
			return allReimbursementFromOne;
		}
		catch(SQLException s) {
			s.getMessage();
		} 
		return new ArrayList<Reimbursement>(); 
	}
}


