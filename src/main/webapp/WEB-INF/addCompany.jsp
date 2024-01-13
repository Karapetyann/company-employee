<%--
  Created by IntelliJ IDEA.
  User: Hakob
  Date: 12.01.2024
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Company</title>
</head>
<body>
Add Company <br>
<form method="post" action="/addCompany">
    Company name: <input type="text" name="companyName"><br><br>
    Company address: <input type="text" name="companyAddress"><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
