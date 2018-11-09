package sphiinx.script.private_script.wyd_sand_crabs.tasks;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;
import sphiinx.script.private_script.wyd_sand_crabs.data.Vars;

public class FixAutoRetaliate extends Task {

    private final int INTER_MASTER = 593;
    private final int INTER_RETALIATE_BUTTON = 27;

    @Override
    public int execute() {
        if (Tabs.isOpen(Tab.COMBAT)) {
            final InterfaceComponent AUTO_RETALIATE = Interfaces.getComponent(INTER_MASTER, INTER_RETALIATE_BUTTON);
            if (AUTO_RETALIATE == null)
                return 100;

            if (AUTO_RETALIATE.interact(a -> true))
                Time.sleepUntil(() -> Varps.get(172) == 1, 1500);

            if (AUTO_RETALIATE.interact(a -> true))
                Time.sleepUntil(() -> Varps.get(172) == 0, 1500);

            Vars.get().needs_auto_retaliate_fix = false;
        } else {
            if (Tabs.open(Tab.COMBAT))
                Time.sleepUntil(() -> Tabs.isOpen(Tab.COMBAT), 1500);
        }


        return 100;
    }

    @Override
    public boolean validate() {
        return Vars.get().needs_auto_retaliate_fix;
    }
}

