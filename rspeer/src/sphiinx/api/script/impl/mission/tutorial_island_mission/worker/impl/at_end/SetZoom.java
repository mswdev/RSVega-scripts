package sphiinx.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.game.ClientSettings;
import sphiinx.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import sphiinx.script.public_script.spx_tutorial_island.Main;

public class SetZoom extends Worker {

    private final TutorialIslandMission mission;

    public SetZoom(TutorialIslandMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!Tabs.isOpen(Tab.OPTIONS))
            if (Tabs.open(Tab.OPTIONS))
                Time.sleepUntil(() -> Tabs.isOpen(Tab.OPTIONS), 1500);

        if (ClientSettings.setZoomLevel(mission.getArgs().set_zoom))
            Time.sleepUntil(() -> ClientSettings.getZoomLevel() == mission.getArgs().set_zoom, 1500);
    }

    @Override
    public String toString() {
        return "Setting zoom.";
    }
}

