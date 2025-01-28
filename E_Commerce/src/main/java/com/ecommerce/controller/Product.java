package com.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.ecommerce.daoimpl.ProductDaoImpl;
import com.ecommerce.model.Products;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Product extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDaoImpl pdi=new ProductDaoImpl();
		
		ArrayList<Products> productList = pdi.fetchAllProducts();
		System.out.println(productList);
		HttpSession session=req.getSession();
		session.setAttribute("products", productList);
		
		resp.sendRedirect("home.jsp");
		
	}
}
