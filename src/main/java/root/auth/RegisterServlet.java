package root.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.User;
import root.reps.UserDAO;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    /**
	 * xử lý đăng ký
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
            return;
        }

        try {
            if (UserDAO.existsUsername(username)) {
                req.setAttribute("error", "Tên đăng nhập đã tồn tại!");
                req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
                return;
            }

            String hashedPassword = hashPassword(password);

            User user = new User();
            user.setUserName(username);
            user.setPassWord(hashedPassword);
            user.setRole("reader");
            user.setActive(1);
            user.setScore(0);
            user.setName("Người dùng mới");
            user.setImage("default.png");
            user.setLevelId(1);

            boolean success = UserDAO.insert(user);
            if (success) {
                req.getSession().setAttribute("success", "Đăng ký thành công! Mời bạn đăng nhập.");
                resp.sendRedirect(req.getContextPath() + "/login");
            } else {
                req.setAttribute("error", "Đăng ký thất bại! Hệ thống lỗi.");
                req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
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