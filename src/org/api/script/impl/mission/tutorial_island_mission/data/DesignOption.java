package org.api.script.impl.mission.tutorial_island_mission.data;

public enum DesignOption {

    HEAD(106, 113, 24),
    JAW(107, 114, 15),
    TORSO(108, 115, 15),
    ARMS(109, 116, 12),
    HANDS(110, 117, 2),
    LEGS(111, 118, 11),
    FEET(112, 119, 2),
    HAIR(105, 121, 26),
    TORSO_COLOR(123, 127, 30),
    LEGS_COLOR(122, 129, 16),
    FEET_COLOR(124, 130, 7),
    SKIN(125, 131, 9),
    GENDER(136, 137, 2);

    private final int component_index_left;
    private final int component_index_right;

    private final int total_options;

    DesignOption(int component_index_left, int component_index_right, int total_options) {
        this.component_index_left = component_index_left;
        this.component_index_right = component_index_right;
        this.total_options = total_options;
    }

    public int getComponentIndexLeft() {
        return component_index_left;
    }

    public int getComponentIndexRight() {
        return component_index_right;
    }

    public int getTotalOptions() {
        return total_options;
    }
}

