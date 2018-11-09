package sphiinx.script.public_script.spx_aio_walking.mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;

public abstract class WalkingWorker extends Worker<WalkingMission> {

    public WalkingWorker(WalkingMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

