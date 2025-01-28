<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="./styles/signup.css">
</head>
<body>
    <div class="container">
        <h1>Sign Up</h1>
        <form id="signupForm" method="post" action="processSignup.jsp">
            <div class="form-group">
                <label for="userName">User Name:</label>
                <input type="text" id="userName" name="userName" required>
                <div class="error" id="userNameError"></div>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <div class="error" id="emailError"></div>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <div class="error" id="passwordError"></div>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
                <div class="error" id="addressError"></div>
            </div>
            <button type="submit">Sign Up</button>
        </form>
    </div>

    <script>
        document.getElementById('signupForm').addEventListener('submit', function(event) {
            let isValid = true;
            const userName = document.getElementById('userName').value.trim();
            const email = document.getElementById('email').value.trim();
            const password = document.getElementById('password').value;
            const address = document.getElementById('address').value.trim();

            // Reset error messages
            document.querySelectorAll('.error').forEach(el => el.textContent = '');

            // Validate User Name
            if (userName.length < 3) {
                document.getElementById('userNameError').textContent = 'User Name must be at least 3 characters long.';
                isValid = false;
            }

            // Validate Email
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                document.getElementById('emailError').textContent = 'Please enter a valid email address.';
                isValid = false;
            }

            // Validate Password
            if (password.length < 6) {
                document.getElementById('passwordError').textContent = 'Password must be at least 6 characters long.';
                isValid = false;
            }

            // Validate Address
            if (address.length < 5) {
                document.getElementById('addressError').textContent = 'Please enter a valid address.';
                isValid = false;
            }

            if (!isValid) {
                event.preventDefault();
            }
        });
    </script>
</body>
</html>

