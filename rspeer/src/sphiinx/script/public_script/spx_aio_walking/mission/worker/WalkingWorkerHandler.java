package sphiinx.script.public_script.spx_aio_walking.mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.impl.WalkToLocation;

public class WalkingWorkerHandler extends WorkerHandler<WalkingMission> {

    private final WalkingWorker walk_to_location;

    public WalkingWorkerHandler(WalkingMission mission) {
        super(mission);
        walk_to_location = new WalkToLocation(mission);
    }

    @Override
    public Worker<WalkingMission> decide() {
        return walk_to_location;
    }
}

