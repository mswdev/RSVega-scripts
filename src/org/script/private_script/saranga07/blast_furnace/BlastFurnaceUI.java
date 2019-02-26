package org.script.private_script.saranga07.blast_furnace;

import org.api.ui.swingui.GUI;
import org.api.ui.swingui.components.AutoCompleteComboBox;
import org.api.game.skills.smithing.BarType;
import org.api.script.impl.mission.blast_furnace_mission.BlastFurnaceMission;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class BlastFurnaceUI extends GUI {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final JComboBox combo_box = new AutoCompleteComboBox(BarType.values(), "");

    @Override
    public void initialize() {
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(combo_box, constraints);
        combo_box.setSelectedIndex(4);
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
        return "[P-SPX] Blast Furnace";
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
        return 225;
    }

    @Override
    public boolean isResizable() {
        return false;
    }

    @Override
    public ActionListener onStart() {
        return e -> {
            BlastFurnaceMission.bar_type = (BarType) combo_box.getSelectedItem();
            frame.setVisible(false);
        };
    }
}

