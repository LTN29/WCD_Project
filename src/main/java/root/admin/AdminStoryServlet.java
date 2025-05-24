package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Story;
import root.entities.Author;
import root.entities.Category;
import root.entities.Status;
import root.reps.StoryDAO;
import root.reps.AuthorDAO;
import root.reps.CategoryDAO;
import root.reps.StatusDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/story")
public class AdminStoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private void setDropdowns(HttpServletRequest req) throws Exception {
        List<Author> authors = AuthorDAO.getAll();
        List<Category> categories = CategoryDAO.getAll();
        List<Status> statusList = StatusDAO.getAll();
        req.setAttribute("authors", authors);
        req.setAttribute("categories", categories);
        req.setAttribute("statusList", statusList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Story story = StoryDAO.getById(id);
                req.setAttribute("story", story);
                setDropdowns(req);
                req.getRequestDispatcher("/admin/story/storyForm.jsp").forward(req, resp);
            } else if ("add".equals(action)) {
                setDropdowns(req);
                req.getRequestDispatcher("/admin/story/storyForm.jsp").forward(req, resp);
            } else { // Hiển thị list
                List<Story> stories = StoryDAO.getAllWithNames();
                req.setAttribute("stories", stories);
                req.getRequestDispatcher("/admin/story/storyList.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("ERROR: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                Story s = new Story(0,
                    req.getParameter("title"),
                    Integer.parseInt(req.getParameter("chapterNumber")),
                    req.getParameter("introduction"),
                    req.getParameter("image"),
                    Integer.parseInt(req.getParameter("likeNumber")),
                    Integer.parseInt(req.getParameter("followNumber")),
                    Integer.parseInt(req.getParameter("viewNumber")),
                    Integer.parseInt(req.getParameter("authorId")),
                    Integer.parseInt(req.getParameter("statusId")),
                    Integer.parseInt(req.getParameter("categoryId"))
                );
                StoryDAO.insert(s);
                resp.sendRedirect(req.getContextPath() + "/admin/story");

            } else if ("update".equals(action)) {
                Story s = new Story(
                    Integer.parseInt(req.getParameter("id")),
                    req.getParameter("title"),
                    Integer.parseInt(req.getParameter("chapterNumber")),
                    req.getParameter("introduction"),
                    req.getParameter("image"),
                    Integer.parseInt(req.getParameter("likeNumber")),
                    Integer.parseInt(req.getParameter("followNumber")),
                    Integer.parseInt(req.getParameter("viewNumber")),
                    Integer.parseInt(req.getParameter("authorId")),
                    Integer.parseInt(req.getParameter("statusId")),
                    Integer.parseInt(req.getParameter("categoryId"))
                );
                StoryDAO.update(s);
                resp.sendRedirect(req.getContextPath() + "/admin/story");

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                StoryDAO.deleteById(id);
                resp.sendRedirect(req.getContextPath() + "/admin/story");

            } else {
                resp.getWriter().println("Unknown action!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}
