package controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Login;

public class FrontController extends DefaultServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8322163559265087178L;
	private RequestHelper rh = new RequestHelper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		if(req.getRequestURI().substring(req.getContextPath().length()).startsWith("/html/")) {
			System.out.println("It is in the doGet()");
			super.doGet(req, res);
		} else {
			rh.process(req, res);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);


	}
	
}
