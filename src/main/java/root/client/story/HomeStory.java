package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Category;
import root.entities.Story;
import root.reps.CategoryDAO;
import root.reps.StoryDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class StoryServlet
 */
@WebServlet("/Home")
public class HomeStory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeStory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categories = CategoryDAO.getAll();
            List<Story> stories = StoryDAO.getTopStories("newest");
            List<Story> mostViewedStories = StoryDAO.getTopStories("most_viewed");
            List<Story> topRatedStories = StoryDAO.getTopStories("highest_score");
            request.setAttribute("categories", categories);
          request.setAttribute("stories", stories);
            request.setAttribute("mostViewedStories", mostViewedStories);
            request.setAttribute("topRatedStories", topRatedStories);
            request.getRequestDispatcher("/client/story/listStory.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
