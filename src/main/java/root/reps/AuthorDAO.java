package root.reps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import root.entities.Author;
import root.myutils.DBUtil;

public class AuthorDAO {

    // ✅ Lấy danh sách tất cả tác giả
    public static List<Author> getAll() throws SQLException {
        List<Author> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_author ORDER BY _id DESC";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Author a = new Author(
                    rs.getInt("_id"),
                    rs.getString("_name"),
                    rs.getString("_information"),
                    rs.getString("_image")
                );
                list.add(a);
            }
        }
        return list;
    }

    // ✅ Thêm tác giả mới (trả về ID tự động sinh)
    public static int insert(Author a) throws SQLException {
        String sql = "INSERT INTO tbl_author (_name, _information, _image) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getName());
            stmt.setString(2, a.getInformation());
            stmt.setString(3, a.getImage());

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

    // ✅ Cập nhật thông tin tác giả
    public static int update(Author a) throws SQLException {
        String sql = "UPDATE tbl_author SET _name = ?, _information = ?, _image = ? WHERE _id = ?";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getName());
            stmt.setString(2, a.getInformation());
            stmt.setString(3, a.getImage());
            stmt.setInt(4, a.getId());

            return stmt.executeUpdate();
        }
    }

    // ✅ Xoá tác giả theo ID
    public static int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM tbl_author WHERE _id = ?";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }

    // ✅ Lấy thông tin tác giả theo ID
    public static Author getById(int id) throws SQLException {
        String sql = "SELECT * FROM tbl_author WHERE _id = ?";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Author(
                        rs.getInt("_id"),
                        rs.getString("_name"),
                        rs.getString("_information"),
                        rs.getString("_image")
                    );
                }
            }
        }
        return null;
    }

    // ✅ (Tuỳ chọn) Kiểm tra tác giả có tồn tại theo ID
    public static boolean exists(int id) throws SQLException {
        String sql = "SELECT 1 FROM tbl_author WHERE _id = ?";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Có dữ liệu là tồn tại
            }
        }
    }

    // ✅ (Tuỳ chọn) Kiểm tra tác giả trùng tên (tránh thêm trùng)
    public static boolean isDuplicateName(String name) throws SQLException {
        String sql = "SELECT 1 FROM tbl_author WHERE _name = ?";

        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    public static List<Author> searchByName(String keyword) throws SQLException {
        List<Author> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_author WHERE _name LIKE ? ORDER BY _id DESC";


        try (Connection conn = DBUtil.getInstance().getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Author a = new Author(
                        rs.getInt("_id"),
                        rs.getString("_name"),
                        rs.getString("_information"),
                        rs.getString("_image")
                    );
                    list.add(a);
                }
            }
        }
        return list;
    }
}
