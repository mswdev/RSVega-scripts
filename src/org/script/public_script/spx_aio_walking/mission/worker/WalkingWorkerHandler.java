package org.script.public_script.spx_aio_walking.mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.worker.MovementWorker;
import org.script.public_script.spx_aio_walking.Main;

public class WalkingWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        return new MovementWorker(Main.ARGS.LOCATION.getPosition());
    }
}

