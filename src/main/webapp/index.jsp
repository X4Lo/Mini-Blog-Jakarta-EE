<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Blog | Home</title>
</head>
<body>
<h1>Welcome to Blog!
</h1>
<br/>
<%
    if (session.getAttribute("username") == null) {
%>
<h3>
    <a href="login">Login</a> or <a href="register">Register</a> to start posting!
</h3>
<%
} else {
%>
<h3>Welcome back ${sessionScope.username}!</h3>
<%
    }
%>

</body>
</html>