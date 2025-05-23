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
		String sql= "SELECT * FROM tbl_category";
		try(
				Connection conn=DBUtil.getInstance().getConnect();
				PreparedStatement stmt= conn.prepareStatement(sql);
				ResultSet rs= stmt.executeQuery();
				){
			while (rs.next()) {
				
			}
			
		}
		
		return list;
		
	}
}
