package sphiinx.script.public_script.spx_aio_woodcutting.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_woodcutting.mission.WCMission;

public class WCWorker extends Worker<WCMission> {

    public WCWorker(WCMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {

    }
}

