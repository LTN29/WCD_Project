package root.admin;



import root.entities.Chapter;
import root.entities.Story;
import root.reps.ChapterDAO;
import root.reps.StoryDAO;

import javax.servlet.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/chapter")
public class AdminChapterServlet extends HttpServlet {
    private static final int PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":
                    List<Story> storiesAdd = new StoryDAO().getAll();
                    request.setAttribute("stories", storiesAdd);
                    request.getRequestDispatcher("/admin/chapter/chapterForm.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Chapter chapter = ChapterDAO.getById(id).orElseThrow(() -> new ServletException("Chapter not found"));
                    List<Story> storiesEdit = new StoryDAO().getAll();
                    request.setAttribute("chapter", chapter);
                    request.setAttribute("stories", storiesEdit);
                    request.getRequestDispatcher("/admin/chapter/chapterForm.jsp").forward(request, response);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    ChapterDAO.delete(deleteId);
                    response.sendRedirect("chapter");
                    break;
                default:
                    showChapterList(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void showChapterList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String keyword = request.getParameter("keyword");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int total = ChapterDAO.count(keyword);
        int offset = (page - 1) * PAGE_SIZE;

        List<Chapter> chapters = ChapterDAO.search(keyword, offset, PAGE_SIZE);

        request.setAttribute("keyword", keyword);
        request.setAttribute("chapters", chapters);
        request.setAttribute("page", page);
        request.setAttribute("totalPage", (int) Math.ceil(total * 1.0 / PAGE_SIZE));

        request.getRequestDispatcher("/admin/chapter/chapterList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idRaw = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String dayCreate = request.getParameter("dayCreate");
        String storyIdRaw = request.getParameter("storyId");

        Chapter c = new Chapter();
        c.setTitle(title);
        c.setContent(content);
        c.setDayCreate(Date.valueOf(dayCreate));
        try {
            c.setStoryId(Integer.parseInt(storyIdRaw));
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid storyId");
        }

        try {
            if (idRaw == null || idRaw.isEmpty()) {
                ChapterDAO.insert(c);
            } else {
                c.setId(Integer.parseInt(idRaw));
                ChapterDAO.update(c);
            }
            response.sendRedirect("chapter");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
