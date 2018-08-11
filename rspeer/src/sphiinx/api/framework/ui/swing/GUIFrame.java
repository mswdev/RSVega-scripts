package sphiinx.api.framework.ui.swing;

public class GUIFrame {

    private final SPXGUI SPX_GUI;

    GUIFrame(SPXGUI spx_gui) {
        this.SPX_GUI = spx_gui;
    }

    void initialize() {
        SPX_GUI.getFrame().setTitle(SPX_GUI.getName());
        SPX_GUI.getFrame().setSize(SPX_GUI.getWidth(), SPX_GUI.getHeight());
        SPX_GUI.getFrame().setLocationRelativeTo(null);
        SPX_GUI.getFrame().setResizable(SPX_GUI.getFrame().isResizable());
    }
}

