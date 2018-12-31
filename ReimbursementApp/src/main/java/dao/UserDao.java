package dao;

import java.util.List;

import beans.Reimbursement;
import beans.User;

public interface UserDao {
	
	public List<Reimbursement> getPendingReimbursements(User user, Reimbursement reimbursement);
	public boolean submitReimbursement(User user, Reimbursement reimbursement);
	public User getUser(User user);  
	public List<Reimbursement> getResolvedReimbursements(User user, Reimbursement reimbursement);
	public User updateUser(User user);
	
	
	public boolean approveReimbursement(Reimbursement reimbursement); 
	public List<Reimbursement> getAllPendingReimbursements(Reimbursement reimbursement); 
	public List<Reimbursement> getAllResolvedReimbursements();
	public List<User> getAllEmployees(); 	public List<Reimbursement> getAllRequestsFromSingleUser(User user, Reimbursement reimbursement);  

}
