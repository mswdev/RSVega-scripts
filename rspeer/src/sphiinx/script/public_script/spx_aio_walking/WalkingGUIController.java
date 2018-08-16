package sphiinx.script.public_script.spx_aio_walking;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sphiinx.api.SPXStyle;
import sphiinx.api.framework.ui.javafx.components.FXAutoCompleteComboBox;
import sphiinx.script.public_script.spx_aio_walking.data.Location;
import sphiinx.script.public_script.spx_aio_walking.data.Vars;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WalkingGUIController {

    @FXML
    private Label logo_first;

    @FXML
    private Label logo_second;

    @FXML
    private ComboBox<Location> locations;

    @FXML
    private TextField location_name;

    @FXML
    private TextField location_position;

    @FXML
    private Hyperlink view_map;

    @FXML
    private Button add_location;

    @FXML
    private Button start;

    @FXML
    void initialize() {
        FXAutoCompleteComboBox.autoCompleteComboBoxPlus(locations, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));

        location_name.setDisable(true);
        location_position.setDisable(true);
        add_location.setDisable(true);

        logo_first.getStylesheets().add(SPXStyle.getLogoFontStyle());
        logo_second.getStylesheets().add(SPXStyle.getLogoFontStyle());
        locations.getItems().setAll(Location.values());

        add_location.setOnAction(event -> {

        });
        view_map.setOnAction(event -> openURL("https://explv.github.io/"));
        start.setOnAction(event -> {
            Vars.get().location = FXAutoCompleteComboBox.getComboBoxValue(locations);
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

