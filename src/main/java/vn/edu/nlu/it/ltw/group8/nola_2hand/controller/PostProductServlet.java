package vn.edu.nlu.it.ltw.group8.nola_2hand.controller;

import vn.edu.nlu.it.ltw.group8.nola_2hand.dao.ProductDAO;
import vn.edu.nlu.it.ltw.group8.nola_2hand.model.Product;
import vn.edu.nlu.it.ltw.group8.nola_2hand.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PostProductServlet", urlPatterns = {"/post-product"})
public class PostProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("account");

        // Chặn nếu chưa đăng nhập hoặc không phải là Seller
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (!"Seller".equalsIgnoreCase(currentUser.getRole())) {
            request.setAttribute("error", "Bạn phải nâng cấp lên tài khoản Người Bán để đăng tin.");
            request.getRequestDispatcher("upgrade-seller.jsp").forward(request, response);
            return;
        }

        // Nếu hợp lệ, chuyển tới trang điền form đăng tin
        request.getRequestDispatcher("post-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("account");

        if (currentUser == null || !"Seller".equalsIgnoreCase(currentUser.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Lấy dữ liệu từ form JSP
            String name = request.getParameter("name");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            double price = Double.parseDouble(request.getParameter("price"));
            int condition = Integer.parseInt(request.getParameter("conditionPercentage"));
            String description = request.getParameter("description");

            // Đưa vào Model
            Product p = new Product();
            p.setSellerId(currentUser.getId());
            p.setName(name);
            p.setCategoryId(categoryId);
            p.setCurrentPrice(price);
            p.setConditionPercentage(condition);
            p.setDescription(description);

            // Gọi DAO lưu vào DB
            ProductDAO dao = new ProductDAO();
            boolean isSuccess = dao.insertProduct(p);

            if (isSuccess) {
                // Đăng tin thành công, chờ duyệt
                response.sendRedirect("seller-dashboard.jsp?status=pending");
            } else {
                request.setAttribute("error", "Đăng tin thất bại, vui lòng thử lại!");
                request.getRequestDispatcher("post-product.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Dữ liệu nhập vào không hợp lệ!");
            request.getRequestDispatcher("post-product.jsp").forward(request, response);
        }
    }
}