package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import root.entities.Author;
import root.reps.AuthorDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/author")
public class AdminAuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Author author = AuthorDAO.getById(id);
                request.setAttribute("author", author);
                request.getRequestDispatcher("/admin/author/authorForm.jsp").forward(request, response);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                AuthorDAO.deleteById(id);
                response.sendRedirect("author");
            } else {
                List<Author> authors = AuthorDAO.getAll();
                request.setAttribute("authors", authors);
                request.getRequestDispatcher("/admin/author/authorList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.getWriter().println("Lỗi: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                    ? Integer.parseInt(request.getParameter("id")) : 0;

            String name = request.getParameter("name");
            String info = request.getParameter("information");
            String image = request.getParameter("image");

            Author author = new Author(id, name, info, image);
            if (id > 0) {
                AuthorDAO.update(author);
            } else {
                AuthorDAO.insert(author);
            }
            response.sendRedirect("author");
        } catch (Exception e) {
            response.getWriter().println("Lỗi xử lý POST: " + e.getMessage());
        }
    }
}
