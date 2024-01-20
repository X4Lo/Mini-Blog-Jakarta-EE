package com.ehei.servlets;

import com.ehei.beans.Post;
import com.ehei.doa.PostDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogsServlet", value = "/blogs")
public class BlogsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") == null) { // utilisateur n'est pas connecté
            resp.sendRedirect("login.jsp");
        } else {
            List<Post> posts = PostDao.getPosts();
            for (Post post : posts) {
                String bannerPath = "uploads" + File.separator + "banners" + File.separator + post.getBanner();
                post.setBanner(bannerPath);
            }

            req.setAttribute("posts", posts);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/blogs.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
