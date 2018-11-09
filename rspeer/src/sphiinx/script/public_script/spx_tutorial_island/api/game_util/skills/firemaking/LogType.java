package sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.firemaking;

public enum LogType {

    LOGS("Logs", 1511, 1, false),
    ACHEY("Achey logs", 2862, 1, true),
    OAK("Oak logs", 1521, 5, false),
    WILLOW("Willow logs", 1519, 30, false),
    TEAK("Teak logs", 6333, 35, true),
    ARCTIC_PINE("Arctic pine logs", 10810, 42, true),
    MAPLE("Maple logs", 1517, 45, false),
    MAHOGANY("Mahogany logs", 6332, 50, true),
    YEW("Yew logs", 1515, 60, false),
    MAGIC("Magic logs", 1513, 75, true),
    REDWOOD("Redwood logs", 19669, 90, true);

    private final String name;
    private final int item_id;
    private final int required_firemaking_level;
    private final boolean is_members;

    LogType(String name, int item_id, int required_firemaking_level, boolean is_members) {
        this.name = name;
        this.item_id = item_id;
        this.required_firemaking_level = required_firemaking_level;
        this.is_members = is_members;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return item_id;
    }

    public int getRequiredFiremakingLevel() {
        return required_firemaking_level;
    }

    public boolean isMembers() {
        return is_members;
    }
}
