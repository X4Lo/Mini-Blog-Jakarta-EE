package com.ehei.servlets;  
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession session = req.getSession();
if (session.getAttribute("is_auth") != null) {
    session.invalidate();
    resp.sendRedirect("login.jsp");
} else {
    resp.sendRedirect("login.jsp");
}
}
}