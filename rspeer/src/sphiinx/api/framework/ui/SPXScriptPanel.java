package sphiinx.api.framework.ui;

import sphiinx.api.framework.SPXColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SPXScriptPanel {

    private final ImageIcon LOGO = new ImageIcon();
    private final JLabel LOGO_LABEL = new JLabel();
    private final JButton START_BUTTON = new JButton();
    private final JSeparator SEPARATOR = new JSeparator();
    private final SPXScriptGUI SPX_SCRIPT_GUI;

    SPXScriptPanel(SPXScriptGUI spx_script_gui) {
        this.SPX_SCRIPT_GUI = spx_script_gui;
    }

    void initialize() {
        SPX_SCRIPT_GUI.getPanel().setLayout(new GridBagLayout());
        SPX_SCRIPT_GUI.getPanel().setBackground(SPXColor.SPX_GRAY.getColor());

        LOGO.setImage(SPX_SCRIPT_GUI.getLogo());

        SPX_SCRIPT_GUI.getConstraints().gridwidth = 2;
        SPX_SCRIPT_GUI.getConstraints().insets = new Insets(10, 0, 15, 0);
        SPX_SCRIPT_GUI.getConstraints().weighty = 1;
        SPX_SCRIPT_GUI.getConstraints().weightx = 1;

        SPX_SCRIPT_GUI.getConstraints().gridx = 0;
        SPX_SCRIPT_GUI.getConstraints().gridy = 0;
        SPX_SCRIPT_GUI.getConstraints().anchor = GridBagConstraints.NORTH;
        LOGO_LABEL.setIcon(LOGO);
        SPX_SCRIPT_GUI.getPanel().add(LOGO_LABEL, SPX_SCRIPT_GUI.getConstraints());

        SPX_SCRIPT_GUI.getConstraints().gridx = 0;
        SPX_SCRIPT_GUI.getConstraints().gridy = 2;
        SPX_SCRIPT_GUI.getConstraints().anchor = GridBagConstraints.SOUTH;
        START_BUTTON.setText("START");
        START_BUTTON.setPreferredSize(new Dimension(100, 30));
        START_BUTTON.setBackground(SPXColor.SPX_RED.getColor());
        START_BUTTON.setForeground(SPXColor.SPX_WHITE.getColor());
        START_BUTTON.setOpaque(true);
        START_BUTTON.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        START_BUTTON.setFocusPainted(false);
        START_BUTTON.addActionListener(setStartButtonActionListener());
        START_BUTTON.addMouseListener(setStartButtonMouseListener());
        SPX_SCRIPT_GUI.getPanel().add(START_BUTTON, SPX_SCRIPT_GUI.getConstraints());

        SPX_SCRIPT_GUI.initialize();

        SPX_SCRIPT_GUI.getConstraints().insets = new Insets(80, 15, 0, 15);
        SPX_SCRIPT_GUI.getConstraints().gridx = 0;
        SPX_SCRIPT_GUI.getConstraints().gridy = 0;
        SPX_SCRIPT_GUI.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        SPX_SCRIPT_GUI.getConstraints().anchor = GridBagConstraints.NORTH;
        SPX_SCRIPT_GUI.getPanel().add(SEPARATOR, SPX_SCRIPT_GUI.getConstraints());
    }

    private ActionListener setStartButtonActionListener() {
        return SPX_SCRIPT_GUI.onStart();
    }

    private MouseListener setStartButtonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                START_BUTTON.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        };
    }
}

