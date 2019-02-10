package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.questing.sheep_shearer_mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.questing.sheep_shearer_mission.data.SheepShearerState;

public class SheepShearerWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        return SheepShearerState.getValidState().getWorker();
    }
}

