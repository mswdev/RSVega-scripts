package sphiinx.script.testing.swing_gui_test;

import sphiinx.api.ui.swingui.components.AutoCompleteComboBox;
import sphiinx.api.ui.swingui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class TestSwingGUI extends GUI {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final GridBagConstraints constraints = new GridBagConstraints();

    @Override
    public void initialize() {
        ArrayList<String> list = new ArrayList<>();
        list.add("LUMBRIDGE");
        list.add("VARROCK");
        list.add("EDGEVILLE");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        JComboBox COMBO_BOX = new AutoCompleteComboBox(list.toArray(), "Location");
        panel.add(COMBO_BOX, constraints);
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public GridBagConstraints getConstraints() {
        return constraints;
    }

    @Override
    public String getName() {
        return "TestSwingGUI";
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
        return e -> frame.setVisible(false);
    }

}

