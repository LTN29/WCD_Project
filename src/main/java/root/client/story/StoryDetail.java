package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Category;
import root.entities.Chapter;
import root.entities.Story;
import root.entities.StoryComment;
import root.entities.User;
import root.reps.CategoryDAO;
import root.reps.ChapterDAO;
import root.reps.StoryCommentDAO;
import root.reps.StoryDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class StoryDetail
 */
@WebServlet("/storyDetail")
public class StoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoryDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int storyId = Integer.parseInt(req.getParameter("id"));
			Story story = StoryDAO.getById(storyId);
			List<Chapter> chapters = ChapterDAO.getByStoryId(storyId);
			List<Category> categories = CategoryDAO.getAll();
			User user = (User) req.getSession().getAttribute("user");
			List<StoryComment> commentList = StoryCommentDAO.getCommentsVisibleToUser(storyId,
					user != null ? user.getId() : -1);

			root.entities.User useR = (root.entities.User) req.getSession().getAttribute("user");
			boolean isLiked = false;
			boolean isFollowing = false;
			if (useR != null) {
				int pendingCount = StoryCommentDAO.countPendingComments(user.getId(), storyId);
				req.setAttribute("pendingCount", pendingCount);
				isLiked = root.reps.StoryLikeDAO.isLiked(useR.getId(), storyId);
				isFollowing = root.reps.StoryFollowDAO.isFollowing(user.getId(), storyId);
			}
			req.setAttribute("isLiked", isLiked);
			req.setAttribute("isFollowing", isFollowing);
			req.setAttribute("categories", categories);
			req.setAttribute("story", story);
			req.setAttribute("chapters", chapters);
			req.setAttribute("commentList", commentList);
			req.getRequestDispatcher("/client/story/storyDetail.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();

			resp.getWriter().println("Lỗi khi load chi tiết truyện: " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
