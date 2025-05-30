package root.reps;

import root.entities.Chapter;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {
    public static List<Chapter> getByStoryId(int storyId) throws SQLException {
        List<Chapter> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_chapter WHERE _story_id = ? ORDER BY _id ASC";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, storyId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Chapter c = new Chapter(
                        rs.getInt("_id"),
                        rs.getString("_title"),
                        rs.getString("_content"),
                        rs.getDate("_day_create"),
                        rs.getInt("_story_id")
                    );
                    list.add(c);
                }
            }
        }
        return list;
    }

    public static Chapter getById(int id) throws SQLException {
        String sql = "SELECT * FROM tbl_chapter WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Chapter(
                        rs.getInt("_id"),
                        rs.getString("_title"),
                        rs.getString("_content"),
                        rs.getDate("_day_create"),
                        rs.getInt("_story_id")
                    );
                }
            }
        }
        return null;
    }

    public static int insert(Chapter c) throws SQLException {
        String sql = "INSERT INTO tbl_chapter (_title, _content, _day_create, _story_id) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getContent());
            stmt.setDate(3, c.getDayCreate());
            stmt.setInt(4, c.getStoryId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Insert failed, no rows affected.");
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }
        }
        return 0;
    }

    // Cập nhật chapter
    public static boolean update(Chapter c) throws SQLException {
        String sql = "UPDATE tbl_chapter SET _title=?, _content=?, _day_create=?, _story_id=? WHERE _id=?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getContent());
            stmt.setDate(3, c.getDayCreate());
            stmt.setInt(4, c.getStoryId());
            stmt.setInt(5, c.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Xóa chapter
    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM tbl_chapter WHERE _id=?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<Chapter> getChaptersByStoryId(int storyId) throws Exception {
        List<Chapter> chapters = new ArrayList<>();
        String query = "SELECT _id, _title FROM tbl_chapter WHERE _story_id = ? ORDER BY _id";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, storyId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Chapter chapter = new Chapter();
                    chapter.setId(rs.getInt("_id"));
                    chapter.setTitle(rs.getString("_title"));
                    chapters.add(chapter);
                }
            }
        }
        return chapters;
    }

}
