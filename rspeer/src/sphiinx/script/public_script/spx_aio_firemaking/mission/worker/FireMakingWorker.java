package sphiinx.script.public_script.spx_aio_firemaking.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;

public abstract class FireMakingWorker extends Worker<FireMakingMission> {


    public FireMakingWorker(FireMakingMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

