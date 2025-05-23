package root.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import root.entities.Author;
import root.reps.AuthorDao;

@WebServlet("/authorServlet")
public class AuthorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        try {
            if ("add".equalsIgnoreCase(action)) {
                String name = request.getParameter("name");
                String information = request.getParameter("information");
                String image = request.getParameter("image");
                if (name == null || information == null) {
                    out.write("Missing name or information for add");
                    return;
                }
                Author author = new Author(0, name, information, image);
                int id = AuthorDao.insert(author);
                out.write("Add OK. New ID: " + id);
            } else if ("update".equalsIgnoreCase(action)) {
                String idStr = request.getParameter("id");
                String name = request.getParameter("name");
                String information = request.getParameter("information");
                String image = request.getParameter("image");
                if (idStr == null || name == null || information == null) {
                    out.write("Missing id, name, or information for update");
                    return;
                }
                int id = Integer.parseInt(idStr);
                Author author = new Author(id, name, information, image);
                int affected = AuthorDao.update(author);
                out.write(affected > 0 ? "Update OK" : "Update FAIL");
            } else if ("delete".equalsIgnoreCase(action)) {
                String idStr = request.getParameter("id");
                if (idStr == null) {
                    out.write("Missing id for delete");
                    return;
                }
                int id = Integer.parseInt(idStr);
                int affected = AuthorDao.deleteById(id);
                out.write(affected > 0 ? "Delete OK" : "Delete FAIL");
            } else {
                out.write("Invalid or missing action param in POST");
            }
        } catch (Exception e) {
            out.write("ERROR: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        try {
            if ("list".equalsIgnoreCase(action)) {
                List<Author> authors = AuthorDao.getAll();
                for (Author a : authors) {
                    out.println("ID: " + a.getId() + ", Name: " + a.getName() + ", Info: " + a.getInformation() + ", Image: " + a.getImage());
                }
            } else if ("detail".equalsIgnoreCase(action)) {
                String idStr = request.getParameter("id");
                if (idStr == null) {
                    out.write("Missing id for detail");
                    return;
                }
                int id = Integer.parseInt(idStr);
                Author a = AuthorDao.getById(id);
                if (a != null)
                    out.println("ID: " + a.getId() + ", Name: " + a.getName() + ", Info: " + a.getInformation() + ", Image: " + a.getImage());
                else
                    out.println("Author not found");
            } else {
                out.write("Invalid or missing action param in GET");
            }
        } catch (Exception e) {
            out.write("ERROR: " + e.getMessage());
        }
    }
}

