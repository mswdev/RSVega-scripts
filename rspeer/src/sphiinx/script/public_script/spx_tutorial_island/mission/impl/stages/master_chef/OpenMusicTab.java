package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.master_chef;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class OpenMusicTab extends TI_Worker {


    public OpenMusicTab(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Tabs.open(Tab.MUSIC_PLAYER))
            Time.sleepUntil(() -> Tabs.isOpen(Tab.MUSIC_PLAYER), 1500);
    }

    @Override
    public String toString() {
        return "[MASTER CHEF]: Opening quest tab";
    }
}

