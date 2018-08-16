package sphiinx.api.framework.ui.swing;

import javax.swing.*;

public class GUIHandler {

    private final GUIFrame FRAME;
    private final GUIPanel PANEL;
    private final GUI GUI;

    public GUIHandler(GUI gui) {
        GUI = gui;
        FRAME = new GUIFrame(gui);
        PANEL = new GUIPanel(gui);
    }

    public void invokeGUI() {
        SwingUtilities.invokeLater(() -> {
            FRAME.initialize();
            PANEL.initialize();
            GUI.getFrame().add(GUI.getPanel());
        });
        GUI.getFrame().setVisible(true);
    }

    public GUI getGUI() {
        return GUI;
    }
}

