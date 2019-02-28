package org.api.script.impl.mission.firemaking_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.firemaking_mission.FireMakingMission;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;

public class WalkToLane extends Worker {

    private final FireMakingMission mission;
    private String status;

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

        if (mission.getCurrentLaneStartPosition() == null)
            status = "Waiting for valid lane to become available.";
        else
            status = "Walking to lane.";

        if (Movement.walkTo(mission.getCurrentLaneStartPosition()))
            Time.sleepUntil(() -> Players.getLocal().getPosition().equals(mission.getCurrentLaneStartPosition()), 1500);
    }


    @Override
    public String toString() {
        return status;
    }
}

