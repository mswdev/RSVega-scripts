package org.script.free_script.spx_aio_firemaking.api.game.player;

import org.rspeer.runetek.api.Varps;

public class Player {

    private static final int TUTORIAL_ISLAND_VARP = 281;
    private static final int TUTORIAL_ISLAND_FINISHED = 1000;
    private static final int IRONMAN_VARP = 1777;

    /**
     * Determines whether the player has completed tutorial island.
     *
     * @return True if the player has completed tutorial island; false otherwise.
     */
    public static int isTutorial() {
        return Varps.get(TUTORIAL_ISLAND_VARP) < TUTORIAL_ISLAND_FINISHED ? 1 : 0;
    }

    /**
     * Gets the players ironman state.
     *
     * @return The players ironman state.
     */
    public static IronmanState getIronManState() {
        final int ironman_state = Varps.getBitValue(IRONMAN_VARP);
        if (ironman_state == IronmanState.NONE.getState())
            return IronmanState.NONE;

        if (ironman_state == IronmanState.IRONMAN.getState())
            return IronmanState.IRONMAN;

        if (ironman_state == IronmanState.ULTIMATE.getState())
            return IronmanState.ULTIMATE;

        if (ironman_state == IronmanState.HARDCORE.getState())
            return IronmanState.HARDCORE;

        return IronmanState.NONE;
    }

}
