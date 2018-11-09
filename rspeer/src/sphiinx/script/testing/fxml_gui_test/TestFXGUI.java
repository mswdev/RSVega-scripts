package sphiinx.script.testing.fxml_gui_test;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXFXML;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.ui.fxui.FXGUI;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.ui.fxui.FXMLType;

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

