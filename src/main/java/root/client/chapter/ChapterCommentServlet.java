package root.client.chapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.ChapterComment;
import root.entities.User;
import root.reps.ChapterCommentDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ChapterCommentServlet
 */
@WebServlet("/ChapterComment")
public class ChapterCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChapterCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	        req.setCharacterEncoding("UTF-8");
	        resp.setCharacterEncoding("UTF-8");


	        User user = (User) req.getSession().getAttribute("user");
	        if (user == null) {
	            resp.sendRedirect("login");
	            return;
	        }

	    
	        String content = req.getParameter("content");
	        int chapterId = Integer.parseInt(req.getParameter("chapterId"));


	        ChapterComment comment = new ChapterComment();
	        comment.setContent(content);
	        comment.setActive(0); 
	        comment.setChapterId(chapterId);
	        comment.setUserId(user.getId());

	        try {
	            ChapterCommentDAO.insert(comment);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        req.getSession().setAttribute("message", "Bình luận của bạn đã được gửi và đang chờ duyệt!");
	        resp.sendRedirect(req.getContextPath() + "/chapterDetail?chapterId=" + chapterId);

	    }
	}

