package sphiinx.script.public_script.spx_tutorial_island.api.game_util.questing;

import org.rspeer.runetek.api.Varps;

public class QuestingUtil {

    /**
     * Gets the total quest points of the player.
     *
     * @return The total quest points of the player.
     * */
    public static int getQuestPoints() {
        return Varps.get(101);
    }

}

