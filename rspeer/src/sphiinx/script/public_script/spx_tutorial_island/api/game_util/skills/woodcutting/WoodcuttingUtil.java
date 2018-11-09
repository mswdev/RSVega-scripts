package sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.woodcutting;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

public class WoodcuttingUtil {

    /**
     * Gets the best usable axe the player has.
     *
     * @param check_bank True to check the bank; false otherwise.
     * @return The best usable axe.
     */
    public static AxeType getBestUsableAxe(boolean check_bank) {
        final AxeType[] axe_types = AxeType.values();
        for (int i = axe_types.length - 1; i >= 0; i--) {
            final AxeType axe_type = axe_types[i];
            if (axe_type.getRequiredWoodcuttingLevel() <= Skills.getLevel(Skill.WOODCUTTING) &&
                    ((Inventory.getCount(axe_type.getItemID()) > 0 || Equipment.getCount(axe_type.getItemID()) > 0) ||
                            (check_bank && Bank.contains(axe_type.getItemID()))))
                return axe_type;
        }

        return null;
    }

    /**
     * Gets the highest level axe the player can use.
     *
     * @return The highest level axe the player can use.
     */
    public static AxeType getAppropriateAxeType() {
        final AxeType[] axe_types = AxeType.values();
        for (int i = axe_types.length - 1; i >= 0; i--) {
            if (axe_types[i].getRequiredWoodcuttingLevel() <= Skills.getLevel(Skill.WOODCUTTING))
                return axe_types[i];
        }

        return AxeType.IRON;
    }
}

