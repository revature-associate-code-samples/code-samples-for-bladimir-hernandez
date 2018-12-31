package data;

import beans.Reimbursement;

public interface ReimbursementDao {
	
	Reimbursement reimbursement(int id, String details, int amount, int employeeID, int managerID, String status);
}



