package sphiinx.script.public_script.spx_aio_walking;

import com.allatori.annotations.DoNotRename;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import sphiinx.script.public_script.spx_tutorial_island.api.ui.SPXStyle;
import sphiinx.script.public_script.spx_aio_walking.data.Location;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@DoNotRename
public class WalkingGUIController {

    @DoNotRename
    @FXML
    private Label logo_first;

    @DoNotRename
    @FXML
    private Label logo_second;

    @DoNotRename
    @FXML
    private ComboBox<Location> locations;

    @DoNotRename
    @FXML
    private TextField location_name;

    @DoNotRename
    @FXML
    private TextField location_position;

    @DoNotRename
    @FXML
    private Hyperlink view_map;

    @DoNotRename
    @FXML
    private Button add_location;

    @DoNotRename
    @FXML
    private Button start;

    @DoNotRename
    @FXML
    void initialize() {
        /*FXAutoCompleteComboBox.autoCompleteComboBoxPlus(locations, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));*/

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
            /*Vars.get().location = FXAutoCompleteComboBox.getComboBoxValue(locations);*/
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

