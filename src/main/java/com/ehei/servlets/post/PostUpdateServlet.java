package com.ehei.servlets.post;

import com.ehei.beans.Post;
import com.ehei.doa.PostDao;
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

@WebServlet(name = "PostUpdateServlet", value = "/post/update")
public class PostUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") == null) { // utilisateur n'est pas connecté
            resp.sendRedirect("blogs.jsp");
        } else { // utilisateur est deja connecté
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            Part filePart = req.getPart("banner");

            if (req.getParameter("id").isEmpty() || title.isEmpty() || content.isEmpty() || filePart == null) { // l'un des champs est vide
                resp.sendRedirect("blogs.jsp");
                //todo: Redirection: vers le post
            } else { // les champs sont pas vide
                Post post = PostDao.getPostById(id);

                if (post == null) { // aucun post trouvé avec l'id fournie
                    resp.sendRedirect("blogs.jsp");
                } else { // post trouvé
                    int userId = (int) session.getAttribute("id");

                    if (post.getAuthorId() != userId) { // l'utilisateur n'est pas l'auteur du post
                        resp.sendRedirect("blogs.jsp");
                    } else { // l'utilisateur est l'auteur du post
                        //todo: suppression de l'ancien banner

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

                        post.setTitle(title);
                        post.setContent(content);
                        post.setBanner(newFileName);
                        PostDao.Update(post);

                        resp.sendRedirect("blogs.jsp");
                        //todo: Redirection: vers le post modifée
                        //todo: Confirm Msg: Post supprimée
                    } // fin: l'utilisateur est l'auteur du post
                } // fin: post trouvé
            } // fin: les champs sont pas vide
        } // fin: utilisateur est deja connecté
    }
}
