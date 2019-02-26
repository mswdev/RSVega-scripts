package org.api.game.skills.magic;

import org.api.game.skills.mining.PickaxeType;

import java.util.Arrays;

public enum RuneType {

    AIR("Air rune", 556, 1, false),
    MIND("Mind rune", 558, 2, false),
    WATER("Water rune", 555, 5, false),
    MIST("Mist rune", 4695, 6, true),
    EARTH("Earth rune", 557, 9, false),
    DUST("Dust rune", 4696, 10, true),
    MUD("Mud rune", 4698, 13, true),
    FIRE("Fire rune", 554, 14, false),
    SMOKE("Smoke rune", 4697, 15, true),
    STEAM("Steam rune", 4694, 19, true),
    BODY("Body rune", 559, 20, false),
    LAVA("Lava rune", 4699, 23, true),
    COSMIC("Cosmic rune", 564, 27, true),
    CHAOS("Chaos rune", 562, 35, true),
    ASTRAL("Astral rune", 9075, 40, true),
    NATURE("Nature rune", 561, 44, true),
    LAW("Law rune", 563, 54, true),
    DEATH("Death rune", 560, 65, true),
    BLOOD("Blood rune", 565, 77, true),
    SOUL("Soul rune", 566, 90, true),
    WRATH("Wrath rune", 21880, 95, true);

    private final String name;
    private final int item_id;
    private final int required_runecrafting_level;
    private final boolean is_members;

    RuneType(String name, int item_id, int required_runecrafting_level, boolean is_members) {
        this.name = name;
        this.item_id = item_id;
        this.required_runecrafting_level = required_runecrafting_level;
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

    public int getRequiredRunecraftingLevel() {
        return required_runecrafting_level;
    }

    public boolean isMembers() {
        return is_members;
    }
}
