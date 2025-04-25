<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mark Attendance</title>
</head>
<body>
    <h1>Mark Attendance</h1>
    <form action="/mark_attendance" method="post">
        <label for="studentId">Student ID:</label>
        <input type="text" id="studentId" name="studentId" required><br>
        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required><br>
        <label for="present">Present:</label>
        <input type="checkbox" id="present" name="present" value="true"><br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>