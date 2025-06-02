package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Story;

import java.io.IOException;

/**
 * Servlet implementation class StoryLikeServlet
 */
@WebServlet("/StoryLike")
public class StoryLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    root.entities.User user = (root.entities.User) request.getSession().getAttribute("user");
	    if (user == null) {
	        response.sendRedirect("login");
	        return;
	    }

	    long userId = user.getId();
	    int storyId = Integer.parseInt(request.getParameter("storyId"));
	    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
	    root.entities.StoryLike like = new root.entities.StoryLike(0, userId, storyId, now, 1);

	    try {
	        long likeId = root.reps.StoryLikeDAO.insert(like);
	        if (likeId > 0) {
	        	root.reps.UserDAO.addScoreAndUpdateLevel(userId, 1);
	        	root.reps.StoryLikeDAO.updateLikeNumber(storyId);
	            root.entities.User newUser = root.reps.UserDAO.findById(userId);
	            request.getSession().setAttribute("user", newUser);
	            request.getSession().setAttribute("message", "Bạn đã like truyện! +1 điểm");
	        } else {
	            request.getSession().setAttribute("message", "Bạn đã like truyện này rồi!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("message", "Có lỗi xảy ra khi like truyện!");
	    }
	    response.sendRedirect("storyDetail?id=" + storyId);
	}

}
