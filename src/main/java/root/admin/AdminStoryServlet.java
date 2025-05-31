package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Story;
import root.entities.StorySchedule;
import root.entities.StoryType;
import root.entities.Author;
import root.entities.Category;
import root.entities.Status;
import root.reps.StoryDAO;
import root.reps.StoryScheduleDAO;
import root.reps.StoryTypeDAO;
import root.reps.AuthorDAO;
import root.reps.CategoryDAO;
import root.reps.StatusDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/story")
public class AdminStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void setDropdowns(HttpServletRequest req) throws Exception {
		req.setAttribute("authors", AuthorDAO.getAll());
		req.setAttribute("categories", CategoryDAO.getAll());
		req.setAttribute("statusList", StatusDAO.getAll());
		req.setAttribute("storyTypes", StoryTypeDAO.getAll());
		req.setAttribute("schedules", StoryScheduleDAO.getAll());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		try {
			if ("edit".equals(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
				Story story = StoryDAO.getById(id);
				req.setAttribute("story", story);
				setDropdowns(req);
				req.getRequestDispatcher("/admin/story/storyForm.jsp").forward(req, resp);
			} else if ("add".equals(action)) {
				setDropdowns(req);
				req.getRequestDispatcher("/admin/story/storyForm.jsp").forward(req, resp);
			} else {
				List<Story> stories = StoryDAO.getAllWithNames();
				req.setAttribute("stories", stories);
				req.getRequestDispatcher("/admin/story/storyList.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("ERROR: " + e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		try {
			if ("add".equals(action)) {
				Story s = new Story();
				s.setTitle(req.getParameter("title"));
				s.setChapterNumber(Integer.parseInt(req.getParameter("chapterNumber")));
				s.setIntroduction(req.getParameter("introduction"));
				s.setImage(req.getParameter("image"));
				s.setAuthorId(Integer.parseInt(req.getParameter("authorId")));
				s.setStatusId(Integer.parseInt(req.getParameter("statusId")));
				s.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
				s.setStoryTypeId(Integer.parseInt(req.getParameter("storyTypeId")));
				s.setScheduleId(Integer.parseInt(req.getParameter("scheduleId")));
				s.setLikeNumber(0);
				s.setFollowNumber(0);
				s.setViewNumber(0);
				StoryDAO.insert(s);
				resp.sendRedirect(req.getContextPath() + "/admin/story");
			} else if ("update".equals(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
				Story old = StoryDAO.getById(id);
				Story s = new Story();
				s.setId(id);
				s.setTitle(req.getParameter("title"));
				s.setChapterNumber(Integer.parseInt(req.getParameter("chapterNumber")));
				s.setIntroduction(req.getParameter("introduction"));
				s.setImage(req.getParameter("image"));
				s.setAuthorId(Integer.parseInt(req.getParameter("authorId")));
				s.setStatusId(Integer.parseInt(req.getParameter("statusId")));
				s.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
				s.setStoryTypeId(Integer.parseInt(req.getParameter("storyTypeId")));
				s.setScheduleId(Integer.parseInt(req.getParameter("scheduleId")));
				s.setLikeNumber(old != null ? old.getLikeNumber() : 0);
				s.setFollowNumber(old != null ? old.getFollowNumber() : 0);
				s.setViewNumber(old != null ? old.getViewNumber() : 0);
				StoryDAO.update(s);
				resp.sendRedirect(req.getContextPath() + "/admin/story");
			} else if ("delete".equals(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
				StoryDAO.deleteById(id);
				resp.sendRedirect(req.getContextPath() + "/admin/story");
			} else {
				resp.getWriter().println("Unknown action!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("ERROR: " + e.getMessage());
		}
	}
}
