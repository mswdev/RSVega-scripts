package sphiinx.api.script.impl.mission.questing.sheep_shearer_mission.worker;

import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.api.script.impl.mission.questing.sheep_shearer_mission.data.SheepShearerState;

public class SheepShearerWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        return SheepShearerState.getValidState().getWorker();
    }
}

