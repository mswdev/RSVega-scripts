package org.api.game.skills.fishing;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

import java.util.Arrays;

public class FishingUtil {

    /**
     * Gets the most appropriate progressive fish the player can fish.
     *
     * @return The most appropriate progressive fish.
     */
    public static FishType getAppropriateFish() {
        return Arrays.stream(FishType.values())
                .filter(fish_type -> Skills.getLevel(Skill.FISHING) >= fish_type.getRequiredFishingLevel() && fish_type.isProgressive())
                .reduce((first, second) -> second)
                .orElse(FishType.SHRIMP);
    }
}

