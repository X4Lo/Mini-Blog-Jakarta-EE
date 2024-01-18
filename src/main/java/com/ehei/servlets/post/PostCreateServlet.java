package com.ehei.servlets.post;

import com.ehei.beans.Post;
import com.ehei.beans.User;
import com.ehei.doa.PostDao;
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
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet(name = "PostCreateServlet", value = "/post/create")
public class PostCreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") == null) { // utilisateur n'est pas connecté
            resp.sendRedirect("login.jsp");
        } else { // utilisateur est connecté
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            Part filePart = req.getPart("banner");

            if (title.isEmpty() || content.isEmpty() || filePart == null) { // l'un des champs est vide
                resp.sendRedirect("blogs.jsp");
                //todo: Error Msg: (entrez tous les champs)
            } else { // les champs sont pas vide
                String newFileName = "";
                try {
                    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    newFileName = UUID.randomUUID() + "_" + originalFileName;

                    // creating the uploads directory
                    String uploadPath = getServletContext().getInitParameter("upload.path") + File.separator + "uploads";
                    File uploadsDir = new File(uploadPath);
                    if (!uploadsDir.exists()) {
                        uploadsDir.mkdir();
                    }

                    InputStream inputStream = filePart.getInputStream();
                    Files.copy(inputStream, Paths.get(uploadPath + File.separator + newFileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int userId = (int) session.getAttribute("id");
                Post post = new Post(title, content, newFileName, userId, LocalDateTime.now());
                PostDao.Add(post);

                resp.sendRedirect("blogs.jsp");
                //todo: Redirection: vers le post crée
            } // fin: les champs sont pas vide
        } // fin: utilisateur est connecté
    }
}
