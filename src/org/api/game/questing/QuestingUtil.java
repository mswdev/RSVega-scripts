package org.api.game.questing;

import org.rspeer.runetek.api.Varps;

public class QuestingUtil {

    /**
     * Gets the total quest points.
     *
     * @return The total quest points.
     * */
    public static int getPoints() {
        return Varps.get(101);
    }

}

