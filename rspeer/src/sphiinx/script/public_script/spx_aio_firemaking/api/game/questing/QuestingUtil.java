package sphiinx.script.public_script.spx_aio_firemaking.api.game.questing;

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

