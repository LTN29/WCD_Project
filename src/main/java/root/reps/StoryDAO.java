package root.reps;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import root.entities.Story;
import root.myutils.DBUtil;
public class StoryDAO {
	public static List<Story> getAll() throws SQLException {
        List<Story> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_story ORDER BY _id DESC";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Story s = new Story(
                    rs.getInt("_id"),
                    rs.getString("_title"),
                    rs.getInt("_chapter_number"),
                    rs.getString("_introduction"),
                    rs.getString("_image"),
                    rs.getInt("_like_number"),
                    rs.getInt("_follow_number"),
                    rs.getInt("_view_number"),
                    rs.getInt("_author_id"),
                    rs.getInt("_status_id"),
                    rs.getInt("_category_id")
                );
                list.add(s);
            }
        }
        return list;
    }
	public static int insert(Story s) throws SQLException {
        String sql = "INSERT INTO tbl_story(_title, _chapter_number, _introduction, _image, _like_number, _follow_number, _view_number, _author_id, _status_id, _category_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, s.getTitle());
            stmt.setInt(2, s.getChapterNumber());
            stmt.setString(3, s.getIntroduction());
            stmt.setString(4, s.getImage());
            stmt.setInt(5, s.getLikeNumber());
            stmt.setInt(6, s.getFollowNumber());
            stmt.setInt(7, s.getViewNumber());
            stmt.setInt(8, s.getAuthorId());
            stmt.setInt(9, s.getStatusId());
            stmt.setInt(10, s.getCategoryId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Insert failed, no rows affected.");
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getInt(1);
                else throw new SQLException("Insert failed, no ID obtained.");
            }
        }
    }
	
	public static int update(Story s) throws SQLException {
        String sql = "UPDATE tbl_story SET _title=?, _chapter_number=?, _introduction=?, _image=?, _like_number=?, _follow_number=?, _view_number=?, _author_id=?, _status_id=?, _category_id=? WHERE _id=?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getTitle());
            stmt.setInt(2, s.getChapterNumber());
            stmt.setString(3, s.getIntroduction());
            stmt.setString(4, s.getImage());
            stmt.setInt(5, s.getLikeNumber());
            stmt.setInt(6, s.getFollowNumber());
            stmt.setInt(7, s.getViewNumber());
            stmt.setInt(8, s.getAuthorId());
            stmt.setInt(9, s.getStatusId());
            stmt.setInt(10, s.getCategoryId());
            stmt.setInt(11, s.getId());
            return stmt.executeUpdate();
        }
    }
	public static int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM tbl_story WHERE _id=?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }

    public static Story getById(int id) throws SQLException {
        String sql = "SELECT s.*, a._name AS authorName, c._name AS categoryName, st._title AS statusName " +
                "FROM tbl_story s " +
                "LEFT JOIN tbl_author a ON s._author_id = a._id " +
                "LEFT JOIN tbl_category c ON s._category_id = c._id " +
                "LEFT JOIN tbl_status st ON s._status_id = st._id " +
                "WHERE s._id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Story(
                        rs.getInt("_id"),
                        rs.getString("_title"),
                        rs.getInt("_chapter_number"),
                        rs.getString("_introduction"),
                        rs.getString("_image"),
                        rs.getInt("_like_number"),
                        rs.getInt("_follow_number"),
                        rs.getInt("_view_number"),
                        rs.getInt("_author_id"),
                        rs.getInt("_status_id"),
                        rs.getInt("_category_id")
                    );
                }
            }
        }
        return null;
    }
    public static List<Story> getAllWithNames() throws SQLException {
        List<Story> list = new ArrayList<>();
        String sql = "SELECT s.*, a._name AS authorName, c._name AS categoryName, st._title AS statusName " +
                     "FROM tbl_story s " +
                     "LEFT JOIN tbl_author a ON s._author_id = a._id " +
                     "LEFT JOIN tbl_category c ON s._category_id = c._id " +
                     "LEFT JOIN tbl_status st ON s._status_id = st._id " +
                     "ORDER BY s._id DESC";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Story s = new Story(
                    rs.getInt("_id"),
                    rs.getString("_title"),
                    rs.getInt("_chapter_number"),
                    rs.getString("_introduction"),
                    rs.getString("_image"),
                    rs.getInt("_like_number"),
                    rs.getInt("_follow_number"),
                    rs.getInt("_view_number"),
                    rs.getInt("_author_id"),
                    rs.getInt("_status_id"),
                    rs.getInt("_category_id")
                );
                s.setAuthorName(rs.getString("authorName"));
                s.setCategoryName(rs.getString("categoryName"));
                s.setStatusName(rs.getString("statusName"));
                list.add(s);
            }
        }
        return list;
    }

	
}
