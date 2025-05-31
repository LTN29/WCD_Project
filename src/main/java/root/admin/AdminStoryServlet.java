package root.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import root.entities.Story;
import root.reps.StoryDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminStoryServlet", value = "/admin/story")
public class AdminStoryServlet extends HttpServlet {

    private final StoryDAO storyDAO = new StoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "create":
                    // Hiển thị form thêm truyện
                    request.getRequestDispatcher("/admin/story-form.jsp").forward(request, response);
                    break;

                case "edit":
                    // Hiển thị form sửa truyện
                    int editId = Integer.parseInt(request.getParameter("id"));
                    Story story = storyDAO.getStoryById(editId);
                    if (story == null) {
                        response.sendRedirect(request.getContextPath() + "/admin/story");
                        return;
                    }
                    request.setAttribute("story", story);
                    request.getRequestDispatcher("/admin/story-form.jsp").forward(request, response);
                    break;

                case "delete":
                    // Xóa truyện
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    storyDAO.deleteStory(deleteId);
                    response.sendRedirect(request.getContextPath() + "/admin/story");
                    break;

                case "list":
                default:
                    // Hiển thị danh sách truyện với phân trang và tìm kiếm
                    showStoryList(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Lỗi truy vấn cơ sở dữ liệu", e);
        }
    }

    private void showStoryList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String searchKey = request.getParameter("search");
        String pageStr = request.getParameter("page");
        int page = 1;
        int pageSize = 10;

        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
                if (page < 1) page = 1;
            } catch (NumberFormatException ignored) {}
        }

        int totalStories = storyDAO.countStories(searchKey);
        int totalPages = (int) Math.ceil((double) totalStories / pageSize);
        if (page > totalPages && totalPages > 0) page = totalPages;

        int offset = (page - 1) * pageSize;
        List<Story> stories = storyDAO.getFilteredStories(offset, pageSize, searchKey);

        request.setAttribute("stories", stories);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchKey", searchKey == null ? "" : searchKey);

        request.getRequestDispatcher("/admin/story/storyList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Để tránh lỗi mã hóa tiếng Việt
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "save";

        try {
            if ("save".equalsIgnoreCase(action)) {
                saveOrUpdateStory(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/story");
            }
        } catch (SQLException e) {
            throw new ServletException("Lỗi khi lưu truyện", e);
        }
    }

    private void saveOrUpdateStory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Lấy dữ liệu từ form
        String idStr = request.getParameter("id");
        String title = request.getParameter("title");
        String chapterNumberStr = request.getParameter("chapterNumber");
        String introduction = request.getParameter("introduction");
        String image = request.getParameter("image");
        String likeNumberStr = request.getParameter("likeNumber");
        String followNumberStr = request.getParameter("followNumber");
        String viewNumberStr = request.getParameter("viewNumber");
        String authorIdStr = request.getParameter("authorId");
        String statusIdStr = request.getParameter("statusId");
        String categoryIdStr = request.getParameter("categoryId");
        String storyTypeIdStr = request.getParameter("storyTypeId");
        String scheduleIdStr = request.getParameter("scheduleId");

        int chapterNumber = parseIntSafe(chapterNumberStr);
        int likeNumber = parseIntSafe(likeNumberStr);
        int followNumber = parseIntSafe(followNumberStr);
        int viewNumber = parseIntSafe(viewNumberStr);
        int authorId = parseIntSafe(authorIdStr);
        int statusId = parseIntSafe(statusIdStr);
        int categoryId = parseIntSafe(categoryIdStr);
        int storyTypeId = parseIntSafe(storyTypeIdStr);
        int scheduleId = parseIntSafe(scheduleIdStr);

        Story story = new Story();
        story.setTitle(title);
        story.setChapterNumber(chapterNumber);
        story.setIntroduction(introduction);
        story.setImage(image);
        story.setLikeNumber(likeNumber);
        story.setFollowNumber(followNumber);
        story.setViewNumber(viewNumber);
        story.setAuthorId(authorId);
        story.setStatusId(statusId);
        story.setCategoryId(categoryId);
        story.setStoryTypeId(storyTypeId);
        story.setScheduleId(scheduleId);

        if (idStr == null || idStr.isEmpty()) {
            // Thêm mới
            storyDAO.insertStory(story);
        } else {
            // Cập nhật
            int id = parseIntSafe(idStr);
            story.setId(id);
            storyDAO.updateStory(story);
        }

        response.sendRedirect(request.getContextPath() + "/admin/story");
    }

    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }
}
