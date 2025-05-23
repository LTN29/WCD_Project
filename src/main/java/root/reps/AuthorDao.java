package root.reps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import root.entities.Author;
import root.myutils.DBUtil;

public class AuthorDao {

	public static List<Author> getAll() throws SQLException {
		List<Author> list = new ArrayList<>();
		String sql = "SELECT * FROM tbl_author";
		try (Connection conn = DBUtil.getInstance().getConnect();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				Author a = new Author(rs.getInt("_id"), rs.getString("_name"), rs.getString("_information"),
						rs.getString("_image"));
				list.add(a);
			}
		}
		return list;
	}

	public static int insert(Author a) throws SQLException {
		String sql = "INSERT INTO tbl_author(_name,_information) VALUES (?,?,?)";
		try (Connection conn = DBUtil.getInstance().getConnect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, a.getName());
			stmt.setString(2, a.getInformation());
			stmt.setString(3, a.getImage());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Insert Category failed, no rows affected");

			}
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				} else {
					throw new SQLException("Insert category failed, no ID obtained.");
				}
			}
		}

	}

	public static int update(Author a) throws SQLException{
		String sql="UPDATE tbl_author SET _name= ?,_information = ?,_image=? WHERE _id=?";
		try(
				Connection conn=DBUtil.getInstance().getConnect();
				PreparedStatement stmt=conn.prepareStatement(sql);
			){
			stmt.setString(1, a.getName());
			stmt.setString(2, a.getInformation());
			stmt.setString(3,a.getImage());
			return stmt.executeUpdate();
		}
	}
	public static int deteleById(int id) throws SQLException{
		String sql="DELETE From tbl_author WHERE _id= ?";
		try(
				Connection conn=DBUtil.getInstance().getConnect();
				PreparedStatement stmt= conn.prepareStatement(sql);
				){
			stmt.setInt(id, 1);
			return stmt.executeUpdate();
		}
	}
}
