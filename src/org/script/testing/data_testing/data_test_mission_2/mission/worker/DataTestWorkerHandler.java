package org.script.testing.data_testing.data_test_mission_2.mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.ui.Log;

public class DataTestWorkerHandler extends WorkerHandler {

    private StopWatch stopWatch;

    @Override
    public Worker decide() {
        if (stopWatch == null)
            stopWatch = StopWatch.start();

        Log.fine("[Seconds]: " + stopWatch.getElapsed().getSeconds());
        return null;
    }
}
