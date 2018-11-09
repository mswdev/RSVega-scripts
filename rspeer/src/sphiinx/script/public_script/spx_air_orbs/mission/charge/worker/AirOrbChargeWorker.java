package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;

public abstract class AirOrbChargeWorker extends Worker<AirOrbChargeMission> {


    public AirOrbChargeWorker(AirOrbChargeMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

