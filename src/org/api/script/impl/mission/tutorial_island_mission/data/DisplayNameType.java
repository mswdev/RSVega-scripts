package org.api.script.impl.mission.tutorial_island_mission.data;

public enum DisplayNameType {

    UNKNOWN(0),
    NOT_AVAILABLE(1),
    SEARCHING(2),
    AVAILABLE(4),
    SET(5);

    public final int varpbit_value;

    DisplayNameType(int varpbit_value) {
        this.varpbit_value = varpbit_value;
    }

    public int getVarpbitValue() {
        return varpbit_value;
    }
}
