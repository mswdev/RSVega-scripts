package sphiinx.script.public_script.spx_aio_firemaking.data;

public enum LogType {

    LOGS(1, 1511, "Logs"),
    OAK(15, -1, "Oak logs"),
    WILLOW(30, -1, "Willow logs"),
    MAPLE(45, -1, "Maple logs"),
    YEW(60, -1, "Yew logs"),
    MAGIC(75, -1, "Magic logs"),
    REDWOOD(90, -1, "Redwood logs");

    private final int REQUIRED_LEVEL;
    private final int ITEM_ID;
    private final String NAME;

    LogType(int required_level, int item_id, String name) {
        this.REQUIRED_LEVEL = required_level;
        this.ITEM_ID = item_id;
        this.NAME = name;
    }

    public int getRequiredLevel() {
        return REQUIRED_LEVEL;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    public String getName() {
        return NAME;
    }

}
