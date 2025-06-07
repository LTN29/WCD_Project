package root.reps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import root.entities.ChapterComment;
import root.myutils.DBUtil;

public class ChapterCommentDAO {
	public static List<ChapterComment> getApprovedCommentsByChapterId(int chapterId) throws SQLException {
	    List<ChapterComment> list = new ArrayList<>();
	    String sql = "SELECT c._id, c._content, c._user_id, u._name AS userName, c._chapter_id " +
	                 "FROM tbl_chapter_comment c " +
	                 "JOIN tbl_user u ON c._user_id = u._id " +
	                 "WHERE c._chapter_id = ? AND c._active = 1 ORDER BY c._id DESC";
	    try (Connection conn = DBUtil.getInstance().getConnect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, chapterId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            ChapterComment comment = new ChapterComment();
	            comment.setId(rs.getLong("_id"));
	            comment.setContent(rs.getString("_content"));
	            comment.setUserId(rs.getLong("_user_id"));
	            comment.setUserName(rs.getString("userName"));
	            comment.setChapterId(rs.getInt("_chapter_id"));
	            list.add(comment);
	        }
	    }
	    return list;
	}


	public static List<ChapterComment> getAllPending() throws SQLException {
		List<ChapterComment> list = new ArrayList<>();
		String sql = "SELECT c._id, c._content, c._user_id, u._name AS userName, c._chapter_id, ch._title AS chapterTitle "
				+ "FROM tbl_chapter_comment c " + "JOIN tbl_user u ON c._user_id = u._id "
				+ "JOIN tbl_chapter ch ON c._chapter_id = ch._id " + "WHERE c._active = 0 ORDER BY c._id DESC";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChapterComment comment = new ChapterComment();
				comment.setId(rs.getLong("_id"));
				comment.setContent(rs.getString("_content"));
				comment.setUserId(rs.getLong("_user_id"));
				comment.setUserName(rs.getString("userName"));
				comment.setChapterId(rs.getInt("_chapter_id"));
				comment.setChapterTitle(rs.getString("chapterTitle"));
				list.add(comment);
			}
		}
		return list;
	}

	public static boolean isFirstComment(long l, int chapterId) throws SQLException {
		String sql = "SELECT 1 FROM tbl_chapter_comment WHERE _user_id = ? AND _chapter_id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, l);
			stmt.setInt(2, chapterId);
			try (ResultSet rs = stmt.executeQuery()) {
				return !rs.next();
			}
		}
	};

	// Hàm thêm comment mới cho chương
	public static boolean addChapterComment(long userId, int chapterId, String content) {
		String sql = "INSERT INTO tbl_chapter_comment (_content, _active, _chapter_id, _user_id) VALUES (?, 1, ?, ?)";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, content);
			ps.setInt(2, chapterId);
			ps.setLong(3, userId);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int insert(ChapterComment c) throws SQLException {
		boolean firstComment = isFirstComment(c.getUserId(), c.getChapterId());
		String sql = "INSERT INTO tbl_chapter_comment (_content, _active, _chapter_id, _user_id) VALUES(?,?,?,?)";
		try (Connection conn = DBUtil.getInstance().getConnect();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, c.getContent());
			stmt.setInt(2, c.getActive());
			stmt.setInt(3, c.getChapterId());
			stmt.setLong(4, c.getUserId());

			int affected = stmt.executeUpdate();
			if (affected == 0)
				throw new SQLException("Insert failed: No rows affected.");
			if (firstComment) {
				UserDAO.addScoreAndUpdateLevel(c.getUserId(), 3);
			}
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next())
					return rs.getInt(1);
			}

		}
		return 0;
	}

	public static boolean setActive(long commentId, boolean active) throws SQLException {
		String sql = "UPDATE tbl_chapter_comment SET _active = ? WHERE _id = ?";
		try (Connection conn = DBUtil.getInstance().getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setBoolean(1, active);
			stmt.setLong(2, commentId);
			return stmt.executeUpdate() > 0;
		}
	}

}
