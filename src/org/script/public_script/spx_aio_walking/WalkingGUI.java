package org.script.public_script.spx_aio_walking;

import org.api.ui.SPXFXML;
import org.api.ui.fxui.FXGUI;
import org.api.ui.fxui.FXMLType;

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

