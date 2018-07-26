package sphiinx.script.public_script.spx_aio_woodcutting.mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_woodcutting.mission.WC_Mission;

public class WCWorker extends Worker<WC_Mission> {

    public WCWorker(WC_Mission mission) {
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

