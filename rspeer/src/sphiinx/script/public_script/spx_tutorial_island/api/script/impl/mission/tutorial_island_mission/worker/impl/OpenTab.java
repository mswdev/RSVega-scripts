package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.worker.impl;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;

public class OpenTab extends Worker {

    private final Tab tab;

    public OpenTab(Tab tab) {
        this.tab = tab;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        // [TODO - 2018-11-01]: Temporary until the client is forced fixed mode.
        if (Game.getClientPreferences().getResizable() == 2 && tab == Tab.OPTIONS) {
            if (Interfaces.getComponent(164, 38).click())
                Time.sleepUntil(() -> Game.getClientPreferences().getResizable() == 1, 2500);
        } else if (Tabs.open(tab))
            Time.sleepUntil(() -> Tabs.isOpen(tab), 1500);
    }

    @Override
    public String toString() {
        return "Opening game tab.";
    }
}

