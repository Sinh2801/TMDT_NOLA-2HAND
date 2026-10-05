package vn.edu.nlu.it.ltw.group8.nola_2hand.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.nlu.it.ltw.group8.nola_2hand.dao.UserDAO;
import vn.edu.nlu.it.ltw.group8.nola_2hand.model.User;

import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        if(user != null) {
            UserDAO dao = new UserDAO();
            dao.updateRememberToken(user.getUsername(), null);
        }
        session.invalidate();

        Cookie tokenCookie = new Cookie("remember_token", "");
        tokenCookie.setMaxAge(0);
        response.addCookie(tokenCookie);

        response.sendRedirect("home");
    }


}