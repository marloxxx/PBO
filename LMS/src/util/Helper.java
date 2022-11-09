package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Helper {
    protected void changeScene(ActionEvent event, String path) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + path));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    protected void errorNotification(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .position(Pos.TOP_RIGHT)
                .showError();
    }

    protected void warningNotification(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .position(Pos.TOP_RIGHT)
                .showWarning();
    }

    protected void infoNotification(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .position(Pos.TOP_RIGHT)
                .showInformation();
    }

    protected void successNotification(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .position(Pos.TOP_RIGHT)
                .showConfirm();
    }
    protected static String getSecurePassword(String password, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    protected static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String toHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    public static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
