package root.admin;

import root.entities.Category;
import root.reps.CategoryDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/category")
public class AdminCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String keyword = req.getParameter("keyword");  // Lấy keyword tìm kiếm nếu có

        try {
            if ("edit".equals(action)) {
                String idParam = req.getParameter("id");
                if (idParam == null || idParam.isEmpty()) {
                    // Nếu không có id => chuyển đến form thêm mới
                    req.getRequestDispatcher("/admin/category/categoryForm.jsp").forward(req, resp);
                } else {
                    int id = Integer.parseInt(idParam);
                    Category c = CategoryDAO.getById(id);
                    req.setAttribute("category", c);
                    req.getRequestDispatcher("/admin/category/categoryForm.jsp").forward(req, resp);
                }

            } else if ("add".equals(action)) {
                // Chuyển thẳng tới form thêm mới
                req.getRequestDispatcher("/admin/category/categoryForm.jsp").forward(req, resp);

            } else if ("delete".equals(action)) {
                String idParam = req.getParameter("id");
                if (idParam != null && !idParam.isEmpty()) {
                    int id = Integer.parseInt(idParam);
                    CategoryDAO.deleteById(id);
                }
                resp.sendRedirect("category");

            } else {
                List<Category> list;
                if (keyword != null && !keyword.trim().isEmpty()) {
                    list = CategoryDAO.searchByName(keyword.trim());
                    req.setAttribute("keyword", keyword); // để giữ giá trị trong form tìm kiếm
                } else {
                    list = CategoryDAO.getAll();
                }
                req.setAttribute("categoryList", list);
                req.getRequestDispatcher("/admin/category/categoryList.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            String idParam = req.getParameter("id");
            int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
            String name = req.getParameter("name");
            boolean active = "on".equals(req.getParameter("active"));

            Category c = new Category(id, name, active);

            if (id == 0) {
                CategoryDAO.insert(c);
            } else {
                CategoryDAO.update(c);
            }
            resp.sendRedirect("category");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
