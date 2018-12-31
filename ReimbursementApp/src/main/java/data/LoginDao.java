package data;

import beans.Login;
import beans.Reimbursement;

public interface LoginDao {
	Login login(String firstname, String lastname, String email, String username, String password, String userType);
	Login login(String username, String password);
}
