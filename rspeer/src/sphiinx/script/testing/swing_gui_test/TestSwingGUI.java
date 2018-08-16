package sphiinx.script.testing.swing_gui_test;

import sphiinx.api.framework.ui.swing.components.AutoCompleteComboBox;
import sphiinx.api.framework.ui.swing.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class TestSwingGUI extends GUI {

    private final JFrame FRAME = new JFrame();
    private final JPanel PANEL = new JPanel();
    private final GridBagConstraints CONSTRAINTS = new GridBagConstraints();

    @Override
    public void initialize() {
        ArrayList<String> list = new ArrayList<>();
        list.add("LUMBRIDGE");
        list.add("VARROCK");
        list.add("EDGEVILLE");
        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 1;
        CONSTRAINTS.anchor = GridBagConstraints.CENTER;
        JComboBox COMBO_BOX = new AutoCompleteComboBox(list.toArray(), "Location");
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
        return "TestSwingGUI WalkingGUI";
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

