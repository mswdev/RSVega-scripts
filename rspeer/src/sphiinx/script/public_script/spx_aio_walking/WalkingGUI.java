package sphiinx.script.public_script.spx_aio_walking;

import sphiinx.api.SPXScript;
import sphiinx.api.framework.ui.AutoCompleteComboBox;
import sphiinx.api.framework.ui.SPXScriptGUI;
import sphiinx.script.public_script.spx_aio_walking.data.Location;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class WalkingGUI extends SPXScriptGUI {

    private final JFrame FRAME = new JFrame();
    private final JPanel PANEL = new JPanel();
    private final GridBagConstraints CONSTRAINTS = new GridBagConstraints();
    private final SPXScript SCRIPT;

    WalkingGUI(SPXScript script) {
        SCRIPT = script;
    }

    @Override
    public void initialize() {
        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 1;
        CONSTRAINTS.anchor = GridBagConstraints.CENTER;
        final JComboBox COMBO_BOX = new AutoCompleteComboBox(Location.values(), "Search location", 200, 30);
        PANEL.add(COMBO_BOX, CONSTRAINTS);
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
        return SCRIPT.getName();
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
        return false;
    }

    @Override
    public ActionListener onStart() {
        return e -> {
            FRAME.setVisible(false);
            SCRIPT.setPaused(false);
        };
    }
}

