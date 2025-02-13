<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.ecommerce.model.*,java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="./styles/home.css?v=1">
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

    <div class="hero-slider">
        <div class="slide">
            <img src="./homeImages/sale.jpg" alt="Sale">
        </div>
        <div class="slide">
            <img src="./homeImages/online.jpg" alt="New Arrivals">
        </div>
        <div class="slide">
            <img src="./homeImages/onlineshopping.jpg" alt="Special Offers">
        </div>
    </div>
    
    <section class="products">
        <h2 class="section-title">Shop By Category</h2>
        <div class="product-grid" id="productGrid">
            <%
                ArrayList<Products> productList = (ArrayList<Products>) session.getAttribute("products");
                
                if (productList == null || productList.isEmpty()) {
            %>
                <div class="no-products">
                    <h3>No products available at the moment.</h3>
                    <p>Check back later for exciting new products!</p>
                </div>
            <%
                } else {
                    for (Products product : productList) {
            %>
                <div class="product-card" data-name="<%= product.getName().toLowerCase() %>">
                    <a href="ProductItemsServlet?productId=<%= product.getProductId() %>">
                        <img src="<%= product.getImagePath() %>" alt="<%= product.getName() %>">
                        <h3><%= product.getName() %></h3>
                        <p class="discount"><%= product.getDiscount() %> OFF</p>
                        <h4>Shop Now</h4>
                    </a>
                </div>
            <%
                    }
                }
            %>
        </div>
    </section>

    <footer>
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
                    <li><a href="#">Shipping & Returns</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Connect With Us</h3>
                <ul>
                    <li><a href="#"><i class="fab fa-facebook"></i> Facebook</a></li>
                    <li><a href="#"><i class="fab fa-instagram"></i> Instagram</a></li>
                    <li><a href="#"><i class="fab fa-twitter"></i> Twitter</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2024 E-Commerce. All rights reserved.</p>
        </div>
    </footer>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const searchInput = document.getElementById('searchInput');
            const productGrid = document.getElementById('productGrid');
            const productCards = productGrid.querySelectorAll('.product-card');

            searchInput.addEventListener('input', (e) => {
                const query = e.target.value.toLowerCase();

                productCards.forEach((card) => {
                    const productName = card.getAttribute('data-name');
                    if (productName.includes(query)) {
                        card.style.display = 'block';
                    } else {
                        card.style.display = 'none';
                    }
                });
            });

            // Hero slider functionality
            const slides = document.querySelectorAll('.slide');
            let currentSlide = 0;

            function showSlide(index) {
                slides[currentSlide].style.opacity = 0;
                slides[index].style.opacity = 1;
                currentSlide = index;
            }

            function nextSlide() {
                let next = (currentSlide + 1) % slides.length;
                showSlide(next);
            }

            setInterval(nextSlide, 5000); 
        });
    </script>
</body>
</html>

