package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.quest_guide;

import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class ClickRun extends TI_Worker {


    public ClickRun(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        Movement.toggleRun(true);
    }

    @Override
    public String toString() {
        return "[QUEST GUIDE]: Turning on run";
    }
}

