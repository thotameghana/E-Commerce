<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.ecommerce.model.*,java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Items</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="./styles/productItems.css">
</head>
<body>
    <nav class="navbar">
        <a href="home.jsp" class="logo">
            <img src="https://img.icons8.com/?size=100&id=3UFq9axr5bPe&format=png&color=000000" alt="Logo">
        </a>
        <div class="search-bar">
            <input type="text" id="searchInput" placeholder="Search products...">
        </div>
        <div class="auth-buttons">
            <a href="login.jsp" class="login">Login</a>
            <a href="signup.jsp" class="signup">Sign Up</a>
        </div>
    </nav>
    <div class="container" id="productContainer">
    <% 
        List<ProductItems> productItems = (List<ProductItems>)session.getAttribute("productItems");
        
        if (productItems == null || productItems.isEmpty()) { 
    %>
        <div class="no-products-message">
            <h2>No products available at the moment.</h2>
            <p>Please check back later!</p>
        </div>
    <% 
        } else { 
            for(ProductItems pi : productItems) { 
    %>
        <div class="product-card" data-name="<%= pi.getName().toLowerCase() %>">
    <div class="product-image">
        <img src="<%= pi.getImagePath()%>" alt="<%= pi.getName()%>">
    </div>
    <div class="product-details">
        <h3><%= pi.getName() %></h3>
        <p><%= pi.getDescription() %></p>
        <div class="ratings-price">
            <p class="ratings">
                <% for(int i = 0; i < Math.floor(pi.getRatings()); i++) { %>
                    <i class="fas fa-star"></i>
                <% } %>
                <% if(pi.getRatings() % 1 != 0) { %>
                    <i class="fas fa-star-half-alt"></i>
                <% } %>
                <span>(<%= pi.getRatings() %>)</span>
            </p>
            <p class="price">&#8377;<%=pi.getPrice() %></p>
        </div>
        <div class="product-actions">
            <button class="buy-now" onclick="window.location.href='BuyServlet?id=<%= pi.getProductItemId() %>'">
                <img src="https://img.icons8.com/material-rounded/24/fast-cart.png" alt="Buy Icon"> Buy Now
            </button>
        </div>
   		</div>
	</div>
        
    <% 
            } 
        } 
    %>
</div>
    
    <footer class="footer">
        <div class="footer-content">
            <div class="footer-section">
                <h3>About Us</h3>
                <ul>
                    <li><a href="#">Our Story</a></li>
                    <li><a href="#">Careers</a></li>
                    <li><a href="#">Press</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Customer Service</h3>
                <ul>
                    <li><a href="#">Contact Us</a></li>
                    <li><a href="#">FAQs</a></li>
                    <li><a href="#">Shipping</a></li>
                    <li><a href="#">Returns</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Connect With Us</h3>
                <ul>
                    <li><a href="#"><i class="fab fa-facebook"></i> Facebook</a></li>
                    <li><a href="#"><i class="fab fa-twitter"></i> Twitter</a></li>
                    <li><a href="#"><i class="fab fa-instagram"></i> Instagram</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 E-Commerce. All rights reserved</p>
        </div>
    </footer>
  	<script>
    document.addEventListener('DOMContentLoaded', () => {
        const searchInput = document.getElementById('searchInput');
        const productContainer = document.getElementById('productContainer');
        const productCards = productContainer.querySelectorAll('.product-card');

        searchInput.addEventListener('input', (e) => {
            const query = e.target.value.toLowerCase();
            let visibleCount = 0;

            productCards.forEach((card) => {
                const productName = card.getAttribute('data-name');
                if (productName && productName.includes(query)) {
                    card.style.display = 'block';
                    visibleCount++;
                } else {
                    card.style.display = 'none';
                }
            });

            // Show message if no products match the query
            const noProductsMessage = productContainer.querySelector('.no-products-message');
            if (noProductsMessage) {
                noProductsMessage.style.display = visibleCount > 0 ? 'none' : 'block';
            }
        });
    });
</script>
  	
    
</body>
</html>

