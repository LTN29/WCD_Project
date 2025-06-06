package root.reps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import root.entities.Category;
import root.myutils.DBUtil;

//xử lý database (DAO = Data Access Object)
public class CategoryDAO {
	
	public static List<Category> getAll() throws SQLException {
	    List<Category> list = new ArrayList<>();
	    String sql = "SELECT * FROM tbl_category";
	    try (
	        Connection conn = DBUtil.getInstance().getConnect();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	    ) {
	        while (rs.next()) {
	            Category c = new Category(
	                rs.getInt("_id"),
	                rs.getString("_name"),
	                rs.getBoolean("_active")
	            );
	            list.add(c);
	        }
	    }
	    return list;
	}
	
	public static int insert(Category c) throws SQLException{
		String sql="INSERT INTO tbl_category(_name,_active) VALUES (?,?)";
		try(
				Connection conn=DBUtil.getInstance().getConnect();
				PreparedStatement stmt=conn.prepareStatement(sql);
				){
			stmt.setString(1, c.getName());
			stmt.setBoolean(2,c.isActive());
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows == 0) {
				throw new SQLException("Insert Category failed, no rows affected");
			}
			try (ResultSet generatedKeys=stmt.getGeneratedKeys()){
				if(generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}else {
					throw new SQLException("Insert category failed, no ID obtained.");
				}
			}
			
			
		}
	}
	
	public static int update(Category c) throws SQLException {
	    String sql = "UPDATE tbl_category SET _name = ?, _active = ? WHERE _id = ?";
	    try (
	        Connection conn = DBUtil.getInstance().getConnect();
	        PreparedStatement stmt = conn.prepareStatement(sql)
	    ) {
	        stmt.setString(1, c.getName());
	        stmt.setBoolean(2, c.isActive());
	        stmt.setInt(3, c.getId());
	        return stmt.executeUpdate() ; 
	    }
	}
	

	public static int deleteById(int id) throws SQLException {
		String sql= "DELETE From tbl_category WHERE _id = ?";
		try(
				Connection conn = DBUtil.getInstance().getConnect();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setInt(1, id);
			return stmt.executeUpdate();
		}
		
	}
	
	public static Category getById(int id) throws SQLException {
	    String sql = "SELECT * FROM tbl_category WHERE _id = ?";
	    try (
	        Connection conn = DBUtil.getInstance().getConnect();
	        PreparedStatement stmt = conn.prepareStatement(sql)
	    ) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Category(
	                rs.getInt("_id"),
	                rs.getString("_name"),
	                rs.getBoolean("_active")
	            );
	        }
	    }
	    return null;
	}

	
	public static String getNameById(int id) throws SQLException {
	    String sql = "SELECT _name FROM tbl_category WHERE _id = ?";
	    try (
	        Connection conn = DBUtil.getInstance().getConnect();
	        PreparedStatement stmt = conn.prepareStatement(sql)
	    ) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("_name");
	        }
	    }
	    return "FAIL";
	}


}
