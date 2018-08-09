package sphiinx.api.framework.ui;

public class SPXScriptFrame {

    private final SPXScriptGUI SPX_SCRIPT_GUI;

    SPXScriptFrame(SPXScriptGUI spx_script_gui) {
        this.SPX_SCRIPT_GUI = spx_script_gui;
    }

    void initialize() {
        SPX_SCRIPT_GUI.getFrame().setTitle(SPX_SCRIPT_GUI.getName());
        SPX_SCRIPT_GUI.getFrame().setSize(SPX_SCRIPT_GUI.getWidth(), SPX_SCRIPT_GUI.getHeight());
        SPX_SCRIPT_GUI.getFrame().setLocationRelativeTo(null);
        SPX_SCRIPT_GUI.getFrame().setResizable(SPX_SCRIPT_GUI.getFrame().isResizable());
    }
}

