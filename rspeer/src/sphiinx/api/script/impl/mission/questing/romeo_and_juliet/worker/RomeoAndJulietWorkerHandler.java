package sphiinx.api.script.impl.mission.questing.romeo_and_juliet.worker;

import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.api.script.impl.mission.questing.romeo_and_juliet.data.RomeoAndJulietState;

public class RomeoAndJulietWorkerHandler extends WorkerHandler {


    @Override
    public Worker decide() {
        return RomeoAndJulietState.getValidState().getWorker();
    }
}

