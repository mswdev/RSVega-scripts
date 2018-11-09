package sphiinx.script.public_script.spx_aio_walking;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXFXML;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.ui.fxui.FXGUI;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.ui.fxui.FXMLType;

public class WalkingGUI extends FXGUI {

    @Override
    public String getTitle() {
        return "[SPX] AIO Walking";
    }

    @Override
    public String getFXML() {
        return SPXFXML.SPX_AIO_WALKER.getFXML();
    }

    @Override
    public FXMLType getFXMLType() {
        return FXMLType.STRING;
    }

    @Override
    public boolean showOnInvoke() {
        return true;
    }
}

