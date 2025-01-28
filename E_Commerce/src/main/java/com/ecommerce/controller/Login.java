package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.daoimpl.UserDaoImpl;
import com.ecommerce.model.User;
import com.ecommerce.resources.SecureData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=SecureData.encrypt(req.getParameter("email"));
		String password=SecureData.encrypt(req.getParameter("password"));
		
		UserDaoImpl udi=new UserDaoImpl();
		User user=udi.fetchSpecificUser(email);
		
		if(user!=null) {
			
			if(password.equals(user.getPassword())) {
				resp.getWriter().println("sucessfully login");
				resp.sendRedirect("Product");
			}
			else {
				resp.getWriter().println("Incorrect password");
			}
		}	
		else {
			resp.getWriter().println("uh don't have account go and register");
		}
		
		
	}
}
