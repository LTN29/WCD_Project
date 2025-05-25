package root.reps;

import root.entities.LevelUser;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LevelUserDAO {

    // Lấy cấp độ theo điểm người dùng
    public static LevelUser getByScore(int score) throws SQLException {
        String sql = "SELECT * FROM tbl_level_user WHERE ? BETWEEN _score_start AND _score_end";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, score);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LevelUser(
                        rs.getInt("_id"),
                        rs.getString("_level"),
                        rs.getInt("_score_start"),
                        rs.getInt("_score_end")
                    );
                }
            }
        }
        return null;
    }

    // Lấy toàn bộ cấp độ
    public static List<LevelUser> getAll() throws SQLException {
        List<LevelUser> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_level_user ORDER BY _score_start ASC";
        try (
            Connection conn = DBUtil.getInstance().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                list.add(new LevelUser(
                    rs.getInt("_id"),
                    rs.getString("_level"),
                    rs.getInt("_score_start"),
                    rs.getInt("_score_end")
                ));
            }
        }
        return list;
    }
}
