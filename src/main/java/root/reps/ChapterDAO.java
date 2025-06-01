package root.reps;

import root.entities.Chapter;
import root.entities.Story;
import root.myutils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {

    public static List<Chapter> getByStoryId(int storyId) throws SQLException {
        List<Chapter> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_chapter WHERE _story_id = ? ORDER BY _id ASC";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public record ChapterView(int id, String title, Date dayCreate,
                              int storyId, String storyTitle, String authorName) {}

    public static List<ChapterView> getAllWithStory() throws SQLException {
        String sql = """
        SELECT c._id, c._title, c._day_create,
               s._id AS storyId, s._title AS storyTitle,
               a._name AS authorName
          FROM tbl_chapter c
          JOIN tbl_story s ON c._story_id = s._id
          JOIN tbl_author a ON s._author_id = a._id
         ORDER BY c._id DESC
        """;

        List<ChapterView> list = new ArrayList<>();
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ChapterView(
                    rs.getInt("_id"),
                    rs.getString("_title"),
                    rs.getDate("_day_create"),
                    rs.getInt("storyId"),
                    rs.getString("storyTitle"),
                    rs.getString("authorName")
                ));
            }
        }
        return list;
    }

    // 1.1 Đếm tổng số bản ghi theo keyword (search)
    public static int count(String keyword) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_chapter"
                   + (keyword != null && !keyword.isBlank() ? " WHERE _title LIKE ?" : "");
        try (Connection c = DBUtil.getInstance().getConnect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            if (keyword != null && !keyword.isBlank()) ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    // 1.2 Lấy danh sách phân trang có tìm kiếm
    public static List<Chapter> search(String keyword, int offset, int limit) throws SQLException {
        String sql = "SELECT * FROM tbl_chapter"
                   + (keyword != null && !keyword.isBlank() ? " WHERE _title LIKE ?" : "")
                   + " ORDER BY _id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<Chapter> list = new ArrayList<>();
        try (Connection c = DBUtil.getInstance().getConnect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            int idx = 1;
            if (keyword != null && !keyword.isBlank()) ps.setString(idx++, "%" + keyword + "%");
            ps.setInt(idx++, offset);
            ps.setInt(idx, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Chapter(
                        rs.getInt("_id"),
                        rs.getString("_title"),
                        rs.getString("_content"),
                        rs.getDate("_day_create"),
                        rs.getInt("_story_id")
                    ));
                }
            }
        }
        return list;
    }

    // Đếm số chương theo storyId
    public static int countByStoryId(Integer storyId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_chapter";
        if (storyId != null) sql += " WHERE _story_id = ?";
        try (Connection c = DBUtil.getInstance().getConnect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            if (storyId != null) ps.setInt(1, storyId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
                return 0;
            }
        }
    }

    // Lấy chương theo storyId, phân trang
    public static List<Chapter> searchByStoryId(Integer storyId, int offset, int limit) throws SQLException {
        List<Chapter> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_chapter";
        if (storyId != null) sql += " WHERE _story_id = ?";
        sql += " ORDER BY _id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection c = DBUtil.getInstance().getConnect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            int i = 1;
            if (storyId != null) ps.setInt(i++, storyId);
            ps.setInt(i++, offset);
            ps.setInt(i, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Chapter(
                        rs.getInt("_id"),
                        rs.getString("_title"),
                        rs.getString("_content"),
                        rs.getDate("_day_create"),
                        rs.getInt("_story_id")
                    ));
                }
            }
        }
        return list;
    }

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

    public static int insert(Chapter c) throws SQLException {
        String sql = "INSERT INTO tbl_chapter (_title, _content, _day_create, _story_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    public static boolean update(Chapter c) throws SQLException {
        String sql = "UPDATE tbl_chapter SET _title=?, _content=?, _day_create=?, _story_id=? WHERE _id=?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getContent());
            stmt.setDate(3, c.getDayCreate());
            stmt.setInt(4, c.getStoryId());
            stmt.setInt(5, c.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM tbl_chapter WHERE _id=?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Lấy tối đa 1000 chương (giúp test)
    public static List<Chapter> getAll() throws SQLException {
        List<Chapter> list = new ArrayList<>();
        String sql = "SELECT TOP 1000 * FROM tbl_chapter ORDER BY _id DESC";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Chapter(
                    rs.getInt("_id"),
                    rs.getString("_title"),
                    rs.getString("_content"),
                    rs.getDate("_day_create"),
                    rs.getInt("_story_id")
                ));
            }
        }
        return list;
    }
    public List<Story> filterStories(String title, int authorId, int categoryId, int statusId, int typeId, String scheduleDay) throws SQLException {
        List<Story> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT s.*, a.name as authorName, c.name as categoryName, st.title as statusTitle, ty.title as storyTypeTitle, ss.schedule_day " +
                "FROM tbl_story s " +
                "JOIN tbl_author a ON s.author_id = a.id " +
                "JOIN tbl_category c ON s.category_id = c.id " +
                "JOIN tbl_status st ON s.status_id = st.id " +
                "JOIN tbl_story_type ty ON s.story_type_id = ty.id " +
                "LEFT JOIN tbl_story_schedule ss ON s.id = ss.story_id " +
                "WHERE 1=1 ");

        // Tạo danh sách tham số
        List<Object> params = new ArrayList<>();

        if (title != null && !title.trim().isEmpty()) {
            sql.append("AND s.title LIKE ? ");
            params.add("%" + title + "%");
        }
        if (authorId > 0) {
            sql.append("AND s.author_id = ? ");
            params.add(authorId);
        }
        if (categoryId > 0) {
            sql.append("AND s.category_id = ? ");
            params.add(categoryId);
        }
        if (statusId > 0) {
            sql.append("AND s.status_id = ? ");
            params.add(statusId);
        }
        if (typeId > 0) {
            sql.append("AND s.story_type_id = ? ");
            params.add(typeId);
        }
        if (scheduleDay != null && !scheduleDay.trim().isEmpty()) {
            sql.append("AND ss.schedule_day = ? ");
            params.add(scheduleDay);
        }

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Story s = new Story();
                    s.setId(rs.getInt("id"));
                    s.setTitle(rs.getString("title"));
                    s.setImage(rs.getString("image"));
                    s.setDescription(rs.getString("description"));
                    s.setAuthorId(rs.getInt("author_id"));
                    s.setCategoryId(rs.getInt("category_id"));
                    s.setStatusId(rs.getInt("status_id"));
                    s.setStoryTypeId(rs.getInt("story_type_id"));
                    s.setPoint(rs.getDouble("point"));
                    s.setCreatedAt(rs.getTimestamp("created_at"));
                    s.setUpdatedAt(rs.getTimestamp("updated_at"));
                    s.setAuthorName(rs.getString("authorName"));
                    s.setCategoryName(rs.getString("categoryName"));
                    s.setStatusTitle(rs.getString("statusTitle"));
                    s.setStoryTypeTitle(rs.getString("storyTypeTitle"));
                    s.setScheduleDay(rs.getString("schedule_day"));
                    list.add(s);
                }
            }
        }
        return list;
    }

}