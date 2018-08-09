package sphiinx.script.private_script.puppy_air_orbs.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerHandler;
import sphiinx.script.private_script.puppy_air_orbs.mission.AirOrbsMission;

public class AirOrbWorkerHandler extends WorkerHandler<AirOrbsMission> {


    public AirOrbWorkerHandler(AirOrbsMission mission) {
        super(mission);
    }

    @Override
    public Worker<AirOrbsMission> decide() {
        return null;
    }
}

