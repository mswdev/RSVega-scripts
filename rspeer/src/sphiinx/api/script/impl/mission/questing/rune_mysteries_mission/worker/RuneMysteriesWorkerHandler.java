package sphiinx.api.script.impl.mission.questing.rune_mysteries_mission.worker;

import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.api.script.impl.mission.questing.rune_mysteries_mission.data.RuneMysteriesState;

public class RuneMysteriesWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        return RuneMysteriesState.getValidState().getWorker();
    }
}

