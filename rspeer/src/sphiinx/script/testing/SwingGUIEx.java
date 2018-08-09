package sphiinx.script.testing;

import sphiinx.api.framework.ui.SPXScriptGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class SwingGUIEx extends SPXScriptGUI {

    private final JFrame FRAME = new JFrame();
    private final JPanel PANEL = new JPanel();
    private final GridBagConstraints CONSTRAINTS = new GridBagConstraints();

    @Override
    public void initialize() {

    }

    @Override
    public JFrame getFrame() {
        return FRAME;
    }

    @Override
    public JPanel getPanel() {
        return PANEL;
    }

    @Override
    public GridBagConstraints getConstraints() {
        return CONSTRAINTS;
    }

    @Override
    public String getName() {
        return "SwingGUIExample WalkingGUI";
    }

    @Override
    public Image getLogo() {
        try {
            return ImageIO.read(new URL("https://i.imgur.com/6dRk3wf.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getWidth() {
        return 315;
    }

    @Override
    public int getHeight() {
        return 400;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public ActionListener onStart() {
        return e -> {
            FRAME.setVisible(false);
        };
    }

}

