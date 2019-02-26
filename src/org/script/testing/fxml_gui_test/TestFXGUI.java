package org.script.testing.fxml_gui_test;

import org.api.ui.SPXFXML;
import org.api.ui.fxui.FXGUI;
import org.api.ui.fxui.FXMLType;

public class TestFXGUI extends FXGUI {

    @Override
    public String getTitle() {
        return "Test FXML GUI";
    }

    @Override
    public String getFXML() {
        return SPXFXML.SPX_TEMPLATE.getFXML();
    }

    @Override
    public FXMLType getFXMLType() {
        return FXMLType.STRING;
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

