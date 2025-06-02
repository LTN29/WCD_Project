package root.reps;

import root.entities.StoryComment;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryCommentDAO {

	
    // 🔍 Lấy danh sách bình luận theo ID truyện
	public static List<StoryComment> getByStoryId(int storyId) throws SQLException {
	    List<StoryComment> list = new ArrayList<>();
	    String sql = "SELECT c._id, c._content, c._active, c._story_id, c._user_id, u._name AS userName " +
	                 "FROM tbl_story_comment c " +
	                 "JOIN tbl_user u ON c._user_id = u._id " +
	                 "WHERE c._story_id = ? ORDER BY c._id ASC";
	    try (Connection conn = DBUtil.getInstance().getConnect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, storyId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                StoryComment comment = new StoryComment(
	                    rs.getLong("_id"),
	                    rs.getString("_content"),
	                    rs.getInt("_active"),
	                    rs.getInt("_story_id"),
	                    rs.getLong("_user_id")
	                );
	                comment.setUserName(rs.getString("userName")); 
	                list.add(comment);
	            }
	        }
	    }
	    return list;
	}


    // ✅ Thêm mới bình luận
 // Thêm hàm kiểm tra user đã comment truyện này chưa:
    public static boolean isFirstComment(long l, int storyId) throws SQLException {
        String sql = "SELECT 1 FROM tbl_story_comment WHERE _user_id = ? AND _story_id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, l);
            stmt.setInt(2, storyId);
            try (ResultSet rs = stmt.executeQuery()) {
                return !rs.next(); // true nếu chưa từng comment
            }
        }
    }

    // Chỉnh lại hàm insert:
    public static int insert(StoryComment c) throws SQLException {
        boolean firstComment = isFirstComment(c.getUserId(), c.getStoryId());
        String sql = "INSERT INTO tbl_story_comment (_content, _active, _story_id, _user_id) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, c.getContent());
            stmt.setInt(2, c.getActive());
            stmt.setInt(3, c.getStoryId());
            stmt.setLong(4, c.getUserId());

            int affected = stmt.executeUpdate();
            if (affected == 0) throw new SQLException("Insert failed: No rows affected.");

            if (firstComment) {
                UserDAO.addScoreAndUpdateLevel(c.getUserId(), 3); // +3 điểm comment đầu tiên truyện này
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }


    // ✅ Duyệt bình luận
    public static boolean approve(int id) throws SQLException {
        String sql = "UPDATE tbl_story_comment SET _active = 1 WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id); // ✅ dùng int thay vì long
            return stmt.executeUpdate() > 0;
        }
    }

    // 🗑 Xoá bình luận
    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM tbl_story_comment WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id); // ✅ dùng int thay vì long
            return stmt.executeUpdate() > 0;
        }
    }

    // 📄 Lấy bình luận theo ID
    public static StoryComment getById(int id) throws SQLException {
        String sql = "SELECT * FROM tbl_story_comment WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new StoryComment(
                        rs.getInt("_id"),           // ✅ Đổi từ getLong
                        rs.getString("_content"),
                        rs.getInt("_active"),
                        rs.getInt("_story_id"),
                        rs.getInt("_user_id")
                    );
                }
            }
        }
        return null;
    }
}
