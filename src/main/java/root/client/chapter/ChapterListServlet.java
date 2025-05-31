package root.client.chapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Chapter;
import root.entities.ChapterImage;
import root.entities.Story;
import root.entities.ChapterComment;
import root.reps.ChapterDAO;
import root.reps.ChapterImageDAO;
import root.reps.StoryDAO;
import root.reps.ChapterCommentDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/ChapterList")
public class ChapterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChapterListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int chapterId = Integer.parseInt(req.getParameter("chapterId"));
        Chapter chapter = ChapterDAO.getById(chapterId);
        Story story = StoryDAO.getById(chapter.getStoryId());
        req.setAttribute("chapter", chapter);
        req.setAttribute("story", story);

        // Nếu là truyện tranh, lấy list ảnh theo chương
        if (story.getStoryTypeId() == 2) {
            List<ChapterImage> images = ChapterImageDAO.getImagesByChapterId(chapterId);
            req.setAttribute("images", images);
        }
        // Lấy danh sách comment theo chương
        List<ChapterComment> commentList = ChapterCommentDAO.getChapterComments(chapterId);
        req.setAttribute("commentList", commentList);

        req.getRequestDispatcher("chapter_detail.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
