package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.rs_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class ExitRSGuide extends TIWorker {


    public ExitRSGuide(TIMission mission) {
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

        if (mission.interactWithHint(mission.getHintNPC()))
            Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);

        if (!Movement.isRunEnabled())
            if (Movement.toggleRun(true))
                Time.sleepUntil(Movement::isRunEnabled, 1500);
    }

    @Override
    public String toString() {
        return "[RS GUIDE]: Exiting RS Guide";
    }
}

