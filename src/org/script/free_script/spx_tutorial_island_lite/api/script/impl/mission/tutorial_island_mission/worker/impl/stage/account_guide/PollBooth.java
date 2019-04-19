package org.script.free_script.spx_tutorial_island_lite.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.account_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.script.free_script.spx_tutorial_island_lite.api.script.framework.worker.Worker;
import org.script.free_script.spx_tutorial_island_lite.api.script.impl.mission.tutorial_island_mission.worker.impl.HintWorker;

public class PollBooth extends Worker {

    private static final HintWorker HINT_WORKER = new HintWorker();

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!Dialog.isOpen()) {
            HINT_WORKER.work();
        } else {
            if (Dialog.processContinue())
                Time.sleepUntil(Dialog::isProcessing, 1500);
        }
    }

    @Override
    public String toString() {
        return "Executing chat dialog worker.";
    }
}

