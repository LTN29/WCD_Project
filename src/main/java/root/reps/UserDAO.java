package root.reps;

import root.entities.User;
import root.myutils.DBUtil;

import java.sql.*;

public class UserDAO {

    // Kiểm tra đăng nhập
    public static User checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT u.*, l._level AS levelName FROM tbl_user u " +
                     "LEFT JOIN tbl_Level_user l ON u._level_id = l._id " +
                     "WHERE u._username = ? AND u._password = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("_id"));
                u.setUserName(rs.getString("_username"));
                u.setPassWord(rs.getString("_password"));
                u.setRole(rs.getString("_role"));
                u.setActive(rs.getInt("_active"));
                u.setName(rs.getString("_name"));
                u.setScore(rs.getInt("_score"));
                u.setImage(rs.getString("_image"));
                u.setLevelId(rs.getInt("_level_id"));
                u.setLevel(rs.getString("levelName"));
                return u;
            }
        }
        return null;
    }

    // Kiểm tra username đã tồn tại chưa
    public static boolean existsUsername(String username) throws SQLException {
        String sql = "SELECT 1 FROM tbl_user WHERE _username = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    // Đăng ký tài khoản
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
            stmt.setInt(8, 1); // cấp mặc định là "Mới"
            return stmt.executeUpdate() > 0;
        }
    }

    // Cập nhật điểm và level sau khi user tương tác
    public static void updateScoreAndLevel(long userId, int newScore) throws SQLException {
        String levelSql = "SELECT _id FROM tbl_Level_user WHERE ? BETWEEN _score_start AND _score_end";
        String updateSql = "UPDATE tbl_user SET _score = ?, _level_id = ? WHERE _id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement levelStmt = conn.prepareStatement(levelSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            levelStmt.setInt(1, newScore);
            ResultSet rs = levelStmt.executeQuery();
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
