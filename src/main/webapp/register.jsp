<%--
  Created by IntelliJ IDEA.
  User: X4Lo
  Date: 18/01/2024
  Time: 07:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>


<form method="post" action="register" enctype="multipart/form-data">
    <label>Username:</label> <br>
    <input type="text" name="username"> <br>

    <label>Password:</label> <br>
    <input type="text" name="password"> <br>
    <label>Confirm Password:</label> <br>
    <input type="text" name="passwordConfirm"> <br>

    <label>Picture:</label> <br>
    <input type="file" name="picture"> <br><br>


    <input type="submit" value="Register">
</form>

</body>
</html>
