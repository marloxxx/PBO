package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN = "jdbc:mysql://localhost:3306/barang";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan " + e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Koneksi gagal " + e.getMessage());
        }
        return connection;
    }
}
