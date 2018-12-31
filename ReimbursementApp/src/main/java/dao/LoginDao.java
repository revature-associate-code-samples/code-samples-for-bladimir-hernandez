package dao;

import java.util.List;

import beans.Reimbursement;
import beans.User;
import beans.Login;

public interface LoginDao {

	public List<Reimbursement> getPendingReimbursements(Login login); 
	public List<Reimbursement> getApprovedReimbursements(Login login);
	public boolean submitReimbursement(Login login, String description, int amount);  
	public boolean updateLogin(Login login, String FirstName, String LastName, String Email, String newUserPassword); 
	public User getUser(User user);  

	
	public Reimbursement approveReimbursement(int someid); 
	public List<Login> getAllEmployees(Login login); 
	public List<Reimbursement> getAllResolvedReimbursements(Login login);
	public List<Reimbursement> getAllPendingReimbursements(Login login);
	public  List<Reimbursement> getAllRequestsFromSingleUser(int id);

}
