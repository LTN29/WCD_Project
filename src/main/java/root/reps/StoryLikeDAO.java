package root.reps;

import root.entities.StoryLike;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryLikeDAO {

    // 📌 Kiểm tra người dùng đã like truyện chưa
    public static boolean isLiked(int userId, int storyId) throws SQLException {
        String sql = "SELECT 1 FROM tbl_story_like WHERE _user_id = ? AND _story_id = ? AND _like = 1";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userId);
            stmt.setInt(2, storyId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // ✅ Thêm lượt thích mới
    public static long insert(StoryLike like) throws SQLException {
        String sql = "INSERT INTO tbl_story_like (_user_id, _story_id, _day_create, _like) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, like.getUserId());
            stmt.setInt(2, like.getStoryId());
            stmt.setDate(3, like.getDayCreate());
            stmt.setInt(4, like.getLike());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
        }
        return 0;
    }

    // 🔢 Đếm tổng số lượt like của một truyện
    public static int countLikeByStory(int storyId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_story_like WHERE _story_id = ? AND _like = 1";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, storyId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }
}
