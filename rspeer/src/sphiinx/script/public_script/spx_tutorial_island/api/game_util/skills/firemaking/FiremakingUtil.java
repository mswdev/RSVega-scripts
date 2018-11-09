package sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.firemaking;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

public class FiremakingUtil {

    /**
     * Gets the best usable log.
     *
     * @param check_inventory True to check the inventory; false otherwise.
     * @param check_bank      True to check the bank; false otherwise.
     * @return The best usable log.
     */
    public static LogType getBestUsableLog(boolean check_inventory, boolean check_bank) {
        final LogType[] log_types = LogType.values();
        for (int i = log_types.length - 1; i >= 0; i--) {
            final LogType log_type = log_types[i];
            if (Skills.getLevel(Skill.FIREMAKING) < log_type.getRequiredFiremakingLevel())
                continue;

            if (check_inventory && !Inventory.contains(log_type.getName()))
                continue;

            if (check_bank && !Bank.contains(log_type.getName()))
                continue;

            return log_type;
        }

        return LogType.LOGS;
    }
}

