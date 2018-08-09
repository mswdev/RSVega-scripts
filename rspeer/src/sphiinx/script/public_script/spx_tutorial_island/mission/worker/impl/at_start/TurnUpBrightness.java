package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_start;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.Interfaces;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class TurnUpBrightness extends TIWorker {

    private final int INTER_OPTION_MASTER = 261;
    private final int INTER_BRIGHTNESS = 20;

    public TurnUpBrightness(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_start_turn_up_brightness)
            return false;

        return InterfaceOptions.Display.getBrightness() != 4;
    }

    @Override
    public void work() {
        final InterfaceComponent BRIGHTNESS = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_BRIGHTNESS);
        if (BRIGHTNESS == null)
            return;

        if (BRIGHTNESS.interact(mission.DEFAULT_ACTION))
            Time.sleepUntil(() -> InterfaceOptions.Display.getBrightness() == 4, 1500);
    }

    @Override
    public String toString() {
        return "[START]: Turning up brightness";
    }
}

