package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Reimbursement;
import util.ConnectionUtil;

public class ReimbursementOracle implements ReimbursementDao{

	private static ConnectionUtil cu = ConnectionUtil.getInstance();

	@Override
	public	Reimbursement reimbursement(int id, String details, int amount, int employeeID, int managerID, String status)
 {
		Reimbursement reimbursement = null;
		
		String sql = "Select * from INSERT_REIMBURSEMENT WHERE = ?";
		
		try(Connection conn = cu.getConnection() ){
			PreparedStatement pstmt = conn.prepareStatement(sql);

			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				reimbursement = new Reimbursement(
						rs.getInt("R_ID"),
						rs.getString("DETAILS"),
						rs.getInt("AMOUNT"),
						rs.getInt("r_employeeID"),
						rs.getInt("r_managerID"),
						rs.getString("STATUS"));
			}
		}catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return reimbursement;
	}
}
