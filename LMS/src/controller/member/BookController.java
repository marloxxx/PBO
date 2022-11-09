package controller.member;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import model.Book;
import model.Borrow;
import model.User;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookController extends Controller {

    @FXML
    private Label author;

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
    private Label cateogry;

    @FXML
    private ImageView cover;

    @FXML
    private Label labelName;

    @FXML
    private Pagination pagination;

    @FXML
    private Label publihser;

    @FXML
    private TextField search;

    @FXML
    private TableView<Book> tableBook;

    @FXML
    private TableColumn<Book, String> tcAction;

    @FXML
    private TableColumn<Book, String> tcAuthor;

    @FXML
    private TableColumn<Book, Long> tcId;

    @FXML
    private TableColumn<Book, String> tcPublisher;

    @FXML
    private TableColumn<Book, Integer> tcQuantity;

    @FXML
    private TableColumn<Book, String> tcTitle;

    @FXML
    private Label title;

    @FXML
    private Label year;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelName.setText(UserSession.getInstance().getUsername());
        createPagination();
        btn_book.setStyle("-fx-background-color: #2a9df4; -fx-text-fill: white;");
        btn_book.setDisable(true);

        btn_dashboard.setOnAction(event -> {
            try {
                changeScene(event, "member/dashboard.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        search.setOnKeyReleased(event -> createPagination());

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
                changeScene(event, "login.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        tableBook.setOnMouseClicked(event -> {
            Book book;
            try {
                book = Book.find(tableBook.getSelectionModel().getSelectedItem().getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            title.setText(book != null ? book.getTitle() : null);
            author.setText(book != null ? book.getAuthor() : null);
            publihser.setText(book != null ? book.getPublisher() : null);
            year.setText(String.valueOf(book != null ? book.getYear() : null));
            cateogry.setText(book != null ? book.getCategory() : null);
            Path path = FileSystems.getDefault().getPath("src/view/images/" + (book != null ? book.getCover() : null));
            cover.setImage(new Image(path.toUri().toString()));
        });

        tableBook.setOnMouseExited(event -> {
            title.setText("");
            author.setText("");
            publihser.setText("");
            year.setText("");
            cateogry.setText("");
            cover.setImage(null);
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

    private void createPagination() {
        int total = Book.count(search.getText());
        int rowsPerPage = 10;
        int pageCount = (total + rowsPerPage - 1) / rowsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, rowsPerPage));
    }

    private Node createPage(Integer pageIndex, int rowsPerPage) {
        try {
            int from = pageIndex * rowsPerPage;
            int to = from + rowsPerPage;
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            tcPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            tcAction.setCellValueFactory(new PropertyValueFactory<>("action"));
            tcAction.setCellFactory(new Callback<>() {
                @Override
                public TableCell<Book, String> call(TableColumn<Book, String> param) {
                    return new TableCell<>() {
                        @Override
                        protected void updateItem(String s, boolean b) {
                            super.updateItem(s, b);
                            if (b) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                Button button = new Button("Borrow");
                                button.setOnAction(event -> {
                                    Book book;
                                    try {
                                        book = Book.find(getTableView().getItems().get(getIndex()).getId());
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    boolean isBorrowed = Objects.requireNonNull(User.find(UserSession.getInstance().getUserId())).isBorrowed(Objects.requireNonNull(book));
                                    if (book.getQuantity() == 0) {
                                        warningNotification("Warning", "Book is not available");
                                    } else if (isBorrowed) {
                                        warningNotification("Warning", "You have already borrowed this book");
                                    } else {
                                        try {
                                            Dialog dialog = new Dialog();
                                            dialog.setTitle("Borrow Book");
                                            dialog.setResizable(true);
                                            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                                            dialog.getDialogPane().setPrefSize(400, 300);
                                            GridPane grid = new GridPane();
                                            grid.setHgap(10);
                                            grid.setVgap(10);
                                            grid.setPadding(new Insets(20, 150, 10, 10));
                                            Label label = new Label("Borrow Book");
                                            label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                                            grid.add(label, 0, 0);
                                            Label labelTitle = new Label("Title");
                                            grid.add(labelTitle, 0, 1);
                                            TextField title = new TextField();
                                            title.setText(book.getTitle());
                                            title.setPrefWidth(200);
                                            title.setDisable(true);
                                            grid.add(title, 1, 1);
//                                        show informaton about fine and date
                                            Label labelFine = new Label("Fine");
                                            grid.add(labelFine, 0, 2);
                                            TextField fine = new TextField();
                                            fine.setText("Rp. 1000");
                                            fine.setPrefWidth(200);
                                            fine.setDisable(true);
                                            grid.add(fine, 1, 2);

                                            Label returnDate = new Label("Return Date");
                                            grid.add(returnDate, 0, 2);
                                            DatePicker date = new DatePicker();
                                            date.setPrefWidth(200);
                                            grid.add(date, 1, 2);
                                            dialog.getDialogPane().setGraphic(grid);

                                            Optional result = dialog.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                                User user = User.find(UserSession.getInstance().getUserId());
                                                Borrow borrow = new Borrow();
                                                borrow.setBook(book);
                                                borrow.setMember(user);
                                                borrow.setBorrow_date(new Date(System.currentTimeMillis()));
                                                borrow.setReturn_date(Date.valueOf(date.getValue()));
                                                borrow.setStatus("borrowed");
                                                borrow.insert();
                                                book.setQuantity(book.getQuantity() - 1);
                                                book.update();
                                                createPagination();
                                            }
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                                setGraphic(button);
                                setText(null);
                            }
                        }
                    };
                }
            });
            tableBook.setItems(Book.list(from, to, search.getText()));
            return new AnchorPane(tableBook);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
