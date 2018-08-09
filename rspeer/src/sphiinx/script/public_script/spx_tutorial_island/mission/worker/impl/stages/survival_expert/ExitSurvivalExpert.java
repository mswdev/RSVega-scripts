package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.survival_expert;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class ExitSurvivalExpert extends TIWorker {


    public ExitSurvivalExpert(TIMission mission) {
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

        if (mission.interactWithHint(mission.getHintSceneObject()))
            Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
    }

    @Override
    public String toString() {
        return "[SURVIVAL EXPERT]: Exiting Survival Expert";
    }
}

