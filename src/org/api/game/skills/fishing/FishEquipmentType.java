package org.api.game.skills.fishing;

public enum FishEquipmentType {

    SMALL_FISHING_NET("Small fishing net", 303, 1, false),
    FISHING_ROD("Fishing rod", 307, 5, false),
    FISHING_BAIT("Fishing bait", 313, 5, false),
    BIG_FISHING_NET("Big fishing net", 305, 16, true),
    FLY_FISHING_ROD("Fly fishing rod", 309, 20, false),
    FEATHER("Feather", 314, 20, false),
    HARPOON("Harpoon", 311, 35, false),
    LOBSTER_POT("Lobster pot", 301, 40, false),
    BARBARIAN_ROD("Barbarian rod", 11323, 48, true),
    DRAGON_HARPOON("Dragon harpoon", 21028, 61, true),
    KARAMBWAN_VESSEL("Karambwan vessel", 3157, 65, true),
    INFERNAL_HARPOON("Infernal harpoon", 21031, 75, true),
    SANDWORMS("Sandworms", 13431, 82, true),
    DARK_FISHING_BAIT("Dark fishing bait", 11940, 85, true);

    private final String name;
    private final int item_id;
    private final int required_fishing_level;
    private final boolean is_members;

    FishEquipmentType(String name, int item_id, int required_fishing_level, boolean is_members) {
        this.name = name;
        this.item_id = item_id;
        this.required_fishing_level = required_fishing_level;
        this.is_members = is_members;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return item_id;
    }

    public int getRequiredFishingLevel() {
        return required_fishing_level;
    }

    public boolean isMembers() {
        return is_members;
    }
}
