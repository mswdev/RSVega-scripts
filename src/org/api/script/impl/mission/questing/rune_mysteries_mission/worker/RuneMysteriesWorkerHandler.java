package org.api.script.impl.mission.questing.rune_mysteries_mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.questing.rune_mysteries_mission.data.RuneMysteriesState;

public class RuneMysteriesWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        return RuneMysteriesState.getValidState().getWorker();
    }
}

