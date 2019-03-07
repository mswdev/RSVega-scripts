package org.script.testing.data_testing.data_test_mission_2.mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.ui.Log;

public class DataTestWorkerHandler extends WorkerHandler {

    private StopWatch stop_watch;

    @Override
    public Worker decide() {
        if (stop_watch == null)
            stop_watch = StopWatch.start();

        Log.fine("[Seconds]: " + stop_watch.getElapsed().getSeconds());
        return null;
    }
}
