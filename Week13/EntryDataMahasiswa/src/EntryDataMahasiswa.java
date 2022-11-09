
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EntryDataMahasiswa extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Form Entry Data Mahasiswa");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //
        Label nim = new Label("NIM");
        grid.add(nim, 0, 1); // kolom 0, baris 1

        TextField nimTextField = new TextField();
        grid.add(nimTextField, 1, 1); // kolom 1, baris 1

        //
        Label namaMahasiswa = new Label("Nama Mahasiswa");
        grid.add(namaMahasiswa, 0, 2);

        TextField namaMahasiswaTextField = new TextField();
        grid.add(namaMahasiswaTextField, 1, 2, 2, 1);

        //
        Label jenisKelamin = new Label("Jenis Kelamin");
        grid.add(jenisKelamin, 0, 3);
        RadioButton lakiLaki = new RadioButton("Laki-Laki");
        RadioButton perempuan = new RadioButton("Perempuan");

        HBox hBoxGender = new HBox(10);
        hBoxGender.getChildren().addAll(lakiLaki, perempuan);
        grid.add(hBoxGender, 1, 3);

        //
        Label tempatLahir = new Label("Tempat Lahir, Tanggal Lahir");
        grid.add(tempatLahir, 0, 4);

        TextField tempatLahirTextField = new TextField();
        HBox hBoxTL = new HBox(10);
        hBoxTL.getChildren().addAll(tempatLahirTextField);
        grid.add(hBoxTL, 1, 4, 1, 1);

        TextField tanggalLahirTextField = new TextField();
        HBox hBoxtgl = new HBox(10);
        hBoxtgl.getChildren().addAll(tanggalLahirTextField);
        grid.add(hBoxtgl, 2, 4, 1, 1);

        //
        Label fakultas = new Label("Fakultas");
        grid.add(fakultas, 0, 5);

        ComboBox<String> cmbFakultas = new ComboBox<>();
        cmbFakultas.getItems().addAll("Fakultas Vokasi", "FITE", "Fakultas Bioteknologi", "Fakultas Industri");
        cmbFakultas.setPromptText("Pilih Fakultas");
        grid.add(cmbFakultas, 1, 5);

        //
        Label prodi = new Label("Program Studi");
        grid.add(prodi, 0, 6);

        ComboBox<String> cmbProdi = new ComboBox<>();
        cmbProdi.getItems().addAll("D3 Teknologi Informasi", "D3 Teknologi Komputer", "D4 TRPL",
                "S1 Sarjana Informatika", "S1 Sarjana Informasi", "S1 Menejemen Rekayasa", "S1 Teknik Bioproses",
                "S1 Teknik Electro");
        cmbProdi.setPromptText("Pilih Program Studi");
        grid.add(cmbProdi, 1, 6);

        //
        Label hobby = new Label("Hobby");
        grid.add(hobby, 0, 7);

        TextField hobbyTextField = new TextField();
        grid.add(hobbyTextField, 1, 7, 2, 1);

        Button btnAdd = new Button("Add");
        HBox hBoxHobby = new HBox(10);
        hBoxHobby.getChildren().addAll(btnAdd);
        grid.add(hBoxHobby, 3, 7);

        //
        TextArea textArea = new TextArea();
        textArea.setPrefWidth(100);
        textArea.setPrefHeight(100);
        grid.add(textArea, 1, 8, 2, 2); // kolom 1, baris 8, lebar 2, tinggi 2

        Button btnEdit = new Button("Edit");
        HBox hBoxedit = new HBox(10);
        hBoxedit.getChildren().addAll(btnEdit);
        grid.add(hBoxedit, 3, 8);

        Button btnDel = new Button("Delete");
        HBox hBoxdel = new HBox(10);
        hBoxdel.getChildren().addAll(btnDel);
        grid.add(hBoxdel, 3, 9);

        //
        Button btnReset = new Button("Ok");
        Button btnExit = new Button("Cancel");
        HBox hBoxBtn = new HBox(10);
        hBoxBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxBtn.getChildren().addAll(btnReset);
        hBoxBtn.getChildren().addAll(btnExit);
        grid.add(hBoxBtn, 3, 11);

        Scene scene = new Scene(grid, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                textArea.appendText(hobbyTextField.getText() + " \n");
            }
        });

        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                textArea.setText(hobbyTextField.getText() + " \n");
            }
        });

        btnDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                textArea.clear();
            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                nimTextField.setText("");
                namaMahasiswaTextField.setText("");
                tempatLahirTextField.setText("");
                tanggalLahirTextField.setText("");
                hobbyTextField.setText("");
                textArea.setText("");
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        // Style
        btnAdd.setStyle("-fx-background-color: #B2A4FF;");
        btnEdit.setStyle("-fx-background-color: #B2A4FF;");
        btnDel.setStyle("-fx-background-color: #B2A4FF;");
        btnReset.setStyle("-fx-background-color: #B2A4FF;");
        btnExit.setStyle("-fx-background-color: #B2A4FF;");
    }
}
