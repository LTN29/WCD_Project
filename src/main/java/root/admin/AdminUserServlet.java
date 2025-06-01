package root.admin;

import root.entities.User;
import root.reps.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/user")
public class AdminUserServlet extends HttpServlet {

    // Hàm mã hóa mật khẩu SHA-256 (bạn có thể thay bằng bcrypt)
    private String hashPassword(String password) {
        if (password == null || password.isEmpty()) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("edit".equals(action)) {
                String idParam = req.getParameter("id");
                if (idParam == null || idParam.isEmpty()) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
                    return;
                }
                long id = Long.parseLong(idParam);
                User u = UserDAO.getUserById(id);
                req.setAttribute("user", u);
                req.getRequestDispatcher("/admin/user/userForm.jsp").forward(req, resp);

            } else if ("add".equals(action)) {
                // Mở form thêm mới user, không cần load dữ liệu nào
                req.getRequestDispatcher("/admin/user/userForm.jsp").forward(req, resp);

            } else if ("delete".equals(action)) {
                String idParam = req.getParameter("id");
                if (idParam == null || idParam.isEmpty()) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
                    return;
                }
                long id = Long.parseLong(idParam);
                UserDAO.deleteUserById(id);
                resp.sendRedirect(req.getContextPath() + "/admin/user");

            } else {
                // Hiển thị danh sách user hoặc tìm kiếm
                String keyword = req.getParameter("keyword");
                List<User> list;
                if (keyword != null && !keyword.trim().isEmpty()) {
                    list = UserDAO.search(keyword.trim());
                    req.setAttribute("keyword", keyword);
                } else {
                    list = UserDAO.getAll();
                }
                req.setAttribute("userList", list);
                req.getRequestDispatcher("/admin/user/userList.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            long id = req.getParameter("id") != null && !req.getParameter("id").isEmpty()
                    ? Long.parseLong(req.getParameter("id"))
                    : 0L;

            String username = req.getParameter("username");
            String name = req.getParameter("name");
            String image = req.getParameter("image");
            String role = req.getParameter("role");
            String password = req.getParameter("password");
            int score = 0;
            try {
                score = Integer.parseInt(req.getParameter("score"));
            } catch (NumberFormatException e) {
                // Có thể để mặc định score = 0 hoặc xử lý lỗi cụ thể
            }
            int active = "on".equals(req.getParameter("active")) ? 1 : 0;
            int levelId = 1; // Mặc định, hoặc lấy từ form nếu có

            // Hash mật khẩu nếu có nhập (chỉ update mật khẩu khi có dữ liệu)
            String hashedPassword = null;
            if (password != null && !password.trim().isEmpty()) {
                hashedPassword = hashPassword(password.trim());
            }

            User user = new User();
            user.setId(id);
            user.setUserName(username);
            user.setName(name);
            user.setImage(image);
            user.setRole(role);
            user.setActive(active);
            user.setScore(score);
            user.setLevelId(levelId);

            if (id == 0) {
                // Thêm mới
                if (hashedPassword == null) {
                    req.setAttribute("error", "Mật khẩu không được để trống khi thêm mới.");
                    req.getRequestDispatcher("/admin/user/userForm.jsp").forward(req, resp);
                    return;
                }
                user.setPassWord(hashedPassword);
                boolean inserted = UserDAO.insert(user);
                if (!inserted) {
                    req.setAttribute("error", "Thêm user thất bại.");
                    req.getRequestDispatcher("/admin/user/userForm.jsp").forward(req, resp);
                    return;
                }
            } else {
                // Cập nhật (ngoại trừ mật khẩu)
                // Nếu muốn cập nhật mật khẩu thì gọi hàm updatePassword riêng biệt
                boolean updated = UserDAO.update(user);
                if (!updated) {
                    req.setAttribute("error", "Cập nhật user thất bại.");
                    req.getRequestDispatcher("/admin/user/userForm.jsp").forward(req, resp);
                    return;
                }
                if (hashedPassword != null) {
                    UserDAO.updatePassword(id, hashedPassword);
                }
            }

            resp.sendRedirect(req.getContextPath() + "/admin/user");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}
