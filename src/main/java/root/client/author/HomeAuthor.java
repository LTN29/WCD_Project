package root.client.author;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import root.entities.Author;
import root.reps.AuthorDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/AuthorHome")
public class HomeAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeAuthor() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		try {
			List<Author> authors = AuthorDAO.getAll();
			request.setAttribute("authors", authors);
			request.getRequestDispatcher("/client/author/author.jsp").forward(request, response);
		} catch (Exception e) {
			response.getWriter().println("Lỗi: " + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		doGet(request, response);
	}
}
