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
    private static final long serialVersionUID = 1L;

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
            } else if ("add".equals(action)) {
                request.getRequestDispatcher("/admin/author/authorForm.jsp").forward(request, response);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                AuthorDAO.deleteById(id);
                response.sendRedirect(request.getContextPath() + "/admin/author");
            } else {
                List<Author> authors = AuthorDAO.getAll();
                request.setAttribute("authors", authors);
                request.getRequestDispatcher("/admin/author/authorList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                Author author = new Author();
                author.setName(request.getParameter("name"));
                author.setInformation(request.getParameter("information"));
                author.setImage(request.getParameter("image"));
                
                AuthorDAO.insert(author);
                response.sendRedirect(request.getContextPath() + "/admin/author");
                
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Author author = new Author();
                author.setId(id);
                author.setName(request.getParameter("name"));
                author.setInformation(request.getParameter("information"));
                author.setImage(request.getParameter("image"));
                
                AuthorDAO.update(author);
                response.sendRedirect(request.getContextPath() + "/admin/author");
                
            } else {
                response.getWriter().println("Unknown action!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}