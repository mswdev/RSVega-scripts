package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.questing.rune_mysteries_mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.questing.rune_mysteries_mission.data.RuneMysteriesState;

public class RuneMysteriesWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        return RuneMysteriesState.getValidState().getWorker();
    }
}

