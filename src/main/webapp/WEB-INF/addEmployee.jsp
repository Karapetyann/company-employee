<%@ page import="com.example.companyemployee.model.Company" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Hakob
  Date: 12.01.2024
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
<% List<Company> companies = (List<Company>) request.getAttribute("companies"); %>

Add Employee <br>
<form method="post" action="/addEmployee">
    name: <input type="text" name="employeeName"><br>
    surname: <input type="text" name="employeeSurname"><br>
    email: <input type="text" name="employeeEmail"><br>
    <select name="companyId">
        <%
        for (Company company : companies) {%>
        <option value="<%=company.getId()%>"><%=company.getName()%></option>
        <%}%>
    </select>
    <input type="submit" value="Add">
</form>
</body>
</html>
