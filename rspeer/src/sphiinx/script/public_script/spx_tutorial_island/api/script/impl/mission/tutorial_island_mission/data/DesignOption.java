package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.data;

public enum DesignOption {

    HEAD(106, 113),
    JAW(107, 114),
    TORSO(108, 115),
    ARMS(109, 116),
    HANDS(110, 117),
    LEGS(111, 118),
    FEET(112, 119),
    HAIR(105, 121),
    TORSO_COLOR(123, 127),
    LEGS_COLOR(122, 129),
    FEET_COLOR(124, 130),
    SKIN(125, 131),
    GENDER(136, 137);

    private final int LEFT;
    private final int RIGHT;

    DesignOption(int left, int right) {
        LEFT = left;
        RIGHT = right;
    }

    public int getLeft() {
        return LEFT;
    }

    public int getRight() {
        return RIGHT;
    }

}

