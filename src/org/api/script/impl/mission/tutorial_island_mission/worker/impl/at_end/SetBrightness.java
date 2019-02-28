package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.api.game.ClientSettings;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;

public class SetBrightness extends Worker {

    private final TutorialIslandMission mission;

    public SetBrightness(TutorialIslandMission mission) {
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

        if (ClientSettings.setBrightnessLevel(mission.getArgs().set_brightness))
            Time.sleepUntil(() -> InterfaceOptions.Display.getBrightness() == mission.getArgs().set_brightness, 1500);
    }

    @Override
    public String toString() {
        return "Setting brightness.";
    }
}

