package com.ehei.servlets.post;

import com.ehei.beans.Post;
import com.ehei.doa.PostDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(name = "PostDeleteServlet", value = "/PostDeleteServlet")
public class PostDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("is_auth") == null) { // utilisateur n'est pas connecté
            resp.sendRedirect("login.jsp");
        } else { //utilisateur est connecté
            int id = Integer.parseInt(req.getParameter("id"));

            if (req.getParameter("id").isEmpty()) { // aucun id est fournie
                resp.sendRedirect("blogs.jsp");
                //todo: Redirection: vers le post
            } else { // l'id a etait fournie
                Post post = PostDao.getPostById(id);

                if (post == null) { // aucun post trouvé avec l'id fournie
                    resp.sendRedirect("blogs.jsp");
                } else { // post trouvé
                    int userId = (int) session.getAttribute("id");
                    if (post.getAuthorId() != userId) { // l'utilisateur n'est pas l'auteur du post
                        resp.sendRedirect("blogs.jsp");
                    } else { // l'utilisateur est l'auteur du post
                        //todo: suppression du banner

                        PostDao.removePostById(id);

                        resp.sendRedirect("blogs.jsp");
                        //todo: Confirm Msg: Post supprimée
                    } // fin: l'utilisateur est l'auteur du post
                } // fin: l'id a etait fournie
            } // fin: aucun id est fournie
        } // fin: utilisateur est connecté
    }
}
