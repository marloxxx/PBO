package controller.member;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Book;
import model.Borrow;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends Controller {
    @FXML
    private JFXButton btn_book;

    @FXML
    private JFXButton btn_borrow;

    @FXML
    private JFXButton btn_dashboard;

    @FXML
    private JFXButton btn_fine;

    @FXML
    private JFXButton btn_logout;

    @FXML
    private Label labelName;

    @FXML
    private Label total_book;

    @FXML
    private Label total_borrow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelName.setText(UserSession.getInstance().getUsername());
        total_book.setText(String.valueOf(Book.count("")));
        total_book.setStyle("-fx-text-fill: #000; -fx-font-size: 20px; -fx-font-weight: bold;");
        total_borrow.setText(String.valueOf(Borrow.count("")));
        total_borrow.setStyle("-fx-text-fill: #000; -fx-font-size: 20px; -fx-font-weight: bold;");

        btn_dashboard.setDisable(true);
        btn_dashboard.setStyle("-fx-background-color: #2a9df4; -fx-text-fill: white;");
        btn_book.setOnAction(event -> {
            try {
                changeScene(event, "member/book.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_borrow.setOnAction(event -> {
            try {
                changeScene(event, "member/borrow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_fine.setOnAction(event -> {
            try {
                changeScene(event, "member/fine.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_logout.setOnAction(event -> {
            try {
                UserSession.getInstance().clearSession();
                changeScene(event, "auth/auth.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

