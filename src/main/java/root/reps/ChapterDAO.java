package root.reps;

import root.entities.Chapter;
import root.myutils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {
    // Lấy tất cả chapter của 1 truyện
    public static List<Chapter> getByStoryId(int storyId) throws SQLException {
        List<Chapter> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_chapter WHERE _story_id = ? ORDER BY _number ASC";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, storyId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Chapter c = new Chapter(
                        rs.getInt("_id"),
                        rs.getInt("_story_id"),
                        rs.getInt("_number"),
                        rs.getString("_title"),
                        rs.getString("_content")
                    );
                    list.add(c);
                }
            }
        }
        return list;
    }

    // Lấy chapter theo ID
    public static Chapter getById(int id) throws SQLException {
        String sql = "SELECT * FROM tbl_chapter WHERE _id = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Chapter(
                        rs.getInt("_id"),
                        rs.getInt("_story_id"),
                        rs.getInt("_number"),
                        rs.getString("_title"),
                        rs.getString("_content")
                    );
                }
            }
        }
        return null;
    }
    
    // Thêm, sửa, xóa (nếu cần) bạn chỉ cần copy mẫu insert/update của các DAO khác rồi đổi trường.
}
