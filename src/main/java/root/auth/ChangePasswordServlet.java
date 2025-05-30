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

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    /**
	 * Đổi mật khẩu
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String current = req.getParameter("currentPassword");
        String newPw = req.getParameter("newPassword");
        String confirm = req.getParameter("confirmPassword");

        if (!user.getPassWord().equals(current)) {
            req.setAttribute("error", "Mật khẩu hiện tại không đúng!");
        } else if (!newPw.equals(confirm)) {
            req.setAttribute("error", "Mật khẩu mới và xác nhận không khớp!");
        } else {
            try {
                boolean updated = UserDAO.updatePassword(user.getId(), newPw);
                if (updated) {
                    user.setPassWord(newPw);
                    session.setAttribute("user", user);
                    req.setAttribute("success", "Đổi mật khẩu thành công!");
                } else {
                    req.setAttribute("error", "Đổi mật khẩu thất bại!");
                }
            } catch (SQLException e) {
                req.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            }
        }

        req.getRequestDispatcher("/auth/user_info.jsp").forward(req, resp);
    }
}

