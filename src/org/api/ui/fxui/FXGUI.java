package org.api.ui.fxui;

public abstract class FXGUI {

    // [TODO - 2018-10-27]: Rename GUI to UI on everything
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

