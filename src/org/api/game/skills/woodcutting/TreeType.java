package org.api.game.skills.woodcutting;

import org.api.game.skills.firemaking.LogType;

public enum TreeType {

    TREE("Tree", 1, false, true, LogType.LOGS),
    ACHEY("Achey", 1, true, false, LogType.ACHEY),
    OAK("Oak", 15, false, true, LogType.OAK),
    WILLOW("Willow", 30, false, true, LogType.WILLOW),
    TEAK("Teak", 35, true, false, LogType.TEAK),
    MAPLE("Maple", 45, false, true, LogType.MAPLE),
    MAHOGANY("Mahogany", 50, true, false, LogType.MAHOGANY),
    ARCTIC_PINE("Arctic pine", 54, true, false, LogType.ARCTIC_PINE),
    YEW("Yew", 60, false, false, LogType.YEW),
    MAGIC("Magic", 75, true, false, LogType.MAGIC),
    REDWOOD("Redwood", 90, true, false, LogType.REDWOOD);

    private final String name;
    private final int required_woodcutting_level;
    private final boolean members;
    private final boolean progressive;
    private final LogType log_type;

    TreeType(String name, int required_woodcutting_level, boolean members, boolean progressive, LogType log_type) {
        this.name = name;
        this.required_woodcutting_level = required_woodcutting_level;
        this.members = members;
        this.progressive = progressive;
        this.log_type = log_type;
    }

    public String getName() {
        return name;
    }

    public int getRequiredWoodcuttingLevel() {
        return required_woodcutting_level;
    }

    public boolean isMembers() {
        return members;
    }

    public boolean isProgressive() {
        return progressive;
    }

    public LogType getLogType() {
        return log_type;
    }
}

