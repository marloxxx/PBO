
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MahasiswaJavaFXApps extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Form Entry Mahasiswa");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label labelNim = new Label("NIM");
        grid.add(labelNim, 0, 0);

        TextField textFieldNim = new TextField();
        HBox hBoxNim = new HBox(10);
        hBoxNim.getChildren().addAll(textFieldNim);
        grid.add(hBoxNim, 1, 0);

        Label labelNama = new Label("Nama Mahasiswa");
        grid.add(labelNama, 0, 1);

        TextField textFieldNama = new TextField();
        HBox hBoxNama = new HBox(10);
        hBoxNama.getChildren().addAll(textFieldNama);
        grid.add(textFieldNama, 1, 1, 1, 1);

        Label labelJenisKelamin = new Label("Jenis Kelamin");
        grid.add(labelJenisKelamin, 0, 2);
        RadioButton radioButtonLakiLaki = new RadioButton("Laki-Laki");
        RadioButton radioButtonPerempuan = new RadioButton("Perempuan");

        HBox hBoxGender = new HBox(10);
        hBoxGender.getChildren().addAll(radioButtonLakiLaki, radioButtonPerempuan);
        grid.add(hBoxGender, 1, 2);

        Label labelTempatTanggalLahir = new Label("Tempat, Tanggal Lahir");
        grid.add(labelTempatTanggalLahir, 0, 3);

        TextField textFieldTempat = new TextField();
        TextField textFieldTanggal = new TextField();
        HBox hBoxTempatTanggalLahir = new HBox(10);
        hBoxTempatTanggalLahir.getChildren().addAll(textFieldTempat, textFieldTanggal);
        grid.add(hBoxTempatTanggalLahir, 1, 3);
//        hBoxTanggalLahir.getChildren().addAll(textFieldTanggal);
//        grid.add(hBoxTempatLahir, 1, 3);

        Label labelFakultas = new Label("Fakultas");
        grid.add(labelFakultas, 0, 4);

        ComboBox comboBoxFakultas = new ComboBox();
        comboBoxFakultas.getItems().addAll(
                "Fakultas Informatika dan Teknik Elektro",
                "Fakultas Vokasi",
                "Fakultas Tekonologi Industri",
                "Fakultas Bio Teknologi");
        comboBoxFakultas.setPromptText("Pilih Fakultas");
        grid.add(comboBoxFakultas, 1, 4);

        Label labelProgramStudi = new Label("Program Studi");
        grid.add(labelProgramStudi, 0, 5);

        ComboBox comboBoxProgramStudi = new ComboBox();
        comboBoxProgramStudi.getItems().addAll(
                "S1 Informatika",
                "S1 Sistem Informasi",
                "S1 Teknik Elektro",
                "D3 Teknologi Informasi",
                "D3 Teknologi Komputer",
                "D4 Teknologi Rekayasa Perangkat Lunak",
                "S1 Manajemen Rekayasa",
                "S1 Teknik Bioproses");
        comboBoxProgramStudi.setPromptText("Pilih Program Studi");
        grid.add(comboBoxProgramStudi, 1, 5);

        Label labelHobby = new Label("Hobby");
        grid.add(labelHobby, 0, 6, 2, 1);

        TextField textFieldHobby = new TextField();
        grid.add(textFieldHobby, 1, 6, 1, 1);

        Button btnAdd = new Button("Add");
        HBox hBoxHobby = new HBox(10);
        hBoxHobby.getChildren().addAll(btnAdd);
        grid.add(btnAdd, 2, 6);

        TextArea textArea = new TextArea();
        textArea.setPrefWidth(100);
        textArea.setPrefHeight(100);
        grid.add(textArea, 1, 7, 1, 2);

        Button btnEdit = new Button("Edit");
        HBox hBoxButtonEdit = new HBox(10);
        hBoxButtonEdit.getChildren().addAll(btnEdit);
        grid.add(hBoxButtonEdit, 2, 7);

        Button btnDelete = new Button("Delete");
        HBox hBoxButtonDelete = new HBox(10);
        hBoxButtonDelete.getChildren().addAll(btnDelete);
        grid.add(hBoxButtonDelete, 2, 8);

        Button btnOk = new Button("OK");

        Button btnCancel = new Button("Cancel");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnOk, btnCancel);
        grid.add(hbBtn, 2, 10);

        btnAdd.setOnAction(event -> {
            textArea.appendText(textFieldHobby.getText() + "\n");
        });

        btnEdit.setOnAction(event -> {
            textArea.setText(textFieldHobby.getText() + "\n");
        });

        btnDelete.setOnAction(event -> {
            textArea.clear();
        });

        btnOk.setOnAction(event -> {
//            textFieldNim.clear();
            textFieldNama.clear();
            textFieldTempat.clear();
            textFieldTanggal.clear();
            textFieldHobby.clear();
            textArea.clear();
        });

        btnCancel.setOnAction(event -> {
            Platform.exit();
        });

//style
        btnAdd.setStyle("-fx-background-color: #B2A4FF");
        btnEdit.setStyle("-fx-background-color: #B2A4FF");
        btnDelete.setStyle("-fx-background-color: #B2A4FF");
        btnOk.setStyle("-fx-background-color: #B2A4FF");
        btnCancel.setStyle("-fx-background-color: #B2A4FF");

        Scene scene = new Scene(grid, 720, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
