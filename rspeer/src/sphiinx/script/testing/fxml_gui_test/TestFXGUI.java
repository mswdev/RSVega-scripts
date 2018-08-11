package sphiinx.script.testing.fxml_gui_test;

import sphiinx.api.framework.ui.javafx.FXGUI;
import sphiinx.api.framework.ui.javafx.FXMLType;

public class TestFXGUI extends FXGUI {

    @Override
    public String getName() {
        return "Test FXML GUI";
    }

    @Override
    public String getFXML() {
        return "https://pastebin.com/raw/3XFXACwQ";
    }

    @Override
    public FXMLType getFXMLType() {
        return FXMLType.URL;
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

