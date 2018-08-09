package sphiinx.script.public_script.spx_aio_walking.mission.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.WalkingWorker;

public class WalkToLocation extends WalkingWorker {


    public WalkToLocation(WalkingMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Movement.walkTo(mission.WALK_POSITION))
            Time.sleepUntil(() -> mission.WALK_POSITION.distance() <= 5, 1500);
    }

    @Override
    public String toString() {
        return "Walking to location";
    }
}

