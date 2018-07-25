package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.rs_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class ExitRSGuide extends TI_Worker {


    public ExitRSGuide(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (mission.interactWithHint())
            Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
    }

    @Override
    public String toString() {
        return "[RS GUIDE]: Exiting RS Guide";
    }
}

