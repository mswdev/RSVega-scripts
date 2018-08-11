package sphiinx.script.testing.fxml_gui_test;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.rspeer.ui.Log;

import java.util.ResourceBundle;

public class TestFXGUIController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ImageView logo;

    @FXML
    private Button start;

    @FXML
    void initialize() {
        logo.setImage(new Image("https://i.imgur.com/SJj0rIs.png"));
        start.setOnAction(event -> {
            Log.fine("START ACTION");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }
}

