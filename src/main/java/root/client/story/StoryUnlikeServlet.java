package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.User;
import root.reps.StoryLikeDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class StoryUnlikeServlet
 */
@WebServlet("/StoryUnlike")
public class StoryUnlikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryUnlikeServlet() {
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
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        User user = (User) req.getSession().getAttribute("user");
	        if (user == null) {
	            resp.sendRedirect("login");
	            return;
	        }

	        try {
	            long storyId = Long.parseLong(req.getParameter("storyId"));
	            StoryLikeDAO.unlikeStory(user.getId(), storyId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        resp.sendRedirect("storyDetail?id=" + req.getParameter("storyId"));
	    }
	}

