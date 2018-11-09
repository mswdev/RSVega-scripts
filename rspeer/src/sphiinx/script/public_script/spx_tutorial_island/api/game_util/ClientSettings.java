package sphiinx.script.public_script.spx_tutorial_island.api.game_util;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.Mouse;

import java.awt.*;
import java.util.function.Predicate;

public class ClientSettings {

    private static final int INTER_OPTION_MASTER = 261;

    private static final int INTER_ADVANCED_MASTER = 60;
    private static final int INTER_ADVANCED_BUTTON = 35;
    private static final int INTER_TOGGLE_ROOFS = 14;

    private static final int INTER_ZOOM = 15;
    private static final int INTER_BRIGHTNESS = 17;
    private static final int INTER_MUSIC_AUDIO = 44;
    private static final int INTER_EFFECT_AUDIO = 50;
    private static final int INTER_AREA_AUDIO = 56;
    private static final Predicate<InterfaceComponent> INTER_ZOOM_LEVELS = a -> a.getIndex() >= 9 && a.getIndex() <= 13;

    /**
     * Gets the zoom level.
     *
     * @return The zoom level between 1-5.
     */
    public static int getZoomLevel() {
        final InterfaceComponent zoom_option = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_ZOOM);
        if (zoom_option == null)
            return -1;

        final InterfaceComponent[] zoom_indexes = Interfaces.get(INTER_OPTION_MASTER, INTER_ZOOM_LEVELS);
        if (zoom_indexes.length < 5)
            return -1;

        for (InterfaceComponent zoom_lvl : zoom_indexes) {
            if (!zoom_lvl.getBounds().contains(zoom_option.getBounds()))
                continue;

            return zoom_lvl.getIndex() - 8;
        }

        return -1;
    }

    /**
     * Sets the zoom level.
     *
     * @param level The zoom level to set; must be 1-5.
     * @return True if the zoom level was set; false otherwise.
     */
    public static boolean setZoomLevel(int level) {
        final InterfaceComponent zoom_indexes = Interfaces.getComponent(INTER_OPTION_MASTER, level + 8);
        if (zoom_indexes == null)
            return false;

        final Point POINT = Random.nextPoint(zoom_indexes.getBounds());
        Mouse.click(true, POINT.x, POINT.y);

        return true;
    }

    /**
     * Sets the brightness level.
     *
     * @param level The brightness level to set; must be 1-4.
     * @return True if the brightness level was set; false otherwise.
     */
    public static boolean setBrightnessLevel(int level) {
        final InterfaceComponent brightness_index = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_BRIGHTNESS + level);
        if (brightness_index == null)
            return false;

        return brightness_index.click();
    }

    /**
     * Sets the music audio level.
     *
     * @param level The music audio level to set; must be 1-5.
     * @return True if the music audio level was set; false otherwise.
     */
    public static boolean setMusicAudioLevel(int level) {
        final InterfaceComponent music_audio_index = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_MUSIC_AUDIO + level);
        if (music_audio_index == null)
            return false;

        return music_audio_index.click();
    }

    /**
     * Sets the effect audio level.
     *
     * @param level The effect audio level to set; must be 1-5.
     * @return True if the effect audio level was set; false otherwise.
     */
    public static boolean setEffectAudioLevel(int level) {
        final InterfaceComponent effect_audio_index = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_EFFECT_AUDIO + level);
        if (effect_audio_index == null)
            return false;

        return effect_audio_index.click();
    }

    /**
     * Sets the area audio level.
     *
     * @param level The area audio level to set; must be 1-5.
     * @return True if the area audio level was set; false otherwise.
     */
    public static boolean setAreaAudioLevel(int level) {
        final InterfaceComponent area_audio_index = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_AREA_AUDIO + level);
        if (area_audio_index == null)
            return false;

        return area_audio_index.click();
    }

    /**
     * Toggles roof visibility on or off.
     *
     * @return True if the roof visibility was successfully toggled.
     */
    public static boolean toggleRoofs() {
        final InterfaceComponent toggle_roofs = Interfaces.getComponent(INTER_ADVANCED_MASTER, INTER_TOGGLE_ROOFS);
        if (toggle_roofs != null) {
            return toggle_roofs.click();
        } else {
            final InterfaceComponent advanced_button = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_ADVANCED_BUTTON);
            if (advanced_button != null)
                advanced_button.click();
        }

        return false;
    }
}

