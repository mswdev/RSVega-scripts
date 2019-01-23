package sphiinx.api.script.impl.mission.tutorial_island_mission.worker.impl.at_start;

import org.rspeer.runetek.api.Varps;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;

public class CharacterSetupWorker extends Worker {

    private final CharacterDesignWorker character_design_worker = new CharacterDesignWorker();
    private final CharacterDisplayNameWorker character_display_name_worker = new CharacterDisplayNameWorker();

    @Override
    public boolean needsRepeat() {
        return Varps.get(TutorialIslandMission.TUTORIAL_ISLAND_VARP) == 1;
    }

    @Override
    public void work() {
        if (Varps.getBitValue(CharacterDisplayNameWorker.DISPLAY_NAME_VARPBIT) != 5) {
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

