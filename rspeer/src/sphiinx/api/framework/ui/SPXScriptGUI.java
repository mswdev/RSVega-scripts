package sphiinx.api.framework.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class SPXScriptGUI {

    public abstract void initialize();

    public abstract JFrame getFrame();

    public abstract JPanel getPanel();

    public abstract GridBagConstraints getConstraints();

    public abstract String getName();

    public abstract Image getLogo();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract boolean isResizable();

    public abstract ActionListener onStart();

}

