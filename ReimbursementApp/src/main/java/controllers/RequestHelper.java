package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Login;
import beans.Reimbursement;
import beans.User;
import delegate.HomeDelegate;
import delegate.LoginDelegate;
import services.Services;



public class RequestHelper {
	
	private HomeDelegate hd = new HomeDelegate();
	private LoginDelegate ld = new LoginDelegate();
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);
						
		while(switchString.indexOf("/")>0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		
		switch(switchString) {
		case "index": hd.goHome(req, resp); break;
		case "login": if("POST".equals(req.getMethod())) {
			System.out.println("it went to the login");
			ld.login(req, resp);
		} else {
			ld.getPage(req, resp);
		}
		break;
		
		
		
		case "searchedUser":
			Login log = new ObjectMapper().readValue(req.getReader(), Login.class);
			List<Reimbursement> searchedReimbursements = Services.getAllRequestsFromSingleUser(log.getId());
			ObjectMapper mapper8 = new ObjectMapper();
			resp.setHeader("Content-Type", "text/javascript");
			mapper8.writeValue(resp.getOutputStream(), searchedReimbursements);

		break;
		
		case "approveReimbursement":
			Login log2 = new ObjectMapper().readValue(req.getReader(), Login.class);
			Reimbursement approveReimbursement = Services.approveReimbursement(log2.getId());

			ObjectMapper mapper9 = new ObjectMapper();
			resp.setHeader("Content-Type", "text/javascript");
			mapper9.writeValue(resp.getOutputStream(), approveReimbursement);

		break;
		
		case "newRequest":
			Login Emp1 = (Login) req.getSession().getAttribute("user");
			String description = req.getParameter("description");
			int amount = Integer.parseInt(req.getParameter("amount"));
			Services.submitReimbursement(Emp1, description, amount);
			resp.sendRedirect("html/employee.html");
		
		break;
		
		case "updateEmployeeInfo":
			Login Emp2 = (Login) req.getSession().getAttribute("user");
			String newFirstName = req.getParameter("Userfirstname");
			String newLastName = req.getParameter("UserlastName");
			String newEmail = req.getParameter("email");
			String newUserPassword = req.getParameter("password");
			Emp2.setFirstname(newFirstName);
			Emp2.setLastname(newLastName);
			Emp2.setEmail(newEmail);
			Emp2.setPassword(newUserPassword);
			resp.sendRedirect("html/employee.html");
			
		case "employee": 
			Login Emp = (Login) req.getSession().getAttribute("user");
			ObjectMapper mapper = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			mapper.writeValue(resp.getOutputStream(), Emp);
		
		break;
		
		case "pendingReimbursements":
			Login Empl = (Login) req.getSession().getAttribute("user");
			List<Reimbursement> pendingReimbursements = Services.getPendingReimbursements(Empl);
			ObjectMapper mapper2 = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			mapper2.writeValue(resp.getOutputStream(), pendingReimbursements);
			
			break;
			
		case "approvedReimbursements":
			Login Emplo = (Login) req.getSession().getAttribute("user");
			List<Reimbursement> approvedReimbursements = Services.getApprovedReimbursements(Emplo);
			ObjectMapper mapper3 = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			mapper3.writeValue(resp.getOutputStream(), approvedReimbursements);
			
			break;
		
		case "allPendingRequests":
			Login Emplo2 = (Login) req.getSession().getAttribute("user");
			List<Reimbursement> allPending = Services.getAllPendingReimbursements(Emplo2);
			ObjectMapper mapper4 = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			mapper4.writeValue(resp.getOutputStream(), allPending);
			
			break;
			
		case "allApprovedRequests":
			Login Emplo4 = (Login) req.getSession().getAttribute("user");
			List<Reimbursement> allApproved = Services.getAllResolvedReimbursements(Emplo4);
			ObjectMapper mapper6 = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			mapper6.writeValue(resp.getOutputStream(), allApproved);
			
		case "allEmployees":
			Login Emplo3 = (Login) req.getSession().getAttribute("user");
			List<Login> allEmployees = Services.getAllEmployees(Emplo3);
			ObjectMapper mapper5 = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			mapper5.writeValue(resp.getOutputStream(), allEmployees);
			
			break;

		case "logout": ld.logout(req, resp); break;
		default: break;
		
		}
	}
}
