package root.reps;

import root.entities.StoryLike;
import root.myutils.DBUtil;
import java.sql.*;

public class StoryLikeDAO {

	// 📌 Kiểm tra người dùng đã like truyện chưa
	public static boolean isLiked(long l, int storyId) throws SQLException {
		String sql = "SELECT 1 FROM tbl_story_like WHERE _user_id = ? AND _story_id = ? AND _like = 1";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, l);
			stmt.setInt(2, storyId);
			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		}
	}

	// ✅ Thêm lượt thích mới
	public static long insert(StoryLike like) throws SQLException {
		if (isLiked(like.getUserId(), like.getStoryId()))
			return 0; // đã like, không insert
		String sql = "INSERT INTO tbl_story_like (_user_id, _story_id, _day_create, _like) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBUtil.getInstance().getConnect();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, like.getUserId());
			stmt.setInt(2, like.getStoryId());
			stmt.setDate(3, like.getDayCreate());
			stmt.setInt(4, like.getLike());
			int affected = stmt.executeUpdate();
			if (affected > 0) {
				UserDAO.addScoreAndUpdateLevel(like.getUserId(), 1); // Tích điểm
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next())
						return rs.getLong(1);
				}
			}
		}
		return 0;
	}

	// 🔢 Đếm tổng số lượt like của một truyện
	public static int countLikeByStory(int storyId) throws SQLException {
		String sql = "SELECT COUNT(*) FROM tbl_story_like WHERE _story_id = ? AND _like_number = 1";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, storyId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
			}
		}
		return 0;
	}

	public static void updateLikeNumber(int storyId) throws SQLException {
		String sql = "UPDATE tbl_story SET _like_number = (SELECT COUNT(*) FROM tbl_story_like WHERE _story_id = ? AND _like = 1) WHERE _id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, storyId);
			stmt.setInt(2, storyId);
			stmt.executeUpdate();
		}
	}

	public static void unlikeStory(long userId, long storyId) throws SQLException {
	    String deleteSql = "DELETE FROM tbl_story_like WHERE _user_id = ? AND _story_id = ?";
	    String updateSql = "UPDATE tbl_story SET _like_number = _like_number - 1 WHERE _id = ? AND _like_number  > 0";

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
