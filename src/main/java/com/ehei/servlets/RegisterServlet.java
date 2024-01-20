package com.ehei.servlets;

import com.ehei.beans.User;
import com.ehei.doa.UserDao;
import com.ehei.tools.Tools;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") != null) {
            resp.sendRedirect("blogs");
        } else {
            resp.sendRedirect("register.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") != null) { // utilisateur est deja connecté
            resp.sendRedirect("blogs.jsp");
        } else { //utilisateur n'est pas connecté
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String passwordConfirm = req.getParameter("passwordConfirm");
            Part filePart = req.getPart("picture");

            if (filePart == null) {
                Tools.log(Level.ERROR, "file not uploaded");
            }

            if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || filePart == null) { // l'un des champs est vide
                resp.sendRedirect("register.jsp");
                //todo: Error Msg: (entrez tous les champs)
            } else { // les champs sont pas vide
                if (!password.equals(passwordConfirm)) { // mdp != mdpConfirm
                    resp.sendRedirect("register.jsp");
                    //todo: Error Msg: mdp != mdpConfirm

                    // fin: mdp != mdpConfirm
                } else { // mdp == mdgConfirm
                    if (UserDao.getUserByUsername(username) != null) { // username deja utilisé
                        resp.sendRedirect("register.jsp");
                        //todo: Error Msg: username deja utilisé

                        // fin: username deja utilisé
                    } else { // username est unique
                        String newFileName = "";
                        try {
                            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                            newFileName = UUID.randomUUID() + "_" + originalFileName;

                            // creating the uploads directory
                            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "pictures";
                            File uploadsDir = new File(uploadPath);
                            if (!uploadsDir.exists()) {
                                uploadsDir.mkdir();
                            }

                            InputStream inputStream = filePart.getInputStream();
                            Files.copy(inputStream, Paths.get(uploadPath + File.separator + newFileName));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        User user = new User(username, Tools.toMd5(password), newFileName, 0, false);
                        UserDao.Add(user);

                        resp.sendRedirect("login.jsp");
                        //todo: Confirmation Msg: Compte crée
                    } // fin: username est unique
                } // fin: mdp == mdgConfirm
            } // fin: les champs sont pas vide
        } // fin: utilisateur n'est pas connecté
    }
}
