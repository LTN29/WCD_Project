package root.admin;

import root.entities.Chapter;
import root.reps.ChapterDAO;

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

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		try {
			if ("add".equals(action)) {
				// Tạo mới, chuyển sang form rỗng
				req.getRequestDispatcher("/admin/chapter/chapterForm.jsp").forward(req, resp);
			} else if ("edit".equals(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
				Chapter ch = ChapterDAO.getById(id);
				req.setAttribute("chapter", ch);
				req.getRequestDispatcher("/admin/chapter/chapterForm.jsp").forward(req, resp);
			} else if ("delete".equals(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
				ChapterDAO.deleteById(id);
				resp.sendRedirect("chapter");
			} else {
				List<Chapter> list = ChapterDAO.getAll();
				req.setAttribute("chapterList", list);
				req.getRequestDispatcher("/admin/chapter/chapterList.jsp").forward(req, resp);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		try {
			int id = req.getParameter("id") != null && !req.getParameter("id").isEmpty()
					? Integer.parseInt(req.getParameter("id"))
					: 0;

			String title = req.getParameter("title");
			String content = req.getParameter("content");
			Date dayCreate = Date.valueOf(req.getParameter("dayCreate"));
			int storyId = Integer.parseInt(req.getParameter("storyId"));

			Chapter ch = new Chapter(id, title, content, dayCreate, storyId);

			if (id == 0) {
				ChapterDAO.insert(ch);
			} else {
				ChapterDAO.update(ch);
			}

			resp.sendRedirect(req.getContextPath() + "/admin/chapter");

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
