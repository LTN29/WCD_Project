package root.reps;

import root.entities.ChapterComment;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterCommentDAO {

    // Lấy danh sách bình luận theo ID chương
    public static List<ChapterComment> getByChapterId(int chapterId) throws SQLException {
        List<ChapterComment> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_chapter_comment WHERE _chapter_id = ? ORDER BY _id ASC";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, chapterId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ChapterComment c = new ChapterComment(
                        rs.getLong("_id"),
                        rs.getString("_content"),
                        rs.getInt("_active"),
                        rs.getInt("_chapter_id"),
                        rs.getInt("_user_id")
                    );
                    list.add(c);
                }
            }
        }
        return list;
    }

    // Lấy chi tiết bình luận theo ID
    public static ChapterComment getById(long id) throws SQLException {
        String sql = "SELECT * FROM tbl_chapter_comment WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ChapterComment(
                        rs.getLong("_id"),
                        rs.getString("_content"),
                        rs.getInt("_active"),
                        rs.getInt("_chapter_id"),
                        rs.getInt("_user_id")
                    );
                }
            }
        }
        return null;
    }

    // Thêm mới bình luận
    public static long insert(ChapterComment c) throws SQLException {
        String sql = "INSERT INTO tbl_chapter_comment (_content, _active, _chapter_id, _user_id) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, c.getContent());
            stmt.setInt(2, c.getActive());
            stmt.setInt(3, c.getChapterId());
            stmt.setInt(4, c.getUserId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Insert failed, no rows affected.");
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getLong(1);
            }
        }
        return 0;
    }

    // Cập nhật bình luận
    public static boolean update(ChapterComment c) throws SQLException {
        String sql = "UPDATE tbl_chapter_comment SET _content = ?, _active = ?, _chapter_id = ?, _user_id = ? WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, c.getContent());
            stmt.setInt(2, c.getActive());
            stmt.setInt(3, c.getChapterId());
            stmt.setInt(4, c.getUserId());
            stmt.setLong(5, c.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Xóa bình luận
    public static boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM tbl_chapter_comment WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Lấy các bình luận được duyệt
    public static List<ChapterComment> getApprovedByChapterId(int chapterId) throws SQLException {
        List<ChapterComment> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_chapter_comment WHERE _chapter_id = ? AND _active = 1 ORDER BY _id ASC";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, chapterId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ChapterComment c = new ChapterComment(
                        rs.getLong("_id"),
                        rs.getString("_content"),
                        rs.getInt("_active"),
                        rs.getInt("_chapter_id"),
                        rs.getInt("_user_id")
                    );
                    list.add(c);
                }
            }
        }
        return list;
    }
}
