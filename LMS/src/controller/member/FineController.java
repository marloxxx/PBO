package controller.member;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Book;
import model.Borrow;
import model.Fine;
import model.User;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FineController extends Controller {

    @FXML
    private Label author;

    @FXML
    private TableView<Fine> fineTable;

    @FXML
    private Label borrow_date;

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
    private JFXButton btn_member;

    @FXML
    private Label category;

    @FXML
    private ImageView cover;

    @FXML
    private Label labelName;

    @FXML
    private Pagination pagination;

    @FXML
    private Label publihser;

    @FXML
    private Label return_date;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<Fine, Integer> tcAmount;

    @FXML
    private TableColumn<Fine, Borrow> tcBook;

    @FXML
    private TableColumn<Fine, User> tcMember;

    @FXML
    private TableColumn<Fine, String> tcStatus;

    @FXML
    private TableColumn<Fine, String> tcAction;
    @FXML
    private Label title;

    @FXML
    private Label year;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelName.setText(UserSession.getInstance().getUsername());
        createPagination();
        btn_dashboard.setOnAction(event -> {
            try {
                changeScene(event, "member/dashboard.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_book.setOnAction(event -> {
            try {
                changeScene(event, "member/book.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_member.setOnAction(event -> {
            try {
                changeScene(event, "member/member.fxml");
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
        btn_fine.setDisable(true);
        btn_fine.setStyle("-fx-background-color: #2a9df4; -fx-text-fill: white;");
        btn_logout.setOnAction(event -> {
            try {
                UserSession.getInstance().clearSession();
                changeScene(event, "auth/auth.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        search.setOnKeyReleased(event -> createPagination());

        fineTable.setOnMouseClicked(event -> {
            Fine fine = fineTable.getSelectionModel().getSelectedItem();
            if (fine != null) {
                try {
                    Book book = Book.find(fine.getBorrow().getBook().getId());
                    Path path = FileSystems.getDefault().getPath("src/view/images/" + book.getCover());
                    cover.setImage(new Image(path.toUri().toString()));
                    title.setText(book.getTitle());
                    author.setText(book.getAuthor());
                    publihser.setText(book.getPublisher());
                    year.setText(book.getYear());
                    category.setText(book.getCategory());
                    borrow_date.setText(fine.getBorrow().getBorrow_date().toString());
                    return_date.setText(fine.getBorrow().getReturn_date().toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        fineTable.setOnMouseExited(event -> {
            cover.setImage(null);
            title.setText("");
            author.setText("");
            publihser.setText("");
            year.setText("");
            category.setText("");
            borrow_date.setText("");
            return_date.setText("");
        });
    }

    private void createPagination() {
        int total = Book.count(search.getText());
        int rowsPerPage = 10;
        int pageCount = (total + rowsPerPage - 1) / rowsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, rowsPerPage));
    }

    private Node createPage(int pageIndex, int rowsPerPage) {
        int from = pageIndex * rowsPerPage;
        int to = from + rowsPerPage - 1;
        tcMember.setCellValueFactory(new PropertyValueFactory<>("member"));
        tcMember.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Fine, User> call(TableColumn<Fine, User> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(User user, boolean b) {
                        super.updateItem(user, b);
                        if (user != null) {
                            setText(user.getName());
                        }
                    }
                };
            }
        });
        tcBook.setCellValueFactory(new PropertyValueFactory<>("book"));
        tcBook.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Fine, Borrow> call(TableColumn<Fine, Borrow> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(Borrow borrow, boolean b) {
                        super.updateItem(borrow, b);
                        if (borrow != null) {
                            setText(borrow.getBook().getTitle());
                        }
                    }
                };
            }
        });
        tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcStatus.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Fine, String> call(TableColumn<Fine, String> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String status, boolean b) {
                        super.updateItem(status, b);
                        if (status != null) {
                            if (status.equals("paid")) {
                                setText("Paid");
                                setStyle("-fx-text-fill: green;");
                            } else {
                                setText("Unpaid");
                                setStyle("-fx-text-fill: red;");
                            }
                        }
                    }
                };
            }
        });
        User user = User.find(UserSession.getInstance().getUserId());
        fineTable.setItems(user.fine(from, to, search.getText()));
        return new AnchorPane(fineTable);
    }
}
