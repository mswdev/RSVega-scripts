package sphiinx.script.public_script.spx_aio_walking.mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.worker.MovementWorker;
import sphiinx.script.public_script.spx_aio_walking.Main;

public class WalkingWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        return new MovementWorker(Main.ARGS.LOCATION.getPosition());
    }
}

