package sphiinx.script.public_script.spx_aio_walking.mission.worker.impl;


import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkMission;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.W_Worker;

public class WalkToLocation extends W_Worker {

    /**
     * Primary constructor for the Worker class. We supply it with a mission, so that it can access any data it needs
     *
     * @param mission the mission that is driving this worker
     */
    public WalkToLocation(WalkMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        Movement.walkTo(mission.WALK_POSITION);
    }


}

