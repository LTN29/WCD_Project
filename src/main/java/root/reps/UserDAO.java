package root.reps;

import root.entities.User;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAO để thao tác với bảng tbl_user trong cơ sở dữ liệu.
 * Bao gồm các chức năng: đăng nhập, kiểm tra tồn tại username, thêm, sửa, xóa user,
 * cập nhật điểm và cấp độ, tìm kiếm và phân trang danh sách user.
 */
public class UserDAO {
	
	public static User findById(long userId) throws SQLException {
	    String sql = "SELECT u.*, l._level AS levelName FROM tbl_user u " +
	                 "LEFT JOIN tbl_Level_user l ON u._level_id = l._id " +
	                 "WHERE u._id = ?";
	    try (Connection conn = DBUtil.getInstance().getConnect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setLong(1, userId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                User user = new User();
	                user.setId(rs.getLong("_id"));
	                user.setUserName(rs.getString("_username"));
	                user.setPassWord(rs.getString("_password"));
	                user.setRole(rs.getString("_role"));
	                user.setActive(rs.getInt("_active"));
	                user.setName(rs.getString("_name"));
	                user.setImage(rs.getString("_image"));
	                user.setScore(rs.getInt("_score"));
	                user.setLevelId(rs.getInt("_level_id"));
	                user.setLevel(rs.getString("levelName")); 
	                return user;
	            }
	        }
	    }
	    return null;
	}


	
	public static void addScoreAndUpdateLevel(long userId, int addScore) throws SQLException {
	    // 1. Cộng điểm
	    String sql = "UPDATE tbl_user SET _score = _score + ? WHERE _id = ?";
	    try (Connection conn = DBUtil.getInstance().getConnect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, addScore);
	        stmt.setLong(2, userId);
	        stmt.executeUpdate();
	    }
	    // 2. Lấy điểm mới
	    int newScore = 0;
	    try (Connection conn = DBUtil.getInstance().getConnect();
	         PreparedStatement stmt = conn.prepareStatement("SELECT _score FROM tbl_user WHERE _id = ?")) {
	        stmt.setLong(1, userId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) newScore = rs.getInt("_score");
	        }
	    }
	    // 3. Tìm level mới theo điểm mới
	    int newLevelId = 0;
	    try (Connection conn = DBUtil.getInstance().getConnect();
	         PreparedStatement stmt = conn.prepareStatement(
	            "SELECT _id FROM tbl_Level_user WHERE ? BETWEEN _score_start AND _score_end")) {
	        stmt.setInt(1, newScore);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) newLevelId = rs.getInt("_id");
	        }
	    }
	    // 4. Cập nhật level mới cho user
	    if (newLevelId > 0) {
	        try (Connection conn = DBUtil.getInstance().getConnect();
	             PreparedStatement stmt = conn.prepareStatement("UPDATE tbl_user SET _level_id = ? WHERE _id = ?")) {
	            stmt.setInt(1, newLevelId);
	            stmt.setLong(2, userId);
	            stmt.executeUpdate();
	        }
	    }
	}




    /**
     * Kiểm tra đăng nhập dựa vào username và mật khẩu đã hash
     * @param username tên đăng nhập
     * @param hashedPassword mật khẩu đã được hash
     * @return User nếu tồn tại, null nếu không đúng
     * @throws SQLException
     */
    public static User checkLogin(String username, String hashedPassword) throws SQLException {
        String sql = "SELECT * FROM tbl_user WHERE _username = ? AND _password = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null;
    }

    /**
     * Kiểm tra username đã tồn tại chưa
     * @param username tên đăng nhập cần kiểm tra
     * @return true nếu đã tồn tại, false nếu chưa
     * @throws SQLException
     */
    public static boolean existsUsername(String username) throws SQLException {
        String sql = "SELECT 1 FROM tbl_user WHERE _username = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Thêm mới User (đăng ký tài khoản)
     * Mặc định cấp levelId = 1 (mới)
     * @param u User cần thêm
     * @return true nếu thành công
     * @throws SQLException
     */
    public static boolean insert(User u) throws SQLException {
        String sql = "INSERT INTO tbl_user (_username, _password, _role, _active, _score, _name, _image, _level_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getUserName());
            stmt.setString(2, u.getPassWord());
            stmt.setString(3, u.getRole());
            stmt.setInt(4, u.getActive());
            stmt.setInt(5, u.getScore());
            stmt.setString(6, u.getName());
            stmt.setString(7, u.getImage());
            stmt.setInt(8, 1); // level mặc định = 1
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Cập nhật điểm số và cấp độ dựa trên điểm mới
     * @param userId ID user cần cập nhật
     * @param newScore điểm số mới
     * @throws SQLException
     */
    public static void updateScoreAndLevel(long userId, int newScore) throws SQLException {
        String levelSql = "SELECT _id FROM tbl_Level_user WHERE ? BETWEEN _score_start AND _score_end";
        String updateSql = "UPDATE tbl_user SET _score = ?, _level_id = ? WHERE _id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement levelStmt = conn.prepareStatement(levelSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            levelStmt.setInt(1, newScore);
            try (ResultSet rs = levelStmt.executeQuery()) {
                if (rs.next()) {
                    int levelId = rs.getInt("_id");
                    updateStmt.setInt(1, newScore);
                    updateStmt.setInt(2, levelId);
                    updateStmt.setLong(3, userId);
                    updateStmt.executeUpdate();
                }
            }
        }
    }

    /**
     * Xóa user theo ID
     * @param id ID user
     * @return true nếu xóa thành công
     * @throws SQLException
     */
    public static boolean deleteUserById(long id) throws SQLException {
        String sql = "DELETE FROM tbl_user WHERE _id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Cập nhật tên user theo ID
     * @param id ID user
     * @param name tên mới
     * @return true nếu cập nhật thành công
     * @throws SQLException
     */
    public static boolean updateName(long id, String name) throws SQLException {
        String sql = "UPDATE tbl_user SET _name = ? WHERE _id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setLong(2, id);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Cập nhật mật khẩu user theo ID
     * @param id ID user
     * @param newPassword mật khẩu mới (đã hash)
     * @return true nếu cập nhật thành công
     * @throws SQLException
     */
    public static boolean updatePassword(long id, String newPassword) throws SQLException {
        String sql = "UPDATE tbl_user SET _password = ? WHERE _id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setLong(2, id);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Lấy danh sách user có tìm kiếm theo username hoặc name, phân trang
     * @param search từ khóa tìm kiếm
     * @param offset vị trí bắt đầu lấy
     * @param limit số lượng bản ghi lấy
     * @return List User
     * @throws SQLException
     */
    public static List<User> getUsers(String search, int offset, int limit) throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_user WHERE (_username LIKE ? OR _name LIKE ?) " +
                     "ORDER BY _id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String keyword = "%" + (search == null ? "" : search) + "%";
            stmt.setString(1, keyword);
            stmt.setString(2, keyword);
            stmt.setInt(3, offset);
            stmt.setInt(4, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToUser(rs));
                }
            }
        }
        return list;
    }

    /**
     * Đếm tổng số user để phân trang
     * @param search từ khóa tìm kiếm
     * @return tổng số bản ghi phù hợp
     * @throws SQLException
     */
    public static int countUsers(String search) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_user WHERE (_username LIKE ? OR _name LIKE ?)";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String keyword = "%" + (search == null ? "" : search) + "%";
            stmt.setString(1, keyword);
            stmt.setString(2, keyword);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
 // Lấy tất cả user không phân trang
    public static List<User> getAll() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_user ORDER BY _id";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToUser(rs));
            }
        }
        return list;
    }

    // Tìm kiếm user theo username hoặc name (trả về list)
    public static List<User> search(String keyword) throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_user WHERE _username LIKE ? OR _name LIKE ? ORDER BY _id";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String key = "%" + (keyword == null ? "" : keyword) + "%";
            stmt.setString(1, key);
            stmt.setString(2, key);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToUser(rs));
                }
            }
        }
        return list;
    }


    /**
     * Lấy user theo ID
     * @param id ID user
     * @return User hoặc null nếu không tìm thấy
     * @throws SQLException
     */
    public static User getUserById(long id) throws SQLException {
        String sql = "SELECT * FROM tbl_user WHERE _id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null;
    }

    /**
     * Cập nhật thông tin user (ngoại trừ mật khẩu)
     * @param u User cần cập nhật
     * @return true nếu cập nhật thành công
     * @throws SQLException
     */
    public static boolean update(User u) throws SQLException {
        String sql = "UPDATE tbl_user SET _username = ?, _role = ?, _active = ?, _score = ?, _name = ?, _image = ?, _level_id = ? WHERE _id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getUserName());
            stmt.setString(2, u.getRole());
            stmt.setInt(3, u.getActive());
            stmt.setInt(4, u.getScore());
            stmt.setString(5, u.getName());
            stmt.setString(6, u.getImage());
            stmt.setInt(7, u.getLevelId());
            stmt.setLong(8, u.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // --- Phương thức hỗ trợ mapping ResultSet sang User object ---
    private static User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("_id"));
        user.setUserName(rs.getString("_username"));
        user.setPassWord(rs.getString("_password"));
        user.setRole(rs.getString("_role"));
        user.setActive(rs.getInt("_active"));
        user.setName(rs.getString("_name"));
        user.setImage(rs.getString("_image"));
        user.setScore(rs.getInt("_score"));
        user.setLevelId(rs.getInt("_level_id"));
        return user;
    }
}
