package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Story;
import root.reps.CategoryDAO;
import root.reps.StoryDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class StoryServlet
 */
@WebServlet("/story")
public class StoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String keyword = req.getParameter("keyword");
            String categoryIdStr = req.getParameter("categoryId");
            String typeStr = req.getParameter("type");
            String ajax = req.getParameter("ajax");

            Integer categoryId = (categoryIdStr != null && !categoryIdStr.isEmpty()) ? Integer.parseInt(categoryIdStr) : null;
            Integer type = (typeStr != null && !typeStr.isEmpty()) ? Integer.parseInt(typeStr) : null;

            // Lấy danh sách truyện
            List<Story> stories = StoryDAO.searchStories(keyword, categoryId, type);
            req.setAttribute("stories", stories);

            if ("1".equals(ajax)) {
                req.getRequestDispatcher("/client/story/storyGrid.jsp").forward(req, resp);
                return;
            }else {
               
                req.setAttribute("categories", CategoryDAO.getAll());
                req.getRequestDispatcher("/client/story/story.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Có lỗi: " + e.getMessage());
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
