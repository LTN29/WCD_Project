package root.client.chapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Chapter;
import root.entities.ChapterImage;
import root.reps.ChapterDAO;
import root.reps.ChapterImageDAO;

import java.io.IOException;

/**
 * Servlet implementation class ChapterListServlet
 */
@WebServlet("/ChapterListServlet")
public class ChapterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChapterListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int chapterId= Integer.parseInt(request.getParameter("id"));
		
//		 Chapter chapter = ChapterDAO.getByStoryId(chapterId);
//		 ChapterImage chapterImg = ChapterImageDAO.getByChapterId(chapterId);
//		 request.setAttribute("chapter", chapter);
//		 request.setAttribute("chapterImg", chapterImg);
//		 
		 request.getRequestDispatcher("/client/chapter/chapterDetail.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
