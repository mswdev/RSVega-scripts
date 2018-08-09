package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.quest_guide;

import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class ClickRun extends TIWorker {


    public ClickRun(TIMission mission) {
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

