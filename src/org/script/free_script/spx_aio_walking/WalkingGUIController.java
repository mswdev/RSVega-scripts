package org.script.free_script.spx_aio_walking;

import com.allatori.annotations.DoNotRename;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import org.api.ui.SPXStyle;
import org.rspeer.runetek.api.commons.BankLocation;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@DoNotRename
public class WalkingGUIController {

    @DoNotRename
    @FXML
    private Label logoFirst;

    @DoNotRename
    @FXML
    private Label logoSecond;

    @DoNotRename
    @FXML
    private ComboBox<BankLocation> locations;

    @DoNotRename
    @FXML
    private TextField locationName;

    @DoNotRename
    @FXML
    private TextField locationPosition;

    @DoNotRename
    @FXML
    private Hyperlink viewMap;

    @DoNotRename
    @FXML
    private Button addLocation;

    @DoNotRename
    @FXML
    private Button start;

    @DoNotRename
    @FXML
    void initialize() {
        /*FXAutoCompleteComboBox.autoCompleteComboBoxPlus(locations, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));*/

        locationName.setDisable(true);
        locationPosition.setDisable(true);
        addLocation.setDisable(true);

        logoFirst.getStylesheets().add(SPXStyle.getLogoFontStyle());
        logoSecond.getStylesheets().add(SPXStyle.getLogoFontStyle());
        locations.getItems().setAll(BankLocation.values());

        addLocation.setOnAction(event -> {

        });
        viewMap.setOnAction(event -> openURL("https://explv.github.io/"));
        start.setOnAction(event -> {
            /*Vars.getCache().location = FXAutoCompleteComboBox.getComboBoxValue(locations);*/
            ((Node) (event.getSource())).getScene().getWindow().hide();
        });
    }

    private void openURL(String url) {
        final Desktop DESKTOP = Desktop.getDesktop();
        try {
            DESKTOP.browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

