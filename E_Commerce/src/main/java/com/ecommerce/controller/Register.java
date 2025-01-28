package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.daoimpl.UserDaoImpl;
import com.ecommerce.model.User;
import com.ecommerce.resources.SecureData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class Register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=SecureData.encrypt(req.getParameter("name"));
		String email=SecureData.encrypt(req.getParameter("email"));
		String password=SecureData.encrypt(req.getParameter("password"));
		String address=SecureData.encrypt(req.getParameter("address"));
		
		UserDaoImpl udi=new UserDaoImpl();
		if(udi.addUser(new User(name,email,password,address))==1) {
			resp.getWriter().println("Created new user");
		}
		else {
			resp.getWriter().println("failed to create");
		}
	}
}
