package org.api.game.skills.mining;

public enum OreType {

    RUNE_ESSENCE("Rune essence", 1436, 1, -1, false, false),
    CLAY("Clay", 434, 1, 6705, false, true),
    COPPER("Copper ore", 436, 1, 4645, false, false),
    TIN("Tin ore", 438, 1, 53, false, false),
    BLURITE("Blurite ore", 668, 10, -1, false, false),
    LIMESTONE("Limestone", 3211, 10, 10295, true, false),
    IRON("Iron ore", 440, 15, 2576, false, true),
    SILVER("Silver ore", 442, 20, 74, false, false),
    VOLCANIC_ASH("Volcanic ash", 21622, 22, -1, true, false),
    COAL("Coal", 453, 30, 10508, false, false),
    PURE_ESSENCE("Pure essence", 7936, 30, -1, true, true),
    MOTHERLODE_LOWER("Pay-dirt", 12011, 30, -1, true, true),
    SANDSTONE_1KG("Sanstone (1kg)", 6971, 35, -1, true, false),
    SANDSTONE_2KG("Sanstone (2kg)", 6973, 35, -1, true, false),
    SANDSTONE_5KG("Sanstone (5kg)", 6975, 35, -1, true, false),
    SANDSTONE_10KG("Sanstone (10kg)", 6977, 35, -1, true, false),
    DENSE_ESSENCE("Dense essence block", 13445, 38, -1, true, false),
    GOLD("Gold ore", 444, 40, 8885, false, false),
    UNCUT_OPAL("Uncut opal", 1609, 40, -1, true, false),
    UNCUT_JADE("Uncut jade", 1627, 40, -1, true, false),
    UNCUT_RED_TOPAZ("Uncut red topaz", 1629, 40, -1, true, false),
    UNCUT_SAPPHIRE("Uncut sapphire", 1623, 40, -1, true, false),
    UNCUT_EMERALD("Uncut emerald", 1621, 40, -1, true, false),
    UNCUT_RUBY("Uncut ruby", 1619, 40, -1, true, false),
    UNCUT_DIAMOND("Uncut diamond", 1617, 40, -1, true, false),
    VOLCANIC_SULPHUR("Volcanic sulphur", 13571, 42, -1, true, false),
    GRANITE_500G("Granite (500g)", 6979, 45, -1, true, false),
    GRANITE_2KG("Granite (2kg)", 6981, 45, -1, true, false),
    GRANITE_5KG("Granite (5kg)", 6983, 45, -1, true, false),
    MITHRIL("Mithril ore", 447, 55, -22239, false, false),
    LOVAKITE("Lovakite ore", 13356, 65, -1, true, false),
    ADAMANTITE("Adamantite ore", 449, 70, 21662, false, false),
    MOTHERLODE_UPPER("Pay-dirt", 12011, 72, -1, true, true),
    RUNITE("Runite ore", 451, 85, -1, false, false),
    AMETHYST("Amethyst", 21347, 92, -1, true, false);

    private final String name;
    private final int item_id;
    private final int required_mining_level;
    private final int rock_color_id;
    private final boolean members;
    private final boolean progressive;

    OreType(String name, int item_id, int required_mining_level, int rock_color_id, boolean members, boolean progressive) {
        this.name = name;
        this.item_id = item_id;
        this.required_mining_level = required_mining_level;
        this.rock_color_id = rock_color_id;
        this.members = members;
        this.progressive = progressive;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return item_id;
    }

    public int getRequiredMiningLevel() {
        return required_mining_level;
    }

    public int getColorID() {
        return rock_color_id;
    }

    public boolean isMembers() {
        return members;
    }

    public boolean isProgressive() {
        return progressive;
    }
}

