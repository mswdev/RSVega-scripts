package org.api.script.impl.mission.questing.restless_ghost_mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.questing.restless_ghost_mission.data.RestlessGhostState;

public class RestlessGhostWorkerHandler extends WorkerHandler {


    @Override
    public Worker decide() {
        return RestlessGhostState.getValidState().getWorker();
    }
}

