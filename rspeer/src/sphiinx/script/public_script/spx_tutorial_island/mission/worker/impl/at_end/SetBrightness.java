package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.ClientSettings;
import sphiinx.script.public_script.spx_tutorial_island.Main;
import sphiinx.script.public_script.spx_tutorial_island.mission.TutorialIslandMission;

public class SetBrightness extends Worker<TutorialIslandMission> {

    @Override
    public void work() {
        if (!Tabs.isOpen(Tab.OPTIONS))
            if (Tabs.open(Tab.OPTIONS))
                Time.sleepUntil(() -> Tabs.isOpen(Tab.OPTIONS), 1500);

        if (ClientSettings.setBrightnessLevel(Main.ARGS.set_brightness))
            Time.sleepUntil(() -> InterfaceOptions.Display.getBrightness() == Main.ARGS.set_brightness, 1500);
    }

    @Override
    public String toString() {
        return "Setting brightness";
    }
}

