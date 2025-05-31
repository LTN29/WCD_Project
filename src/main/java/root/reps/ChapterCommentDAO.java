package root.reps;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import root.entities.ChapterComment;
import root.myutils.DBUtil;

public class ChapterCommentDAO {
	public static List<ChapterComment> getChapterComments(int chapterId) {
	    List<ChapterComment> list = new ArrayList<>();
	    String sql = "SELECT c._id, c._content, c._user_id, u._name AS userName, c._chapter_id " +
	                 "FROM tbl_chapter_comment c " +
	                 "JOIN tbl_user u ON c._user_id = u._id " +
	                 "WHERE c._chapter_id = ? AND c._active = 1 ORDER BY c._id DESC";
	    try (Connection conn = DBUtil.getInstance().getConnect();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, chapterId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            ChapterComment comment = new ChapterComment();
	            comment.setId(rs.getLong("_id"));
	            comment.setContent(rs.getString("_content"));
	            comment.setUserId(rs.getLong("_user_id")); // sửa thành long
	            comment.setUserName(rs.getString("userName")); // sửa hàm set đúng tên
	            comment.setChapterId(rs.getInt("_chapter_id"));
	            list.add(comment);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

    // Hàm thêm comment mới cho chương
    public static boolean addChapterComment(long userId, int chapterId, String content) {
        String sql = "INSERT INTO tbl_chapter_comment (_content, _active, _chapter_id, _user_id) VALUES (?, 1, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, content);
            ps.setInt(2, chapterId);
            ps.setLong(3, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
