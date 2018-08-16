package sphiinx.api.game;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.component.Interfaces;

public class Wilderness {

    private static final int INTER_MASTER_ENTER_WILDERNESS_WARNING = 475;
    private static final int INTER_COMP_ENTER_WILDERNESS = 11;
    private static final int INTER_COMP_ENTER_WILDERNESS_REMEMBER = 13;

    private static final int INTER_MASTER_WILDERNESS_LEVEL = 90;
    private static final int INTER_COMP_WILDERNESS_LEVEL = 46;

    /**
     * Gets the wilderness level.
     *
     * @return The wilderness level.
     */
    public static int getWildernessLevel() {
        final InterfaceComponent LEVEL = Interfaces.getComponent(INTER_MASTER_WILDERNESS_LEVEL, INTER_COMP_WILDERNESS_LEVEL);
        if (LEVEL == null)
            return -1;

        return Integer.parseInt(LEVEL.getText().replace("Level: ", ""));
    }

    /**
     * Checks whether the enter wilderness warning interface is present.
     *
     * @return True if the enter wilderness warning interface is present; false otherwise.
     */
    public static boolean hasWildernessWarning() {
        final InterfaceComponent ENTER_WILDERNESS = Interfaces.getComponent(INTER_MASTER_ENTER_WILDERNESS_WARNING, INTER_COMP_ENTER_WILDERNESS);
        return ENTER_WILDERNESS != null;
    }

    /**
     * Clicks the enter wilderness button on the enter wilderness warning interface and checks the remember option if
     * present.
     *
     * @return True if the enter wilderness button was clicked; false otherwise.
     */
    public static boolean enterWilderness() {
        final InterfaceComponent ENTER_WILDERNESS = Interfaces.getComponent(INTER_MASTER_ENTER_WILDERNESS_WARNING, INTER_COMP_ENTER_WILDERNESS);
        if (ENTER_WILDERNESS == null)
            return false;

        final InterfaceComponent ENTER_WILDERNESS_REMEMBER = Interfaces.getComponent(INTER_MASTER_ENTER_WILDERNESS_WARNING, INTER_COMP_ENTER_WILDERNESS_REMEMBER);
        if (ENTER_WILDERNESS_REMEMBER != null)
            ENTER_WILDERNESS_REMEMBER.click();

        return ENTER_WILDERNESS.click();
    }
}

