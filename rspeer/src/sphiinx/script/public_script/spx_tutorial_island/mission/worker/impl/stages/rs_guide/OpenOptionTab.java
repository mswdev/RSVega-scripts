package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.rs_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class OpenOptionTab extends TIWorker {


    public OpenOptionTab(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Tabs.open(Tab.OPTIONS))
            Time.sleepUntil(() -> Tabs.isOpen(Tab.OPTIONS), 1500);
    }

    @Override
    public String toString() {
        return "[RS GUIDE]: Opening option tab";
    }
}

