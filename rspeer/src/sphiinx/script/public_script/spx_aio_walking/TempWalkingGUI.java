package sphiinx.script.public_script.spx_aio_walking;

import sphiinx.api.ui.swingui.GUI;
import sphiinx.api.ui.swingui.components.AutoCompleteComboBox;
import sphiinx.script.public_script.spx_aio_walking.data.Location;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class TempWalkingGUI extends GUI {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private AutoCompleteComboBox combo_box;

    @Override
    public void initialize() {
        ArrayList<Location> list = new ArrayList<>();
        Collections.addAll(list, Location.values());
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        combo_box = new AutoCompleteComboBox(list.toArray(), "Location");
        panel.add(combo_box, constraints);
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
        return "[SPX] AIO Walking";
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
            Main.ARGS.LOCATION = (Location) combo_box.getSelectedItem();
            frame.setVisible(false);
        };
    }
}

