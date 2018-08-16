package sphiinx.script.public_script.spx_aio_walking.mission.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_aio_walking.data.Vars;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.WalkingWorker;

public class WalkToLocation extends WalkingWorker {


    public WalkToLocation(WalkingMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Vars.get().location == null)
            return;

        if (Vars.get().location.getPosition().distance() <= 5)
            return;

        if (Players.getLocal().isMoving())
            return;

        if (Movement.walkTo(Vars.get().location.getPosition()))
            Time.sleepUntil(() -> Vars.get().location.getPosition().distance() <= 5, 1500);
    }

    @Override
    public String toString() {
        return "Walking to " + Vars.get().location;
    }
}

