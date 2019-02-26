package org.api.game.skills.firemaking;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

import java.util.Arrays;

public class FiremakingUtil {

    /**
     * Gets the most appropriate logs the player has in their inventory, or bank.
     *
     * @return The most appropriate logs.
     */
    public static LogType getAppropriateOwnedLogs() {
        return Arrays.stream(LogType.values())
                .filter(log_type -> Skills.getLevel(Skill.FIREMAKING) >= log_type.getRequiredFiremakingLevel() && (Inventory.contains(log_type.getItemID()) || Bank.contains(log_type.getItemID())))
                .reduce((first, second) -> second)
                .orElse(LogType.LOGS);
    }

    /**
     * Gets the most appropriate logs the player can light.
     *
     * @return The most appropriate logs.
     */
    public static LogType getAppropriateLogs() {
        return Arrays.stream(LogType.values())
                .filter(log_type -> Skills.getLevel(Skill.FIREMAKING) >= log_type.getRequiredFiremakingLevel())
                .reduce((first, second) -> second)
                .orElse(LogType.LOGS);
    }
}

