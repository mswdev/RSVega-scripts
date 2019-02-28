package org.api.ui.swingui;

import org.api.ui.SPXStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIPanel {

    private final ImageIcon logo = new ImageIcon();
    private final JLabel logo_label = new JLabel();
    private final JButton start_button = new JButton();
    private final JSeparator separator = new JSeparator();
    private final GUI spx_gui;

    GUIPanel(GUI spx_gui) {
        this.spx_gui = spx_gui;
    }

    void initialize() {
        spx_gui.getPanel().setLayout(new GridBagLayout());
        spx_gui.getPanel().setBackground(SPXStyle.SPX_GRAY.getColor());

        logo.setImage(spx_gui.getLogo());

        spx_gui.getConstraints().gridwidth = 2;
        spx_gui.getConstraints().insets = new Insets(10, 0, 15, 0);
        spx_gui.getConstraints().weighty = 1;
        spx_gui.getConstraints().weightx = 1;

        spx_gui.getConstraints().gridx = 0;
        spx_gui.getConstraints().gridy = 0;
        spx_gui.getConstraints().anchor = GridBagConstraints.NORTH;
        logo_label.setIcon(logo);
        spx_gui.getPanel().add(logo_label, spx_gui.getConstraints());

        spx_gui.getConstraints().gridx = 0;
        spx_gui.getConstraints().gridy = 2;
        spx_gui.getConstraints().anchor = GridBagConstraints.SOUTH;
        start_button.setText("START");
        start_button.setPreferredSize(new Dimension(100, 30));
        start_button.setBackground(SPXStyle.SPX_RED.getColor());
        start_button.setForeground(SPXStyle.SPX_WHITE.getColor());
        start_button.setOpaque(true);
        start_button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        start_button.setFocusPainted(false);
        start_button.addActionListener(setStartButtonActionListener());
        start_button.addMouseListener(setStartButtonMouseListener());
        spx_gui.getPanel().add(start_button, spx_gui.getConstraints());

        spx_gui.initialize();

        spx_gui.getConstraints().insets = new Insets(80, 15, 0, 15);
        spx_gui.getConstraints().gridx = 0;
        spx_gui.getConstraints().gridy = 0;
        spx_gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        spx_gui.getConstraints().anchor = GridBagConstraints.NORTH;
        spx_gui.getPanel().add(separator, spx_gui.getConstraints());
    }

    private ActionListener setStartButtonActionListener() {
        return spx_gui.onStart();
    }

    private MouseListener setStartButtonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                start_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        };
    }
}

