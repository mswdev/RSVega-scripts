package sphiinx.script.public_script.spx_aio_woodcutting.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerManager;
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

