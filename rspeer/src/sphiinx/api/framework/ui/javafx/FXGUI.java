package sphiinx.api.framework.ui.javafx;

public abstract class FXGUI {

    public abstract String getName();

    public String getFXML() {
        return null;
    }

    public abstract FXMLType getFXMLType();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract boolean showOnInvoke();

    public boolean isResizable() {
        return true;
    }
}

