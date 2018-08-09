package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.quest_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class OpenQuestTab extends TIWorker {


    public OpenQuestTab(TIMission mission) {
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

