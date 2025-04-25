<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Attendance</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        h1 {
            color: #333;
        }
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007BFF;
            color: white;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Attendance Records</h1>

    <c:choose>
        <c:when test="${not empty attendanceList}">
            <table>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Date</th>
                    <th>Present</th>
                </tr>
                <c:forEach var="attendance" items="${attendanceList}">
                    <tr>
                        <td>${attendance.student.id}</td>
                        <td>${attendance.student.fullName}</td>
                        <td>${attendance.date}</td>
                        <td><c:out value="${attendance.present ? 'Yes' : 'No'}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No attendance records found.</p>
        </c:otherwise>
    </c:choose>

    <a href="admin_dashboard" class="btn">Back to Dashboard</a>
</body>
</html>
