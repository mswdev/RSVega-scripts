package sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.fishing;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

public class FishingUtil {

    /**
     * Gets the highest level fish that the player can fish.
     *
     * @return The highest level fish the player can fish.
     */
    public FishType getBestFishableFish(boolean is_members) {
        final FishType[] fish_types = FishType.values();
        for (int i = fish_types.length - 1; i >= 0; i--) {
            if (fish_types[i].getRequiredFishingLevel() <= Skills.getLevel(Skill.FISHING)) {
                if (fish_types[i].isMembers() && !is_members) {
                    continue;
                }
                return fish_types[i];
            }
        }

        return FishType.SHRIMP;
    }
}

