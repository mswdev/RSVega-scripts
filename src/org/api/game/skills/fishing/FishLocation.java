package org.api.game.skills.fishing;


import org.rspeer.runetek.api.movement.position.Position;

public enum FishLocation {

    //Net (small)/Bait
    LUMBRIDGE_SWAMP(new Position(3243, 3152, 0), false),
    DRAYNOR_VILLAGE(new Position(3087, 3228, 0), false),
    MUD_SKIPPER_POINT_1(new Position(2988, 3176, 0), false),
    MUD_SKIPPER_POINT_2(new Position(2988, 3158, 0), false),
    WILDERNESS_BANDIT_CAMP(new Position(3008, 3705, 0), false),
    AL_KHARID(new Position(3272, 3144, 0), false),
    CORSAIR_COVE(new Position(2512, 2840), false),
    ENTRANA_DOCK(new Position(2877, 3336, 0), true),
    ENTRANA_RIVER(new Position(2844, 3362, 0), true),
    BARBARIAN_OUTPOST_1(new Position(2518, 3573, 0), true),
    BARBARIAN_OUTPOST_2(new Position(2512, 3560, 0), true),
    BARBARIAN_OUTPOST_3(new Position(2499, 3545, 0), true),
    FISHING_PLATFORM(new Position(2614, 3443, 0), true),
    BRIMHAVEN(new Position(2766, 3166, 0), true),

    //Lure/Bait
    BARBARIAN_VILLAGE(new Position(3104, 3428, 0), false),
    LUMBRIDGE_RIVER(new Position(3240, 3246, 0), false),
    SHILO_VILLAGE(new Position(2854, 2979, 0), true),
    SEERS_VILLAGE(new Position(2722, 3530, 0), true),
    TREE_GNOME_STRONGHOLD(new Position(2393, 3422, 0), true),
    OBSERVATORY(new Position(2460, 3154, 0), true),
    BAXTORIAN_FALLS(new Position(2535, 3411, 0), true),
    HOSIDIUS_NORTH_1(new Position(1646, 3560, 0), true),
    HOSIDIUS_NORTH_2(new Position(1585, 3569, 0), true),

    //Cage/Harpoon
    KARAMJA(new Position(2809, 3013, 0), true),
    CORSAIR_COVE_RESOURCE_AREA(new Position(2457, 2892, 0), false),
    WILDERNESS_RESOURCE_AREA(new Position(3184, 3925, 0), false),
    WILDERNESS_EAST_1(new Position(3345, 3812, 0), false),
    WILDERNESS_EAST_2(new Position(3363, 3803, 0), false),

    //Cage/Harpoon & Net (small)/Bait
    MUSA_POINT(new Position(2924, 3179, 0), false),

    //Net (big)/Harpoon
    BURGH_DE_ROTT(new Position(3497, 3177, 0), true),
    APE_ATOLL(new Position(2775, 2741, 0), true),
    HOSIDIUS_SOUTH(new Position(1675, 3478, 0), true),

    //Net (big)/Harpoon & Lure/Bait
    ISAFDAR(new Position(2216, 3243, 0), true),

    //Net (big)/Harpoon & Cage/Harpoon
    FISHING_GUILD_1(new Position(2600, 3421, 0), true),
    FISHING_GUILD_2(new Position(2604, 3413, 0), true),
    JATIZSO(new Position(2408, 3781, 0), true),

    //Net (big)/Harpoon Net & Cage/Harpoon & (small)/Bait
    CATHERBY_1(new Position(2842, 3433, 0), true),
    CATHERBY_2(new Position(2856, 3427, 0), true),

    //Harpoon/Net (small)
    PISCATORIS(new Position(2348, 3780, 0), true),

    //Karambwan vessel
    SHIP_YARD(new Position(2905, 3115, 0), true),

    //Trawler
    TRAWLER(new Position(2723, 3275, 0), true);


    private final Position position;

    private final boolean members;

    FishLocation(Position position, boolean members) {
        this.position = position;
        this.members = members;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isMembers() {
        return members;
    }
}
