package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Chapter;
import root.entities.Story;
import root.reps.ChapterDAO;
import root.reps.StoryDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class StoryDetail
 */
@WebServlet("/StoryDetail")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/client/home.jsp");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            Story story = StoryDAO.getById(id); 
            List<Chapter> chapters = ChapterDAO.getByStoryId(id); 

            req.setAttribute("story", story);
            req.setAttribute("chapters", chapters);
            req.getRequestDispatcher("/client/story/StoryDetail.jsp").forward(req, resp);
        } catch (Exception e) {
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
