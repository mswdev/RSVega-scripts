package org.api.game.skills.smithing;

import org.api.game.skills.mining.OreType;

public enum BarType {

    BRONZE("Bronze bar", 2349, 1, false, OreType.TIN, OreType.COPPER, 1, 1),
    BLURITE("Blurite bar", 9467, 8, true, OreType.BLURITE, null, 1, -1),
    IRON("Iron bar", 2351, 15, false, OreType.IRON, null, 1, -1),
    SILVER("Sliver bar", 2355, 20, false, OreType.SILVER, null, 1, -1),
    STEEL("Steel bar", 2353, 30, false, OreType.COAL, OreType.IRON, 2, 1),
    GOLD("Gold bar", 2357, 40, false, OreType.GOLD, null, 1, -1),
    LOVAKITE("Lovakite bar", 13354, 45, true, OreType.COAL, OreType.LOVAKITE, 2, 1),
    MITHRIL("Mithril bar", 2359, 50, false, OreType.COAL, OreType.MITHRIL, 4, 1),
    ADAMANT("Adamantite bar", 2361, 70, false, OreType.COAL, OreType.ADAMANTITE, 6, 1),
    RUNITE("Rune bar", 2363, 85, false, OreType.COAL, OreType.RUNITE, 8, 1);

    private final String name;
    private final int item_id;
    private final int required_smithing_level;
    private final boolean is_members;
    private final OreType ingrediant_one;
    private final OreType ingrediant_two;
    private final int ingrediant_one_amount;
    private final int ingrediant_two_amount;

    BarType(String name, int item_id, int required_smithing_level, boolean is_members, OreType ingrediant_one, OreType ingrediant_two, int ingrediant_one_amount, int ingrediant_two_amount) {
        this.name = name;
        this.item_id = item_id;
        this.required_smithing_level = required_smithing_level;
        this.is_members = is_members;
        this.ingrediant_one = ingrediant_one;
        this.ingrediant_two = ingrediant_two;
        this.ingrediant_one_amount = ingrediant_one_amount;
        this.ingrediant_two_amount = ingrediant_two_amount;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return item_id;
    }

    public int getRequiredSmithingLevel() {
        return required_smithing_level;
    }

    public boolean isMembers() {
        return is_members;
    }

    public OreType getIngrediantOne() {
        return ingrediant_one;
    }

    public OreType getIngrediantTwo() {
        return ingrediant_two;
    }

    public int getIngrediantOneAmount() {
        return ingrediant_one_amount;
    }

    public int getIngrediantTwoAmount() {
        return ingrediant_two_amount;
    }
}
