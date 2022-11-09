package controller.admin;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import model.Book;
import util.UserSession;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookController extends Controller {

    @FXML
    private TextField author;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_book;

    @FXML
    private JFXButton btn_borrow;

    @FXML
    private JFXButton btn_cancel;

    @FXML
    private JFXButton btn_dashboard;

    @FXML
    private JFXButton btn_fine;

    @FXML
    private JFXButton btn_logout;

    @FXML
    private JFXButton btn_member;

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXButton btn_upload;

    @FXML
    private ComboBox<String> category;

    @FXML
    private ImageView cover;

    @FXML
    private AnchorPane form_input;

    @FXML
    private TextField id;

    @FXML
    private AnchorPane index;

    @FXML
    private Label labelName;

    @FXML
    private Pagination pagination;

    @FXML
    private TextField publisher;

    @FXML
    private TextField quantity;

    @FXML
    private TextField search;

    @FXML
    private TableView<Book> tableBook;

    @FXML
    private TableColumn<Book, String> tcAction;

    @FXML
    private TableColumn<Book, String> tcAuthor;

    @FXML
    private TableColumn<Book, String> tcCategory;

    @FXML
    private TableColumn<Book, Long> tcId;

    @FXML
    private TableColumn<Book, String> tcPublisher;

    @FXML
    private TableColumn<Book, Integer> tcQuantity;

    @FXML
    private TableColumn<Book, String> tcTitle;

    @FXML
    private TableColumn<Book, String> tcYear;

    @FXML
    private TextField title;

    @FXML
    private TextField year;

    private FileChooser fileChooser;
    private File file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelName.setText(UserSession.getInstance().getUsername());
        createPagination();
        btn_book.setStyle("-fx-background-color: #2a9df4; -fx-text-fill: white;");
        btn_book.setDisable(true);
        btn_add.setOnAction(event -> {
            index.setVisible(false);
            form_input.setVisible(true);
        });

        btn_dashboard.setOnAction(event -> {
            try {
                changeScene(event, "admin/dashboard.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btn_save.setOnAction(event -> {
            if (cover.getImage() == null) {
                warningNotification("Error", "Please upload book cover");
            } else if (title.getText().isEmpty()) {
                warningNotification("Error", "Title is required");
            } else if (author.getText().isEmpty()) {
                warningNotification("Error", "Author is required");
            } else if (publisher.getText().isEmpty()) {
                warningNotification("Error", "Publisher is required");
            } else if (year.getText().isEmpty()) {
                warningNotification("Error", "Year is required");
            } else if (category.getSelectionModel().isEmpty()) {
                warningNotification("Error", "Category is required");
            } else if (quantity.getText().isEmpty()) {
                warningNotification("Error", "Quantity is required");
            } else {
                Book book = new Book();
                if (!id.getText().isEmpty()) {
                    book.setId(Long.parseLong(id.getText()));
                }
                if (file != null) {
                    Path movefrom = FileSystems.getDefault().getPath(file.getAbsolutePath());
                    Path targetDir = FileSystems.getDefault().getPath("src/view/images");
                    try {
                        Files.move(movefrom, targetDir.resolve(movefrom.getFileName()),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    book.setCover(movefrom.getFileName().toString());
                }
                book.setTitle(title.getText());
                book.setAuthor(author.getText());
                book.setPublisher(publisher.getText());
                book.setYear(year.getText());
                book.setCategory(category.getSelectionModel().getSelectedItem());
                book.setQuantity(Integer.parseInt(quantity.getText()));
                try {
                    if (!id.getText().isEmpty()) {
                        if (book.update()) {
                            successNotification("Success", "Book updated successfully");
                            clearForm();
                            createPagination();
                            index.setVisible(true);
                            form_input.setVisible(false);
                        } else {
                            errorNotification("Error", "Failed to update book");
                        }
                    } else {
                        if (book.insert()) {
                            successNotification("Success", "Book added successfully");
                            clearForm();
                            createPagination();
                            index.setVisible(true);
                            form_input.setVisible(false);
                        } else {
                            errorNotification("Error", "Failed to add book");
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btn_cancel.setOnAction(event -> {
            clearForm();
            index.setVisible(true);
            form_input.setVisible(false);
        });

        search.setOnKeyReleased(event -> createPagination());

        btn_member.setOnAction(event -> {
            try {
                changeScene(event, "admin/member.fxml");
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

        btn_logout.setOnAction(event -> {
            try {
                UserSession.getInstance().clearSession();
                changeScene(event, "auth/auth.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btn_upload.setOnAction(event -> {
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            file = fileChooser.showOpenDialog(null);
            if (file != null) {
                cover.setImage(new Image(file.toURI().toString()));
            }
        });

        btn_fine.setOnAction(event -> {
            try {
                changeScene(event, "admin/fine.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void clearForm() {
        id.clear();
        title.clear();
        author.clear();
        publisher.clear();
        year.clear();
        category.getSelectionModel().clearSelection();
        quantity.clear();
        cover.setImage(null);
    }

    // create table book with pagination
    private void createPagination() {
        int total = Book.count(search.getText());
        int rowsPerPage = 10;
        int pageCount = (total + rowsPerPage - 1) / rowsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, rowsPerPage));
    }


    private Node createPage(int pageIndex, int rowsPerPage) {
        try {
            int from = pageIndex * rowsPerPage;
            int to = from + rowsPerPage;
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            tcPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            tcYear.setCellValueFactory(new PropertyValueFactory<>("year"));
            tcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            tcAction.setCellValueFactory(new PropertyValueFactory<>("action"));
            tcAction.setCellFactory(new Callback<>() {
                @Override
                public TableCell<Book, String> call(TableColumn<Book, String> param) {
                    return new TableCell<>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                Button btn_edit = new Button("Edit");
                                btn_edit.setOnAction(event -> {
                                    Book book = null;
                                    try {
                                        book = Book.find(getTableRow().getItem().getId());
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    id.setText(String.valueOf(book.getId()));
                                    title.setText(book.getTitle());
                                    author.setText(book.getAuthor());
                                    publisher.setText(book.getPublisher());
                                    year.setText(book.getYear());
                                    category.getSelectionModel().select(book.getCategory());
                                    quantity.setText(String.valueOf(book.getQuantity()));
                                    cover.setImage(new Image("file:src/view/images/" + book.getCover()));
                                    index.setVisible(false);
                                    form_input.setVisible(true);
                                });
                                Button btn_delete = new Button("Delete");
                                btn_delete.setOnAction(event -> {
                                    Book book = null;
                                    try {
                                        book = Book.find(getTableRow().getItem().getId());
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    try {
                                        if (book.delete()) {
                                            successNotification("Success", "Book deleted successfully");
                                            createPagination();
                                        } else {
                                            errorNotification("Error", "Failed to delete book");
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                                HBox hBox = new HBox(btn_edit, btn_delete);
                                hBox.setSpacing(10);
                                setGraphic(hBox);
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
