package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stage.account_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.mission.TutorialIslandMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.HintWorker;

public class PollBooth extends Worker<TutorialIslandMission> {

    private static final HintWorker HINT_WORKER = new HintWorker();

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
        return "Executing chat dialog worker";
    }
}

