package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.quest_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class OpenEmoteTab extends TI_Worker {


    public OpenEmoteTab(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Tabs.open(Tab.EMOTES))
            Time.sleepUntil(() -> Tabs.isOpen(Tab.EMOTES), 1500);
    }

    @Override
    public String toString() {
        return "[QUEST GUIDE]: Opening emote tab";
    }
}

