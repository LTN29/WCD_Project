package root.reps;

import root.entities.StoryFollow;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryFollowDAO {

	// Lấy danh sách theo dõi của một người dùng
	public static List<StoryFollow> getByUserId(int userId) throws SQLException {
		List<StoryFollow> list = new ArrayList<>();
		String sql = "SELECT * FROM tbl_story_follow WHERE _user_id = ? ORDER BY day_create DESC";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					list.add(new StoryFollow(rs.getLong("_id"), rs.getInt("_user_id"), rs.getInt("_story_id"),
							rs.getDate("day_create"), rs.getInt("_folow")));
				}
			}
		}
		return list;
	}

	// Thêm theo dõi
	public static long insert(StoryFollow f) throws SQLException {
		if (isFollowing(f.getUserId(), f.getStoryId()))
			return 0; // Đã follow rồi, không insert
		String sql = "INSERT INTO tbl_story_follow (_user_id, _story_id, day_create, _folow) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBUtil.getInstance().getConnect();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, f.getUserId());
			stmt.setInt(2, f.getStoryId());
			stmt.setDate(3, f.getDayCreate());
			stmt.setInt(4, f.getFollow());
			int affected = stmt.executeUpdate();
			if (affected > 0) {
				UserDAO.addScoreAndUpdateLevel(f.getUserId(), 2); // +2 điểm khi follow thành công
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next())
						return rs.getLong(1);
				}
			}
		}
		return 0;
	}

	// Kiểm tra người dùng đã theo dõi truyện chưa
	public static boolean isFollowing(long l, int storyId) throws SQLException {
		String sql = "SELECT 1 FROM tbl_story_follow WHERE _user_id = ? AND _story_id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, l);
			stmt.setInt(2, storyId);
			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		}
	}

	public static void updateFollowNumber(int storyId) throws SQLException {
		String sql = "UPDATE tbl_story SET _follow_number = (SELECT COUNT(*) FROM tbl_story_follow WHERE _story_id = ?) WHERE _id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, storyId);
			stmt.setInt(2, storyId);
			stmt.executeUpdate();
		}
	}

	public static void unfollowStory(long userId, long storyId) throws SQLException {
	    String deleteSql = "DELETE FROM tbl_story_follow WHERE _user_id = ? AND _story_id = ?";
	    String updateSql = "UPDATE tbl_story SET _follow_number = _follow_number  - 1 WHERE _id = ? AND _follow_number  > 0";

	    try (Connection conn = DBUtil.getInstance().getConnect()) {
	        conn.setAutoCommit(false);

	        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
	             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

	            deleteStmt.setLong(1, userId);
	            deleteStmt.setLong(2, storyId);
	            deleteStmt.executeUpdate();

	            updateStmt.setLong(1, storyId);
	            updateStmt.executeUpdate();

	            conn.commit();
	        } catch (SQLException e) {
	            conn.rollback();
	            throw e;
	        }
	    }
	}


}
