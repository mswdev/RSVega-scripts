package sphiinx.script.public_script.spx_aio_firemaking.data;

public enum LogType {

    LOGS(1, 1511, "Logs"),
    OAK(15, 1521, "Oak logs"),
    WILLOW(30, 1519, "Willow logs"),
    TEAK(35, 6333, "Teak logs"),
    ARCTIC_PINE(42, 10810, "Arctic pine logs"),
    MAPLE(45, 1517, "Maple logs"),
    MAHOGANY(50, 6332, "Mahogany logs"),
    YEW(60, 1515, "Yew logs"),
    MAGIC(75, 1513, "Magic logs"),
    REDWOOD(90, 19669, "Redwood logs");

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
