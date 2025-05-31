package root.reps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import root.entities.Story;
import root.myutils.DBUtil;

public class StoryDAO {

    // Thêm truyện mới
    public boolean insertStory(Story story) throws SQLException {
        String sql = "INSERT INTO [dbo].[tbl_story] ([_title], [_chapter_number], [_introduction], [_image], [_like_number], [_follow_number], [_view_number], [_author_id], [_status_id], [_category_id], [_story_type_id], [_schedule_id]) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, story.getTitle());
            ps.setInt(2, story.getChapterNumber());
            ps.setString(3, story.getIntroduction());
            ps.setString(4, story.getImage());
            ps.setInt(5, story.getLikeNumber());
            ps.setInt(6, story.getFollowNumber());
            ps.setInt(7, story.getViewNumber());
            ps.setInt(8, story.getAuthorId());
            ps.setInt(9, story.getStatusId());
            ps.setInt(10, story.getCategoryId());
            ps.setInt(11, story.getStoryTypeId());
            ps.setInt(12, story.getScheduleId());
            return ps.executeUpdate() > 0;
        }
    }

    // Lấy truyện theo id
    public Story getStoryById(int id) throws SQLException {
        String sql = "SELECT * FROM [dbo].[tbl_story] WHERE [_id] = ?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToStory(rs);
                }
            }
        }
        return null;
    }

    // Cập nhật truyện
    public boolean updateStory(Story story) throws SQLException {
        String sql = "UPDATE [dbo].[tbl_story] SET [_title]=?, [_chapter_number]=?, [_introduction]=?, [_image]=?, [_like_number]=?, [_follow_number]=?, [_view_number]=?, [_author_id]=?, [_status_id]=?, [_category_id]=?, [_story_type_id]=?, [_schedule_id]=? WHERE [_id]=?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, story.getTitle());
            ps.setInt(2, story.getChapterNumber());
            ps.setString(3, story.getIntroduction());
            ps.setString(4, story.getImage());
            ps.setInt(5, story.getLikeNumber());
            ps.setInt(6, story.getFollowNumber());
            ps.setInt(7, story.getViewNumber());
            ps.setInt(8, story.getAuthorId());
            ps.setInt(9, story.getStatusId());
            ps.setInt(10, story.getCategoryId());
            ps.setInt(11, story.getStoryTypeId());
            ps.setInt(12, story.getScheduleId());
            ps.setInt(13, story.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // Xóa truyện theo id
    public boolean deleteStory(int id) throws SQLException {
        String sql = "DELETE FROM [dbo].[tbl_story] WHERE [_id]=?";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Lấy danh sách truyện với phân trang và tìm kiếm (SQL Server syntax)
    public List<Story> getFilteredStories(int offset, int limit, String searchKey) throws SQLException {
        List<Story> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.[_id], s.[_title], s.[_chapter_number], s.[_introduction], s.[_image], ")
           .append("s.[_like_number], s.[_follow_number], s.[_view_number], ")
           .append("s.[_author_id], s.[_status_id], s.[_category_id], s.[_story_type_id], s.[_schedule_id], ")
           .append("a.[_name] AS authorName, c.[_name] AS categoryName, st.[_title] AS statusTitle, sch.[_day] AS scheduleDay ")
           .append("FROM [dbo].[tbl_story] s ")
           .append("LEFT JOIN [dbo].[tbl_author] a ON s.[_author_id] = a.[_id] ")
           .append("LEFT JOIN [dbo].[tbl_category] c ON s.[_category_id] = c.[_id] ")
           .append("LEFT JOIN [dbo].[tbl_status] st ON s.[_status_id] = st.[_id] ")
           .append("LEFT JOIN [dbo].[tbl_schedule] sch ON s.[_schedule_id] = sch.[_id] ");

        if (searchKey != null && !searchKey.trim().isEmpty()) {
            sql.append("WHERE s.[_title] LIKE ? ");
        }

        sql.append("ORDER BY s.[_id] DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (searchKey != null && !searchKey.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + searchKey + "%");
            }
            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Story s = new Story();
                    s.setId(rs.getInt("_id"));
                    s.setTitle(rs.getString("_title"));
                    s.setChapterNumber(rs.getInt("_chapter_number"));
                    s.setIntroduction(rs.getString("_introduction"));
                    s.setImage(rs.getString("_image"));
                    s.setLikeNumber(rs.getInt("_like_number"));
                    s.setFollowNumber(rs.getInt("_follow_number"));
                    s.setViewNumber(rs.getInt("_view_number"));
                    s.setAuthorId(rs.getInt("_author_id"));
                    s.setStatusId(rs.getInt("_status_id"));
                    s.setCategoryId(rs.getInt("_category_id"));
                    s.setStoryTypeId(rs.getInt("_story_type_id"));
                    s.setScheduleId(rs.getInt("_schedule_id"));
                    s.setAuthorName(rs.getString("authorName"));
                    s.setCategoryName(rs.getString("categoryName"));
                    s.setStatusTitle(rs.getString("statusTitle"));
                    s.setScheduleDay(rs.getString("scheduleDay"));
                    list.add(s);
                }
            }
        }
        return list;
    }


    // Đếm tổng số truyện (có tìm kiếm)
    public int countStories(String searchKey) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM [dbo].[tbl_story] ");
        if (searchKey != null && !searchKey.trim().isEmpty()) {
            sql.append("WHERE [_title] LIKE ?");
        }
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            if (searchKey != null && !searchKey.trim().isEmpty()) {
                ps.setString(1, "%" + searchKey + "%");
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    // Map ResultSet thành object Story
    private Story mapResultSetToStory(ResultSet rs) throws SQLException {
        Story s = new Story();
        s.setId(rs.getInt("_id"));
        s.setTitle(rs.getString("_title"));
        s.setChapterNumber(rs.getInt("_chapter_number"));
        s.setIntroduction(rs.getString("_introduction"));
        s.setImage(rs.getString("_image"));
        s.setLikeNumber(rs.getInt("_like_number"));
        s.setFollowNumber(rs.getInt("_follow_number"));
        s.setViewNumber(rs.getInt("_view_number"));
        s.setAuthorId(rs.getInt("_author_id"));
        s.setStatusId(rs.getInt("_status_id"));
        s.setCategoryId(rs.getInt("_category_id"));
        s.setStoryTypeId(rs.getInt("_story_type_id"));
        s.setScheduleId(rs.getInt("_schedule_id"));
        return s;
    }
}
