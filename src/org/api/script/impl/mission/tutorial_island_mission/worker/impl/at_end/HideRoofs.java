package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.api.game.ClientSettings;
import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;

public class HideRoofs extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (ClientSettings.toggleRoofs())
            Time.sleepUntil(InterfaceOptions.Display::isRoofsHidden, 1500);
    }

    @Override
    public String toString() {
        return "Hiding roofs.";
    }
}

