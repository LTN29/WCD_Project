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
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {
    /**
	 * Xóa tài khoản 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            boolean deleted = UserDAO.deleteUserById(user.getId());
            if (deleted) {
                session.invalidate();
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                req.setAttribute("error", "Không thể xóa tài khoản.");
                req.getRequestDispatcher("/client/user_info.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("/auth/user_info.jsp").forward(req, resp);
        }
    }
}
