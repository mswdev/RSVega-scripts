package org.api.game.skills.woodcutting;

import org.api.game.skills.mining.PickaxeType;

import java.util.Arrays;

public enum AxeType {

    BRONZE("Bronze axe", 1351, 1, 1, false),
    IRON("Iron axe", 1349, 1, 1, false),
    STEEL("Steel axe", 1353, 6, 5, false),
    BLACK("Black axe", 1361, 11, 10, false),
    MITHRIL("Mithril axe", 1355, 21, 20, false),
    ADAMANT("Adamant axe", 1357, 31, 30, false),
    RUNE("Rune axe", 1359, 41, 40, false),
    DRAGON("Dragon axe", 6739, 61, 60, true),
    THIRD_AGE("3rd age axe", 20011, 61, 65, true),
    INFERNAL("Infernal axe", 13241, 61, -1, true);

    private final String name;
    private final int item_id;
    private final int required_woodcutting_level;
    private final int required_attack_level;
    private final boolean members;

    AxeType(String name, int item_id, int required_woodcutting_level, int required_attack_level, boolean members) {
        this.name = name;
        this.item_id = item_id;
        this.required_woodcutting_level = required_woodcutting_level;
        this.required_attack_level = required_attack_level;
        this.members = members;
    }

    /**
     * Gets all of the axe item ids in the enum.
     *
     * @return An array containing all of the axe item ids in the enum.
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

    public int getRequiredWoodcuttingLevel() {
        return required_woodcutting_level;
    }

    public int getRequiredAttackLevel() {
        return required_attack_level;
    }

    public boolean isMembers() {
        return members;
    }
}

