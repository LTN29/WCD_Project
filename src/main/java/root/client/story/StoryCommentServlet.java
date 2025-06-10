package root.client.story;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class StoryCommentServlet
 */
@WebServlet("/StoryComment")
public class StoryCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoryCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    root.entities.User user = (root.entities.User) request.getSession().getAttribute("user");
	    if (user == null) {
	        response.sendRedirect("login");
	        return;
	    }

	    long userId = user.getId();
	    int storyId = Integer.parseInt(request.getParameter("storyId"));
	    String content = request.getParameter("content");

	    if (content == null || content.trim().isEmpty()) {
	        request.getSession().setAttribute("message", "Nội dung bình luận không được để trống!");
	        response.sendRedirect("storyDetail?id=" + storyId);
	        return;
	    }

	    root.entities.StoryComment comment = new root.entities.StoryComment(0, content, 0, storyId, userId);

	    try {
	        int commentId = root.reps.StoryCommentDAO.insert(comment);
	        if (commentId > 0) {
	            request.getSession().setAttribute("message", "Bình luận của bạn đã được gửi và đang chờ duyệt!");
	        } else {
	            request.getSession().setAttribute("message", "Bạn đã bình luận truyện này rồi!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("message", "Có lỗi xảy ra khi bình luận!");
	    }

	    response.sendRedirect("storyDetail?id=" + storyId);
	}


}
