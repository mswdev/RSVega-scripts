package sphiinx.script.public_script.spx_tutorial_island.api.game.skills.fishing;

import java.util.Arrays;

public enum FishType {

    SHRIMP("Raw shrimps", 317, 1, "Net", false, true, new FishEquipmentType[]{
            FishEquipmentType.SMALL_FISHING_NET
    }),
    SARDINE("Raw sardine", 327, 5, "Bait", false, false, new FishEquipmentType[]{
            FishEquipmentType.FISHING_BAIT,
            FishEquipmentType.FISHING_ROD
    }),
    KARAMBWANJI("Raw karambwanji", 3150, 5, "Net", true, false, new FishEquipmentType[]{
            FishEquipmentType.SMALL_FISHING_NET,
    }),
    HERRING("Raw herring", 345, 10, "Bait", false, false, new FishEquipmentType[]{
            FishEquipmentType.FISHING_BAIT,
            FishEquipmentType.FISHING_ROD
    }),
    ANCHOVIES("Raw anchovies", 321, 15, "Net", false, true, new FishEquipmentType[]{
            FishEquipmentType.SMALL_FISHING_NET
    }),
    MACKEREL("Raw mackerel", 353, 16, "Net", true, false, new FishEquipmentType[]{
            FishEquipmentType.BIG_FISHING_NET
    }),
    TROUT("Raw trout", 335, 20, "Lure", false, true, new FishEquipmentType[]{
            FishEquipmentType.FLY_FISHING_ROD,
            FishEquipmentType.FEATHER,
    }),
    COD("Raw cod", 341, 23, "Net", false, false, new FishEquipmentType[]{
            FishEquipmentType.BIG_FISHING_NET,
    }),
    PIKE("Raw pike", 349, 25, "Bait", false, false, new FishEquipmentType[]{
            FishEquipmentType.FISHING_ROD,
            FishEquipmentType.FISHING_BAIT,
    }),
    SALMON("Raw salmon", 331, 30, "Lure", false, true, new FishEquipmentType[]{
            FishEquipmentType.FLY_FISHING_ROD,
            FishEquipmentType.FEATHER,
    }),
    TUNA("Raw tuna", 359, 35, "Harpoon", false, false, new FishEquipmentType[]{
            FishEquipmentType.HARPOON,
            FishEquipmentType.DRAGON_HARPOON,
            FishEquipmentType.INFERNAL_HARPOON,
    }),
    LOBSTER("Raw lobster", 377, 40, "Cage", false, false, new FishEquipmentType[]{
            FishEquipmentType.LOBSTER_POT
    }),
    BASS("Raw bass", 363, 46, "Net", true, false, new FishEquipmentType[]{
            FishEquipmentType.BIG_FISHING_NET
    }),
    SWORDFISH("Raw swordfish", 371, 50, "Harpoon", false, false, new FishEquipmentType[]{
            FishEquipmentType.HARPOON,
            FishEquipmentType.DRAGON_HARPOON,
            FishEquipmentType.INFERNAL_HARPOON,
    }),
    MONKFISH("Raw monkfish", 7944, 62, "Net", true, false, new FishEquipmentType[]{
            FishEquipmentType.SMALL_FISHING_NET,
    }),
    KARAMBWAN("Raw karambwan", 3142, 65, "Fish", true, false, new FishEquipmentType[]{
            FishEquipmentType.KARAMBWAN_VESSEL,
    }),
    SHARK("Raw shark", 383, 76, "Harpoon", true, false, new FishEquipmentType[]{
            FishEquipmentType.HARPOON,
            FishEquipmentType.DRAGON_HARPOON,
            FishEquipmentType.INFERNAL_HARPOON,
    }),
    SEA_TURTLE("Raw sea turtle", 395, 79, null, true, false, null),
    MANTA_RAY("Raw manta ray", 389, 81, null, true, false, null),
    ANGLERFISH("Raw anglerfish", 13439, 82, "Bait", true, false, new FishEquipmentType[]{
            FishEquipmentType.FISHING_ROD,
            FishEquipmentType.SANDWORMS,

    }),
    DARK_CRAB("Raw dark crab", 11934, 85, "Bait", true, false, new FishEquipmentType[]{
            FishEquipmentType.LOBSTER_POT,
            FishEquipmentType.DARK_FISHING_BAIT,

    });

    private final String name;
    private final int item_id;
    private final int required_fishing_level;
    private String action;
    private boolean members;
    private boolean progressive;
    private FishEquipmentType[] required_equipment;

    FishType(String name, int item_id, int required_fishing_level, String action, boolean members, boolean progressive, FishEquipmentType[] required_equipment) {
        this.name = name;
        this.item_id = item_id;
        this.required_fishing_level = required_fishing_level;
        this.action = action;
        this.members = members;
        this.progressive = progressive;
        this.required_equipment = required_equipment;
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
}

