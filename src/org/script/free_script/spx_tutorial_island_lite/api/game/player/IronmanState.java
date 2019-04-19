package org.script.free_script.spx_tutorial_island_lite.api.game.player;

public enum IronmanState {

    NONE(0),
    IRONMAN(1),
    ULTIMATE(2),
    HARDCORE(3);

    private final int state;

    IronmanState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
