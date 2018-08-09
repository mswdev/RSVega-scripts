package sphiinx.script.private_script.puppy_air_orbs.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.script.private_script.puppy_air_orbs.mission.AirOrbsMission;

public abstract class AirOrbWorker extends Worker<AirOrbsMission> {


    public AirOrbWorker(AirOrbsMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

