package root.reps;

import root.entities.User;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	public static User findById(long userId) throws SQLException {
		String sql = "SELECT u.*, l._level AS levelName FROM tbl_user u "
				+ "LEFT JOIN tbl_Level_user l ON u._level_id = l._id " + "WHERE u._id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
				if (rs.next())
					newScore = rs.getInt("_score");
			}
		}
		// 3. Tìm level mới theo điểm mới
		int newLevelId = 0;
		try (Connection conn = DBUtil.getInstance().getConnect();
				PreparedStatement stmt = conn.prepareStatement(
						"SELECT _id FROM tbl_Level_user WHERE ? BETWEEN _score_start AND _score_end")) {
			stmt.setInt(1, newScore);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next())
					newLevelId = rs.getInt("_id");
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

	// Kiểm tra đăng nhập
	public static User checkLogin(String username, String hashedPassword) throws SQLException {
		String sql = "SELECT * FROM tbl_user WHERE _username = ? AND _password = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

	public static boolean update(User u) throws SQLException {
		String sql = "UPDATE tbl_user SET _username = ?, _role = ?, _active = ?, _score = ?, _name = ?, _image = ?, _level_id = ? WHERE _id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

	public static List<User> search(String keyword) throws SQLException {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM tbl_user WHERE _username LIKE ? OR _name LIKE ? ORDER BY _id";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

	public static User getUserById(long id) throws SQLException {
		String sql = "SELECT * FROM tbl_user WHERE _id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
		}
		return null;
	}
}
