package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.quest_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class OpenQuestTab extends TI_Worker {


    public OpenQuestTab(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Tabs.open(Tab.QUEST_LIST))
            Time.sleepUntil(() -> Tabs.isOpen(Tab.QUEST_LIST), 1500);
    }

    @Override
    public String toString() {
        return "[QUEST GUIDE]: Opening quest tab";
    }
}

