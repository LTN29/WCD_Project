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
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new StoryFollow(
                        rs.getLong("_id"),
                        rs.getInt("_user_id"),
                        rs.getInt("_story_id"),
                        rs.getDate("day_create"),
                        rs.getInt("_folow")
                    ));
                }
            }
        }
        return list;
    }

    // Thêm theo dõi
    public static long insert(StoryFollow f) throws SQLException {
        String sql = "INSERT INTO tbl_story_follow (_user_id, _story_id, day_create, _folow) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, f.getUserId());
            stmt.setInt(2, f.getStoryId());
            stmt.setDate(3, f.getDayCreate());
            stmt.setInt(4, f.getFollow());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
        }
        return 0;
    }

    // Kiểm tra người dùng đã theo dõi truyện chưa
    public static boolean isFollowing(int userId, int storyId) throws SQLException {
        String sql = "SELECT 1 FROM tbl_story_follow WHERE _user_id = ? AND _story_id = ?";
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
}
