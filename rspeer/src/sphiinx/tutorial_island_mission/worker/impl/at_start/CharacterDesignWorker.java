package sphiinx.api.script.impl.mission.tutorial_island_mission.worker.impl.at_start;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.tutorial_island_mission.data.DesignOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CharacterDesignWorker extends Worker {

    private static final int INTER_MASTER = 269;
    private static final int INTER_ACCEPT_BUTTON = 99;

    private boolean shouldExecute() {
        final InterfaceComponent CHARACTER_DESIGN = Interfaces.getComponent(INTER_MASTER, INTER_ACCEPT_BUTTON);
        return CHARACTER_DESIGN != null && CHARACTER_DESIGN.isVisible();
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final List<DesignOption> design_options = new ArrayList<>(Arrays.asList(DesignOption.values()));
        Collections.shuffle(design_options);
        design_options.remove(DesignOption.GENDER);
        design_options.add(0, DesignOption.GENDER);

        for (DesignOption option : design_options) {
            final InterfaceComponent left_option = Interfaces.getComponent(INTER_MASTER, option.getLeft());
            final InterfaceComponent right_option = Interfaces.getComponent(INTER_MASTER, option.getRight());
            if (left_option == null || right_option == null)
                continue;

            int left_clicks = Random.nextInt(0, 6);
            int right_clicks = Random.nextInt(0, 6);
            while (left_clicks > 0 || right_clicks > 0) {
                final boolean LEFT_CLICK = Random.nextInt(0, 1) == 0;
                if (LEFT_CLICK && left_clicks-- >= 0) {
                    left_option.interact(a -> true);
                } else if (right_clicks-- >= 0) {
                    right_option.interact(a -> true);
                }

                Time.sleep(100, 200);
            }
        }

        final InterfaceComponent ACCEPT_BUTTON = Interfaces.getComponent(INTER_MASTER, INTER_ACCEPT_BUTTON);
        if (ACCEPT_BUTTON == null)
            return;

        if (ACCEPT_BUTTON.interact(a -> true))
            Time.sleepUntil(() -> !shouldExecute(), 1500);
    }

    @Override
    public String toString() {
        return "Executing character design worker.";
    }
}

