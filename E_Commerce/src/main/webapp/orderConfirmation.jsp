<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmed</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #4CAF50;
            --secondary-color: #45a049;
            --background-color: #f4f4f4;
            --text-color: #333;
            --accent-color: #FFC107;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            overflow: hidden;
        }

        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 40px;
            text-align: center;
            max-width: 400px;
            width: 90%;
        }

        .checkmark {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            display: block;
            stroke-width: 2;
            stroke: #fff;
            stroke-miterlimit: 10;
            box-shadow: inset 0px 0px 0px var(--primary-color);
            animation: fill .4s ease-in-out .4s forwards, scale .3s ease-in-out .9s both;
            margin: 0 auto 30px;
        }

        .checkmark__circle {
            stroke-dasharray: 166;
            stroke-dashoffset: 166;
            stroke-width: 2;
            stroke-miterlimit: 10;
            stroke: var(--primary-color);
            fill: none;
            animation: stroke 0.6s cubic-bezier(0.65, 0, 0.45, 1) forwards;
        }

        .checkmark__check {
            transform-origin: 50% 50%;
            stroke-dasharray: 48;
            stroke-dashoffset: 48;
            animation: stroke 0.3s cubic-bezier(0.65, 0, 0.45, 1) 0.8s forwards;
        }

        @keyframes stroke {
            100% {
                stroke-dashoffset: 0;
            }
        }

        @keyframes scale {
            0%, 100% {
                transform: none;
            }
            50% {
                transform: scale3d(1.1, 1.1, 1);
            }
        }

        @keyframes fill {
            100% {
                box-shadow: inset 0px 0px 0px 30px var(--primary-color);
            }
        }

        h1 {
            color: var(--primary-color);
            margin-bottom: 20px;
        }

        p {
            margin-bottom: 20px;
            line-height: 1.5;
        }

        .timer {
            font-size: 24px;
            font-weight: bold;
            color: var(--accent-color);
            margin-bottom: 30px;
        }

        .home-button {
            background-color: var(--primary-color);
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.1s;
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }

        .home-button:hover {
            background-color: var(--secondary-color);
            transform: translateY(-2px);
        }

        .home-button i {
            margin-right: 8px;
        }

        @media (max-width: 480px) {
            .container {
                padding: 30px;
            }

            .checkmark {
                width: 60px;
                height: 60px;
            }

            h1 {
                font-size: 24px;
            }

            .timer {
                font-size: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
            <circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/>
            <path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/>
        </svg>
        <h1>Order Confirmed!</h1>
        <p>Thank you for your purchase. Your order has been successfully placed and will be processed shortly.</p>
        <div class="timer" id="timer">Redirecting to Home</div>
        <button class="home-button" onclick="goToHome()">
            <i class="fas fa-home"></i> Go to Home
        </button>
    </div>

    <script>
        let timeLeft = 10;
        const timerElement = document.getElementById('timer');

        function updateTimer() {
            timerElement.textContent = `Redirecting in: ${timeLeft}`;
            if (timeLeft === 0) {
                goToHome();
            } else {
                timeLeft--;
                setTimeout(updateTimer, 1000);
            }
        }

        function goToHome() {
            window.location.href = 'home.jsp';
        }

        updateTimer();
    </script>
</body>
</html>

