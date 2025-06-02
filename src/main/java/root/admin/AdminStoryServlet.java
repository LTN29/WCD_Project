package root.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import root.entities.Story;
<<<<<<< HEAD
import root.entities.StorySchedule;
import root.entities.StoryType;
import root.entities.Author;
import root.entities.Category;
import root.entities.Status;
=======
>>>>>>> ea6c6e3d76476ac087f3641b8dcfa409ef395a94
import root.reps.StoryDAO;
import root.reps.StoryScheduleDAO;
import root.reps.StoryTypeDAO;
import root.reps.AuthorDAO;
import root.reps.CategoryDAO;
import root.reps.StatusDAO;

<<<<<<< HEAD

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Bảo đảm đọc tiếng Việt chính xác
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = ""; // tránh NullPointerException

        try {
            switch (action) {
                case "edit" -> {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Story story = StoryDAO.getById(id);
                    if (story == null) {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                                       "Không tìm thấy truyện với ID = " + id);
                        return;
                    }
                    req.setAttribute("story", story);
                    setDropdowns(req);
                    req.getRequestDispatcher("/admin/story/storyForm.jsp")
                       .forward(req, resp);
                }

                case "add" -> {
                    setDropdowns(req);
                    req.getRequestDispatcher("/admin/story/storyForm.jsp")
                       .forward(req, resp);
                }

                case "searchAjax" -> {
                    String keyword = req.getParameter("keyword");
                    List<Story> stories = StoryDAO.search(keyword);

                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");

                    String json = new Gson().toJson(stories);
                    resp.getWriter().write(json);
                }

                default -> {
                    List<Story> stories = StoryDAO.getAllWithNames();
                    req.setAttribute("stories", stories);
                    req.getRequestDispatcher("/admin/story/storyList.jsp")
                       .forward(req, resp);
                }
            }
        } catch (NumberFormatException nfe) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ!");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                           "Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
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
=======
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
>>>>>>> ea6c6e3d76476ac087f3641b8dcfa409ef395a94
}
