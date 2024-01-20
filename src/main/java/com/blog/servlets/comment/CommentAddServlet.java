package com.blog.servlets.comment;

import com.blog.beans.Comment;
import com.blog.dao.CommentDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "CommentAddServlet", value = "/commentAdd")
public class CommentAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") == null) { // utilisateur n'est pas connecté
            resp.sendRedirect("login.jsp");
        } else { // utilisateur est connecté
            int postId = Integer.parseInt(req.getParameter("postId"));
            String content = req.getParameter("content");

            if (req.getParameter("postId") == null || content.isEmpty()) { // l'un des champs est vide
                resp.sendRedirect("blogs");
                //todo: Error Msg: (entrez tous les champs)
            } else { // les champs sont pas vide
                int userId = (int) session.getAttribute("id");
                Comment comment = new Comment(postId, userId, content, LocalDateTime.now());
                CommentDao.Add(comment);

                resp.sendRedirect("post?id=" + postId);
                //todo: Redirection: vers le post crée
            } // fin: les champs sont pas vide
        } // fin: utilisateur est connecté

    }
}
