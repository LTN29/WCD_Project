package root.client.chapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.ChapterComment;
import root.entities.User;
import root.reps.ChapterCommentDAO;
import root.reps.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ChapterComment")
public class ChapterCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChapterCommentServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			resp.sendRedirect("login");
			return;
		}

		String content = req.getParameter("content");
		int chapterId = Integer.parseInt(req.getParameter("chapterId"));

		if (content == null || content.trim().isEmpty()) {
			req.getSession().setAttribute("message", "Nội dung bình luận không được để trống!");
			resp.sendRedirect(req.getContextPath() + "/ChapterDetail?chapterId=" + chapterId);
			return;
		}

		ChapterComment comment = new ChapterComment();
		comment.setContent(content.trim());
		comment.setActive(0);
		comment.setChapterId(chapterId);
		comment.setUserId(user.getId());

		try {
			int pending = ChapterCommentDAO.countPendingComments(user.getId(), chapterId);
			if (pending >= 3) {
				req.getSession().setAttribute("message", "Bạn chỉ được gửi tối đa 3 bình luận đang chờ duyệt.");
				resp.sendRedirect(req.getContextPath() + "/ChapterDetail?chapterId=" + chapterId);
				return;
			}

			boolean first = ChapterCommentDAO.isFirstComment(user.getId(), chapterId);
			int insertedId = ChapterCommentDAO.insert(comment);

			if (insertedId > 0) {
				if (first) {
					UserDAO.addScoreAndUpdateLevel(user.getId(), 3);
				}
				User updatedUser = UserDAO.findById(user.getId());
				req.getSession().setAttribute("user", updatedUser);
				req.getSession().setAttribute("message", "Bình luận của bạn đã được gửi và đang chờ duyệt!"
						+ (first ? " +3 điểm vì đây là bình luận đầu tiên của bạn!" : ""));
			} else {
				req.getSession().setAttribute("message", "Không thể gửi bình luận.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			req.getSession().setAttribute("message", "Có lỗi xảy ra khi gửi bình luận.");
		}

		resp.sendRedirect(req.getContextPath() + "/ChapterDetail?chapterId=" + chapterId);
	}
}
