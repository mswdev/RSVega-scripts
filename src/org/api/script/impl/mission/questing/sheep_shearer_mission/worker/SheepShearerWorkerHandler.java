package org.api.script.impl.mission.questing.sheep_shearer_mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.questing.sheep_shearer_mission.data.SheepShearerState;

public class SheepShearerWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        return SheepShearerState.getValidState().getWorker();
    }
}

