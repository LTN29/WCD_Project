package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import root.entities.Chapter;
import root.entities.Story;
import root.reps.ChapterDAO;
import root.reps.StoryDAO;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/chapter")
public class AdminChapterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private void setStories(HttpServletRequest req) throws Exception {
        List<Story> stories = StoryDAO.getAll();
        req.setAttribute("stories", stories);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Chapter chapter = ChapterDAO.getById(id);
                req.setAttribute("chapter", chapter);
                setStories(req);
                req.getRequestDispatcher("/admin/chapter/chapterForm.jsp").forward(req, resp);
            } else if ("add".equals(action)) {
                setStories(req);
                req.getRequestDispatcher("/admin/chapter/chapterForm.jsp").forward(req, resp);
            } else {
                String keyword = req.getParameter("keyword");
                if (keyword == null) keyword = "";
                List<Chapter> chapters = ChapterDAO.search(keyword, 0, 100);
                req.setAttribute("chapters", chapters);
                req.setAttribute("keyword", keyword);
                req.getRequestDispatcher("/admin/chapter/chapterList.jsp").forward(req, resp);
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
            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                ChapterDAO.delete(id);
                resp.sendRedirect(req.getContextPath() + "/admin/chapter");
                return;  // Kết thúc sớm, không chạy code bên dưới
            }

            // Các xử lý cho add/update dưới đây, bao gồm validation
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String dayCreateStr = req.getParameter("dayCreate");
            String storyIdSelect = req.getParameter("storyIdSelect");
            List<String> errors = new ArrayList<>();

            // Validation dữ liệu (title, content, dayCreate, storyIdSelect) ...

            if (!errors.isEmpty()) {
                // Hiển thị lại form với lỗi
                Chapter c = new Chapter();
                c.setTitle(title);
                c.setContent(content);
                if (dayCreateStr != null && !dayCreateStr.trim().isEmpty()) {
                    c.setDayCreate(Date.valueOf(dayCreateStr));
                }
                if (storyIdSelect != null && !storyIdSelect.trim().isEmpty()) {
                    c.setStoryId(Integer.parseInt(storyIdSelect));
                }

                req.setAttribute("errors", errors);
                req.setAttribute("chapter", c);
                setStories(req);
                req.getRequestDispatcher("/admin/chapter/chapterForm.jsp").forward(req, resp);
                return;
            }

            // Tạo hoặc cập nhật chapter
            Chapter c = new Chapter();
            c.setTitle(title);
            c.setContent(content);
            c.setDayCreate(Date.valueOf(dayCreateStr));
            c.setStoryId(Integer.parseInt(storyIdSelect));

            if ("add".equals(action)) {
                ChapterDAO.insert(c);
            } else if ("update".equals(action)) {
                c.setId(Integer.parseInt(req.getParameter("id")));
                ChapterDAO.update(c);
            }

            resp.sendRedirect(req.getContextPath() + "/admin/chapter");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("ERROR: " + e.getMessage());
        }
    }


}
