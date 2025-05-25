package root.reps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import root.entities.Status;
import root.myutils.DBUtil;

public class StatusDAO {
	public static List<Status> getAll() throws SQLException {
        List<Status> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_status";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Status st = new Status(rs.getInt("_id"), rs.getString("_title"), rs.getInt("_active"),rs.getInt("_group_status_id"));
                list.add(st);
            }
        }
        return list;
    }
}
