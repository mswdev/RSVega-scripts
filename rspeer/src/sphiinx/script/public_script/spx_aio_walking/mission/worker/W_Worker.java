package sphiinx.script.public_script.spx_aio_walking.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkMission;

public abstract class W_Worker extends Worker<WalkMission> {


    /**
     * Primary constructor for the Worker class. We supply it with a mission, so that it can access any data it needs
     *
     * @param mission the mission that is driving this worker
     */
    public W_Worker(WalkMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }



}

