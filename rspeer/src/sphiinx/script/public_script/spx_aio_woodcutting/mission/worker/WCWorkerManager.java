package sphiinx.script.public_script.spx_aio_woodcutting.mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.WorkerManager;
import sphiinx.script.public_script.spx_aio_woodcutting.mission.WC_Mission;

public class WCWorkerManager extends WorkerManager<WC_Mission> {

    public WCWorkerManager(WC_Mission mission) {
        super(mission);
    }

    @Override
    public Worker<WC_Mission> decide() {
        return null;
    }


}

