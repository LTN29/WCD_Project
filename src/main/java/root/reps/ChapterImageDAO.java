package root.reps;

import root.entities.ChapterImage;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterImageDAO {

    // Lấy danh sách ảnh theo chapter ID
    public static List<ChapterImage> getByChapterId(int chapterId) throws SQLException {
        List<ChapterImage> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_chapter_image WHERE _chapter_id = ? ORDER BY _index ASC";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, chapterId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new ChapterImage(
                        rs.getLong("_id"),
                        rs.getString("_image"),
                        rs.getInt("_index"),
                        rs.getInt("_chapter_id")
                    ));
                }
            }
        }
        return list;
    }

    // Thêm ảnh
    public static long insert(ChapterImage image) throws SQLException {
        String sql = "INSERT INTO tbl_chapter_image (_image, _index, _chapter_id) VALUES (?, ?, ?)";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, image.getImage());
            stmt.setInt(2, image.getIndex());
            stmt.setInt(3, image.getChapterId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
        }
        return 0;
    }

    // Xoá ảnh theo ID
    public static boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM tbl_chapter_image WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
