<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 50px;
        }
        h1 {
            color: #333;
        }
        .dashboard {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .button {
            background: #007BFF;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block; /* Ensures the button behaves like a block element */
            margin: 10px 0; /* Adds vertical spacing between buttons */
        }
        .message {
            color: green;
        }
    </style>
</head>
<body>
    <div class="dashboard">
        <h1>Welcome, ${student.fullName}!</h1>
        <p>Your ID: ${student.id}</p>
        <form action="dashboard" method="post">
            <button type="submit" class="button">Mark Attendance</button>
        </form>
        <c:if test="${not empty message}">
            <p class="message">${message}</p>
        </c:if>
        <a href="attendance" class="button">View Attendance</a>
        <a href="logout" style="color: red;">Logout</a>
        
    </div>
</body>
</html>