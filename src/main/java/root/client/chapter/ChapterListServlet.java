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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ChapterList")
public class ChapterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChapterListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int chapterId = Integer.parseInt(req.getParameter("chapterId"));
            Chapter chapter = ChapterDAO.getById(chapterId);
            Story story = StoryDAO.getById(chapter.getStoryId());
            req.setAttribute("chapter", chapter);
            req.setAttribute("story", story);

            if (story.getStoryTypeId() == 2) {
                List<ChapterImage> images = ChapterImageDAO.getImagesByChapterId(chapterId);
                req.setAttribute("images", images);
            }

            List<ChapterComment> commentList = ChapterCommentDAO.getApprovedCommentsByChapterId(chapterId);
            req.setAttribute("commentList", commentList);

            req.getRequestDispatcher("/client/chapter/chapterDetail.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
