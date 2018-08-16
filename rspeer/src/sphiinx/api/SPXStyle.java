package sphiinx.api;

import java.awt.*;

public enum SPXStyle {

    SPX_RED(new Color(168,34, 25)),
    SPX_GRAY(new Color(26, 24, 24)),
    SPX_WHITE(new Color(251, 249, 250));

    private static final String LOGO_FONT_STYLE = "https://fonts.googleapis.com/css?family=Fjalla+One";
    private final Color COLOR;

    SPXStyle(Color color) {
        this.COLOR = color;
    }

    public Color getColor() {
        return COLOR;
    }

    public static String getLogoFontStyle() {
        return LOGO_FONT_STYLE;
    }

}

