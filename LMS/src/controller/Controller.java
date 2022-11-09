package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import util.Helper;

public abstract class Controller extends Helper implements Initializable {

    @Override
    public abstract void initialize(URL url, ResourceBundle rb);
}
