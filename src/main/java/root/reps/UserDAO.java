package root.reps;

import root.entities.User;
import root.myutils.DBUtil;

import java.sql.*;

public class UserDAO {

	// Kiểm tra đăng nhập
	public static User checkLogin(String username, String hashedPassword) throws SQLException {
	    String sql = "SELECT * FROM tbl_user WHERE _username = ? AND _password = ?";
	    try (Connection conn = DBUtil.getInstance().getConnect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, username);
	        stmt.setString(2, hashedPassword);
	        ResultSet rs = stmt.executeQuery();
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
	            return user;
	        }
	    }
	    return null;
	}


	// Kiểm tra username đã tồn tại chưa
	public static boolean existsUsername(String username) throws SQLException {
		String sql = "SELECT 1 FROM tbl_user WHERE _username = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}
	}

	// Đăng ký tài khoản
	public static boolean insert(User u) throws SQLException {
		String sql = "INSERT INTO tbl_user (_username, _password, _role, _active, _score, _name, _image, _level_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

	public static boolean deleteUserById(long id) throws SQLException {
		String sql = "DELETE FROM tbl_user WHERE _id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			return stmt.executeUpdate() > 0;
		}
	}

	public static boolean updateName(long id, String name) throws SQLException {
		String sql = "UPDATE tbl_user SET _name = ? WHERE _id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setLong(2, id);
			return stmt.executeUpdate() > 0;
		}
	}

	public static boolean updatePassword(long id, String newPassword) throws SQLException {
		String sql = "UPDATE tbl_user SET _password = ? WHERE _id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, newPassword);
			stmt.setLong(2, id);
			return stmt.executeUpdate() > 0;
		}
	}

}
