package javatomysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksiMysql {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/barang";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan " + e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal " + e.getMessage());
        }

        return connection;
    }
}
