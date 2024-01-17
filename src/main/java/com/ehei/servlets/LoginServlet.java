package com.ehei.servlets;

import com.ehei.tools.ConnectionDB;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") != null) {
            resp.sendRedirect("blogs.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") == null) {
            resp.sendRedirect("login.jsp");
        } else {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (username.isEmpty() || password.isEmpty()) {
                resp.sendRedirect("login.jsp");
            } else {
                String query = "SELECT * FROM users WHERE username = ?;";
                try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
                    preparedStatement.setString(1, username);

                    ResultSet rs = preparedStatement.executeQuery();
                    if (!rs.next()) { // utilisateur introuvable

                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String cnxString = "jdbc:mysql://localhost:3306/crud_project?useSSL=false";
                Connection connection = DriverManager.getConnection(cnxString, "root", "password");
                String query = "SELECT * FROM USERS WHERE username=? and password=?;";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    // using session to save the login state
                    session.setAttribute("is_auth", "true");

                    // saving the username in the cookie
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(36000);
                    resp.addCookie(cookie);

                    resp.sendRedirect("list.jsp");
                } else {
                    resp.sendRedirect("login.jsp");
                }

            } catch (Exception e) {
                System.out.println("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                e.printStackTrace();
            }

        }


    }
}
