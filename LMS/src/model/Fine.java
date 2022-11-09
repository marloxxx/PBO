package model;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Fine implements Model {

    private long id;

    private User member;
    private Borrow borrow;
    private double amount;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private String action;
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public Fine() {

    }

    public static ObservableList<Fine> list(int from, int to, String text) {
        ObservableList<Fine> list = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM fines JOIN borrows ON fines.borrow_id = borrows.id JOIN users ON borrows.user_id = users.id JOIN books ON borrows.book_id = books.id WHERE (users.name LIKE ? OR books.title LIKE ?) AND fines.deleted_at IS NULL ORDER BY fines.id DESC LIMIT ?, ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + text + "%");
            preparedStatement.setString(2, "%" + text + "%");
            preparedStatement.setInt(3, from);
            preparedStatement.setInt(4, to);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Fine fine = new Fine();
                fine.setId(resultSet.getLong("id"));
                fine.setMember(User.find(resultSet.getLong("user_id")));
                fine.setBorrow(Borrow.find(resultSet.getLong("borrow_id")));
                fine.setAmount(resultSet.getInt("amount"));
                fine.setStatus(resultSet.getString("status"));
                list.add(fine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
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
        String query = "INSERT INTO fines (user_id, borrow_id, amount, status) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, member.getId());
        preparedStatement.setLong(2, borrow.getId());
        preparedStatement.setDouble(3, amount);
        preparedStatement.setString(4, "unpaid");
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean update() throws SQLException {
        connection = DBConnection.getConnection();
        String query = "UPDATE fines SET user_id = ?, borrow_id = ?, amount = ?, status = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, member.getId());
        preparedStatement.setLong(2, borrow.getId());
        preparedStatement.setDouble(3, amount);
        preparedStatement.setString(4, status);
        preparedStatement.setLong(5, id);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean delete() throws SQLException {
        connection = DBConnection.getConnection();
        String query = "UPDATE fines SET deleted_at = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setLong(2, id);
        return preparedStatement.executeUpdate() > 0;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void pay() {
        try {
            connection = DBConnection.getConnection();
            String query = "UPDATE fines SET status = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "paid");
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
