package root.myutils;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class DBUtil {
    // Singleton instance
    private static DBUtil _instance = null;

    // Private constructor to prevent instantiation
    private DBUtil() {
        // You can initialize anything here if needed
    }

    // Public method to get the singleton instance
    public static DBUtil getInstance() {
        if (_instance == null) {
            _instance = new DBUtil();
        }
        return _instance;
    }

    // Method to get database connection
    public Connection getConnect() {
    	SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(StringValue.USER_NAME);
        ds.setPassword(StringValue.PWD);
        ds.setServerName(StringValue.SERVER_NAME);
        ds.setPortNumber(Integer.parseInt(StringValue.PORT));
        ds.setDatabaseName(StringValue.DATABASE_NAME);
        ds.setTrustServerCertificate(true);
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
//    test connect
//    public static void main(String[] args) {
//    	Connection conn=DBUtil.getInstance().getConnect();
//    	if (conn != null) {
//    	    System.out.println("Kết nối thành công!");
//    	} else {
//    	    System.out.println("Kết nối thất bại!");
//    	}
//    }
}
