package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;


import beans.Login;
import dao.UserDao;
import beans.Reimbursement;
import beans.User;
import dao.LoginImplementation;


public class Services {
	
	final static Logger Log = Logger.getLogger(Services.class);
	private static Services service;
	
	private Services() {
	}
	
	public static Services getReimbursementService() {

		if(service == null) {
			service = new Services();
		}
		return service;
	}

	public static List<Reimbursement> getPendingReimbursements(Login login){
		return LoginImplementation.getLoginDao().getPendingReimbursements(login);

	}
	public static List<Reimbursement> getApprovedReimbursements(Login login){
		return LoginImplementation.getLoginDao().getApprovedReimbursements(login); 
	}
	public static boolean updateLogin(Login login, String FirstName, String LastName, String Email, String password) {
		return LoginImplementation.getLoginDao().updateLogin(login, FirstName, LastName, Email, password);
	}; 
	public static boolean submitReimbursement(Login login, String description, int amount) { 
		return LoginImplementation.getLoginDao().submitReimbursement(login, description, amount);
	}
	public static List<Reimbursement> getAllPendingReimbursements(Login login){
		return LoginImplementation.getLoginDao().getAllPendingReimbursements(login);
	}
	public static List<Login> getAllEmployees(Login login){
		return LoginImplementation.getLoginDao().getAllEmployees(login);
	}
	public static List<Reimbursement> getAllResolvedReimbursements(Login login){
		return LoginImplementation.getLoginDao().getAllResolvedReimbursements(login);
	};
	public static List<Reimbursement> getAllRequestsFromSingleUser(int id){
		return LoginImplementation.getLoginDao().getAllRequestsFromSingleUser(id);
	}; 
	public static Reimbursement approveReimbursement(int someid) {
		return LoginImplementation.getLoginDao().approveReimbursement(someid);
	}; 
}
