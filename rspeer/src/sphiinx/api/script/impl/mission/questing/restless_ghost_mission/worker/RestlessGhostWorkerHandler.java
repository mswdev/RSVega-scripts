package sphiinx.api.script.impl.mission.questing.restless_ghost_mission.worker;

import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.api.script.impl.mission.questing.restless_ghost_mission.data.RestlessGhostState;

public class RestlessGhostWorkerHandler extends WorkerHandler {


    @Override
    public Worker decide() {
        return RestlessGhostState.getValidState().getWorker();
    }
}

