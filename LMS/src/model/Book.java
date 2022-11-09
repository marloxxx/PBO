package model;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Book implements Model {

    private long id;
    private String cover;
    private String title;
    private String author;
    private String publisher;
    private String year;
    private String category;
    private int quantity;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private String action;
    private static Connection connection;
    private PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public Book() {
    }

    public static Book find(long id) throws SQLException {
        connection = DBConnection.getConnection();
        try {
            String query = "SELECT * FROM books WHERE id = ? AND deleted_at IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setCover(resultSet.getString("cover"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getString("year"));
                book.setCategory(resultSet.getString("category"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setCreated_at(resultSet.getTimestamp("created_at"));
                book.setUpdated_at(resultSet.getTimestamp("updated_at"));
                book.setDeleted_at(resultSet.getTimestamp("deleted_at"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public Timestamp getCreated_at() {
        return created_at;
    }

    @Override
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public Timestamp getUpdated_at() {
        return updated_at;
    }

    @Override
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    @Override
    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    public static ObservableList<Book> list(String keywords) throws SQLException {
        ObservableList<Book> books = FXCollections.observableArrayList();
        connection = DBConnection.getConnection();
        try {
            String query = "SELECT * FROM books WHERE title LIKE ? AND deleted_at IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keywords + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getString("year"));
                book.setCategory(resultSet.getString("category"));
                book.setQuantity(resultSet.getInt("quantity"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ObservableList<Book> list(int fromIndex, int toIndex, String keywords) throws SQLException {
        ObservableList<Book> books = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM books WHERE title LIKE ? AND deleted_at IS NULL LIMIT ?, ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keywords + "%");
            preparedStatement.setInt(2, fromIndex);
            preparedStatement.setInt(3, toIndex);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getString("year"));
                book.setCategory(resultSet.getString("category"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setCreated_at(resultSet.getTimestamp("created_at"));
                book.setUpdated_at(resultSet.getTimestamp("updated_at"));
                book.setDeleted_at(resultSet.getTimestamp("deleted_at"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static double count() {
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT COUNT(*) FROM books WHERE deleted_at IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insert() throws SQLException {
        connection = DBConnection.getConnection();
        try {
            String query = "INSERT INTO books (cover, title, author, publisher, year, category, quantity, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.cover);
            preparedStatement.setString(2, this.title);
            preparedStatement.setString(3, this.author);
            preparedStatement.setString(4, this.publisher);
            preparedStatement.setString(5, this.year);
            preparedStatement.setString(6, this.category);
            preparedStatement.setInt(7, this.quantity);
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update() throws SQLException {
        connection = DBConnection.getConnection();
        try {
            String query = "UPDATE books SET cover = ?, title = ?, author = ?, publisher = ?, year = ?, category = ?, quantity = ?, updated_at = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.cover);
            preparedStatement.setString(2, this.title);
            preparedStatement.setString(3, this.author);
            preparedStatement.setString(4, this.publisher);
            preparedStatement.setString(5, this.year);
            preparedStatement.setString(6, this.category);
            preparedStatement.setInt(7, this.quantity);
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(9, this.id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() throws SQLException {
        connection = DBConnection.getConnection();
        try {
            String query = "UPDATE books SET deleted_at = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(2, this.id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int count(String keywords) {
        try {
            return list("").size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
