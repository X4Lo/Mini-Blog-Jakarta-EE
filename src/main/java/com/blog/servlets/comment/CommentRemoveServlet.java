package com.blog.servlets.comment;

import com.blog.beans.Comment;
import com.blog.beans.User;
import com.blog.dao.CommentDao;
import com.blog.dao.UserDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CommentRemoveServlet", value = "/commentRemove")
public class CommentRemoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") == null) { // utilisateur n'est pas connecté
            resp.sendRedirect("login.jsp");
        } else { // utilisateur est connecté
            int postId = Integer.parseInt(req.getParameter("postId"));
            int commentId = Integer.parseInt(req.getParameter("commentId"));

            if (req.getParameter("postId") == null || req.getParameter("commentId") == null) { // l'un des champs est vide
                resp.sendRedirect("blogs");
                //todo: Error Msg: (entrez tous les champs)
            } else { // les champs sont pas vide
                int userId = (int) session.getAttribute("id");
                User author = UserDao.getUserById(userId);
                Comment comment = CommentDao.getCommentById(commentId);

                if (author.getId() == comment.getAuthorId()) {
                    CommentDao.removeCommentById(comment.getId());
                }

                resp.sendRedirect("post?id=" + postId);
            } // fin: les champs sont pas vide
        } // fin: utilisateur est connecté
    }
}
