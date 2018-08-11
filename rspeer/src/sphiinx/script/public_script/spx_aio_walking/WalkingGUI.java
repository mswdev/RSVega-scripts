package sphiinx.script.public_script.spx_aio_walking;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.rspeer.ui.Log;
import sphiinx.api.framework.ui.javafx.FXGUI;
import sphiinx.api.framework.ui.javafx.components.FXAutoCompleteComboBox;
import sphiinx.script.public_script.spx_aio_walking.data.Location;

import java.net.MalformedURLException;
import java.net.URL;

public class WalkingGUI extends FXGUI {

    @FXML
    private URL location;

    @FXML
    private ImageView logo;

    @FXML
    private ComboBox<Location> locations;

    @FXML
    private TextField location_position;

    @FXML
    private Button add_location;

    @FXML
    private Hyperlink view_map;

    @FXML
    private Button start;

    @FXML
    void initialize() {
        logo.setImage(new Image("https://i.imgur.com/SJj0rIs.png"));

        locations.getItems().setAll(Location.values());
        FXAutoCompleteComboBox.autoCompleteComboBoxPlus(locations, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
        start.setOnAction(event -> {
            Log.fine("ACTION");
        });
    }

    @Override
    public String getName() {
        return "Test";
    }

    @Override
    public URL getFXMLURL() {
        try {
            return new URL("https://pastebin.com/raw/xapV8Ysd");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getFXMLString() {
        return null;
    }

    @Override
    public URL getStylesheetURL() {
        return null;
    }

    @Override
    public String getStylesheetString() {
        return null;
    }

    @Override
    public int getWidth() {
        return 300;
    }

    @Override
    public int getHeight() {
        return 400;
    }

    @Override
    public boolean showOnInvoke() {
        return true; //todo Change this when quickstart args are added.
    }

    @Override
    public boolean decorated() {
        return false;
    }

    @Override
    public boolean isResizable() {
        return false;
    }
}

