package org.api.game.skills.mining;

import java.util.Arrays;

public enum PickaxeType {

    BRONZE("Bronze pickaxe", 1265, 1, 1, false),
    IRON("Iron pickaxe", 1267, 1, 1, false),
    STEEL("Steel pickaxe", 1269, 6, 5, false),
    BLACK("Black pickaxe", 12297, 11, 10, false),
    MINING_GLOVES("Mining gloves", 21343, 20, -1, true),
    MITHRIL("Mithril pickaxe", 1273, 21, 20, false),
    ADAMANT("Adamant pickaxe", 1271, 31, 30, false),
    RUNE("Rune pickaxe", 1275, 41, 40, false),
    SUPERIOR_MINING_GLOVES("Superior mining gloves", 21345, 55, -1, true),
    DRAGON("Dragon pickaxe", 11920, 61, 60, true),
    THIRD_AGE("3rd age pickaxe", 20014, 61, 65, true),
    INFERNAL("Infernal pickaxe", 13243, 61, -1, true),
    EXPERT_MINING_GLOVES("Expert mining gloves", 21392, 70, -1, true);

    private final String name;
    private final int item_id;
    private final int required_mining_level;
    private final int required_attack_level;
    private final boolean is_members;

    PickaxeType(String name, int item_id, int required_mining_level, int required_attack_level, boolean is_members) {
        this.name = name;
        this.item_id = item_id;
        this.required_mining_level = required_mining_level;
        this.required_attack_level = required_attack_level;
        this.is_members = is_members;
    }

    /**
     * Gets all of the pickaxe item ids in the enum.
     *
     * @return An array containing all of the pickaxe item ids in the enum.
     */
    public static int[] getItemIDs() {
        return Arrays.stream(PickaxeType.values()).mapToInt(PickaxeType::getItemID).toArray();
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

    public int getRequiredAttackLevel() {
        return required_attack_level;
    }

    public boolean isMembers() {
        return is_members;
    }
}

