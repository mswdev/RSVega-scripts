package sphiinx.script.public_script.spx_aio_walking.mission.worker;

import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.api.script.impl.worker.MovementWorker;
import sphiinx.script.public_script.spx_aio_walking.Main;

public class WalkingWorkerHandler extends WorkerHandler {

    private final MovementWorker movement_worker = new MovementWorker(Main.ARGS.LOCATION.getPosition());;

    @Override
    public Worker decide() {
        return movement_worker;
    }
}

