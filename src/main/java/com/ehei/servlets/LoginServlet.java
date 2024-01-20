package com.ehei.servlets;

import com.ehei.beans.User;
import com.ehei.doa.UserDao;
import com.ehei.tools.Tools;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") != null) {
            resp.sendRedirect("blogs");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") != null) {
            resp.sendRedirect("blogs");
        } else {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (username.isEmpty() || password.isEmpty()) { // username or password empty
                resp.sendRedirect("login.jsp");
                //todo: message d'erreur
            } else {// username and password not empty
                User user = UserDao.getUserByUsername(username);
                if (user != null) { // username trouvé
                    if (user.isLocked()) { // compte bloqué
                        resp.sendRedirect("login.jsp");
                        //todo: message d'erreur

                        //fin: compte bloqué
                    } else { // compte non bloqué
                        if (!user.getPassword().equals(Tools.toMd5(password))) { // password incorrect
                            if (user.getAttempts() < 2) { // incrementation des tentatives
                                user.setAttempts(user.getAttempts() + 1);
                            } else { // bloquage du compte
                                user.setAttempts(user.getAttempts() + 1);
                                user.setLocked(true);
                            } // fin: bloquage du compte

                            resp.sendRedirect("login.jsp");
                            //todo: message d'erreur

                            // fin: password incorrecte
                        } else { // password correcte
                            String picturePath = "uploads" + File.separator + "pictures" + File.separator + user.getPicture();

                            session.setAttribute("is_auth", "true");
                            session.setAttribute("username", user.getUsername());
                            session.setAttribute("id", user.getId());
                            session.setAttribute("picture", picturePath);

                            resp.sendRedirect("blogs");
                        } // fin: password correcte
                    } // fin: compte non bloqué

                } else { // username introuvable
                    resp.sendRedirect("login.jsp");
                    //todo: message d'erreur

                } // fin: username introuvable
            }// fin: username and password not empty


        }


    }
}
