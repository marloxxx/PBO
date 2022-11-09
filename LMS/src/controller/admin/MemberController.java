package controller.admin;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.User;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MemberController extends Controller {

    @FXML
    private Pagination pagination;

    @FXML
    private Button btn_book;

    @FXML
    private Button btn_borrow;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_fine;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_member;

    @FXML
    private Label labelName;

    @FXML
    private TextField search;

    @FXML
    private TableView<User> tableMember;

    @FXML
    private TableColumn<User, String> tcId;

    @FXML
    private TableColumn<User, String> tcName;

    @FXML
    private TableColumn<User, String> tcUsername;

    @FXML
    private TableColumn<User, String> tcAction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPagination();
        labelName.setText(UserSession.getInstance().getUsername());
        btn_member.setDisable(true);
        btn_member.setStyle("-fx-background-color: #2a9df4; -fx-text-fill: white;");
        btn_book.setOnAction(event -> {
            try {
                changeScene(event, "admin/book.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_dashboard.setOnAction(event -> {
            try {
                changeScene(event, "admin/dashboard.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_borrow.setOnAction(event -> {
            try {
                changeScene(event, "admin/borrow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_fine.setOnAction(event -> {
            try {
                changeScene(event, "admin/fine.fxml");
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

        search.setOnKeyReleased(event -> createPagination());
    }

    private void createPagination() {
        int total = User.count(search.getText());
        int rowsPerPage = 10;
        int pageCount = (total + rowsPerPage - 1) / rowsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, rowsPerPage));
    }

    private Node createPage(int pageIndex, int rowsPerPage) {
        int from = pageIndex * rowsPerPage;
        int to = from + rowsPerPage;
        try {
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            tcAction.setCellValueFactory(new PropertyValueFactory<>("action"));
            tcAction.setCellFactory(new Callback<>() {
                @Override
                public TableCell<User, String> call(TableColumn<User, String> userStringTableColumn) {
                    return new TableCell<>() {
                        @Override
                        protected void updateItem(String s, boolean b) {
                            super.updateItem(s, b);
                            if (b) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                Button btn = new Button("Delete");
                                btn.setOnAction(event -> {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation");
                                    alert.setHeaderText("Are you sure to delete this member?");
                                    alert.setContentText("Click OK to confirm");
                                    alert.showAndWait().ifPresent(response -> {
                                        if (response == ButtonType.OK) {
                                            User user = User.find(getTableView().getItems().get(getIndex()).getId());
                                            try {
                                                user.delete();
                                                successNotification("Success", "Member deleted successfully");
                                                createPagination();
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    });
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tableMember.setItems(User.list(from, to, search.getText()));
        return new AnchorPane(tableMember);
    }
}
