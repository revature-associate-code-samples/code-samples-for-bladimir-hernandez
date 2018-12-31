package delegate;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;
import beans.Reimbursement;
import dao.ReimbursementDao;
import data.LoginDao;
import data.LoginOracle;
import data.ReimbursementOracle;
import services.Services;


public class LoginDelegate {

	public LoginDao ld = new LoginOracle();
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession var = req.getSession(false);
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String userType = req.getParameter("userType");
		
	
		Login login = ld.login(firstname, lastname, email, username, password, userType);		

		if(login == null) {
			resp.sendRedirect("login");
		}
		
		else {
			if ( login.getUserType().equals("employee")) {
				HttpSession session = req.getSession();
				session.setAttribute("user", login);
				resp.sendRedirect("./html/employee.html");
			}
			else {
				HttpSession session = req.getSession();
				session.setAttribute("user", login);
				resp.sendRedirect("./html/manager.html");
			}

		}
	}

	
	public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")==null) {
			req.getRequestDispatcher("static/login.html").forward(req,resp);
		} else {
			resp.sendRedirect("./html/employee.html");
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect("html/index.html");
	}
}