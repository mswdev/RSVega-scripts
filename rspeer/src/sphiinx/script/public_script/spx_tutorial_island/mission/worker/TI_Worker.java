package sphiinx.script.public_script.spx_tutorial_island.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;

public abstract class TI_Worker extends Worker<TI_Mission> {

    public TI_Worker(TI_Mission mission) {
        super(mission);
    }

    public abstract boolean shouldExecute();

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

