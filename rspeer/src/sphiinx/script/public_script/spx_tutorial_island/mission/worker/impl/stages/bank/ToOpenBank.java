package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.bank;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class ToOpenBank extends TIWorker {


    public ToOpenBank(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (Interfaces.isViewingChatOptions()) {
            if (Interfaces.processDialog(0))
                Time.sleepUntil(Interfaces::isDialogProcessing, 1500);
        } else {
            if (mission.interactWithHint(mission.getHintSceneObject()))
                Time.sleepUntil(Interfaces::isDialogOpen, 1500);
        }
    }

    @Override
    public String toString() {
        return "[BANK]: Entering Bank";
    }
}

