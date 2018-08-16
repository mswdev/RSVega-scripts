package sphiinx.api.framework.ui.javafx;

public abstract class FXGUI {

    public abstract String getTitle();

    public abstract String getFXML();

    public abstract FXMLType getFXMLType();

    public int getWidth() {
        return -1;
    }

    public int getHeight() {
        return -1;
    }

    public abstract boolean showOnInvoke();

    public boolean isResizable() {
        return true;
    }
}

