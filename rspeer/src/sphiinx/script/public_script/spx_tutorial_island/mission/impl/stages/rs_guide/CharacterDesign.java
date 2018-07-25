package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.rs_guide;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import sphiinx.script.public_script.spx_tutorial_island.data.DesignOption;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CharacterDesign extends TI_Worker {

    private final int INTER_MASTER = 269;
    private final int INTER_ACCEPT_BUTTON = 99;

    public CharacterDesign(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        final InterfaceComponent CHARACTER_DESIGN = Interfaces.getComponent(INTER_MASTER, INTER_ACCEPT_BUTTON);
        return CHARACTER_DESIGN != null && CHARACTER_DESIGN.isVisible();
    }

    @Override
    public void work() {
        final List<DesignOption> OPTIONS = Arrays.asList(DesignOption.values());
        Collections.shuffle(OPTIONS);

        for (DesignOption option : OPTIONS) {
            final InterfaceComponent LEFT_OPTION = Interfaces.getComponent(INTER_MASTER, option.getLeft());
            final InterfaceComponent RIGHT_OPTION = Interfaces.getComponent(INTER_MASTER, option.getRight());
            if (LEFT_OPTION == null || RIGHT_OPTION == null)
                continue;

            int left_clicks = Random.nextInt(0, 6);
            int right_clicks = Random.nextInt(0, 6);
            while (left_clicks > 0 || right_clicks > 0) {
                final boolean LEFT_CLICK = Random.nextInt(0, 1) == 0;
                if (LEFT_CLICK && left_clicks-- >= 0) {
                    LEFT_OPTION.interact(mission.DEFAULT_ACTION);
                } else if (right_clicks-- >= 0) {
                    RIGHT_OPTION.interact(mission.DEFAULT_ACTION);
                }

                //TODO Do I actually need this?
                /*if (option == DesignOption.GENDER)
                    break;*/

                Time.sleep(100, 600);
            }
        }

        final InterfaceComponent ACCEPT_BUTTON = Interfaces.getComponent(INTER_MASTER, INTER_ACCEPT_BUTTON);
        if (ACCEPT_BUTTON == null)
            return;

        if (ACCEPT_BUTTON.interact(mission.DEFAULT_ACTION))
            Time.sleepUntil(() -> !shouldExecute(), 1500);
    }

    @Override
    public String toString() {
        return "Creating character design";
    }
}

