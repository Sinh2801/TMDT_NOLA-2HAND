package vn.edu.nlu.it.ltw.group8.nola_2hand.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.nlu.it.ltw.group8.nola_2hand.dao.ReviewDAO;
import vn.edu.nlu.it.ltw.group8.nola_2hand.model.User;

import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete-review"})
public class DeleteReviewServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int rid = Integer.parseInt(request.getParameter("rid"));
            int pid = Integer.parseInt(request.getParameter("pid"));
            User user = (User) request.getSession().getAttribute("acc");

            if (user != null) {
                ReviewDAO dao = new ReviewDAO();
                dao.deleteReview(rid, user.getId());
            }
            response.sendRedirect("detail?pid=" + pid);
        }
    }
