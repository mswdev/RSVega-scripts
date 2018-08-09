package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.survival_expert;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class OpenInventory extends TIWorker {


    public OpenInventory(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Tabs.open(Tab.INVENTORY))
            Time.sleepUntil(() -> Tabs.isOpen(Tab.INVENTORY), 1500);
    }

    @Override
    public String toString() {
        return "[SURVIVAL EXPERT]: Opening inventory";
    }
}

