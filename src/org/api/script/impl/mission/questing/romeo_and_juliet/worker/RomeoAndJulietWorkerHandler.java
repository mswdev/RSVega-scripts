package org.api.script.impl.mission.questing.romeo_and_juliet.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.questing.romeo_and_juliet.data.RomeoAndJulietState;

public class RomeoAndJulietWorkerHandler extends WorkerHandler {


    @Override
    public Worker decide() {
        return RomeoAndJulietState.getValidState().getWorker();
    }
}

