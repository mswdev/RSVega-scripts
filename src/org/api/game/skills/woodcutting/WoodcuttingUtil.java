package org.api.game.skills.woodcutting;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

import java.util.Arrays;

public class WoodcuttingUtil {

    /**
     * Gets the most appropriate axe the player has in their equipment, inventory, or bank.
     *
     * @return The most appropriate axe.
     */
    public static AxeType getAppropriateOwnedAxe() {
        return Arrays.stream(AxeType.values())
                .filter(axe_type -> Skills.getLevel(Skill.WOODCUTTING) >= axe_type.getRequiredWoodcuttingLevel() && Equipment.contains(axe_type.getItemID()) || Inventory.contains(axe_type.getItemID()) || Bank.contains(axe_type.getItemID()))
                .reduce((first, second) -> second)
                .orElse(AxeType.IRON);
    }

    /**
     * Gets the most appropriate axe the player can use.
     *
     * @return The most appropriate axe.
     */
    public static AxeType getAppropriateAxe() {
        return Arrays.stream(AxeType.values())
                .filter(axe_type -> Skills.getLevel(Skill.WOODCUTTING) >= axe_type.getRequiredWoodcuttingLevel())
                .reduce((first, second) -> second)
                .orElse(AxeType.IRON);
    }

    /**
     * Gets the most appropriate progressive tree the player can woodcut.
     *
     * @return The most appropriate progressive tree.
     */
    public static TreeType getAppropriateTree() {
        return Arrays.stream(TreeType.values())
                .filter(tree_type -> Skills.getLevel(Skill.WOODCUTTING) >= tree_type.getRequiredWoodcuttingLevel() && tree_type.isProgressive())
                .reduce((first, second) -> second)
                .orElse(TreeType.TREE);
    }
}

