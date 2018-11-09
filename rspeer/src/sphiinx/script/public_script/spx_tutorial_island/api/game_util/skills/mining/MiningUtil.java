package sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.mining;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

public class MiningUtil {

    /**
     * Gets the best usable pickaxe the player has.
     *
     * @param check_bank True to check the bank; false otherwise.
     * @return The best usable pickaxe.
     */
    public static PickaxeType getBestUsablePickaxe(boolean check_bank) {
        final PickaxeType[] pickaxe_types = PickaxeType.values();
        for (int i = pickaxe_types.length - 1; i >= 0; i--) {
            final PickaxeType pickaxe_type = pickaxe_types[i];
            if (pickaxe_type.getRequiredMiningLevel() <= Skills.getLevel(Skill.MINING) &&
                    ((Inventory.getCount(pickaxe_type.getItemID()) > 0 || Equipment.getCount(pickaxe_type.getItemID()) > 0) ||
                            (check_bank && Bank.contains(pickaxe_type.getItemID()))))
                return pickaxe_type;
        }

        return null;
    }

    /**
     * Gets the highest level pickaxe the player can use.
     *
     * @return The highest level pickaxe the player can use.
     */
    public static PickaxeType getAppropriatePickaxeType() {
        final PickaxeType[] pickaxe_types = PickaxeType.values();
        for (int i = pickaxe_types.length - 1; i >= 0; i--) {
            if (pickaxe_types[i].getRequiredMiningLevel() <= Skills.getLevel(Skill.MINING))
                return pickaxe_types[i];
        }

        return PickaxeType.IRON;
    }
}

