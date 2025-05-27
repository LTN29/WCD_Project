package root.client.author;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import root.entities.Author;
import root.entities.Story;
import root.reps.AuthorDAO;
import root.reps.StoryDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/authorDetail")
public class AuthorDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Author author = AuthorDAO.getById(id);
			List<Story> stories = StoryDAO.getByAuthorId(id);

			request.setAttribute("author", author);
			request.setAttribute("stories", stories);
			request.getRequestDispatcher("/client/author/authorDetail.jsp").forward(request, response);
		} catch (Exception e) {
			response.getWriter().println("Lỗi: " + e.getMessage());
		}
	}
}
