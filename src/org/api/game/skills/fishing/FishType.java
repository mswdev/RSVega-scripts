package org.api.game.skills.fishing;

import java.util.Arrays;

public enum FishType {

    SHRIMP("Raw shrimps", 317, 1, "Net", false, true, new FishEquipmentType[]{
            FishEquipmentType.SMALL_FISHING_NET
    }, FishLocation.LUMBRIDGE_SWAMP, FishLocation.DRAYNOR_VILLAGE, FishLocation.MUSA_POINT, FishLocation.MUD_SKIPPER_POINT_1, FishLocation.MUD_SKIPPER_POINT_2, FishLocation.WILDERNESS_BANDIT_CAMP, FishLocation.AL_KHARID, FishLocation.CORSAIR_COVE, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.BARBARIAN_OUTPOST_1, FishLocation.BARBARIAN_OUTPOST_2, FishLocation.BARBARIAN_OUTPOST_3, FishLocation.FISHING_PLATFORM),
    SARDINE("Raw sardine", 327, 5, "Bait", false, false, new FishEquipmentType[]{
            FishEquipmentType.FISHING_BAIT,
            FishEquipmentType.FISHING_ROD
    }, FishLocation.LUMBRIDGE_SWAMP, FishLocation.DRAYNOR_VILLAGE, FishLocation.MUSA_POINT, FishLocation.MUD_SKIPPER_POINT_1, FishLocation.MUD_SKIPPER_POINT_2, FishLocation.WILDERNESS_BANDIT_CAMP, FishLocation.AL_KHARID, FishLocation.CORSAIR_COVE, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.BARBARIAN_OUTPOST_1, FishLocation.BARBARIAN_OUTPOST_2, FishLocation.BARBARIAN_OUTPOST_3, FishLocation.FISHING_PLATFORM),
    KARAMBWANJI("Raw karambwanji", 3150, 5, "Net", true, false, new FishEquipmentType[]{
            FishEquipmentType.SMALL_FISHING_NET,
    }, FishLocation.BRIMHAVEN, FishLocation.KARAMJA),
    HERRING("Raw herring", 345, 10, "Bait", false, false, new FishEquipmentType[]{
            FishEquipmentType.FISHING_BAIT,
            FishEquipmentType.FISHING_ROD
    }, FishLocation.LUMBRIDGE_SWAMP, FishLocation.DRAYNOR_VILLAGE, FishLocation.MUSA_POINT, FishLocation.MUD_SKIPPER_POINT_1, FishLocation.MUD_SKIPPER_POINT_2, FishLocation.WILDERNESS_BANDIT_CAMP, FishLocation.AL_KHARID, FishLocation.CORSAIR_COVE, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.BARBARIAN_OUTPOST_1, FishLocation.BARBARIAN_OUTPOST_2, FishLocation.BARBARIAN_OUTPOST_3, FishLocation.FISHING_PLATFORM),
    ANCHOVIES("Raw anchovies", 321, 15, "Net", false, true, new FishEquipmentType[]{
            FishEquipmentType.SMALL_FISHING_NET
    }, FishLocation.LUMBRIDGE_SWAMP, FishLocation.DRAYNOR_VILLAGE, FishLocation.MUSA_POINT, FishLocation.MUD_SKIPPER_POINT_1, FishLocation.MUD_SKIPPER_POINT_2, FishLocation.WILDERNESS_BANDIT_CAMP, FishLocation.AL_KHARID, FishLocation.CORSAIR_COVE, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.BARBARIAN_OUTPOST_1, FishLocation.BARBARIAN_OUTPOST_2, FishLocation.BARBARIAN_OUTPOST_3, FishLocation.FISHING_PLATFORM),
    MACKEREL("Raw mackerel", 353, 16, "Net", true, false, new FishEquipmentType[]{
            FishEquipmentType.BIG_FISHING_NET
    }, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.FISHING_GUILD_1, FishLocation.FISHING_GUILD_2, FishLocation.BURGH_DE_ROTT, FishLocation.ISAFDAR, FishLocation.JATIZSO, FishLocation.APE_ATOLL, FishLocation.HOSIDIUS_SOUTH),
    TROUT("Raw trout", 335, 20, "Lure", false, true, new FishEquipmentType[]{
            FishEquipmentType.FLY_FISHING_ROD,
            FishEquipmentType.FEATHER,
    }, FishLocation.BARBARIAN_VILLAGE, FishLocation.LUMBRIDGE_RIVER, FishLocation.SHILO_VILLAGE, FishLocation.SEERS_VILLAGE, FishLocation.ENTRANA_RIVER, FishLocation.TREE_GNOME_STRONGHOLD, FishLocation.OBSERVATORY, FishLocation.ISAFDAR, FishLocation.BAXTORIAN_FALLS, FishLocation.HOSIDIUS_NORTH_1, FishLocation.HOSIDIUS_NORTH_2),
    COD("Raw cod", 341, 23, "Net", false, false, new FishEquipmentType[]{
            FishEquipmentType.BIG_FISHING_NET,
    }, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.FISHING_GUILD_1, FishLocation.FISHING_GUILD_2, FishLocation.BURGH_DE_ROTT, FishLocation.ISAFDAR, FishLocation.JATIZSO, FishLocation.APE_ATOLL, FishLocation.HOSIDIUS_SOUTH),
    PIKE("Raw pike", 349, 25, "Bait", false, false, new FishEquipmentType[]{
            FishEquipmentType.FISHING_ROD,
            FishEquipmentType.FISHING_BAIT,
    }, FishLocation.BARBARIAN_VILLAGE, FishLocation.LUMBRIDGE_RIVER, FishLocation.SHILO_VILLAGE, FishLocation.SEERS_VILLAGE, FishLocation.ENTRANA_RIVER, FishLocation.TREE_GNOME_STRONGHOLD, FishLocation.OBSERVATORY, FishLocation.ISAFDAR, FishLocation.BAXTORIAN_FALLS, FishLocation.HOSIDIUS_NORTH_1, FishLocation.HOSIDIUS_NORTH_2),
    SALMON("Raw salmon", 331, 30, "Lure", false, true, new FishEquipmentType[]{
            FishEquipmentType.FLY_FISHING_ROD,
            FishEquipmentType.FEATHER,
    }, FishLocation.BARBARIAN_VILLAGE, FishLocation.LUMBRIDGE_RIVER, FishLocation.SHILO_VILLAGE, FishLocation.SEERS_VILLAGE, FishLocation.ENTRANA_RIVER, FishLocation.TREE_GNOME_STRONGHOLD, FishLocation.OBSERVATORY, FishLocation.ISAFDAR, FishLocation.BAXTORIAN_FALLS, FishLocation.HOSIDIUS_NORTH_1, FishLocation.HOSIDIUS_NORTH_2),
    TUNA("Raw tuna", 359, 35, "Harpoon", false, false, new FishEquipmentType[]{
            FishEquipmentType.HARPOON,
            FishEquipmentType.DRAGON_HARPOON,
            FishEquipmentType.INFERNAL_HARPOON,
    }, FishLocation.MUSA_POINT, FishLocation.CORSAIR_COVE_RESOURCE_AREA, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.FISHING_GUILD_1, FishLocation.FISHING_GUILD_2, FishLocation.JATIZSO, FishLocation.PISCATORIS, FishLocation.WILDERNESS_RESOURCE_AREA, FishLocation.WILDERNESS_EAST_1, FishLocation.WILDERNESS_EAST_2),
    LOBSTER("Raw lobster", 377, 40, "Cage", false, false, new FishEquipmentType[]{
            FishEquipmentType.LOBSTER_POT
    }, FishLocation.MUSA_POINT, FishLocation.CORSAIR_COVE_RESOURCE_AREA, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.FISHING_GUILD_1, FishLocation.FISHING_GUILD_2, FishLocation.JATIZSO, FishLocation.WILDERNESS_RESOURCE_AREA, FishLocation.WILDERNESS_EAST_1, FishLocation.WILDERNESS_EAST_2),
    BASS("Raw bass", 363, 46, "Net", true, false, new FishEquipmentType[]{
            FishEquipmentType.BIG_FISHING_NET
    }, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.FISHING_GUILD_1, FishLocation.FISHING_GUILD_2, FishLocation.BURGH_DE_ROTT, FishLocation.ISAFDAR, FishLocation.JATIZSO, FishLocation.APE_ATOLL, FishLocation.HOSIDIUS_SOUTH),
    SWORDFISH("Raw swordfish", 371, 50, "Harpoon", false, false, new FishEquipmentType[]{
            FishEquipmentType.HARPOON,
            FishEquipmentType.DRAGON_HARPOON,
            FishEquipmentType.INFERNAL_HARPOON,
    }, FishLocation.MUSA_POINT, FishLocation.CORSAIR_COVE_RESOURCE_AREA, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.FISHING_GUILD_1, FishLocation.FISHING_GUILD_2, FishLocation.JATIZSO, FishLocation.PISCATORIS, FishLocation.WILDERNESS_RESOURCE_AREA, FishLocation.WILDERNESS_EAST_1, FishLocation.WILDERNESS_EAST_2),
    MONKFISH("Raw monkfish", 7944, 62, "Net", true, false, new FishEquipmentType[]{
            FishEquipmentType.SMALL_FISHING_NET,
    }, FishLocation.PISCATORIS),
    KARAMBWAN("Raw karambwan", 3142, 65, "Fish", true, false, new FishEquipmentType[]{
            FishEquipmentType.KARAMBWAN_VESSEL,
    }, FishLocation.SHIP_YARD),
    SHARK("Raw shark", 383, 76, "Harpoon", true, false, new FishEquipmentType[]{
            FishEquipmentType.HARPOON,
            FishEquipmentType.DRAGON_HARPOON,
            FishEquipmentType.INFERNAL_HARPOON,
    }, FishLocation.CATHERBY_1, FishLocation.CATHERBY_2, FishLocation.FISHING_GUILD_1, FishLocation.FISHING_GUILD_2, FishLocation.BURGH_DE_ROTT, FishLocation.ISAFDAR, FishLocation.JATIZSO, FishLocation.APE_ATOLL, FishLocation.HOSIDIUS_SOUTH),
    SEA_TURTLE("Raw sea turtle", 395, 79, null, true, false, null, FishLocation.TRAWLER),
    MANTA_RAY("Raw manta ray", 389, 81, null, true, false, null, FishLocation.TRAWLER),
    ANGLERFISH("Raw anglerfish", 13439, 82, "Bait", true, false, new FishEquipmentType[]{
            FishEquipmentType.FISHING_ROD,
            FishEquipmentType.SANDWORMS,

    }, FishLocation.BARBARIAN_VILLAGE, FishLocation.LUMBRIDGE_RIVER, FishLocation.SHILO_VILLAGE, FishLocation.SEERS_VILLAGE, FishLocation.ENTRANA_RIVER, FishLocation.TREE_GNOME_STRONGHOLD, FishLocation.OBSERVATORY, FishLocation.ISAFDAR, FishLocation.BAXTORIAN_FALLS, FishLocation.HOSIDIUS_NORTH_1, FishLocation.HOSIDIUS_NORTH_2),
    DARK_CRAB("Raw dark crab", 11934, 85, "Bait", true, false, new FishEquipmentType[]{
            FishEquipmentType.LOBSTER_POT,
            FishEquipmentType.DARK_FISHING_BAIT,

    }, FishLocation.WILDERNESS_RESOURCE_AREA, FishLocation.WILDERNESS_EAST_1, FishLocation.WILDERNESS_EAST_2);

