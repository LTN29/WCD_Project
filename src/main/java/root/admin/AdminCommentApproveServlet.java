package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.reps.ChapterCommentDAO;
import root.reps.StoryCommentDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/comment/approve")
public class AdminCommentApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminCommentApproveServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type"); 
		String action = req.getParameter("action"); 
		long commentId = Long.parseLong(req.getParameter("id"));

		boolean isApprove = "approve".equalsIgnoreCase(action);
		boolean success = false;

		try {
			if ("chapter".equalsIgnoreCase(type)) {
				success = ChapterCommentDAO.setActive(commentId, isApprove);
			} else if ("story".equalsIgnoreCase(type)) {
				success = StoryCommentDAO.setActive(commentId, isApprove);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	
		resp.sendRedirect(req.getContextPath() + "/admin/comment");
	}
}
