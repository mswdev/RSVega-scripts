package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.Main;
import sphiinx.script.public_script.spx_tutorial_island.mission.TutorialIslandMission;

public class WalkToPosition extends Worker<TutorialIslandMission> {


    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (Movement.walkTo(Main.ARGS.walk_position))
            Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
    }

    @Override
    public String toString() {
        return "Walking to position";
    }
}