    private final String name;
    private final int item_id;
    private final int required_fishing_level;
    private String action;
    private boolean members;
    private boolean progressive;
    private FishEquipmentType[] required_equipment;
    private FishLocation[] fish_location;

    FishType(String name, int item_id, int required_fishing_level, String action, boolean members, boolean progressive, FishEquipmentType[] required_equipment, FishLocation... fish_location) {
        this.name = name;
        this.item_id = item_id;
        this.required_fishing_level = required_fishing_level;
        this.action = action;
        this.members = members;
        this.progressive = progressive;
        this.required_equipment = required_equipment;
        this.fish_location = fish_location;
    }

    /**
     * Gets all of the fish item ids in the enum.
     *
     * @return An array containing all of the fish item ids in the enum.
     */
    public static int[] getItemIDs() {
        return Arrays.stream(FishType.values()).mapToInt(FishType::getItemID).toArray();
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

    public String getAction() {
        return action;
    }

    public boolean isMembers() {
        return members;
    }

    public FishEquipmentType[] getRequiredEquipment() {
        return required_equipment;
    }

    public boolean isProgressive() {
        return progressive;
    }

    public int[] getRequiredEquipmentIDs() {
        return Arrays.stream(required_equipment).mapToInt(FishEquipmentType::getItemID).toArray();
    }

    public FishLocation[] getFishLocation() {
        return fish_location;
    }
}

