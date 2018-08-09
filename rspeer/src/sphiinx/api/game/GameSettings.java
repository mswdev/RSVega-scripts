package sphiinx.api.game;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.input.Mouse;

import java.awt.*;
import java.util.function.Predicate;

public class GameSettings {

    private static final int INTER_OPTION_MASTER = 261;
    private static final int INTER_ZOOM = 14;
    private static final Predicate<InterfaceComponent> INTER_ZOOM_LEVELS = a -> a.getIndex() >= 10 && a.getIndex() <= 13;

    /**
     * Gets the set zoom level.
     *
     * @return The set zoom level between 1-4.
     */
    public static int getZoomLevel() {
        final InterfaceComponent ZOOM_OPTION = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_ZOOM);
        if (ZOOM_OPTION == null)
            return -1;

        final InterfaceComponent[] ZOOM_LVLS = Interfaces.get(INTER_OPTION_MASTER, INTER_ZOOM_LEVELS);
        if (ZOOM_LVLS.length < 4)
            return -1;

        for (InterfaceComponent zoom_lvl : ZOOM_LVLS) {
            if (!zoom_lvl.getBounds().contains(ZOOM_OPTION.getBounds()))
                continue;

            return 14 - zoom_lvl.getIndex();
        }

        return -1;
    }

    /**
     * Sets the zoom level to the specified level.
     *
     * @param level The zoom level to set; must be 1-4.
     * @return True if the zoom level was set; false otherwise.
     */
    public static boolean setZoomLevel(int level) {
        if (Tabs.isOpen(Tab.OPTIONS)) {
            final InterfaceComponent ZOOM_OPTION = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_ZOOM);
            if (ZOOM_OPTION == null)
                return false;

            final InterfaceComponent ZOOM_LVL = Interfaces.getComponent(INTER_OPTION_MASTER, 14 - level);
            if (ZOOM_LVL == null)
                return false;

            final Point POINT = Random.nextPoint(ZOOM_LVL.getBounds());
            Mouse.click(true, POINT.x, POINT.y);

            return true;
        } else {
            if (Tabs.open(Tab.OPTIONS))
                Time.sleepUntil(() -> Tabs.isOpen(Tab.OPTIONS), 1500);
        }

        return false;
    }

}

