package sphiinx.script.public_script.spx_aio_walking.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.impl.WalkToLocation;

public class WalkingWorkerHandler extends WorkerHandler<WalkingMission> {

    private final WalkingWorker WALK_TO_LOCATION;

    public WalkingWorkerHandler(WalkingMission mission) {
        super(mission);
        WALK_TO_LOCATION = new WalkToLocation(mission);
    }

    @Override
    public Worker<WalkingMission> decide() {
        return WALK_TO_LOCATION;
    }
}

