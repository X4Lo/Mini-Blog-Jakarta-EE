<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("is_auth") != null) {
        response.sendRedirect("list.jsp");
    }
%>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form method="post" action="login">
    <label>Username:
        <input type="text" name="username">
    </label>
    <br>
    <label>Username:
        <input type="password" name="password">
    </label>

    <input type="submit" value="Login">
</form>
</body>
</html>
