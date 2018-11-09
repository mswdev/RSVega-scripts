package sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.woodcutting;

import sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.firemaking.LogType;

public enum TreeType {

    TREE("Tree", 1, false, LogType.LOGS),
    ACHEY("Achey", 1, true, LogType.ACHEY),
    OAK("Oak", 15, false, LogType.OAK),
    WILLOW("Willow", 30, false, LogType.WILLOW),
    TEAK("Teak", 35, true, LogType.TEAK),
    MAPLE("Maple", 45, false, LogType.MAPLE),
    MAHOGANY("Mahogany", 50, true, LogType.MAHOGANY),
    ARCTIC_PINE("Arctic pine", 54, true, LogType.ARCTIC_PINE),
    YEW("Yew", 60, false, LogType.YEW),
    MAGIC("Magic", 75, true, LogType.MAGIC),
    REDWOOD("Redwood", 90, true, LogType.REDWOOD);

    private final String name;
    private final int required_woodcutting_level;
    private final boolean is_members;
    private final LogType log_type;

    TreeType(String name, int required_woodcutting_level, boolean is_members, LogType log_type) {
        this.name = name;
        this.required_woodcutting_level = required_woodcutting_level;
        this.is_members = is_members;
        this.log_type = log_type;
    }

    public String getName() {
        return name;
    }

    public int getRequiredWoodcuttingLevel() {
        return required_woodcutting_level;
    }

    public boolean isMembers() {
        return is_members;
    }

    public LogType getLogType() {
        return log_type;
    }
}

