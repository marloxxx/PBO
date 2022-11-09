
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/auth/auth.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        primaryStage.setTitle("Library Management System");
//        set logo icon
        primaryStage.getIcons().add(new Image("file:src/view/auth/Icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
