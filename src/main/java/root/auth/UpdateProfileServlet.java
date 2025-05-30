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


@WebServlet("/updateProfile")

public class UpdateProfileServlet extends HttpServlet {
    /**
	 * chỉnh sửa lại profile cho user đó ku
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String name = req.getParameter("name");
        try {
            user.setName(name);
            boolean updated = UserDAO.updateName(user.getId(), name);
            if (updated) {
                session.setAttribute("user", user);
                req.setAttribute("success", "Cập nhật tên thành công!");
            } else {
                req.setAttribute("error", "Không thể cập nhật tên.");
            }
        } catch (SQLException e) {
            req.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }

        req.getRequestDispatcher("/auth/user_info.jsp").forward(req, resp);
    }
}

