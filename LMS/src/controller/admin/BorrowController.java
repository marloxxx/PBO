package controller.admin;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Book;
import model.Borrow;
import model.Fine;
import model.User;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BorrowController extends Controller {

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
    private TableView<Borrow> borrowTable;

    @FXML
    private TableColumn<Borrow, Long> tcId;

    @FXML
    private TableColumn<Borrow, Date> tcBorrowDate;

    @FXML
    private TableColumn<Borrow, User> tcMember;

    @FXML
    private TableColumn<Borrow, Book> tcBook;
    @FXML
    private TableColumn<Borrow, Date> tcReturnDate;

    @FXML
    private TableColumn<Borrow, String> tcStatus;

    @FXML
    private TableColumn<Borrow, String> tcAction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelName.setText(UserSession.getInstance().getUsername());
        createPagination();
        btn_dashboard.setOnAction(event -> {
            try {
                changeScene(event, "admin/dashboard.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_book.setOnAction(event -> {
            try {
                changeScene(event, "admin/book.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_member.setOnAction(event -> {
            try {
                changeScene(event, "admin/member.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_borrow.setDisable(true);
        btn_borrow.setStyle("-fx-background-color: #2a9df4; -fx-text-fill: white;");
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
        int total = Book.count(search.getText());
        int rowsPerPage = 10;
        int pageCount = (total + rowsPerPage - 1) / rowsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, rowsPerPage));
    }

    private Node createPage(int pageIndex, int rowsPerPage) {
        int from = pageIndex * rowsPerPage;
        int to = from + rowsPerPage - 1;
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcMember.setCellValueFactory(new PropertyValueFactory<>("member"));
        tcMember.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Borrow, User> call(TableColumn<Borrow, User> borrowUserTableColumn) {
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
                            if (s.equals("borrowed")) {
                                setText("Borrowed");
                            } else {
                                setText("Returned");
                            }
                        }
                    }
                };
            }
        });

        tcAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        tcAction.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Borrow, String> call(TableColumn<Borrow, String> borrowStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (b) {
                            setGraphic(null);
                        } else {
                            Button btn = new Button("Fine");
                            Borrow borrow;
                            try {
                                borrow = Borrow.find(getTableView().getItems().get(getIndex()).getId());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if (borrow.getStatus().equals("returned") && borrow.getReturn_date().before(borrow.getUpdated_at())) {
                                btn.setOnAction(event -> {
                                    try {
                                        long diff = borrow.getUpdated_at().getTime() - borrow.getReturn_date().getTime();
                                        long diffDays = diff / (24 * 60 * 60 * 1000);
                                        int fine = (int) diffDays * 1000;
                                        Fine fine1 = new Fine();
                                        fine1.setMember(borrow.getMember());
                                        fine1.setBorrow(borrow);
                                        fine1.setAmount(fine);
                                        fine1.insert();
                                        successNotification("Success", "Fine has been added");
                                        createPagination();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                                if (borrow.fine()) {
                                    btn.setText("Fine Has Been Added");
                                    btn.setDisable(true);
                                }
                                setGraphic(btn);
                            }
                        }
                    }
                };
            }
        });
        borrowTable.setItems(Borrow.list(from, to, search.getText()));
        return new AnchorPane(borrowTable);
    }
}
