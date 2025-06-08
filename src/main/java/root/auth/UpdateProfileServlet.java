package root.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import root.entities.LevelUser;
import root.entities.User;
import root.reps.LevelUserDAO;
import root.reps.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateProfile")

public class UpdateProfileServlet extends HttpServlet {
	/**
	 * chỉnh sửa lại profile cho user
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		int score = user.getScore();
		LevelUser level = null;
		try {
			level = LevelUserDAO.getByScore(score);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String levelName = (level != null) ? level.getLevel() : "Chưa xác định";
		user.setLevel(levelName);
		session.setAttribute("user", user);
		req.setAttribute("levelName", levelName);


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
