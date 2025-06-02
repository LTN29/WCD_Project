package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Story;
import root.reps.StoryDAO;
import root.reps.StoryFollowDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class MyFollowedStoriesServlet
 */
@WebServlet("/MyFollowedStoriesServlet")
public class MyFollowedStoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyFollowedStoriesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		root.entities.User user = (root.entities.User) req.getSession().getAttribute("user");
		if (user == null) {
			resp.sendRedirect("login");
			return;
		}
		try {
			List<Story> followedStories = StoryDAO.getStoriesFollowedByUser(user.getId());
			req.setAttribute("followedStories", followedStories);
			req.getRequestDispatcher("/client/story/myFollowedStories.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
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
