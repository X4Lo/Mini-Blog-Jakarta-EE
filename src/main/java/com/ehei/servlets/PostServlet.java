package com.ehei.servlets;

import com.ehei.beans.Comment;
import com.ehei.beans.Post;
import com.ehei.beans.User;
import com.ehei.doa.CommentDao;
import com.ehei.doa.PostDao;
import com.ehei.doa.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PostServlet", value = "/post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") == null) { // utilisateur n'est pas connecté
            resp.sendRedirect("login.jsp");
        } else {
            int id = Integer.parseInt(req.getParameter("id"));

            if (req.getParameter("id") != null) {
                Post post = PostDao.getPostById(id);
                String bannerPath = "uploads" + File.separator + "banners" + File.separator + post.getBanner();
                post.setBanner(bannerPath);

                User author = UserDao.getUserById(post.getAuthorId());
                List<Comment> comments = CommentDao.getPostCommentsOffset(post.getId(), 10, 0);

                for (Comment comment : comments) {
                    User commentUser = UserDao.getUserById(comment.getAuthorId());
                    comment.setAuthorUsername(commentUser.getUsername());
                }

                req.setAttribute("post", post);
                req.setAttribute("author", author);
                req.setAttribute("comments", comments);
                req.getRequestDispatcher("/post.jsp").forward(req, resp);
            }
        }

    }
}
