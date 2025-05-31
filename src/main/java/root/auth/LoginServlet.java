package root.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import root.entities.User;
import root.reps.UserDAO;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            String hashedPassword = hashPassword(password);
            User user = UserDAO.checkLogin(username, hashedPassword);

            if (user != null && user.getActive() == 1) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                if ("admin".equalsIgnoreCase(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/Admin");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/Home");
                }
            } else {
                req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
                req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi hệ thống khi đăng nhập!");
            req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        }
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashed = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashed) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
