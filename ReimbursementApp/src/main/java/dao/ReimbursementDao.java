package dao;

import java.util.List;

import beans.Reimbursement;


public interface ReimbursementDao {
	public boolean insertReimbursement(Reimbursement reimbursement);
	public boolean insertReimbursementProcedure(Reimbursement reimbursement);
	public Reimbursement getReimbursement(Reimbursement reimbursement);
	public boolean checkReimbursement();
	public List<Reimbursement> getAllReimbursements();
}
