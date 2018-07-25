package sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_start;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.Interfaces;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class HideRoofs extends TI_Worker {

    private final int INTER_OPTION_MASTER = 261;
    private final int INTER_ADV_BTN = 24;

    private final int INTER_ADV_MASTER = 60;
    private final int INTER_HIDE_ROOF = 14;



    public HideRoofs(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_start_hide_roofs)
            return false;

        return !InterfaceOptions.Display.isRoofsHidden();
    }

    @Override
    public void work() {
        final InterfaceComponent HIDE_ROOF = Interfaces.getComponent(INTER_ADV_MASTER, INTER_HIDE_ROOF);
        if (HIDE_ROOF == null) {
            final InterfaceComponent ADV_BTN = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_ADV_BTN);
            if (ADV_BTN == null)
                return;

            if (ADV_BTN.interact(mission.DEFAULT_ACTION))
                Time.sleepUntil(() -> Interfaces.getComponent(INTER_ADV_MASTER, INTER_HIDE_ROOF) != null, 1500);
        } else {
            if (HIDE_ROOF.interact(mission.DEFAULT_ACTION))
                Time.sleepUntil(InterfaceOptions.Display::isRoofsHidden, 1500);
        }
    }

    @Override
    public String toString() {
        return "[START]: Hiding roofs";
    }
}

