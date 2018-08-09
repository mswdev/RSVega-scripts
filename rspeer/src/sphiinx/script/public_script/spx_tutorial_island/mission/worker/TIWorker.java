package sphiinx.script.public_script.spx_tutorial_island.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;

public abstract class TIWorker extends Worker<TIMission> {

    public TIWorker(TIMission mission) {
        super(mission);
    }

    public abstract boolean shouldExecute();

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

