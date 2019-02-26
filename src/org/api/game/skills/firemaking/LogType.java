package org.api.game.skills.firemaking;

public enum LogType {

    LOGS("Logs", 1511, 1512, 1, false),
    ACHEY("Achey logs", 2862, 2863, 1, true),
    OAK("Oak logs", 1521, 1522, 15, false),
    WILLOW("Willow logs", 1519, 1520, 30, false),
    TEAK("Teak logs", 6333, 6334, 35, true),
    ARCTIC_PINE("Arctic pine logs", 10810, 10811, 42, true),
    MAPLE("Maple logs", 1517, 1518, 45, false),
    MAHOGANY("Mahogany logs", 6332, 8836, 50, true),
    YEW("Yew logs", 1515, 1516, 60, false),
    MAGIC("Magic logs", 1513, 1514, 75, true),
    REDWOOD("Redwood logs", 19669, 19670, 90, true);

    private final String name;
    private final int item_id;
    private final int noted_item_id;
    private final int required_firemaking_level;
    private final boolean members;

    LogType(String name, int item_id, int noted_item_id, int required_firemaking_level, boolean members) {
        this.name = name;
        this.item_id = item_id;
        this.noted_item_id = noted_item_id;
        this.required_firemaking_level = required_firemaking_level;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return item_id;
    }

    public int getNotedItemID() {
        return noted_item_id;
    }

    public int getRequiredFiremakingLevel() {
        return required_firemaking_level;
    }

    public boolean isMembers() {
        return members;
    }
}
