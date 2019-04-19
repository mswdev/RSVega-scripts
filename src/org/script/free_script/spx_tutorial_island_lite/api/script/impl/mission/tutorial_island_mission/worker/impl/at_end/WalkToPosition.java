package org.script.free_script.spx_tutorial_island_lite.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.script.free_script.spx_tutorial_island_lite.api.script.framework.worker.Worker;
import org.script.free_script.spx_tutorial_island_lite.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;

public class WalkToPosition extends Worker {

    private final TutorialIslandMission mission;

    public WalkToPosition(TutorialIslandMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (Movement.walkTo(mission.getArgs().walk_position))
            Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
    }

    @Override
    public String toString() {
        return "Walking to position.";
    }
}

