package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;

public class WalkToLane extends Worker {

    private final FireMakingMission mission;

    public WalkToLane(FireMakingMission mission) {
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

        if (Movement.walkTo(mission.getCurrentLaneStartPosition()))
            Time.sleepUntil(() -> Players.getLocal().getPosition().equals(mission.getCurrentLaneStartPosition()), 1500);
    }


    @Override
    public String toString() {
        return "Walking to lane.";
    }
}

