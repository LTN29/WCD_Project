package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.ChapterComment;
import root.entities.StoryComment;
import root.reps.ChapterCommentDAO;
import root.reps.StoryCommentDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/comment")
public class AdminCommentPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminCommentPageServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ChapterComment> chapterComments = ChapterCommentDAO.getAllPending();
            List<StoryComment> storyComments = StoryCommentDAO.getAllPending();

            request.setAttribute("chapterComments", chapterComments);
            request.setAttribute("storyComments", storyComments);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/admin/author/commentList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
