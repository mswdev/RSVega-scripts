package sphiinx.script.public_script.spx_aio_firemaking.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FM_Mission;

public abstract class FM_Worker extends Worker<FM_Mission> {


    public FM_Worker(FM_Mission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

