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
import java.sql.SQLException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if ("login".equals(action)) {
                handleLogin(req, resp);
            } else if ("register".equals(action)) {
                handleRegister(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        }
    }


    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = UserDAO.checkLogin(username, password);
            if (user != null && user.getActive() == 1) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                if ("admin".equalsIgnoreCase(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/admin");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
                req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi hệ thống khi đăng nhập!");
            req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        }
    }


    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
            return;
        }

        if (!password.matches("(?=.*[A-Z]).{6,}")) {
            req.setAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự và 1 chữ hoa!");
            req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
            return;
        }

        if (UserDAO.existsUsername(username)) {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
            return;
        }

        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setRole("reader");
        user.setActive(1);
        user.setScore(0);
        user.setName("Người dùng mới");
        user.setImage("default.png");

        UserDAO.insert(user);
        req.getSession().setAttribute("success", "Đăng ký thành công! Mời bạn đăng nhập.");
        resp.sendRedirect(req.getContextPath() + "/auth/login.jsp");
    }
}