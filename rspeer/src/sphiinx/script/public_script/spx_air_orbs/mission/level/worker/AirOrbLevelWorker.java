package sphiinx.script.public_script.spx_air_orbs.mission.level.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;

public abstract class AirOrbLevelWorker extends Worker<AirOrbLevelMission> {


    public AirOrbLevelWorker(AirOrbLevelMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

