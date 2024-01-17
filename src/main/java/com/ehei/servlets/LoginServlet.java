package com.ehei.servlets;

import com.ehei.beans.User;
import com.ehei.doa.UserDao;
import com.ehei.tools.ConnectionDB;
import com.ehei.tools.Tools;
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

        if (session.getAttribute("is_auth") != null) {
            resp.sendRedirect("blogs.jsp");
        } else {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (username.isEmpty() || password.isEmpty()) { // username or password empty
                resp.sendRedirect("login.jsp");
            } else {// username and password not empty
                User user = UserDao.getUserByUsername(username);
                if (user != null) { // username trouvé
                    if (!user.getPassword().equals(Tools.toMd5(password))) { // password incorrect
                        if (user.getAttempts() < 2) { // incrementation des tentatives
                            user.setAttempts(user.getAttempts() + 1);
                        } else { // bloquage du compte
                            user.setAttempts(user.getAttempts() + 1);
                            user.setIs_locked(true);
                        } // fin: bloquage du compte

                        resp.sendRedirect("login.jsp");
                        // fin: password incorrecte
                    } else { // password correcte
                        session.setAttribute("is_auth", "true");
                        session.setAttribute("username", user.getUsername());
                        session.setAttribute("id", user.getId());
                    } // fin: password correcte
                } else { // username introuvable
                    resp.sendRedirect("login.jsp");
                } // fin: username introuvable
            }// fin: username and password not empty


        }


    }
}
