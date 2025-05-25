package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import root.entities.Author;
import root.reps.AuthorDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/Admin/author")
public class AuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Author author = AuthorDAO.getById(id);
                if (author != null) {
                    request.setAttribute("author", author);
                    request.getRequestDispatcher("/admin/author/authorForm.jsp").forward(request, response);
                } else {
                    response.sendRedirect("author?message=notfound");
                }

            } else if ("add".equals(action)) {
                request.getRequestDispatcher("/admin/author/authorForm.jsp").forward(request, response);

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Author author = AuthorDAO.getById(id);
                if (author != null) {
                    AuthorDAO.deleteById(id);
                    response.sendRedirect("author?message=deleted");
                } else {
                    response.sendRedirect("author?message=notfound");
                }

            } else {
                List<Author> authors = AuthorDAO.getAll();
                request.setAttribute("authors", authors);
                String msg = request.getParameter("message");
                if (msg != null) {
                    switch (msg) {
                        case "added": request.setAttribute("success", "Thêm thành công!"); break;
                        case "updated": request.setAttribute("success", "Cập nhật thành công!"); break;
                        case "deleted": request.setAttribute("success", "Xoá thành công!"); break;
                        case "notfound": request.setAttribute("error", "Tác giả không tồn tại!"); break;
                        case "error": request.setAttribute("error", "Đã có lỗi xảy ra."); break;
                    }
                }
                request.getRequestDispatcher("/admin/author/authorList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("author?message=error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id = 0;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException ignored) {}

            String name = request.getParameter("name");
            String info = request.getParameter("information");
            String image = request.getParameter("image");

            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("error", "Tên tác giả không được để trống.");
                Author a = new Author(id, name, info, image);
                request.setAttribute("author", a);
                request.getRequestDispatcher("/admin/author/authorForm.jsp").forward(request, response);
                return;
            }

            Author author = new Author(id, name.trim(), info, image);

            if (id > 0) {
                if (AuthorDAO.getById(id) != null) {
                    AuthorDAO.update(author);
                    response.sendRedirect("author?message=updated");
                } else {
                    response.sendRedirect("author?message=notfound");
                }
            } else {
                AuthorDAO.insert(author);
                response.sendRedirect("author?message=added");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("author?message=error");
        }
    }

    }
}
