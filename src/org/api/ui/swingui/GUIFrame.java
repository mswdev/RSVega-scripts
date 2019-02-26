package org.api.ui.swingui;

public class GUIFrame {

    private final GUI spx_gui;

    GUIFrame(GUI spx_gui) {
        this.spx_gui = spx_gui;
    }

    void initialize() {
        spx_gui.getFrame().setTitle(spx_gui.getName());
        spx_gui.getFrame().setSize(spx_gui.getWidth(), spx_gui.getHeight());
        spx_gui.getFrame().setLocationRelativeTo(null);
        spx_gui.getFrame().setResizable(spx_gui.getFrame().isResizable());
    }
}

