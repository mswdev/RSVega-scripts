package sphiinx.api.framework;

import java.awt.*;

public enum SPXColor {

    SPX_RED(new Color(168,34, 25)),
    SPX_GRAY(new Color(26, 24, 24)),
    SPX_WHITE(new Color(251, 249, 250));

    private final Color COLOR;

    SPXColor(Color color) {
        this.COLOR = color;
    }

    public Color getColor() {
        return COLOR;
    }

}

