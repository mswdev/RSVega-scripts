package sphiinx.script.public_script.spx_aio_woodcutting.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerManager;
import sphiinx.script.public_script.spx_aio_woodcutting.mission.WCMission;

public class WCWorkerManager extends WorkerManager<WCMission> {

    public WCWorkerManager(WCMission mission) {
        super(mission);
    }

    @Override
    public Worker<WCMission> decide() {
        return null;
    }


}

