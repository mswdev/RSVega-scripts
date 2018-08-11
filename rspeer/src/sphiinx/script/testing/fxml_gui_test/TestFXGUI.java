package sphiinx.script.testing.fxml_gui_test;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.rspeer.ui.Log;
import sphiinx.api.framework.ui.javafx.FXGUI;

import java.net.MalformedURLException;
import java.net.URL;

public class TestFXGUI extends FXGUI {

    @FXML
    private ImageView logo;

    @FXML
    private Button start;

    @FXML
    void initialize() {
        logo.setImage(new Image("https://i.imgur.com/SJj0rIs.png"));
        start.setOnAction(event -> {
            Log.fine("START ACTION");
            getStage().close();
        });
    }

    @Override
    public String getName() {
        return "Test FXML GUI";
    }

    @Override
    public URL getFXMLURL() {
        try {
            return new URL("https://pastebin.com/raw/3XFXACwQ");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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
        return true;
    }

}

