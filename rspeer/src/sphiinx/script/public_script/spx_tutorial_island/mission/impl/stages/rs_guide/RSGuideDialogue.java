package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.rs_guide;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class RSGuideDialogue extends TI_Worker {

    private final int INTER_MASTER = 269;
    private final int INTER_ACCEPT_BUTTON = 99;

    public RSGuideDialogue(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        final InterfaceComponent CHARACTER_DESIGN = Interfaces.getComponent(INTER_MASTER, INTER_ACCEPT_BUTTON);
        return CHARACTER_DESIGN == null;
    }

    @Override
    public void work() {
        if (Interfaces.isViewingChatOptions()) {
            if (Interfaces.processDialog(Random.nextInt(0, 3)))
                Time.sleepUntil(Interfaces::isDialogProcessing, 1500);
        } else {
            if (mission.interactWithHint())
                Time.sleepUntil(Interfaces::isDialogOpen, 1500);
        }
    }

    @Override
    public String toString() {
        return "[RS GUIDE]: Completing dialogue";
    }
}

