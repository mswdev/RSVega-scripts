package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.questing.restless_ghost_mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.questing.restless_ghost_mission.data.RestlessGhostState;

public class RestlessGhostWorkerHandler extends WorkerHandler {


    @Override
    public Worker decide() {
        return RestlessGhostState.getValidState().getWorker();
    }
}

