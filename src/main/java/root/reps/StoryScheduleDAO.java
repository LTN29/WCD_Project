package root.reps;

import root.entities.StorySchedule;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryScheduleDAO {

    // 📌 Lấy danh sách lịch cập nhật
    public static List<StorySchedule> getAll() throws SQLException {
        List<StorySchedule> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_story_schedule ORDER BY _id ASC";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                list.add(new StorySchedule(
                    rs.getInt("_id"),
                    rs.getString("_day"),
                    rs.getInt("_time")
                ));
            }
        }
        return list;
    }

    // ✅ Thêm lịch cập nhật mới
    public static int insert(StorySchedule s) throws SQLException {
        String sql = "INSERT INTO tbl_story_schedule (_day, _time) VALUES (?, ?)";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, s.getDay());
            stmt.setInt(2, s.getTime());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    // 🗑 Xoá lịch cập nhật theo ID
    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM tbl_story_schedule WHERE _id = ?";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
