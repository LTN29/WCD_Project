package root.reps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import root.entities.Chapter;
import root.myutils.DBUtil;

public class ChapterDAO {

    // Lấy danh sách tất cả chapter, sắp xếp theo ID giảm dần (mới nhất lên đầu)
    public static List<Chapter> getAll() throws SQLException {
        List<Chapter> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_chapter ORDER BY _id DESC";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
        return list;
    }

    // Thêm chapter mới, trả về ID tự động sinh
    public static int insert(Chapter c) throws SQLException {
        String sql = "INSERT INTO tbl_chapter (_title, _content, _day_create, _story_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getContent());
            stmt.setDate(3, c.getDayCreate());
            stmt.setInt(4, c.getStoryId());

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new SQLException("Insert thất bại: không có dòng nào được thêm.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Insert thất bại: không lấy được ID.");
                }
            }
        }
    }

    // Cập nhật chapter
    public static int update(Chapter c) throws SQLException {
        String sql = "UPDATE tbl_chapter SET _title = ?, _content = ?, _day_create = ?, _story_id = ? WHERE _id = ?";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getContent());
            stmt.setDate(3, c.getDayCreate());
            stmt.setInt(4, c.getStoryId());
            stmt.setInt(5, c.getId());

            return stmt.executeUpdate();
        }
    }

    // Xoá chapter theo ID
    public static int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM tbl_chapter WHERE _id = ?";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }

    // Lấy thông tin chapter theo ID
    public static Chapter getById(int id) throws SQLException {
        String sql = "SELECT * FROM tbl_chapter WHERE _id = ?";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
}
