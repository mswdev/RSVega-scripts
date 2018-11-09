package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.AirOrbRestockMission;

public abstract class AirOrbRestockWorker extends Worker<AirOrbRestockMission> {


    public AirOrbRestockWorker(AirOrbRestockMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

