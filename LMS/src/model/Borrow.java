package model;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.*;

public class Borrow implements Model {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private long id;
    private User member;
    private Book book;
    private Date borrow_date;
    private Date return_date;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private String action   ;
    public Borrow() {
    }

    public static XYChart.Series<String, Integer> borrowChart() {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Borrow");
        try {
            String query = "SELECT MONTHNAME(borrow_date) as month, COUNT(*) as total FROM borrows GROUP BY MONTH(borrow_date)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            var rs = ps.executeQuery();
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString("month"), rs.getInt("total")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }


    public long getId() {
        return id;
    }

    public User getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public Timestamp getCreated_at() {
        return created_at;
    }

    @Override
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public Timestamp getUpdated_at() {
        return updated_at;
    }

    @Override
    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    @Override
    public boolean insert() throws SQLException {
        connection = DBConnection.getConnection();
        preparedStatement = connection.prepareStatement("INSERT INTO borrows (user_id, book_id, borrow_date, return_date, status) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setLong(1, member.getId());
        preparedStatement.setObject(2, book.getId());
        preparedStatement.setDate(3, borrow_date);
        preparedStatement.setDate(4, return_date);
        preparedStatement.setString(5, status);
        try {
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update() throws SQLException {
        connection = DBConnection.getConnection();
        preparedStatement = connection.prepareStatement("UPDATE borrows SET user_id = ?, book_id = ?, borrow_date = ?, return_date = ?, status = ? WHERE id = ?");
        preparedStatement.setLong(1, member.getId());
        preparedStatement.setLong(2, book.getId());
        preparedStatement.setDate(3, borrow_date);
        preparedStatement.setDate(4, return_date);
        preparedStatement.setString(5, status);
        preparedStatement.setLong(6, id);
        try {
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() throws SQLException {
        connection = DBConnection.getConnection();
        preparedStatement = connection.prepareStatement("UPDATE borrows SET deleted_at = ? WHERE id = ?");
        preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setLong(2, id);
        try {
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int count(String s)  {
        try {
            return list("").size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMember(User user) {
        this.member = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Borrow find(long id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM borrows WHERE `id` = " + id;
        resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            Borrow borrow = new Borrow();
            borrow.setId(resultSet.getLong("id"));
            borrow.setMember(User.find(resultSet.getLong("user_id")));
            borrow.setBook(Book.find(resultSet.getLong("book_id")));
            borrow.setBorrow_date(resultSet.getDate("borrow_date"));
            borrow.setReturn_date(resultSet.getDate("return_date"));
            borrow.setStatus(resultSet.getString("status"));
            borrow.setCreated_at(resultSet.getTimestamp("created_at"));
            borrow.setUpdated_at(resultSet.getTimestamp("updated_at"));
            borrow.setDeleted_at(resultSet.getTimestamp("deleted_at"));
            return borrow;
        }
        return null;
    }

    public static ObservableList<Borrow> list(String keywords) throws SQLException {
        ObservableList<Borrow> borrows = FXCollections.observableArrayList();
        connection = DBConnection.getConnection();
        try {
            String query = "SELECT * FROM borrows JOIN users ON borrows.user_id = users.id JOIN books ON borrows.book_id = books.id WHERE borrows.deleted_at IS NULL AND (users.name LIKE ? OR books.title LIKE ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keywords + "%");
            preparedStatement.setString(2, "%" + keywords + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Borrow borrow = new Borrow();
                borrow.setId(resultSet.getLong("id"));
                long user_id = resultSet.getLong("user_id");
                borrow.setMember(User.find(user_id));
                long book_id = resultSet.getLong("book_id");
                borrow.setBook(Book.find(book_id));
                borrow.setBorrow_date(resultSet.getDate("borrow_date"));
                borrow.setReturn_date(resultSet.getDate("return_date"));
                borrow.setStatus(resultSet.getString("status"));
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    public static ObservableList<Borrow> list(int from, int to, String text) {
        ObservableList<Borrow> borrows = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String query = "SELECT * FROM borrows JOIN users ON borrows.user_id = users.id JOIN books ON borrows.book_id = books.id WHERE borrows.deleted_at IS NULL AND (users.name LIKE ? OR books.title LIKE ?) LIMIT ?, ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + text + "%");
            preparedStatement.setString(2, "%" + text + "%");
            preparedStatement.setInt(3, from);
            preparedStatement.setInt(4, to);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Borrow borrow = new Borrow();
                borrow.setId(resultSet.getLong("id"));
                long user_id = resultSet.getLong("user_id");
                borrow.setMember(User.find(user_id));
                long book_id = resultSet.getLong("book_id");
                borrow.setBook(Book.find(book_id));
                borrow.setBorrow_date(resultSet.getDate("borrow_date"));
                borrow.setReturn_date(resultSet.getDate("return_date"));
                borrow.setStatus(resultSet.getString("status"));
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
    public void returnBook() {
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE borrows SET return_date = ?, status = ?, updated_at = ? WHERE id = ?");
            preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
            preparedStatement.setString(2, "returned");
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.status = "returned";
        this.book.setQuantity(this.book.getQuantity() + 1);
    }

    public boolean fine() {
        String query = "SELECT * FROM fines WHERE borrow_id = ?";
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}