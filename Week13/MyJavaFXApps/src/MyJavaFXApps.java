
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyJavaFXApps extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Form Pembelian Barang");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitile = new Text("Data Pembelian Barang");
        scenetitile.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitile, 0, 0, 2, 1);

        Label lblKodeBarang = new Label("Kode Barang:");
        grid.add(lblKodeBarang, 0, 1);

        TextField txtKode = new TextField();
        grid.add(txtKode, 1, 1);

        Label lblNamaBarang = new Label("Nama Barang:");
        grid.add(lblNamaBarang, 0, 2);

        TextField txtNama = new TextField();
        grid.add(txtNama, 1, 2);

        // Buat combobox untuk Barang
        ComboBox<String> cmbKategoriBarang = new ComboBox<>();
//        Tambahkan Item ke dalam ComboBox
        cmbKategoriBarang.getItems().addAll("Elektronik", "Furniture", "Rumah Tangga", "Peralatan Dapur");
//        Pilih item pertama dari nama barang
        cmbKategoriBarang.getSelectionModel().selectFirst();
        grid.add(cmbKategoriBarang, 1, 3);

        Label lblHargaBeli = new Label("Harga Beli:");
        grid.add(lblHargaBeli, 0, 4);

        TextField txtHargaBeli = new TextField();
        grid.add(txtHargaBeli, 1, 4);

        Label lblHargaJual = new Label("Harga Jual:");
        grid.add(lblHargaJual, 0, 5);

        TextField txtHargaJual = new TextField();
        grid.add(txtHargaJual, 1, 5);

        Label lblStok = new Label("Stok:");
        grid.add(lblStok, 0, 6);

        TextField txtStok = new TextField();
        grid.add(txtStok, 1, 6);

        Button btnProses = new Button("Proses");
        Button btnBatal = new Button("Batal");
        Button btnExit = new Button("Exit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnProses);
        hbBtn.getChildren().add(btnBatal);
        hbBtn.getChildren().add(btnExit);
        grid.add(hbBtn, 1, 8);

        final Text actionTarget1 = new Text();
        grid.add(actionTarget1, 1, 9);
        final Text actionTarget2 = new Text();
        grid.add(actionTarget2, 1, 10);
        final Text actionTarget3 = new Text();
        grid.add(actionTarget3, 1, 11);
        final Text actionTarget4 = new Text();
        grid.add(actionTarget4, 1, 12);
        final Text actionTarget5 = new Text();
        grid.add(actionTarget5, 1, 13);
        final Text actionTarget6 = new Text();
        grid.add(actionTarget6, 1, 14);

        btnProses.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actionTarget1.setText("Kode Barang: " + txtKode.getText());
                actionTarget2.setFill(Color.FIREBRICK);
                actionTarget2.setText("Nama Barang: " + txtNama.getText());
                actionTarget3.setFill(Color.FIREBRICK);
                actionTarget3.setText("Kategori Barang: " + cmbKategoriBarang.getValue());
                actionTarget4.setFill(Color.FIREBRICK);
                actionTarget4.setText("Harga Beli: " + txtHargaBeli.getText());
                actionTarget5.setFill(Color.FIREBRICK);
                actionTarget5.setText("Harga Jual: " + txtHargaJual.getText());
                actionTarget6.setFill(Color.FIREBRICK);
                actionTarget6.setText("Stok" + txtStok.getText());
            }
        });

        btnBatal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                txtKode.setText("");
                txtNama.setText("");
                cmbKategoriBarang.setValue("");
                txtHargaBeli.setText("");
                txtHargaJual.setText("");
                txtStok.setText("");
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });

        Scene scene = new Scene(grid, 430, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
