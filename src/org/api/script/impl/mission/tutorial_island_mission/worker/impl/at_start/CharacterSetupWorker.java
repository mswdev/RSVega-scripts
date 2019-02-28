package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_start;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.rspeer.runetek.api.Varps;

public class CharacterSetupWorker extends Worker {

    private final CharacterDesignWorker character_design_worker;
    private final CharacterDisplayNameWorker character_display_name_worker;

    public CharacterSetupWorker(TutorialIslandMission mission) {
        this.character_design_worker = new CharacterDesignWorker();
        this.character_display_name_worker = new CharacterDisplayNameWorker(mission);
    }

    @Override
    public boolean needsRepeat() {
        return Varps.get(TutorialIslandMission.TUTORIAL_ISLAND_VARP) == 1;
    }

    @Override
    public void work() {
        if (Varps.getBitValue(CharacterDisplayNameWorker.DISPLAY_NAME_VARPBIT) != 0 && Varps.getBitValue(CharacterDisplayNameWorker.DISPLAY_NAME_VARPBIT) != 5) {
            character_display_name_worker.work();
            return;
        }

        character_design_worker.work();
    }

    @Override
    public String toString() {
        return "Executing character setup worker.";
    }
}

