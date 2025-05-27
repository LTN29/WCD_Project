package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Chapter;
import root.reps.ChapterDAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/chapter")
public class AdminChapter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":
                    showForm(request, response, new Chapter());
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "detail":
                    showDetail(request, response);
                    break;
                case "delete":
                    deleteChapter(request, response);
                    break;
                default:
                    listChapters(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/chapter");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                insertChapter(request, response);
            } else if ("update".equals(action)) {
                updateChapter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/chapter");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/chapter");
        }
    }

    private void listChapters(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Chapter> chapters = ChapterDAO.getAll();
        request.setAttribute("chapters", chapters);
        request.getRequestDispatcher("/admin/chapter/chapterList.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, Chapter chapter)
            throws ServletException, IOException {
        request.setAttribute("chapter", chapter);
        request.getRequestDispatcher("/admin/chapter/chapterForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Chapter chapter = ChapterDAO.getById(id);
        if (chapter == null) {
            request.getSession().setAttribute("error", "Không tìm thấy chương #" + id);
            response.sendRedirect(request.getContextPath() + "/admin/chapter");
        } else {
            showForm(request, response, chapter);
        }
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Chapter chapter = ChapterDAO.getById(id);
        if (chapter == null) {
            request.getSession().setAttribute("error", "Không tìm thấy chương #" + id);
            response.sendRedirect(request.getContextPath() + "/admin/chapter");
        } else {
            request.setAttribute("chapter", chapter);
            request.getRequestDispatcher("/admin/chapter/chapterDetail.jsp").forward(request, response);
        }
    }

    private void insertChapter(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            Chapter chapter = parseChapterFromRequest(request, false);
            int newId = ChapterDAO.insert(chapter);
            if (newId > 0) {
                request.getSession().setAttribute("success", "Đã thêm chương mới (ID = " + newId + ")");
            } else {
                request.getSession().setAttribute("error", "Không thêm được chương.");
            }
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", "Lỗi dữ liệu: " + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/admin/chapter");
    }

    private void updateChapter(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            Chapter chapter = parseChapterFromRequest(request, true);
            boolean updated = ChapterDAO.update(chapter);
            if (updated) {
                request.getSession().setAttribute("success", "Đã cập nhật chương #" + chapter.getId());
            } else {
                request.getSession().setAttribute("error", "Không cập nhật được chương.");
            }
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", "Lỗi dữ liệu: " + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/admin/chapter");
    }

    private void deleteChapter(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean deleted = ChapterDAO.delete(id);
        if (deleted) {
            request.getSession().setAttribute("success", "Đã xoá chương #" + id);
        } else {
            request.getSession().setAttribute("error", "Không xoá được chương #" + id);
        }
        response.sendRedirect(request.getContextPath() + "/admin/chapter");
    }

    private Chapter parseChapterFromRequest(HttpServletRequest request, boolean hasId) {
        Chapter c = new Chapter();

        if (hasId) {
            try {
                c.setId(Integer.parseInt(request.getParameter("id")));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ID không hợp lệ.");
            }
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String dayCreateStr = request.getParameter("dayCreate");
        String storyIdStr = request.getParameter("storyId");

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề không được để trống.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung không được để trống.");
        }

        try {
            c.setStoryId(Integer.parseInt(storyIdStr));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Mã truyện không hợp lệ.");
        }

        try {
            c.setDayCreate((dayCreateStr != null && !dayCreateStr.isEmpty())
                    ? Date.valueOf(dayCreateStr)
                    : new Date(System.currentTimeMillis()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ngày tạo không hợp lệ (yyyy-MM-dd).");
        }

        c.setTitle(title.trim());
        c.setContent(content.trim());

        return c;
    }
}
