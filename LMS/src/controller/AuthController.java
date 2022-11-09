package controller;

import com.jfoenix.controls.JFXButton;
import database.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.User;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AuthController extends Controller {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @FXML
    private JFXButton btn_login;

    @FXML
    private JFXButton btn_register;

    @FXML
    private Hyperlink login;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink register;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField username;
    @FXML
    private TextField username2;

    @FXML
    private PasswordField password2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = new User();
        login.setOnAction(event -> {
            login_form.setVisible(true);
            register_form.setVisible(false);
        });

        register.setOnAction(event -> {
            register_form.setVisible(true);
            login_form.setVisible(false);
        });

        btn_login.setOnAction(event -> {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                warningNotification("Login failed", "Username or password is empty");
            } else {
                user.setUsername(username.getText());
                user.setPassword(password.getText());
                try {
                    connection = DBConnection.getConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String query = "SELECT * FROM users WHERE username = ? AND deleted_at IS NULL";
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, user.getUsername());
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        String password = resultSet.getString("password");
                        byte[] salt = fromHex(resultSet.getString("salt"));
                        String hashedPassword = getSecurePassword(user.getPassword(), salt);
                        System.out.println(resultSet.getString("password"));
                        System.out.println(hashedPassword);
                        if (password.equals(hashedPassword)) {
                            UserSession userSession = UserSession.getInstance();
                            userSession.setUserId(resultSet.getLong("id"));
                            userSession.setUsername(resultSet.getString("username"));
                            userSession.setRole(resultSet.getInt("role_id"));
                            successNotification("Login success", "Welcome " + resultSet.getString("username"));
                            if (userSession.getRole() == 1) {
                                changeScene(event, "admin/dashboard.fxml");
                            } else {
                                changeScene(event, "member/dashboard.fxml");
                            }
                        } else {
                            errorNotification("Login failed", "Password is incorrect");
                        }
                    } else {
                        errorNotification("Login failed", "Username is incorrect");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btn_register.setOnAction(event -> {
            if (name.getText().isEmpty()) {
                errorNotification("Register failed", "Name is empty");
            } else if (username2.getText().isEmpty()) {
                errorNotification("Register failed", "Username is empty");
            } else if (password2.getText().isEmpty()) {
                errorNotification("Register failed", "Password is empty");
            } else {
                try {
                    user.setName(name.getText());
                    user.setUsername(username2.getText());
                    user.setPassword(password2.getText());
                    user.setRole(2);
                    try {
                        connection = DBConnection.getConnection();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    byte[] salt = getSalt();
                    String password = getSecurePassword(this.password.getText(), salt);
                    String query = "INSERT INTO users (name, username, password, salt, role_id) VALUES (?, ?, ?, ?, ?)";
                    try {
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, user.getName());
                        preparedStatement.setString(2, user.getUsername());
                        preparedStatement.setString(3, password);
                        preparedStatement.setString(4, toHex(salt));
                        preparedStatement.setInt(5, user.getRole());
                        preparedStatement.executeUpdate();
                        successNotification("Registration successful", "You can now login");
                    } catch (SQLException e) {
                        errorNotification("Registration failed", "Username already exists");
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } finally {
                    name.clear();
                    username2.clear();
                    password2.clear();
                    register_form.setVisible(false);
                    login_form.setVisible(true);
                }
            }
        });
    }
}
