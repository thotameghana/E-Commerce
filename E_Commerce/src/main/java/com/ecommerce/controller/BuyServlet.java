package com.ecommerce.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class BuyServlet
 */
public class BuyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the product ID from the request parameter
        String productId = req.getParameter("id");
        
        // Get the current session
        HttpSession session = req.getSession();
        
        // Store the product ID in the session
        if (productId != null) {
            session.setAttribute("productItemId", Integer.parseInt(productId));
        }
        
        // Redirect the user to the product details page
        resp.sendRedirect("productDetails.jsp");
    }
}
