package root.reps;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
