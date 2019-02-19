package sphiinx.script.public_script.spx_aio_walking;

import sphiinx.api.ui.SPXFXML;
import sphiinx.api.ui.fxui.FXGUI;
import sphiinx.api.ui.fxui.FXMLType;

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

