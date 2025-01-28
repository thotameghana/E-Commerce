package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.ProductItemDao;
import com.ecommerce.daoimpl.ProductItemDaoImpl;
import com.ecommerce.model.ProductItems;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ProductItemsServlet extends HttpServlet {
	 @Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int productId=Integer.parseInt(req.getParameter("productId"));
		 ProductItemDao pid=new ProductItemDaoImpl();
		 List<ProductItems> productItems=pid.fetchByProductId(productId);
		 
		 HttpSession session=req.getSession();
		 session.setAttribute("productItems", productItems);
		 
		 resp.sendRedirect("productItems.jsp");
	 }
}
