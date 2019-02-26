package org.api.ui.swingui;

import javax.swing.*;

public class GUIBuilder {

    private final GUIFrame frame;
    private final GUIPanel panel;
    private final GUI gui;

    public GUIBuilder(GUI gui) {
        this.gui = gui;
        frame = new GUIFrame(gui);
        panel = new GUIPanel(gui);
    }

    public void invokeGUI() {
        SwingUtilities.invokeLater(() -> {
            frame.initialize();
            panel.initialize();
            gui.getFrame().add(gui.getPanel());
        });
        gui.getFrame().setVisible(true);
    }

    public GUI getGUI() {
        return gui;
    }

    public void show() {
        if (gui == null)
            return;

        SwingUtilities.invokeLater(() -> gui.getFrame().setVisible(true));
    }

    public void hide() {
        if (gui == null)
            return;

        SwingUtilities.invokeLater(() -> gui.getFrame().setVisible(false));
    }

    public void close() {
        if (gui == null)
            return;

        SwingUtilities.invokeLater(() -> gui.getFrame().dispose());
    }

    public boolean isInvoked() {
        if (gui == null)
            return false;

        return gui.getFrame().isVisible();
    }
}

