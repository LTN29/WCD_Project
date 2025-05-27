package root.client.category;

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
 * Servlet implementation class CategoryStoryServlet
 */
@WebServlet("/story")
public class CategoryStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryStoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));

            List<Story> stories = StoryDAO.getByCategoryId(categoryId);
            req.setAttribute("stories", stories);

         
            String categoryName = CategoryDAO.getNameById(categoryId);
            req.setAttribute("listTitle", "Thể loại: " + categoryName);

            
            List<Category> categories = CategoryDAO.getAll(); 
            req.setAttribute("categories", categories);

        
            req.getRequestDispatcher("/client/story/listStory.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Lỗi: " + e.getMessage());
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
