package org.script.paid_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.script.paid_script.spx_tutorial_island.api.script.framework.worker.Worker;

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
        if (Tabs.open(tab))
            Time.sleepUntil(() -> Tabs.isOpen(tab), 1500);
    }

    @Override
    public String toString() {
        return "Opening game tab.";
    }
}

