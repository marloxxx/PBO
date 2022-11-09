package product;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    final static Controller controller = new Controller();
    final static Model model = new Model();
    final static TableView<Model> table = new TableView<>();
    final static GridPane grid = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        primaryStage.setTitle("Product");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text textFormProduct = new Text("Form Product");
        grid.add(textFormProduct, 0, 0);

        Text textTableProduct = new Text("Table Product");
        grid.add(textTableProduct, 2, 0);

        Label lkode = new Label("Kode");
        grid.add(lkode, 0, 1);
        TextField tfkode = new TextField();
        grid.add(tfkode, 1, 1);

        Label lnama = new Label("Nama");
        grid.add(lnama, 0, 2);
        TextField tfnama = new TextField();
        grid.add(tfnama, 1, 2);

        Label lkategori = new Label("Kategori");
        grid.add(lkategori, 0, 3);
        TextField tfkategori = new TextField();
        grid.add(tfkategori, 1, 3);

        Label lharga_beli = new Label("Harga Beli");
        grid.add(lharga_beli, 0, 4);
        TextField tfharga_beli = new TextField();
        grid.add(tfharga_beli, 1, 4);

        Label lharga_jual = new Label("Harga Jual");
        grid.add(lharga_jual, 0, 5);
        TextField tfharga_jual = new TextField();
        grid.add(tfharga_jual, 1, 5);

        Label ljumlah = new Label("Jumlah");
        grid.add(ljumlah, 0, 6);
        TextField tfjumlah = new TextField();
        grid.add(tfjumlah, 1, 6);

        Button btnSimpan = new Button("Simpan");
        Button btnUpdate = new Button("Update");
        Button btnDelete = new Button("Delete");
        Button btnClear = new Button("Clear");
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(btnSimpan, btnUpdate, btnDelete, btnClear);
        grid.add(hBox, 1, 7);

        TableView tableViewProduct = new TableView();
        table.setEditable(true);
        TableColumn kode = new TableColumn("Kode");
        kode.setCellValueFactory(new PropertyValueFactory<>("kode"));
        TableColumn nama = new TableColumn("Nama");
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        TableColumn kategori = new TableColumn("Kategori");
        kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        TableColumn harga_beli = new TableColumn("Harga Beli");
        harga_beli.setCellValueFactory(new PropertyValueFactory<>("harga_beli"));
        TableColumn harga_jual = new TableColumn("Harga Jual");
        harga_jual.setCellValueFactory(new PropertyValueFactory<>("harga_jual"));
        TableColumn jumlah = new TableColumn("Stok");
        jumlah.setCellValueFactory(new PropertyValueFactory<>("stok"));
        table.getColumns().addAll(kode, nama, kategori, harga_beli, harga_jual, jumlah);
        table.setItems(controller.getDataProduct());
        grid.add(table, 2, 1, 2, 7);

        btnSimpan.setOnAction(event -> {
            if (tfkode.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Kode Barang Kosong");
                alert.setContentText("Kode Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfkode.getText().length() > 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Kode Barang Lebih Dari 4 Karakter");
                alert.setContentText("Kode Barang Tidak Boleh Lebih Dari 4 Karakter");
                alert.showAndWait();
            } else if (tfnama.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Nama Barang Kosong");
                alert.setContentText("Nama Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfkategori.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Kategori Barang Kosong");
                alert.setContentText("Kategori Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfharga_beli.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Harga Beli Barang Kosong");
                alert.setContentText("Harga Beli Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfharga_jual.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Harga Jual Barang Kosong");
                alert.setContentText("Harga Jual Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfjumlah.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Jumlah Barang Kosong");
                alert.setContentText("Jumlah Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else {
                model.setKode(tfkode.getText());
                model.setNama(tfnama.getText());
                model.setKategori(tfkategori.getText());
                model.setHarga_beli(tfharga_beli.getText());
                model.setHarga_jual(tfharga_jual.getText());
                model.setStok(tfjumlah.getText());
                try {
                    controller.insert(model);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    table.setItems(controller.getDataProduct());
                    tfkode.clear();
                    tfnama.clear();
                    tfkategori.clear();
                    tfharga_beli.clear();
                    tfharga_jual.clear();
                    tfjumlah.clear();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Data Berhasil Disimpan");
                alert.setContentText("Data Berhasil Disimpan");
                alert.showAndWait();
            }
        });

        table.setOnMouseClicked(event -> {
            tfkode.setText(table.getSelectionModel().getSelectedItem().getKode());
            tfnama.setText(table.getSelectionModel().getSelectedItem().getNama());
            tfkategori.setText(table.getSelectionModel().getSelectedItem().getKategori());
            tfharga_beli.setText(table.getSelectionModel().getSelectedItem().getHarga_beli());
            tfharga_jual.setText(table.getSelectionModel().getSelectedItem().getHarga_jual());
            tfjumlah.setText(table.getSelectionModel().getSelectedItem().getStok());
        });

        btnUpdate.setOnAction(event -> {
            if (tfkode.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Kode Barang Kosong");
                alert.setContentText("Kode Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfkode.getText().length() > 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Kode Barang Lebih Dari 4 Karakter");
                alert.setContentText("Kode Barang Tidak Boleh Lebih Dari 4 Karakter");
                alert.showAndWait();
            } else if (tfnama.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Nama Barang Kosong");
                alert.setContentText("Nama Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfkategori.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Kategori Barang Kosong");
                alert.setContentText("Kategori Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfharga_beli.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Harga Beli Barang Kosong");
                alert.setContentText("Harga Beli Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfharga_jual.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Harga Jual Barang Kosong");
                alert.setContentText("Harga Jual Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else if (tfjumlah.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Jumlah Barang Kosong");
                alert.setContentText("Jumlah Barang Tidak Boleh Kosong");
                alert.showAndWait();
            } else {
                model.setKode(tfkode.getText());
                model.setNama(tfnama.getText());
                model.setKategori(tfkategori.getText());
                model.setHarga_beli(tfharga_beli.getText());
                model.setHarga_jual(tfharga_jual.getText());
                model.setStok(tfjumlah.getText());
                try {
                    controller.update(model);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    table.setItems(controller.getDataProduct());
                    tfkode.clear();
                    tfnama.clear();
                    tfkategori.clear();
                    tfharga_beli.clear();
                    tfharga_jual.clear();
                    tfjumlah.clear();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Data Berhasil Diupdate");
                alert.setContentText("Data Berhasil Diupdate");
                alert.showAndWait();
            }
        });

        btnDelete.setOnAction(event -> {
            model.setKode(table.getSelectionModel().getSelectedItem().getKode());
            try {
                controller.delete(model);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                table.setItems(controller.getDataProduct());
                tfkode.clear();
                tfnama.clear();
                tfkategori.clear();
                tfharga_beli.clear();
                tfharga_jual.clear();
                tfjumlah.clear();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data Berhasil Dihapus");
            alert.setContentText("Data Berhasil Dihapus");
            alert.showAndWait();
        });

        btnClear.setOnAction(event -> {
            tfkode.clear();
            tfnama.clear();
            tfkategori.clear();
            tfharga_beli.clear();
            tfharga_jual.clear();
            tfjumlah.clear();
        });

        Scene scene = new Scene(grid, 1200, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
