package sphiinx.api.framework.ui.swing;

import javax.swing.*;

public class GUIHandler {

    private final GUIFrame FRAME;
    private final GUIPanel PANEL;
    private final SPXGUI SPX_GUI;

    public GUIHandler(SPXGUI spx_gui) {
        SPX_GUI = spx_gui;
        FRAME = new GUIFrame(spx_gui);
        PANEL = new GUIPanel(spx_gui);
    }

    public void initialize() {
        SwingUtilities.invokeLater(() -> {
            FRAME.initialize();
            PANEL.initialize();
            SPX_GUI.getFrame().add(SPX_GUI.getPanel());
        });
        SPX_GUI.getFrame().setVisible(true);
    }

    public SPXGUI getGUI() {
        return SPX_GUI;
    }
}

