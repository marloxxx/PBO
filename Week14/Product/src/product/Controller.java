package product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Controller {

    public ObservableList<Model> getDataProduct() {
        Connection connection = null;
        Statement statement = null;
        ObservableList<Model> models = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM barang");
            while (resultSet.next()) {
                Model model = new Model();
                model.setKode(resultSet.getString(1));
                model.setNama(resultSet.getString(2));
                model.setKategori(resultSet.getString(3));
                model.setHarga_beli(resultSet.getString(4));
                model.setHarga_jual(resultSet.getString(5));
                model.setStok(resultSet.getString(6));
                models.add(model);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return models;
    }

    public void insert(Model model) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO barang VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, model.getKode());
            preparedStatement.setString(2, model.getNama());
            preparedStatement.setString(3, model.getKategori());
            preparedStatement.setString(4, model.getHarga_beli());
            preparedStatement.setString(5, model.getHarga_jual());
            preparedStatement.setString(6, model.getStok());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data " + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void update(Model model) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE barang SET nama=?, katagori=?, harga_beli=?, harga_jual=?, stok=? WHERE kode=?");
            preparedStatement.setString(1, model.getNama());
            preparedStatement.setString(2, model.getKategori());
            preparedStatement.setString(3, model.getHarga_beli());
            preparedStatement.setString(4, model.getHarga_jual());
            preparedStatement.setString(5, model.getStok());
            preparedStatement.setString(6, model.getKode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal mengubah data " + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void delete(Model model) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM barang WHERE kode=?");
            preparedStatement.setString(1, model.getKode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data " + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
