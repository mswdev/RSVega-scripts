package org.api.game.skills.contruction;

public enum PlankType {

    PLANK("Plank", 960, 100),
    OAK("Oak plank", 8778, 250),
    TEAK("Teak plank", 8780, 500),
    MAHOGANY("Mahogany plank", 8782, 1500);

    private final String name;
    private final int item_id;
    private final int sawmill_cost;

    PlankType(String name, int item_id, int sawmill_cost) {
        this.name = name;
        this.item_id = item_id;
        this.sawmill_cost = sawmill_cost;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return item_id;
    }

    public int getSawmillCost() {
        return sawmill_cost;
    }
}
