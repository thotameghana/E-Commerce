<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.ecommerce.model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Product Details</title>
    <link rel="stylesheet" href="./styles/productDetails.css?v=1.0">
</head>
<body>
    <nav class="navbar">
        <a href="home.jsp" class="logo">
            <img src="https://img.icons8.com/?size=100&id=3UFq9axr5bPe&format=png&color=000000" height="50px" width="50px">
        </a>
        <div class="auth-buttons">
            <a href="login.jsp" class="login">Login</a>
            <a href="signup.jsp" class="signup">Sign Up</a>
        </div>
    </nav>
    <div class="container">
        <%
            // Fetch the product ID from the session
            Integer productItemId = (Integer) session.getAttribute("productItemId");
            ProductItems selectedProduct = null;

            // Retrieve the product list from the session
            List<ProductItems> productItems = (List<ProductItems>) session.getAttribute("productItems");
            if (productItems != null && productItemId != null) {
                for (ProductItems pi : productItems) {
                    if (pi.getProductItemId() == productItemId) {
                        selectedProduct = pi;
                        break;
                    }
                }
            }

            if (selectedProduct != null) {
        %>
        <div class="product-details-card">
            <div class="product-image">
                <img src="<%= selectedProduct.getImagePath()%>" alt="<%= selectedProduct.getName() %>" />
            </div>
            <div class="product-info">
                <h1 class="product-name"><%= selectedProduct.getName() %></h1>
                <p class="product-description"><%= selectedProduct.getDescription() %></p>
                <p class="product-price">Price: &#8377;<%= selectedProduct.getPrice() %></p>
                <p class="product-rating">Rating: <%= selectedProduct.getRatings() %>&#9733;</p>
                <a href="orderConfirmation.jsp"><button class="place-order">Place Order</button></a>
            </div>
        </div>
        <% } else { %>
        <p class="error-message">Product not found.</p>
        <% } %>
    </div>
</body>
</html>
