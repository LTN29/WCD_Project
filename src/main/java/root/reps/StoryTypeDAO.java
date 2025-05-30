package root.reps;

import root.entities.StoryType;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryTypeDAO {

    // ✅ Lấy tất cả thể loại
    public static List<StoryType> getAll() throws SQLException {
        List<StoryType> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_story_type";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new StoryType(rs.getInt("_id"), rs.getString("_title"), rs.getInt("_active")));
            }
        }
        return list;
    }

    // 📌 Lấy tất cả thể loại đang hoạt động
    public static List<StoryType> getActiveTypes() throws SQLException {
        List<StoryType> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_story_type WHERE _active = 1 ORDER BY _title ASC";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                list.add(new StoryType(
                    rs.getInt("_id"),
                    rs.getString("_title"),
                    rs.getInt("_active")
                ));
            }
        }
        return list;
    }

    // ✅ Thêm thể loại mới
    public static int insert(StoryType t) throws SQLException {
        String sql = "INSERT INTO tbl_story_type (_title, _active) VALUES (?, ?)";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, t.getTitle());
            stmt.setInt(2, t.getActive());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    // 🖊 Cập nhật thể loại
    public static boolean update(StoryType t) throws SQLException {
        String sql = "UPDATE tbl_story_type SET _title = ?, _active = ? WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, t.getTitle());
            stmt.setInt(2, t.getActive());
            stmt.setInt(3, t.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // 🗑 Xoá thể loại
    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM tbl_story_type WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
