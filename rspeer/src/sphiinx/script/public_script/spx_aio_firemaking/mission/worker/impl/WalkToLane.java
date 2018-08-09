package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FireMakingWorker;

public class WalkToLane extends FireMakingWorker {

    public WalkToLane(FireMakingMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Movement.walkTo(mission.current_lane_start_position))
            Time.sleepUntil(() -> Players.getLocal().getPosition().equals(mission.current_lane_start_position), 1500);
    }


    @Override
    public String toString() {
        return "Walking to lane";
    }
}

