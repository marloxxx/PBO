package controller.member;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Book;
import model.Borrow;
import model.User;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class BorrowController extends Controller{

    @FXML
    private TableView<Borrow> borrowTable;

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
    private Pagination pagination;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<Borrow, String> tcAction;

    @FXML
    private TableColumn<Borrow, Date> tcBorrowDate;

    @FXML
    private TableColumn<Borrow, Book> tcBook;

    @FXML
    private TableColumn<Borrow, Date> tcReturnDate;

    @FXML
    private TableColumn<Borrow, String> tcStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelName.setText(UserSession.getInstance().getUsername());
        createPagination();
        btn_borrow.setDisable(true);
        btn_borrow.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: #ffffff;");

        btn_book.setOnAction(event -> {
            try {
                changeScene(event, "member/book.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btn_dashboard.setOnAction(event -> {
            try {
                changeScene(event, "member/dashboard.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btn_fine.setOnAction(event -> {
            try {
                changeScene(event, "member/fine.fxml");
            } catch (IOException e) {
                e.printStackTrace();
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
        int total = Book.count(search.getText());
        int rowsPerPage = 10;
        int pageCount = (total + rowsPerPage - 1) / rowsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, rowsPerPage));
    }

    private Node createPage(int pageIndex, int rowsPerPage) {
        int from = pageIndex * rowsPerPage;
        int to = from + rowsPerPage - 1;
        tcBook.setCellValueFactory(new PropertyValueFactory<>("book"));
        tcBook.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Borrow, Book> call(TableColumn<Borrow, Book> borrowBookTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(Book book, boolean b) {
                        super.updateItem(book, b);
                        if (book != null) {
                            setText(book.getTitle());
                        }
                    }
                };
            }
        });

        tcBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrow_date"));
        tcBorrowDate.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Borrow, java.sql.Date> call(TableColumn<Borrow, java.sql.Date> borrowDateTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(java.sql.Date date, boolean b) {
                        super.updateItem(date, b);
                        if (date != null) {
                            setText(date.toString());
                        }
                    }
                };
            }
        });

        tcReturnDate.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        tcReturnDate.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Borrow, Date> call(TableColumn<Borrow, Date> borrowDateTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(Date date, boolean b) {
                        super.updateItem(date, b);
                        if (date != null) {
                            setText(date.toString());
                        }
                    }
                };
            }
        });
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcStatus.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Borrow, String> call(TableColumn<Borrow, String> borrowStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (s != null) {
                            setText(s);
                        }
                    }
                };
            }
        });

        tcAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        tcAction.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Borrow, String> call(TableColumn<Borrow, String> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Button btn = new Button("Return");
                            boolean isReturned = Objects.equals(getTableView().getItems().get(getIndex()).getStatus(), "returned");
                            btn.setOnAction(event -> {
                                Borrow borrow = getTableView().getItems().get(getIndex());
                                borrow.returnBook();
                                successNotification("Success", "Book has been returned");
                                createPagination();
                            });
                            if (isReturned) {
                                btn.setDisable(true);
                            }
                            setGraphic(btn);
                        }
                    }
                };
            }
        });
        User user = User.find(UserSession.getInstance().getUserId());
        borrowTable.setItems(Objects.requireNonNull(user).borrows(from, to, search.getText()));
        return new AnchorPane(borrowTable);
    }
}
