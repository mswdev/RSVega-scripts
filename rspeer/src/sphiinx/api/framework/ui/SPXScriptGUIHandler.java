package sphiinx.api.framework.ui;

import javax.swing.*;

public class SPXScriptGUIHandler {

    private final SPXScriptFrame SPX_SCRIPT_FRAME;
    private final SPXScriptPanel SPX_SCRIPT_PANEL;
    private final SPXScriptGUI SPX_SCRIPT_GUI;

    public SPXScriptGUIHandler(SPXScriptGUI spx_script_gui) {
        SPX_SCRIPT_GUI = spx_script_gui;
        SPX_SCRIPT_FRAME = new SPXScriptFrame(spx_script_gui);
        SPX_SCRIPT_PANEL = new SPXScriptPanel(spx_script_gui);
    }

    public void initialize() {
        SwingUtilities.invokeLater(() -> {
            SPX_SCRIPT_FRAME.initialize();
            SPX_SCRIPT_PANEL.initialize();
            SPX_SCRIPT_GUI.getFrame().add(SPX_SCRIPT_GUI.getPanel());
        });
        SPX_SCRIPT_GUI.getFrame().setVisible(true);
    }

    public SPXScriptGUI getGUI() {
        return SPX_SCRIPT_GUI;
    }
}

