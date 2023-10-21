package cardgame.utils;

import java.sql.*;

public class DBUtils {
    private static DBUtils instance;
    private static final String url = "jdbc:mysql://localhost:3306/element_cg";
    private static final String username = "root";
    private static final String password = "12345678";
    private static Connection conn;

    private DBUtils() {
        instance = new DBUtils();
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBUtils getInstance() {
        if (instance == null) {
            instance = new DBUtils();
        }
        return instance;
    }

    public ResultSet selectQuery(String sql){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateQuery(String sql){
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
