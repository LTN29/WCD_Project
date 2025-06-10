package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class StoryFollowServlet
 */
@WebServlet("/StoryFollow")
public class StoryFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryFollowServlet() {
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
	    root.entities.StoryFollow follow = new root.entities.StoryFollow(0, userId, storyId, now, 1);

	    try {
	        long followId = root.reps.StoryFollowDAO.insert(follow);
	        if (followId > 0) {
	        	root.reps.UserDAO.addScoreAndUpdateLevel(userId, 3);
	        	root.reps.StoryFollowDAO.updateFollowNumber(storyId);
	            root.entities.User newUser = root.reps.UserDAO.findById(userId);
	            request.getSession().setAttribute("user", newUser);
	            request.getSession().setAttribute("message", "Bạn đã theo dõi truyện! +2 điểm");
	        } else {
	            request.getSession().setAttribute("message", "Bạn đã theo dõi truyện này rồi!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("message", "Có lỗi xảy ra khi theo dõi truyện!");
	    }

	    response.sendRedirect("storyDetail?id=" + storyId);
	}


}
