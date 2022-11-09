package model;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class User implements Model {

    private long id;
    private String name;
    private String username;
    private String password;
    private String salt;
    private int role_id;

    private Timestamp created_at;

    private Timestamp updated_at;

    private Timestamp deleted_at;
    private String action;

    public User() {
    }

    public static User find(long user_id) {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE id = " + user_id);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
                user.setRole(resultSet.getInt("role_id"));
                user.setCreated_at(resultSet.getTimestamp("created_at"));
                user.setUpdated_at(resultSet.getTimestamp("updated_at"));
                user.setDeleted_at(resultSet.getTimestamp("deleted_at"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public static ObservableList<User> list(int from, int to, String text) {
        ObservableList<User> users = FXCollections.observableArrayList();
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement statement = null;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE name LIKE '%" + text + "%' AND role_id = 2 AND deleted_at IS NULL LIMIT " + from + ", " + to);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
                user.setRole(resultSet.getInt("role_id"));
                user.setCreated_at(resultSet.getTimestamp("created_at"));
                user.setUpdated_at(resultSet.getTimestamp("updated_at"));
                user.setDeleted_at(resultSet.getTimestamp("deleted_at"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getRole() {
        return role_id;
    }

    public void setRole(int role_id) {
        this.role_id = role_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    @Override
    public boolean insert() throws SQLException {
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO users (name, username, password, salt, role) VALUES ('" + this.name + "', '" + this.username + "',  '" + this.password + "', '" + this.salt + "', '" + this.role_id + "')";
        try {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update() throws SQLException {
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "UPDATE users SET name = '" + this.name + "', username = '" + this.username + "', password = '" + this.password + "', salt = '" + this.salt + "', role = '" + this.role_id + "' WHERE id = " + this.id;
        try {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() throws SQLException {
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "UPDATE users SET deleted_at = '" + new Timestamp(System.currentTimeMillis()) + "' WHERE id = " + this.id;
        try {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    //    get all borrows of user
    public ObservableList<Borrow> borrows(int from, int to, String text) {
        ObservableList<Borrow> borrows = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM borrows JOIN books ON borrows.book_id = books.id WHERE borrows.user_id = ? AND books.title LIKE ? AND borrows.deleted_at IS NULL LIMIT ?, ?");
            statement.setLong(1, this.id);
            statement.setString(2, "%" + text + "%");
            statement.setInt(3, from);
            statement.setInt(4, to);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Borrow borrow = new Borrow();
                borrow.setId(resultSet.getLong("id"));
                borrow.setBook(Book.find(resultSet.getLong("book_id")));
                borrow.setMember(User.find(resultSet.getLong("user_id")));
                borrow.setBorrow_date(Date.valueOf(resultSet.getString("borrow_date")));
                borrow.setReturn_date(resultSet.getDate("return_date"));
                borrow.setStatus(resultSet.getString("status"));
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    public static int count(String keywords) {
        int count = 0;
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM users WHERE name LIKE '%" + keywords + "%' AND role_id = 2 AND deleted_at IS NULL");
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }

    public Boolean isBorrowed(Book book) {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM borrows WHERE user_id = " + this.id + " AND book_id = " + book.getId() + " AND status = 'borrowed'");
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ObservableList<Fine> fine(int from, int to, String text) {
        ObservableList<Fine> fines = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fines JOIN borrows ON fines.borrow_id = borrows.id JOIN books ON borrows.book_id = books.id WHERE fines.user_id = ? AND books.title LIKE ? AND fines.deleted_at IS NULL LIMIT ?, ?");
            statement.setLong(1, this.id);
            statement.setString(2, "%" + text + "%");
            statement.setInt(3, from);
            statement.setInt(4, to);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Fine fine = new Fine();
                fine.setId(resultSet.getLong("id"));
                fine.setBorrow(Borrow.find(resultSet.getLong("borrow_id")));
                fine.setMember(User.find(resultSet.getLong("user_id")));
                fine.setAmount(resultSet.getDouble("amount"));
                fine.setStatus(resultSet.getString("status"));
                fines.add(fine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fines;
    }
}
