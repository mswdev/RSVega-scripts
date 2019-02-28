package org.api.ui;

import java.awt.*;

public enum SPXStyle {

    SPX_RED(new Color(168, 34, 25)),
    SPX_GRAY(new Color(26, 24, 24)),
    SPX_WHITE(new Color(251, 249, 250));

    private static final String LOGO_FONT_STYLE = "https://fonts.googleapis.com/css?family=Fjalla+One";
    private final Color color;

    SPXStyle(Color color) {
        this.color = color;
    }

    public static String getLogoFontStyle() {
        return LOGO_FONT_STYLE;
    }

    /**
     * Formats a number by replacing zeros with 'k' or 'm' depending on the value of the number.
     *
     * @param number The number to format.
     * @return The formatted number.
     */
    public static String formatNumber(long number) {
        if (number < 1000) {
            return Long.toString(number);
        } else if (Math.round(number) / 10000 < 100) {
            return Math.round(number) / 1000 + "k";
        }

        return Math.round(number) / 1000000 + "m";
    }

    public Color getColor() {
        return color;
    }

}

