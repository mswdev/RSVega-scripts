package sphiinx.api.framework.ui.javafx;

import java.net.URL;

public abstract class FXGUI {

    public abstract String getName();

    public URL getFXMLURL() {
        return null;
    }

    public String getFXMLString() {
        return null;
    }

    public URL getStylesheetURL() {
        return null;
    }

    public String getStylesheetString() {
        return null;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract boolean showOnInvoke();

    public boolean decorated() {
        return false;
    }

    public boolean isResizable() {
        return true;
    }
}

