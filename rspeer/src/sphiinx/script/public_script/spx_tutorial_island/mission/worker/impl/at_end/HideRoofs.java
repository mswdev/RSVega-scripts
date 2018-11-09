package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.ClientSettings;
import sphiinx.script.public_script.spx_tutorial_island.mission.TutorialIslandMission;

public class HideRoofs extends Worker<TutorialIslandMission> {


    @Override
    public void work() {
        if (ClientSettings.toggleRoofs())
            Time.sleepUntil(InterfaceOptions.Display::isRoofsHidden, 1500);
    }

    @Override
    public String toString() {
        return "Hiding roofs";
    }
}

