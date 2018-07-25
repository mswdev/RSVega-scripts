package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.combat_instructor;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class OpenCombatTab extends TI_Worker {


    public OpenCombatTab(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Tabs.open(Tab.COMBAT))
            Time.sleepUntil(() -> Tabs.isOpen(Tab.COMBAT), 1500);
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Opening combat tab";
    }
}

